package com.Expense.ExpenseApp.ExpenseServiceImpl;

import com.Expense.ExpenseApp.Entity.User;
import com.Expense.ExpenseApp.Entity.UserModel;
import com.Expense.ExpenseApp.Exceptions.ItemAlreadyExistsException;
import com.Expense.ExpenseApp.Exceptions.ResourceNotFoundException;
import com.Expense.ExpenseApp.Repository.UserRepository;
import com.Expense.ExpenseApp.Service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

 

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserModel userModel) {
        if(userRepository.existsByEmail(userModel.getEmail())){
            throw new ItemAlreadyExistsException("The User with Email "+userModel.getEmail()+" already Exists");
        }
        User newUser= new User();
        BeanUtils.copyProperties(userModel,newUser);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public User read() throws ResourceNotFoundException {
        Long userId=getLoggedInUser().getId();
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("The user not found with the given Id"));


    }

    @Override
    public User update(User user) {
        User existingUser= read();
        existingUser.setName(user.getName() !=null ? user.getName() : existingUser.getName());
        existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword(user.getPassword()!= null ?  (passwordEncoder.encode(user.getPassword())): (passwordEncoder.encode(existingUser.getPassword())));
        existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void delete() {
       User user= read();
       userRepository.delete(user);
    }

    @Override
    public User getLoggedInUser() {

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        String email=authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("The USer does not Exist"+ email));


    }
}
