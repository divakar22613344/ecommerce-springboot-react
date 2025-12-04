package com.ecommerce.project.repositories;

import com.ecommerce.project.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories(basePackages = "com.ecommerce.project")
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryName(@Size(min = 5, message="Category name must contain at least 5 characters..!") @NotBlank(message="Category name cannot be blank Divakar..!") String categoryName);
}
