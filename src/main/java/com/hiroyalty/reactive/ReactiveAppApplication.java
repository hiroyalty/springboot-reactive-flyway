package com.hiroyalty.reactive;

import com.github.javafaker.Faker;
import com.hiroyalty.reactive.customer.Customer;
import com.hiroyalty.reactive.customer.CustomerRequest;
import com.hiroyalty.reactive.customer.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReactiveAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CustomerService customerService) {
		return args -> {
			for (int i = 0; i < 100; i++) {
				Faker faker = new Faker();
				// note you have to subscribe to the save method to persist it in the database.
				CustomerRequest request = new CustomerRequest();
				request.setFirstname(faker.name().firstName());
				request.setLastname(faker.name().lastName());
				request.setAge(faker.number().numberBetween(22, 45));

				customerService.save(request).subscribe();
			}
		};
	}

}
