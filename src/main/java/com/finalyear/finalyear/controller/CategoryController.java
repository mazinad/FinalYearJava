package com.finalyear.finalyear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.finalyear.finalyear.model.Category;
import com.finalyear.finalyear.service.CategoryService;
@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@GetMapping("/api/homeCategory")
	public String homePage(Model model) {
		model.addAttribute("listAllCategories", categoryService.getAllCategories());
		return "category";
	}
	@GetMapping("/api/showNewCategory")
	public String showNewExpenses(Model model) {
		Category category=new Category(); 
		model.addAttribute("category", category);
		return "NewCategory";
	}
	@PostMapping("/api/saveCategory")
	public String saveCategory(@ModelAttribute("category") Category category) {
		try {
			categoryService.saveCategory(category);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "redirect:/api/homeCategory";
	}
	@GetMapping("/api/updateCategoryById/{id}")
	public String findCategoryByid(@PathVariable(value = "id") long id,Model model) {
		try {
		Category category=categoryService.findCategoryById(id);
		model.addAttribute("category", category);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "updateCategory";
	}
	@GetMapping("/api/deleteCategoryById/{id}")
	public String deleteCategoryById(@PathVariable(value = "id")long id,Model model) {
		try {
			categoryService.deleteCategoryByid(id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/api/homeCategory";
	}
}
