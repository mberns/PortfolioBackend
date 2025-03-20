package com.portfolio.martina_b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(basePackages = "com.portfolio.martina_b.Security.Repository")
@SpringBootApplication
//@EntityScan(basePackages = "com.portfolio.martina_b.entidad")
//@EntityScan(basePackages = "com.portfolio.martina_b.Security.Entity")
@EntityScan(basePackages = {
    "com.portfolio.martina_b.entidad",       // Escanea el paquete de Persona
    "com.portfolio.martina_b.Security.Entity" // Escanea el paquete de Usuario
})
public class MartinaBApplication {

	public static void main(String[] args) {
		SpringApplication.run(MartinaBApplication.class, args);
	}

}
