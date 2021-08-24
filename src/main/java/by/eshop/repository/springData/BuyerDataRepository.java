package by.eshop.repository.springData;

import by.eshop.domain.hibernate.HibernateBuyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BuyerDataRepository extends JpaRepository<HibernateBuyer, Long> {


    Optional<List<HibernateBuyer>> findByNameAndLogin(String name, String login);

    HibernateBuyer findByLogin(String login);

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = SQLException.class)
    @Modifying
    @Query(value = "insert into buyer_roles(buyer_id, role_id) values (:buyer_id, :role_id)", nativeQuery = true)
    int createSomeRow(@Param("buyer_id") Long userId, @Param("role_id") Long roleId);
}
