package by.eshop.controller.rest;

import by.eshop.domain.Product;
import by.eshop.repository.impl.JdbcTemplateProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final JdbcTemplateProductRepository productRepository;

    @GetMapping
    public List<Product> findAll(){
        return productRepository.findAll();
    }


    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @GetMapping("/search")
    public List<Product> searchProduct(@RequestParam String product){
        return productRepository.findProductsByQuery(product);
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
