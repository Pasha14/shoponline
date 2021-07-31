package by.eshop.repository;

import by.eshop.domain.Buyer;
import by.eshop.domain.Role;

import java.util.List;

public interface RoleRepository extends CrudOperations<Long, Role>{

    List<Role> getUserRoles(Buyer buyer);
}
