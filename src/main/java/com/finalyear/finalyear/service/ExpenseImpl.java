package com.finalyear.finalyear.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalyear.finalyear.model.Expense;
import com.finalyear.finalyear.repository.ExpenseRepository;
@Service
public class ExpenseImpl implements ExpenseService{
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Override
	public List<Expense> getAllExspenses() {
		// TODO Auto-generated method stub
		
		return expenseRepository.findAll();
	}

	@Override
	public void save(Expense expense) {
		// TODO Auto-generated method stub
		expenseRepository.save(expense);
		
	}

	@Override
	public Expense getExpenseById(Long id) {
		// TODO Auto-generated method stub
		Optional<Expense> optional=expenseRepository.findById(id);
		Expense exp=null;
		if(optional.isPresent()) {
			exp=optional.get();
		}
		return exp;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		boolean exists=expenseRepository.existsById(id);
		if(!exists) {
			throw new RuntimeException("Expense With this id does not exist");
		}
		this.expenseRepository.deleteById(id);
		
	}

}
