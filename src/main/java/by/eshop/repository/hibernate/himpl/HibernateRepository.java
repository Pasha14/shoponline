package by.eshop.repository.hibernate.himpl;

import by.eshop.domain.hibernate.HibernateBuyer;
import by.eshop.repository.hibernate.HibernateBuyerRepository;
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
public class HibernateRepository implements HibernateBuyerRepository {

    @Autowired
    @Qualifier("sessionFactory")
    private final SessionFactory sessionFactory;

    @Override
    public List<HibernateBuyer> findAll() {
        try(Session session = sessionFactory.openSession()){
            return session.createNamedQuery("HibernateBuyer_findAll", HibernateBuyer.class).getResultList();
        }
    }

    @Override
    public HibernateBuyer findOne(Long id) {
        try(Session session = sessionFactory.openSession()){
            return session.find(HibernateBuyer.class, id);
        }
    }

    @Override
    public HibernateBuyer save(HibernateBuyer entity) {
        return null;
    }

    @Override
    public HibernateBuyer update(HibernateBuyer entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
