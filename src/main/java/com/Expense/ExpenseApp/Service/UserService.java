package com.Expense.ExpenseApp.Service;

import com.Expense.ExpenseApp.Entity.User;
import com.Expense.ExpenseApp.Entity.UserModel;
import org.springframework.stereotype.Service;


public interface UserService {
    User createUser(UserModel userModel);

    User read();

    User update(User user);

    void delete();

    User getLoggedInUser();
}
