package by.eshop;

import by.eshop.beans.ApplicationBeans;
import by.eshop.beans.SecurityConfig;
import by.eshop.beans.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "by.eshop")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
@EnableSwagger2
@Import({
        ApplicationBeans.class,
        SwaggerConfig.class,
        SecurityConfig.class})
public class SpringBootStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }
}
