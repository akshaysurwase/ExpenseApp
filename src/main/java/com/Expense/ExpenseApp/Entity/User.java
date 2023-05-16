package com.Expense.ExpenseApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @Column(unique=true)
    private String email;


    @JsonIgnore
    private String password;

    private Long age;

    @Column(name="created_At", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAT;

    @Column(name="updated_At")
    @UpdateTimestamp
    private Timestamp updatedAt;
}
