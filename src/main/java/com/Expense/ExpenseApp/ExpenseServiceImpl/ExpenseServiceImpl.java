package com.Expense.ExpenseApp.ExpenseServiceImpl;

import com.Expense.ExpenseApp.Entity.Expense;

import com.Expense.ExpenseApp.Exceptions.ResourceNotFoundException;
import com.Expense.ExpenseApp.Repository.ExpenseRepository;
import com.Expense.ExpenseApp.Service.ExpenseService;
import com.Expense.ExpenseApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    UserService userService;

    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        expenseRepository.findByUserId(userService.getLoggedInUser().getId(),page);

        return expenseRepository.findAll(page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense= expenseRepository.findByUserIdAndId(userService.getLoggedInUser().getId(), id);
        if(expense.isPresent()){
           return expense.get();
        }
        throw new ResourceNotFoundException("The Given Resource doesNot Exist : "+id);
    }

    @Override
    public void deleteById(Long id) {
        expenseRepository.deleteById(id);

    }

    @Override
    public Expense SaveExpenseDetails(Expense expense) {
        expense.setUser(userService.getLoggedInUser());
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Long id, Expense expense) {
        Expense existingExpense=getExpenseById(id);
        existingExpense.setName(expense.getName() != null ? expense.getName(): existingExpense.getName());
        existingExpense.setAmount(expense.getAmount() !=null ? expense.getAmount() : existingExpense.getAmount());
        existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
        existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense.setDate(expense.getDate()!= null ? expense.getDate() : existingExpense.getDate());
        return expenseRepository.save(existingExpense);

    }

    @Override
    public List<Expense> getByCategory(String category, Pageable pageable) {

        return expenseRepository.findByUserIdAndCategory(userService.getLoggedInUser().getId(),category,pageable).toList();
    }

    @Override
    public List<Expense> searchByNameContaining(String name, Pageable pageable) {
        return expenseRepository.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(),name,pageable).toList();
    }

    @Override
    public List<Expense> searchByDates(Date startDate, Date endDate, Pageable pageable) {
        if(startDate==null) {
            startDate = new Date(0);
        }
        if(endDate==null){
            endDate=new Date(System.currentTimeMillis());
        }
        return expenseRepository.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(), startDate,endDate,pageable).toList();

    }


}
