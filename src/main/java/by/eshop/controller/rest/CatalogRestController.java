package by.eshop.controller.rest;

import by.eshop.domain.Category;
import by.eshop.domain.Product;
import by.eshop.repository.impl.CategoryRepository;
import by.eshop.repository.impl.JdbcTemplateProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogRestController {

    private final JdbcTemplateProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryRepository.save(category);
    }

    @GetMapping("/{category}")
    public List<Product> findProductByCategory(@PathVariable String category){
        return productRepository.findProductsByCategory(category);
    }

    @GetMapping("/{category}/{subcategory}")
    public List<Product> findProductBySubcategory(@PathVariable String category, @PathVariable String subcategory){
        return productRepository.findProductsBySubcategory(category, subcategory);
    }


}
