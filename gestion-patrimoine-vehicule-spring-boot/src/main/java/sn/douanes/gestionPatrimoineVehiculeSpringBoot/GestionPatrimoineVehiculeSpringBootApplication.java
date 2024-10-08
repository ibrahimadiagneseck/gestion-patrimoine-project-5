package sn.douanes.gestionPatrimoineVehiculeSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true,  securedEnabled = true,  jsr250Enabled = true)
public class GestionPatrimoineVehiculeSpringBootApplication {

	public static void main(String[] args) {
 		SpringApplication.run(GestionPatrimoineVehiculeSpringBootApplication.class, args);
	}

}
