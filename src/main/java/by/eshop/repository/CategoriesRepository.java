package by.eshop.repository;

import by.eshop.domain.Category;

import java.util.List;

public interface CategoriesRepository extends CrudOperations<Long, Category>{

    List<Category> getSubcategoryByCategory(String categoryName);

}
