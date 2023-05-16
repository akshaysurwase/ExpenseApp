package com.Expense.ExpenseApp.Controller;

import com.Expense.ExpenseApp.Entity.Expense;
import com.Expense.ExpenseApp.Repository.ExpenseRepository;
import com.Expense.ExpenseApp.Service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;
    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable page){
        return expenseService.getAllExpenses(page).stream().toList();

    }

    @GetMapping("/expenses/{id}")
    public Expense getExpesesById(@PathVariable("id") Long id){
        return expenseService.getExpenseById(id);

    }

    @DeleteMapping("/expenses/{id}")
    public String DeleteExpenseById(@PathVariable("id") Long id) {
         Expense expense=getExpesesById(id);
         expenseRepository.delete(expense);
         return id+" : deleted Sucessfully ";

    }

    @PostMapping("/expenses")
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense){
        return expenseService.SaveExpenseDetails(expense);
    }


    @PutMapping("/expenses/{id}")
    public  Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id) {
        return expenseService.updateExpenseDetails(id,expense);
    }

    @GetMapping("/expenses/category")
    public List<Expense> getExpensesByCategory(@RequestParam(value="category") String category, Pageable pageable){
        return expenseService.getByCategory(category, pageable);
    }

    @GetMapping("/expenses/name")
    public List<Expense> searchByNameContaining(@RequestParam(value="name") String name, Pageable pageable){
        return expenseService.searchByNameContaining(name,pageable);
    }

    @GetMapping("expenses/date")
    public List<Expense> searchByDate(@RequestParam(required = false) Date startDate, @RequestParam(required = false) Date endDate, Pageable pageable){
        return expenseService.searchByDates(startDate,endDate,pageable);
    }
}
