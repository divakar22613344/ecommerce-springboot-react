package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new APIException("No Category Created till now!!");
        }
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());

        if (savedCategory != null) {
            throw new APIException("Category with the name "+ category.getCategoryName() + " already exists  !!!!");
        }
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryId);

        Category savedCategory = savedCategoryOptional.orElseThrow(() -> new ResourceNotFoundException("Catogory","categoryId", categoryId));

        categoryRepository.delete(savedCategory);
        return "Category with CategoryID " + categoryId + " deleted successfully..!!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryId);
        Category savedCategory = savedCategoryOptional.orElseThrow(() -> new ResourceNotFoundException("Catogory","categoryId", categoryId));
        category.setCategoryId(categoryId);

        savedCategory = categoryRepository.save(category);
        return savedCategory;
    }
}

