package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //@GetMapping("/public/categories") <- we can use both methods getmapping or requestmapping
    @RequestMapping(value="/public/categories", method=RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }


    //@PostMapping("/public/categories")
    @RequestMapping(value="/public/categories", method=RequestMethod.POST)
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added successfully",HttpStatus.CREATED);
    }

    //@DeleteMapping("/admin/categories/{categoryId}")
    @RequestMapping(value="/admin/categories/{categoryId}", method=RequestMethod.DELETE)
    public ResponseEntity deleteCategory(@PathVariable("categoryId") Long categoryId) {
        try {
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }


    //@PutMapping("/public/categories/{categoryID}")
    @RequestMapping(value="/public/categories/{categoryID}", method=RequestMethod.PUT)
    public ResponseEntity<String> updateCategory(@RequestBody Category category,@PathVariable Long categoryID) {
        try{
            Category savedCategory = categoryService.updateCategory(category,categoryID);
            return new ResponseEntity<>("Category with Category ID: "+  categoryID, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }
}

