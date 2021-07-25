package by.eshop.repository.impl;

import by.eshop.domain.Product;
import by.eshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class JdbcTemplateProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Product getProductRowMapper(ResultSet rs, int i) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setCategoryId(rs.getLong("category_id"));
        product.setBrandId(rs.getLong("brand_id"));
        product.setName(rs.getString("name"));
        product.setModel(rs.getString("model"));
        product.setPrice(rs.getInt("price"));
        product.setAvailability(rs.getBoolean("availability"));
        return product;
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from products order by id desc", this::getProductRowMapper);
    }

    @Override
    public Product findOne(Long id) {
        final String findOne = "select * from products where id = ?";
        return jdbcTemplate.queryForObject(findOne, this::getProductRowMapper, id);
    }

    private MapSqlParameterSource productParamsMap(Product product){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("categoryId", product.getCategoryId());
        params.addValue("brandId", product.getBrandId());
        params.addValue("name", product.getName());
        params.addValue("model", product.getModel());
        params.addValue("price", product.getPrice());
        params.addValue("availability", product.getAvailability());
        return params;
    }

    @Override
    public Product save(Product product) {
        final String creatQuery = "insert into products (category_id, brand_id, name, model, price, availability)" +
                "values (:categoryId, :brandId, :name, :model, :price, :availability);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = productParamsMap(product);

        namedParameterJdbcTemplate.update(creatQuery, params, keyHolder, new String[]{"id"});

        long createdProductId = Objects.requireNonNull(keyHolder.getKey().longValue());
        return findOne(createdProductId);

    }

    @Override
    public Product update(Product product) {
        final String updateQuery = "UPDATE products SET category_id = :categoryId, brand_id = :brandId, name = :name, model = :model," +
                "price = :price, availability = :availability WHERE id = " + product.getId();

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = productParamsMap(product);

        namedParameterJdbcTemplate.update(updateQuery, params, keyHolder, new String[]{"id"});

        long createdProductId = Objects.requireNonNull(keyHolder.getKey().longValue());
        return findOne(createdProductId);
    }

    @Override
    public void delete(Long id) {
        final String deleteQuery = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(deleteQuery, id);
    }


    @Override
    public List<Product> findProductsByQuery(String productName) {
        final String searchQuery = "select * from products where name like :productName";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("productName", "%" + productName + "%");
        return namedParameterJdbcTemplate.query(searchQuery, params, this::getProductRowMapper);
    }

    @Override
    public List<Product> findProductsByCategory(String categoryName) {
        final String searchQuery = "SELECT * FROM products JOIN (SELECT id AS cat_id FROM categories WHERE category = ?) cat on category_id = cat.cat_id;";

        return jdbcTemplate.query(searchQuery, this::getProductRowMapper, categoryName);
    }

    @Override
    public List<Product> findProductsBySubcategory(String categoryName, String subcategoryName) {
        final String searchQuery = "SELECT * FROM products WHERE category_id = (SELECT id FROM categories WHERE category = :category AND subcategory = subcategory);";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("category", categoryName);
        params.addValue("subcategory", subcategoryName);
        return namedParameterJdbcTemplate.query(searchQuery, params, this::getProductRowMapper);
    }
}
