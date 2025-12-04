package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value="/public/categories", method=RequestMethod.GET)
    public ResponseEntity<CategoryResponse> getAllCategories() {
        CategoryResponse categoryResponse = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
    }

    @RequestMapping(value="/public/categories", method=RequestMethod.POST)
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO,HttpStatus.CREATED);
    }

    @RequestMapping(value="/admin/categories/{categoryId}", method=RequestMethod.DELETE)
    public ResponseEntity deleteCategory(@PathVariable("categoryId") Long categoryId) {
        String status = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @RequestMapping(value="/public/categories/{categoryID}", method=RequestMethod.PUT)
    public ResponseEntity<String> updateCategory(@Valid @RequestBody Category category,@PathVariable Long categoryID) {
        Category savedCategory = categoryService.updateCategory(category,categoryID);
        return new ResponseEntity<>("Category with Category ID: "+  categoryID, HttpStatus.OK);
    }
}

