package by.eshop.repository.springData;

import by.eshop.domain.hibernate.HibernateOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDataRepository extends JpaRepository<HibernateOrder, Long> {

}
