package by.eshop.repository.hibernate;

import by.eshop.controller.requests.SearchRequest;
import by.eshop.domain.hibernate.HibernateProduct;
import by.eshop.repository.CrudOperations;

import java.util.List;

public interface HibernateProductRepository extends CrudOperations<Long, HibernateProduct> {

    List<HibernateProduct> findProductsByQuery(SearchRequest searchRequest);
}
