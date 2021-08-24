package by.eshop.repository.jdbcTamplate.impl;

import by.eshop.domain.jdbctamplate.Buyer;
import by.eshop.domain.jdbctamplate.Role;
import by.eshop.repository.jdbcTamplate.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Primary
@RequiredArgsConstructor
public class JdbcTemplateBuyerRepository implements BuyerRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//    public static final String LOGIN = "login";
//    public static final String PASSWORD = "password";
//    public static final String NAME = "name";
//    public static final String SURNAME = "surname";
//    public static final String BIRTH_DATE = "birth_date";
//    public static final String PHONE = "phone";
//    public static final String EMAIL = "email";
//    public static final String POSTAL_CODE = "postal_code";
//    public static final String CITY = "city";
//    public static final String ADDRESS = "address";

    @Override
    public List<Buyer> findAll() {
        return jdbcTemplate.query("select * from buyer order by id desc", this::getBuyerRowMapper);
    }

    private Buyer getBuyerRowMapper(ResultSet rs, int i) throws SQLException{
        Buyer buyer = new Buyer();
        buyer.setId(rs.getLong("id"));
        buyer.setLogin(rs.getString("login"));
        buyer.setPassword(rs.getString("password"));
        buyer.setName(rs.getString("name"));
        buyer.setSurname(rs.getString("surname"));
        buyer.setBirthDate(rs.getDate("birth_date"));
        buyer.setPhone(rs.getString("phone"));
        buyer.setEmail(rs.getString("email"));
        buyer.setPostal_code(rs.getInt("postal_code"));
        buyer.setCity(rs.getString("city"));
        buyer.setAddress(rs.getString("address"));
        return buyer;
    }

    @Override
    public Buyer findOne(Long id) {
        final String findOneWithWildcard = "select * from buyer where id = ?";
        return jdbcTemplate.queryForObject(findOneWithWildcard, this::getBuyerRowMapper, id);

// another way:
//        final String findOneWithNamedParam = "select * from buyer where id = :id";
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("id", id);
//        return namedParameterJdbcTemplate.queryForObject(findOneWithNamedParam, params, this::getBuyerRowMapper);
    }

    @Override
    public Buyer save(Buyer buyer) {
        final String creatQuery = "insert into buyer (login, password, name, surname, birth_date, phone, email, " +
                "postal_code, city, address) values (:login, :password, :name, :surname, :birthDate, :phone, " +
                ":email, :postal_code, :city, :address);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = generateBuyerParamsMap(buyer);

        namedParameterJdbcTemplate.update(creatQuery, params, keyHolder, new String[]{"id"});

        long createdUserId = Objects.requireNonNull(keyHolder.getKey().longValue());
        return findOne(createdUserId);
    }

    private MapSqlParameterSource generateBuyerParamsMap(Buyer buyer){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", buyer.getLogin());
        params.addValue("password", buyer.getPassword());
        params.addValue("name", buyer.getName());
        params.addValue("surname", buyer.getSurname());
        params.addValue("birthDate", new Date(buyer.getBirthDate().getTime()));
        params.addValue("phone", buyer.getPhone());
        params.addValue("email", buyer.getEmail());
        params.addValue("postal_code", buyer.getPostal_code());
        params.addValue("city", buyer.getCity());
        params.addValue("address", buyer.getAddress());
        return params;
    }

    @Override
    public Buyer update(Buyer buyer) {
        final String updateQuery = "UPDATE buyer SET login = :login, password = :password, name = :name, surname = :surname," +
                "birth_date = :birthDate, phone = :phone, email = :email, postal_code = :postal_code, city = :city, " +
                "address = :address WHERE id = " + buyer.getId();

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = generateBuyerParamsMap(buyer);

        namedParameterJdbcTemplate.update(updateQuery, params, keyHolder, new String[]{"id"});

        long createdBuyerId = Objects.requireNonNull(keyHolder.getKey().longValue());
        return findOne(createdBuyerId);
    }

    @Override
    public void delete(Long id) {
        final String deleteQuery = "DELETE FROM buyer WHERE id = ?";
        jdbcTemplate.update(deleteQuery, id);
    }

    @Override
    public void batchInsert(List<Buyer> buyers) {
        final String creatQuery = "insert into buyer (login, password, name, surname, birth_date, phone, email, " +
                "postal_code, city, address) values (:login, :password, :name, :surname, :birthDate, :phone, " +
                ":email, :postal_code, :city, :address);";

        List<MapSqlParameterSource> batchParams = new ArrayList<>();

        for (Buyer buyer : buyers){
            batchParams.add(generateBuyerParamsMap(buyer));
        }

        namedParameterJdbcTemplate.batchUpdate(creatQuery, batchParams.toArray(new MapSqlParameterSource[0]));
    }

    @Override
    public List<Buyer> findBuyersByQuery(Integer limit, String query) {
        final String searchQuery = "select * from buyer where name like :query limit :limit";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("query", "%" + query + "%");
        params.addValue("limit", limit);

        return namedParameterJdbcTemplate.query(searchQuery, params, this::getBuyerRowMapper);
    }

    @Override
    public void saveBuyerRoles(Buyer buyer, List<Role> buyerRoles) {
        final String createQuery = "INSERT INTO buyer_roles (role_id, buyer_id) VALUES (:roleId, :buyerId)";

        List<MapSqlParameterSource> batchParams = new ArrayList<>();
        for (Role role : buyerRoles){
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("roleId", role.getId());
            params.addValue("buyerId", buyer.getId());
            batchParams.add(params);
        }

        namedParameterJdbcTemplate.batchUpdate(createQuery, batchParams.toArray(new MapSqlParameterSource[0]));
    }

    @Override
    public Buyer findByLoginAndPassword(String login, String password) {
        final String searchQuery = "select * from buyer where login = :login AND password = :password";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", login);
        params.addValue("password", password);

        return namedParameterJdbcTemplate.queryForObject(searchQuery, params, this::getBuyerRowMapper);
    }

    @Override
    public Buyer findByLogin(String login) {
        final String searchQuery = "select * from buyer where login = :login";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", login);

        return namedParameterJdbcTemplate.queryForObject(searchQuery, params, this::getBuyerRowMapper);
    }
}
