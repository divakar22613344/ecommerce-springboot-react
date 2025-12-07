package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstants;
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

    @GetMapping("/echo")
    public ResponseEntity<String> echoMessage(@RequestParam(name="message", defaultValue = "Default message.! Please reset !") String message) {
        return new ResponseEntity<>("Echo Message : " + message, HttpStatus.OK);
    }

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value="/public/categories", method=RequestMethod.GET)
    public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name="pageNumber",defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber
                                                           , @RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE) Integer pageSize
                                                           , @RequestParam(name="sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY) String sortBy
                                                           , @RequestParam(name="sortOrder", defaultValue = AppConstants.SORT_DIR) String sortOrder) {
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
    }

    @RequestMapping(value="/public/categories", method=RequestMethod.POST)
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO,HttpStatus.CREATED);
    }

    @RequestMapping(value="/admin/categories/{categoryId}", method=RequestMethod.DELETE)
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(deletedCategory, HttpStatus.OK);
    }

    @RequestMapping(value="/public/categories/{categoryID}", method=RequestMethod.PUT)
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable Long categoryID) {
        CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO,categoryID);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
    }
}

