package by.eshop.repository;

import by.eshop.domain.Category;
import by.eshop.domain.Subcategory;

import java.util.List;

public interface CategoriesRepository extends CrudOperations<Long, Category>{

    List<Subcategory> findAllSubcategory();

    void deleteSubcategory(Long id);

    List<Subcategory> getSubcategoriesByCategory(Integer categoryId);

    void saveSubcategoriesForCategory(Integer categoryId, List<String> subcategory);


}
