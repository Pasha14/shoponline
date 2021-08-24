package by.eshop.controller.rest;

import by.eshop.controller.requests.ProductCreateRequest;
import by.eshop.domain.hibernate.HibernateProduct;
import by.eshop.repository.springData.ProductDataRepository;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/products")
@RequiredArgsConstructor
@Api(description = "Products controller", tags = { "Products" })
public class ProductRestController {

    private final ProductDataRepository productRepository;

    @GetMapping
    public List<HibernateProduct> findAll(){
        return productRepository.findAll();
    }

    @GetMapping("/search")
    public List<HibernateProduct> findByQuery(@RequestParam String name){
        return productRepository.findByName(name);
    }


    @GetMapping("/{category}")
    public List<HibernateProduct> findProductByCategory(@PathVariable String category){
        return productRepository.findProductsByCategory(category);
    }

    @GetMapping("/{category}/{subcategory}")
    public List<HibernateProduct> findProductBySubcategory(@PathVariable String category, @PathVariable String subcategory){
        return productRepository.findProductsBySubcategory(category, subcategory);
    }

    @PostMapping
    public HibernateProduct createProduct(@RequestBody ProductCreateRequest product){
        HibernateProduct newProduct = new HibernateProduct();
        newProduct.setCategoryId(product.getCategoryId());
        newProduct.setBrandId(product.getBrandId());
        newProduct.setName(product.getName());
        newProduct.setModel(product.getModel());
        newProduct.setPrice(product.getPrice());
        newProduct.setAvailability(product.getAvailability());

        return productRepository.save(newProduct);
    }


}
