package com.Expense.ExpenseApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="expense_name")
    @NotNull(message="Expense name must not be empty")
    @Size(max=10,min = 2, message = "Should be at least 2 character to 10")
    private String name;

    private String description;

    @Column(name="expense_amount")
    private Double amount;

    private String category;

    private Date date;

    @Column(name="createdAt", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name="updatedAt", nullable = false, updatable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch =FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
}
