package com.finalyear.finalyear.service;

import java.util.List;

import com.finalyear.finalyear.model.Expense;

public interface ExpenseService {
	List<Expense>getAllExspenses();
	void save(Expense expense);
	Expense getExpenseById(Long id);
	void deleteById(Long id);
}
