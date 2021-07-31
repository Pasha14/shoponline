package by.eshop.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("jwtconfig")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtConfig {

    private String secret;
    private Integer expiration;

}
