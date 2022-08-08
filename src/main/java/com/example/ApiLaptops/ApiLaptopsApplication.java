package com.example.ApiLaptops;

import com.example.ApiLaptops.entities.Laptop;
import com.example.ApiLaptops.repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ApiLaptopsApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ApiLaptopsApplication.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		// Instanciación ordenadores
		Laptop laptop1 = new Laptop(null, "Dell", "XPS15", 32, 1798.25);
		Laptop laptop2 = new Laptop(null, "Apple", "MacBook Pro 15", 32, 2456.75);
		System.out.println("Número de ordenadores en la BBDD: " + repository.findAll().size());

		// Almacenamiento de ordenadores en la BBDD
		repository.save(laptop1);
		repository.save(laptop2);

		// Recuperar todos los ordenadores
		System.out.println("Número de ordenadores en la BBDD: " + repository.findAll().size());
	}

}
