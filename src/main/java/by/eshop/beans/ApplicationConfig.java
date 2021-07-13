package by.eshop.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationConfig {

    @Value("${secretKey}")
    private String secretKey;

}
