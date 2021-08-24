package by.eshop.repository.springData;

import by.eshop.domain.hibernate.HibernateCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryDataRepository extends JpaRepository<HibernateCategory, Long> {

    Optional<HibernateCategory> findById(Long categoryId);

}
