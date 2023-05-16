package com.Expense.ExpenseApp.Entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserModel {

    private String name;

    @NotNull(message="Email Id should not be null")
    @Email(message = "Please Enter a valid Email")
    private String email;

    @NotNull(message="password Should Not be Empty")
    @Size(min=5,message = "password should be of mininmun length 5 characters  ")
    private String password;

    private Long age =0L;
}
