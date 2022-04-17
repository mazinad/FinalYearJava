package com.finalyear.finalyear.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalyear.finalyear.model.Category;
import com.finalyear.finalyear.repository.CategoryRepository;
@Service
public class CategoryImpl implements CategoryService{
	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public void saveCategory(Category category) {
		// TODO Auto-generated method stub
		categoryRepository.save(category);
	}

	@Override
	public Category findCategoryById(Long id) {
		// TODO Auto-generated method stub
		Optional<Category>optional=categoryRepository.findById(id);
		Category category=null;
		if(optional.isPresent()) {
			category=optional.get();
		}
		return category;
	}

	@Override
	public void deleteCategoryByid(Long id) {
		// TODO Auto-generated method stub
		boolean exists=categoryRepository.existsById(id);
		if(!exists) {
			throw new RuntimeException("This type of Id is not found"+id);
		}
		categoryRepository.deleteById(id);
		
	}

}
