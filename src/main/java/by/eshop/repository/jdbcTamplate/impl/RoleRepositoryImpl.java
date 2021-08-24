package by.eshop.repository.jdbcTamplate.impl;

import by.eshop.domain.jdbctamplate.Buyer;
import by.eshop.domain.jdbctamplate.Role;
import by.eshop.repository.jdbcTamplate.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    private Role getRoleRowMapper(ResultSet rs, int i) throws SQLException {
        Role role = new Role();
        role.setId(rs.getLong("id"));
        role.setRoleName(rs.getString("role_name"));
        return role;
    }

    @Override
    public List<Role> findAll() {
        return jdbcTemplate.query("SELECT * FROM roles ORDER BY id DESC", this::getRoleRowMapper);
    }

    @Override
    public Role findOne(Long id) {
        final String findOneQuery = "SELECT * FROM roles WHERE  id = ?";
        return jdbcTemplate.queryForObject(findOneQuery, this::getRoleRowMapper, id);
    }

    private MapSqlParameterSource roleParamsMap(Role role){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rolName", role.getRoleName());
        return params;
    }

    @Override
    public Role save(Role role) {
        final String creatQuery = "insert into roles (role_name) values (:rolName);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = roleParamsMap(role);

        namedParameterJdbcTemplate.update(creatQuery, params, keyHolder, new String[]{"id"});

        long createdRoleId = Objects.requireNonNull(keyHolder.getKey().longValue());
        return findOne(createdRoleId);
    }

    @Override
    public Role update(Role entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Role> getUserRoles(Buyer buyer) {
        final String findBuyerRoles

                = "select r.id as id, r.role_name as role_name from buyer_roles inner join roles r on r.id = buyer_roles.role_id where buyer_roles.buyer_id = :buyerId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("buyerId", buyer.getId());

        return namedParameterJdbcTemplate.query(findBuyerRoles, params, this::getRoleRowMapper);
    }
}
