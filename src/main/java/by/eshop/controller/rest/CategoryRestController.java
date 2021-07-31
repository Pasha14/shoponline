package by.eshop.controller.rest;

import by.eshop.controller.requests.SaveSubcategories;
import by.eshop.domain.Category;
import by.eshop.domain.Subcategory;
import by.eshop.repository.impl.CategoryRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryRepositoryImpl categoryRepository;

    @GetMapping
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryRepository.save(category);
    }


    @GetMapping("/{categoryId}")
    public List<Subcategory> getSubCategoryForCategory(@PathVariable Integer categoryId){
        return categoryRepository.getSubcategoriesByCategory(categoryId);
    }

    @GetMapping("/subcategories")
    public List<Subcategory> findAllSubcategories(){
        return categoryRepository.findAllSubcategory();
    }

    @PostMapping("/subcategories")
    public void saveSubcategoriesForCategory(@RequestBody SaveSubcategories saveSubcategories){
        categoryRepository.saveSubcategoriesForCategory(saveSubcategories.getCategoryId(), saveSubcategories.getSubcategoryList());
    }




}
