package by.eshop.repository.jdbcTamplate;

import by.eshop.domain.jdbctamplate.Buyer;
import by.eshop.domain.jdbctamplate.Role;
import by.eshop.repository.CrudOperations;

import java.util.List;

public interface BuyerRepository extends CrudOperations<Long, Buyer> {

    void batchInsert(List<Buyer> buyers);

    List<Buyer> findBuyersByQuery(Integer limit, String query);

    void saveBuyerRoles(Buyer buyer, List<Role> buyerRoles);

    Buyer findByLoginAndPassword(String login, String password);

    Buyer findByLogin(String login);
}
