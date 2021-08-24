package by.eshop.repository.jdbcTamplate;

import by.eshop.domain.jdbctamplate.Product;
import by.eshop.repository.CrudOperations;

import java.util.List;

public interface ProductRepository extends CrudOperations<Long, Product> {

    List<Product> findProductsByQuery(String query);

    List<Product> findProductsByCategory(String categoryName);
    List<Product> findProductsBySubcategory(String categoryName, String subcategoryName);
}
