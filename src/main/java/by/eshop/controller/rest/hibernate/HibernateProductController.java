package by.eshop.controller.rest.hibernate;

import by.eshop.controller.requests.SearchRequest;
import by.eshop.domain.hibernate.HibernateSubcategory;
import by.eshop.domain.hibernate.HibernateProduct;
import by.eshop.repository.hibernate.HibernateCategoryRepository;
import by.eshop.repository.hibernate.HibernateProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hibernate/product")
@RequiredArgsConstructor
public class HibernateProductController {

    private final HibernateProductRepository productRepository;
    private final HibernateCategoryRepository categoryRepository;

    @GetMapping
    public List<HibernateProduct> findAll(){
        return productRepository.findAll();
    }

    @GetMapping("/category")
    public List<HibernateSubcategory> findAllCategory(){
        return categoryRepository.findAll();
    }

    @GetMapping("/search")
    private List<HibernateProduct> search(@ModelAttribute SearchRequest searchRequest){
        return productRepository.findProductsByQuery(searchRequest);
    }
}
