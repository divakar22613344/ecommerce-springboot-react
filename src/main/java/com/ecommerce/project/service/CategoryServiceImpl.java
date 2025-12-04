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
    //private List<Category> categories = new ArrayList<>();

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
        //List<Category> categories = categoryRepository.findAll();
        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryId);

        //Category savedCategory = savedCategoryOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));

        Category savedCategory = savedCategoryOptional.orElseThrow(() -> new ResourceNotFoundException("Catogory","categoryId", categoryId));

        //Category category  = categories.stream().filter(c -> c.getCategoryId()
        //        .equals(categoryId))
        //        .findFirst()
        //        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));


        categoryRepository.delete(savedCategory);
        return "Category with CategoryID " + categoryId + " deleted successfully..!!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryID) {
        //List<Category> categories = categoryRepository.findAll();
        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryID);
        //Category savedCategory = savedCategoryOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));
        Category savedCategory = savedCategoryOptional.orElseThrow(() -> new ResourceNotFoundException("Catogory","categoryId", categoryID));
        category.setCategoryId(categoryID);

        savedCategory = categoryRepository.save(category);
        return savedCategory;
//        Optional<Category> optionalCategory  = categories.stream().filter(c -> c.getCategoryId()
//                            .equals(categoryID))
//                            .findFirst();
//
//        if(optionalCategory.isPresent()){
//            Category existingCategory = optionalCategory.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//            Category savedCategory = categoryRepository.save(existingCategory);
//            return savedCategory;
//        }else{
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found");
//        }
    }


}

