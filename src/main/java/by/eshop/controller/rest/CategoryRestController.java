package by.eshop.controller.rest;

import by.eshop.controller.requests.CategoryCreateRequest;
import by.eshop.controller.requests.SaveSubcategories;
import by.eshop.domain.hibernate.HibernateCategory;
import by.eshop.domain.hibernate.HibernateSubcategory;
import by.eshop.repository.springData.CategoryDataRepository;
import by.eshop.repository.springData.SubcategoryDataRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/categories")
@RequiredArgsConstructor
@Api(description = "categories controller", tags = { "Categories" })
public class CategoryRestController {

    private final CategoryDataRepository categoryRepository;
    private final SubcategoryDataRepository subcategoryRepository;

    @ApiOperation(value = "Get all categories with it own subcategory")
    @GetMapping
    public List<HibernateCategory> findAll(){
        return categoryRepository.findAll();
    }

    @ApiOperation(value = "Save one category")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", dataType = "CreateCategoryRequest", paramType = "body", value = "Name and picture link for new category")
    })
    @PostMapping
    public HibernateCategory saveCategory(@RequestBody CategoryCreateRequest request){
        HibernateCategory newCategory = new HibernateCategory();
        newCategory.setCategory(request.getCategory());
        newCategory.setPicture(request.getPicture());
        return categoryRepository.save(newCategory);
    }

    @ApiOperation(value = "Save subcategories for one category")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", dataType = "SaveSubcategories", paramType = "body", value = "List of subcategories for one category")
    })
    @PostMapping("/subcategories")
    public Optional<HibernateCategory> saveSubcategoriesForCategory(@RequestBody SaveSubcategories request){
        List<HibernateSubcategory> subcategoryList = new ArrayList<>();

        for (String subcategoryName : request.getSubcategoryList()) {
        HibernateSubcategory subcategory = new HibernateSubcategory();
        subcategory.setCategoryId(request.getCategoryId());
        subcategory.setSubcategory(subcategoryName);
        subcategoryList.add(subcategory);
        }

        subcategoryRepository.saveAll(subcategoryList);
        return categoryRepository.findById(request.getCategoryId());
    }

    @ApiOperation(value = "Delete category by ID")@ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "long", paramType = "query", value = "Delete category by ID")
    })
    @PostMapping("/delete")
    public void deleteCategory(@RequestParam Long id){
        categoryRepository.deleteById(id);
    }

    @ApiOperation(value = "Delete subcategory by ID")@ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "long", paramType = "query", value = "Delete subcategory by ID")
    })
    @PostMapping("/subcategories/delete")
    public void deleteSubcategory(@RequestParam Long id){
        subcategoryRepository.deleteById(id);
    }

}
