package by.eshop.repository.hibernate.himpl;

import by.eshop.controller.requests.SearchRequest;
import by.eshop.domain.hibernate.HibernateProduct;
import by.eshop.domain.hibernate.HibernateProduct_;
import by.eshop.repository.hibernate.HibernateProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HProductRepository implements HibernateProductRepository {

    @Autowired
    @Qualifier("sessionFactory")
    private final SessionFactory sessionFactory;

    @Autowired
    @Qualifier("entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<HibernateProduct> findAll() {
        try(Session session = sessionFactory.openSession()){
            return Collections.singletonList(session.find(HibernateProduct.class, 1L));
        }
    }

    @Override
    public HibernateProduct findOne(Long id) {
        return null;
    }

    @Override
    public HibernateProduct save(HibernateProduct entity) {
        return null;
    }

    @Override
    public HibernateProduct update(HibernateProduct entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<HibernateProduct> findProductsByQuery(SearchRequest searchRequest) {
        //1. Get Builder for Criteria object
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<HibernateProduct> query = cb.createQuery(HibernateProduct.class); //here select, where, orderBy, having
        Root<HibernateProduct> root = query.from(HibernateProduct.class); //here params  select * from m_users -> mapping

        /*type of future params in prepared statement определение типов для вайлдкардов*/
        ParameterExpression<String> param = cb.parameter(String.class);
        ParameterExpression<Long> userSearchParam = cb.parameter(Long.class);

        /*Provide access to fields in class that mapped to columns*/
        Expression<Long> id = root.get(HibernateProduct_.id);
        Expression<String> name = root.get(HibernateProduct_.name);
        Expression<String> model = root.get(HibernateProduct_.model);

        /*SQL Query customizing*/
        query
                .select(root) //select * = select method, (root) = from users
                .distinct(true)
                .where( //where
                        cb.or(
                                cb.like(name, param),  //userName like param
                                cb.like(model, param)  //userSurname like param
                        )

                )
                .orderBy(cb.asc(id));

        TypedQuery<HibernateProduct> resultQuery = entityManager.createQuery(query); //prepared statement on hql
        resultQuery.setParameter(param, StringUtils.join("%", searchRequest.getQuery(), "%"));
        return resultQuery.getResultList();
    }
}
