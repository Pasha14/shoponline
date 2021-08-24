package by.eshop.repository.springData;

import by.eshop.domain.hibernate.HibernateBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandDataRepository extends JpaRepository<HibernateBrand, Long> {

}
