package by.eshop.beans;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class ApplicationBeans {


    @Bean
    public NoOpPasswordEncoder noOpPasswordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
//    Эти бины сконфигурированны автоматически с помощью application.yml
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource){
//        return new JdbcTemplate(dataSource);
//    }
//
//    @Bean
//    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource){
//        return new NamedParameterJdbcTemplate(dataSource);
//    }

//    @Bean
//    public DataSource dataSource(DatabaseProperties databaseProperties){
//        HikariDataSource hikariDataSource = new HikariDataSource();
//
//        hikariDataSource.setJdbcUrl(databaseProperties.getUrl());
//        hikariDataSource.setUsername(databaseProperties.getLogin());
//        hikariDataSource.setPassword(databaseProperties.getPassword());
//        hikariDataSource.setDriverClassName(databaseProperties.getDriverName());
//        hikariDataSource.setMaximumPoolSize(10);
//
//        return hikariDataSource;
//    }


}
