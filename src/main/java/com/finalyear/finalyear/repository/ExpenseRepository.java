package com.finalyear.finalyear.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalyear.finalyear.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense,Long>{

}
