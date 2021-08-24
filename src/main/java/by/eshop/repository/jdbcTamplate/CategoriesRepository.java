package by.eshop.repository.jdbcTamplate;

import by.eshop.domain.jdbctamplate.Category;
import by.eshop.domain.jdbctamplate.Subcategory;
import by.eshop.repository.CrudOperations;

import java.util.List;

public interface CategoriesRepository extends CrudOperations<Long, Category> {

    List<Subcategory> findAllSubcategory();

    void deleteSubcategory(Long id);

    List<Subcategory> getSubcategoriesByCategory(Integer categoryId);

    void saveSubcategoriesForCategory(Integer categoryId, List<String> subcategory);


}
