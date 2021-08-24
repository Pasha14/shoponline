package by.eshop.repository.springData;

import by.eshop.domain.hibernate.HibernateRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDataRepository extends JpaRepository<HibernateRole, Integer> {

}
