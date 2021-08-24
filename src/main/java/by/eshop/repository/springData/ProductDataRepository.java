package by.eshop.repository.springData;

import by.eshop.domain.hibernate.HibernateProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDataRepository extends JpaRepository<HibernateProduct, Long> {

    List<HibernateProduct> findByName(String name);

    @Query(value = "SELECT * FROM products JOIN (SELECT id AS cat_id FROM categories JOIN category_name cn on categories.category_id = cn.id WHERE cn.category = :categoryName) cat on category_id = cat.cat_id;", nativeQuery = true)
    List<HibernateProduct> findProductsByCategory(@Param("categoryName") String categoryName);

    @Query(value = "SELECT * FROM products WHERE category_id = (SELECT id FROM categories WHERE category_id = (SELECT id FROM category_name WHERE category = :category) AND subcategory = :subcategory);", nativeQuery = true)
    List<HibernateProduct> findProductsBySubcategory(@Param("category") String category, @Param("subcategory") String subcategory);


}
