package com.bnt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bnt.entity.Category;
import com.bnt.entity.CategoryResponse;
import com.bnt.exception.CategoryNotFoundException;
import com.bnt.exception.DuplicateCategoryException;
import com.bnt.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository repository;

	public CategoryServiceImpl(CategoryRepository repository) {
		this.repository = repository;
	}

	@Override
	public Category addNewCategory(Category category) {

		if (repository.findByTitle(category.getTitle()).isPresent()) {
			throw new DuplicateCategoryException("Category with title '" + category.getTitle() + "' already exists");
		}
		Category newCategory = new Category();
		newCategory.setTitle(category.getTitle());
		newCategory.setDescription(category.getDescription());
		return this.repository.save(newCategory);
	}

	@Override
	public List<Category> getAllCatogory() {

		return (ArrayList<Category>) this.repository.findAll();
	}

	public Category getCategoryById(Long categoryId) {
		return repository.findById(categoryId)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found" + categoryId));
	}

	@Override
	public Category updateCategory(Category category) {
		try {
			Category existingCategory = this.repository.findById(category.getCategoryId())
					.orElseThrow(() -> new CategoryNotFoundException("Category not found"));

			existingCategory.setTitle(category.getTitle());
			existingCategory.setDescription(category.getDescription());

			Category updatedCategory = this.repository.save(existingCategory);
			return updatedCategory;
		} catch (CategoryNotFoundException e) {
			throw new CategoryNotFoundException("Failed to update category" + category.getCategoryId() + " not found");
		}
	}

	@Override
	public void deleteCategory(Long categoryId) {
		if (repository.existsById(categoryId)) {
			try {
				repository.deleteById(categoryId);
			} catch (CategoryNotFoundException e) {
				throw new CategoryNotFoundException("Failed to delete category");
			}
		} else {
			throw new CategoryNotFoundException("Category with ID " + categoryId + " not found");
		}
	}

}
