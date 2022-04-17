package com.finalyear.finalyear.service;

import java.util.List;

import com.finalyear.finalyear.model.Category;

public interface CategoryService {
	List<Category>getAllCategories();
	void saveCategory(Category category);
	Category findCategoryById(Long id);
	void deleteCategoryByid(Long id);
}
