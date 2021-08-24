package by.eshop.repository.jdbcTamplate.impl;

import by.eshop.domain.jdbctamplate.Category;
import by.eshop.domain.jdbctamplate.Subcategory;
import by.eshop.repository.jdbcTamplate.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoriesRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private Category getCategoryRowMapper(ResultSet rs, int i) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setCategory(rs.getString("category"));
        category.setPicture(rs.getString("picture"));
        return category;
    }

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query("select * from category_name order by id desc", this::getCategoryRowMapper);
    }

    @Override
    public Category findOne(Long id) {
        final String findOne = "select * from category_name where id = ?";
        return jdbcTemplate.queryForObject(findOne, this::getCategoryRowMapper, id);
    }

    private MapSqlParameterSource categoryParamsMap(Category category){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("category", category.getCategory());
        params.addValue("picture", category.getPicture());
        return params;
    }

    @Override
    public Category save(Category category) {
        final String creatQuery = "insert into category_name (category, picture)" +
                "values (:category, :picture);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = categoryParamsMap(category);

        namedParameterJdbcTemplate.update(creatQuery, params, keyHolder, new String[]{"id"});

        long createdCategoryId = Objects.requireNonNull(keyHolder.getKey().longValue());
        return findOne(createdCategoryId);
    }

    @Override
    public Category update(Category category) {
        final String updateQuery = "UPDATE category_name SET category = :category, picture = :picture WHERE id = " + category.getId();

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = categoryParamsMap(category);

        namedParameterJdbcTemplate.update(updateQuery, params, keyHolder, new String[]{"id"});

        long createdCategoryId = Objects.requireNonNull(keyHolder.getKey().longValue());
        return findOne(createdCategoryId);
    }

    @Override
    public void delete(Long id) {
        final String deleteQuery = "DELETE FROM category_name WHERE id = ?";
        jdbcTemplate.update(deleteQuery, id);
    }

    private Subcategory getSubcategoryRowMapper(ResultSet rs, int i) throws SQLException {
        Subcategory subcategory = new Subcategory();
        subcategory.setId(rs.getLong("id"));
        subcategory.setCategoryId(rs.getInt("category_id"));
        subcategory.setSubcategory(rs.getString("subcategory"));
        return subcategory;
    }

    @Override
    public List<Subcategory> findAllSubcategory() {
        return jdbcTemplate.query("select * from categories order by id desc", this::getSubcategoryRowMapper);
    }


    @Override
    public List<Subcategory> getSubcategoriesByCategory(Integer categoryId) {
        final String searchQuery = "select * from categories where category_id = :categoryId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("categoryId", categoryId);
        return namedParameterJdbcTemplate.query(searchQuery, params, this::getSubcategoryRowMapper);
    }

    @Override
    public void saveSubcategoriesForCategory(Integer categoryId, List<String> subcategoryList) {
        final String createQuery = "INSERT INTO categories (category_id, subcategory) VALUES (:categoryId, :subcategory)";

        List<MapSqlParameterSource> batchParams = new ArrayList<>();

        for (String subcategory : subcategoryList){
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("categoryId", categoryId);
            params.addValue("subcategory", subcategory);
            batchParams.add(params);
        }

        namedParameterJdbcTemplate.batchUpdate(createQuery, batchParams.toArray(new MapSqlParameterSource[0]));
    }

    @Override
    public void deleteSubcategory(Long id) {
        jdbcTemplate.update("DELETE FROM categories WHERE id = ?", id);
    }

}
