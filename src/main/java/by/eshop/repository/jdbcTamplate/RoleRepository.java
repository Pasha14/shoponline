package by.eshop.repository.jdbcTamplate;

import by.eshop.domain.jdbctamplate.Buyer;
import by.eshop.domain.jdbctamplate.Role;
import by.eshop.repository.CrudOperations;

import java.util.List;

public interface RoleRepository extends CrudOperations<Long, Role> {

    List<Role> getUserRoles(Buyer buyer);
}
