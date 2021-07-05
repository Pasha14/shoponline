package by.eshop.repository.impl;

import by.eshop.beans.DatabaseProperties;
import by.eshop.domain.Buyer;
import by.eshop.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuyerRepositoryImpl implements BuyerRepository {

    @Autowired
    @Qualifier("databaseProperties")
    private DatabaseProperties properties;

    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String BIRTH_DATE = "birth_date";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    public static final String POSTAL_CODE = "postal_code";
    public static final String CITY = "city";
    public static final String ADDRESS = "address";


    @Override
    public List<Buyer> findAll() {
        final String findAllQuery = "select * from buyer order by id desc";

        List<Buyer> result = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String jdbcURL = properties.getUrl();
        String login = properties.getLogin();
        String password = properties.getPassword();

        try {
            connection = DriverManager.getConnection(jdbcURL, login, password);
            statement = connection.createStatement();
            rs = statement.executeQuery(findAllQuery);

            //Row mapping
            while (rs.next()) {
                Buyer buyer = new Buyer();
                buyer.setId(rs.getLong(ID));
                buyer.setLogin(rs.getString(LOGIN));
                buyer.setPassword(rs.getString(PASSWORD));
                buyer.setName(rs.getString(NAME));
                buyer.setSurname(rs.getString(SURNAME));
                buyer.setBirthDate(rs.getDate(BIRTH_DATE));
                buyer.setPhone(rs.getString(PHONE));
                buyer.setEmail(rs.getString(EMAIL));
                buyer.setPostal_code(rs.getInt(POSTAL_CODE));
                buyer.setCity(rs.getString(CITY));
                buyer.setAddress(rs.getString(ADDRESS));

                result.add(buyer);
            }

            return result;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Buyer findOne(Long id) {
        return null;
    }

    @Override
    public Buyer save(Buyer entity) {
        return null;
    }

    @Override
    public Buyer update(Buyer buyer) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void batchInsert(List<Buyer> buyers) {

    }
}
