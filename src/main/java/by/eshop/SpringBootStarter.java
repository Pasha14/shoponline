package by.eshop;

import by.eshop.beans.ApplicationBeans;
import by.eshop.beans.SecurityConfig;
import by.eshop.beans.TaskGoogleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "by.eshop")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
@Import({
        ApplicationBeans.class,
        SecurityConfig.class,
        TaskGoogleConfig.class})
public class SpringBootStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }
}
