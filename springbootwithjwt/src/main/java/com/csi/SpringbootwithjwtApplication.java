package com.csi;

import com.csi.entity.User;
import com.csi.service.UserInfoDetailService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "Product Management APPLICATION", version = "1.0", description = "product service application rest api documentation"),
        servers = @Server(description = "Local Tomcat 10", url = "http://localhost:8080"))
@SecurityScheme(name = "Bearer Auth", description = "Provide JWT Token", scheme = "Bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class SpringbootwithjwtApplication {

    @Autowired
    UserInfoDetailService userInfoDetailService;


    public static void main(String[] args) {

		SpringApplication.run(SpringbootwithjwtApplication.class, args);
    }


    @PostConstruct
    public void save() {

        userInfoDetailService.save(new User(121, "RISHI", "PUNE, INDIA", "RISHI@2025"));

    }
}
