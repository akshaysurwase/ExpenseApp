package com.Expense.ExpenseApp.Controller;

import com.Expense.ExpenseApp.Entity.User;
import com.Expense.ExpenseApp.Entity.UserModel;
import com.Expense.ExpenseApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;



    @GetMapping("/profile")
    public ResponseEntity<User> readUser(){
        return new ResponseEntity<User>(userService.read(),HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.update(user), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deactivate")
    public  ResponseEntity<HttpStatus> delete(){
        userService.delete();
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
