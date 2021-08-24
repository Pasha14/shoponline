package by.eshop.repository.hibernate.himpl;

import by.eshop.domain.hibernate.HibernateRole;
import by.eshop.repository.hibernate.HibernateRoleRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HRoleRepository implements HibernateRoleRepository {

    @Autowired
    @Qualifier("sessionFactory")
    private final SessionFactory sessionFactory;



    @Override
    public List<HibernateRole> findAll() {
        try(Session session = sessionFactory.openSession()){
            return Collections.singletonList(session.find(HibernateRole.class, 1L));
        }
    }

    @Override
    public HibernateRole findOne(Long id) {
        try (Session session = sessionFactory.openSession()){
            Query<HibernateRole> query = session.createQuery("SELECT r FROM HibernateRole r WHERE r.id = :id", HibernateRole.class);
            query.setParameter("id", id);

            return query.getSingleResult();
        }
    }

    @Override
    public HibernateRole save(HibernateRole entity) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long roleId = (Long) session.save(entity);
            transaction.commit();
            return findOne(roleId);
        }
    }

    @Override
    public HibernateRole update(HibernateRole entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
