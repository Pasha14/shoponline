package by.eshop.repository.impl;

import by.eshop.domain.Category;
import by.eshop.domain.Product;
import by.eshop.repository.CategoriesRepository;
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
public class CategoryRepository implements CategoriesRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private Category getCategoryRowMapper(ResultSet rs, int i) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("id"));
        category.setCategory(rs.getString("category"));
        category.setPicture(rs.getString("picture"));
        category.setSubcategory(rs.getString("subcategory"));
        return category;
    }

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query("select * from categories order by id desc", this::getCategoryRowMapper);
    }

    @Override
    public Category findOne(Long id) {
        final String findOne = "select * from categories where id = ?";
        return jdbcTemplate.queryForObject(findOne, this::getCategoryRowMapper, id);
    }

    private MapSqlParameterSource categoryParamsMap(Category category){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("category", category.getCategory());
        params.addValue("picture", category.getPicture());
        params.addValue("subcategory", category.getSubcategory());
        return params;
    }

    @Override
    public Category save(Category category) {
        final String creatQuery = "insert into categories (category, picture, subcategory)" +
                "values (:category, :picture, :subcategory);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = categoryParamsMap(category);

        namedParameterJdbcTemplate.update(creatQuery, params, keyHolder, new String[]{"id"});

        long createdCategoryId = Objects.requireNonNull(keyHolder.getKey().longValue());
        return findOne(createdCategoryId);
    }

    @Override
    public Category update(Category category) {
        final String updateQuery = "UPDATE categories SET category = :category, picture = :picture, subcategory = :subcategory WHERE id = " + category.getId();

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = categoryParamsMap(category);

        namedParameterJdbcTemplate.update(updateQuery, params, keyHolder, new String[]{"id"});

        long createdCategoryId = Objects.requireNonNull(keyHolder.getKey().longValue());
        return findOne(createdCategoryId);
    }

    @Override
    public void delete(Long id) {
        final String deleteQuery = "DELETE FROM categories WHERE id = ?";
        jdbcTemplate.update(deleteQuery, id);
    }

    @Override
    public List<Category> getSubcategoryByCategory(String categoryName) {
        final String searchQuery = "select * from categories where category = :categoryName";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("categoryName", categoryName);
        return namedParameterJdbcTemplate.query(searchQuery, params, this::getCategoryRowMapper);
    }
}
