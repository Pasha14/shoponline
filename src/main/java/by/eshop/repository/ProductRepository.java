package by.eshop.repository;

import by.eshop.domain.Product;

import java.util.List;

public interface ProductRepository extends CrudOperations<Long, Product>{

    List<Product> findProductsByQuery(String query);

    List<Product> findProductsByCategory(String categoryName);
    List<Product> findProductsBySubcategory(String categoryName, String subcategoryName);
}
