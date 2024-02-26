package com.bnt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bnt.entity.Categories;
import com.bnt.entity.CategoryResponse;
import com.bnt.exception.CategoryNotFoundException;
import com.bnt.service.CategoryService;

public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    public Categories categoryData() {
		Categories category = new Categories();
		category.setCategoryId(1L);
		category.setTitle("Spring Core");
		category.setDescription("This is Spring Core Category Created");
		return category;
	}

	@Test
	public void testAddNewCategory() {

		Categories category = categoryData();

		when(categoryService.addNewCategory(any(Categories.class))).thenReturn(category);

		Categories result = categoryController.addNewCategory(category);

		assertEquals(category, result);
	}
	
	@Test
	public void testGetAllCategory() {
		List<CategoryResponse> mockCategories = new ArrayList<>();
		Categories category = categoryData();
		mockCategories.add(category.toResponse());

		when(categoryService.getAllCatogory()).thenReturn(mockCategories);

		List<CategoryResponse> result = categoryController.getAllCategory();

		assertNotNull(result);
		assertEquals(mockCategories, result);
	}
	
	@Test
	public void testGetCategoryById_Success() {

		Long categoryId = 1L;
		CategoryResponse mockCategory = new CategoryResponse(categoryId, "Spring Core",
				"This is Spring Core Category Created");
		when(categoryService.getCategoryById(categoryId)).thenReturn(mockCategory);

		ResponseEntity<?> result = categoryController.getCategoryById(categoryId);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(mockCategory, result.getBody());
	}

	@Test
	public void testUpdateCategory_Success() {

		Categories categoryToUpdate = categoryData();
		CategoryResponse mockUpdatedCategory = new CategoryResponse(1L, "Spring Core",
				"This is Spring Core Category Created");
		when(categoryService.updateCategory(categoryToUpdate)).thenReturn(mockUpdatedCategory);

		ResponseEntity<CategoryResponse> result = categoryController.updateCategory(categoryToUpdate);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(mockUpdatedCategory, result.getBody());
	}

	@Test
	public void testDeleteCategory_Success() {

		Long categoryId = 1L;
		ResponseEntity<?> result = categoryController.deleteCategory(categoryId);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
	@Test
	public void testGetCategoryById_CategoryNotFound() {

		Long categoryId = 1L;
		when(categoryService.getCategoryById(categoryId))
				.thenThrow(new CategoryNotFoundException("Category not found"));
		try {
			ResponseEntity<?> result = categoryController.getCategoryById(categoryId);
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
		} catch (CategoryNotFoundException e) {
		}
	}
	
	@Test
	public void testUpdateCategory_CategoryNotFound() {

		Categories categoryToUpdate = new Categories();
		when(categoryService.updateCategory(categoryToUpdate))
				.thenThrow(new CategoryNotFoundException("Category not found"));

		try {
			ResponseEntity<CategoryResponse> result = categoryController.updateCategory(categoryToUpdate);
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
		} catch (CategoryNotFoundException e) {

		}
	}
	
	@Test
	public void testDeleteCategory_CategoryNotFound() {

		Long categoryId = 1L;
		doThrow(new CategoryNotFoundException("Category not found")).when(categoryService).deleteCategory(categoryId);

		try {
			ResponseEntity<?> result = categoryController.deleteCategory(categoryId);
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
		} catch (CategoryNotFoundException e) {

		}

	}
}
