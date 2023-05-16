package com.Expense.ExpenseApp.Security;

import com.Expense.ExpenseApp.Entity.User;
import com.Expense.ExpenseApp.Exceptions.ResourceNotFoundException;
import com.Expense.ExpenseApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User existingUser= userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("The User doesnot Exist"));
        return new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), new ArrayList<>());
    }
}
