package com.Expense.ExpenseApp.Repository;

import com.Expense.ExpenseApp.Entity.Expense;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

        Page<Expense> findByUserIdAndCategory(Long id,String category, Pageable pageable);

        Page<Expense> findByUserIdAndNameContaining(Long id,String name, Pageable pageable);

        Page<Expense> findByUserIdAndDateBetween(Long id,Date StartDate, Date endDate, Pageable pageable);

        Page<Expense> findByUserId(Long id, Pageable pageable);


        Optional<Expense> findByUserIdAndId(Long UserId, Long Id);
}


