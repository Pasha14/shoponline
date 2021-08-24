package by.eshop.repository.hibernate.himpl;

import by.eshop.domain.hibernate.HibernateSubcategory;
import by.eshop.repository.hibernate.HibernateCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HCategoryRepository implements HibernateCategoryRepository {

    @Autowired
    @Qualifier("sessionFactory")
    private final SessionFactory sessionFactory;

    @Override
    public List<HibernateSubcategory> findAll() {
        try(Session session = sessionFactory.openSession()){
            return Collections.singletonList(session.find(HibernateSubcategory.class, 1L));
        }
    }

    @Override
    public HibernateSubcategory findOne(Long id) {
        return null;
    }

    @Override
    public HibernateSubcategory save(HibernateSubcategory entity) {
        return null;
    }

    @Override
    public HibernateSubcategory update(HibernateSubcategory entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
