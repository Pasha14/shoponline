package by.eshop.repository.springData;

import by.eshop.domain.hibernate.HibernateSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryDataRepository extends JpaRepository<HibernateSubcategory, Long> {

}
