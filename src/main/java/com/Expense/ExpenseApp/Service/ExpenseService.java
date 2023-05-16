package com.Expense.ExpenseApp.Service;

import com.Expense.ExpenseApp.Entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface ExpenseService {

    Page<Expense> getAllExpenses(Pageable page);

    public Expense getExpenseById(Long id);

     void deleteById(Long id);

     public Expense SaveExpenseDetails(Expense expense);

     public Expense updateExpenseDetails(Long id,Expense expense);

     List<Expense> getByCategory(String category, Pageable pageable);

    List<Expense> searchByNameContaining(String name, Pageable pageable);

    List<Expense> searchByDates(Date startDate, Date endDate, Pageable pageable);
}
