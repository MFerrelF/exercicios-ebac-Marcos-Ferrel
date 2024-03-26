package marcos.ferrel.Module40;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import marcos.ferrel.domain.Customer;
import marcos.ferrel.repository.ICustomerRepository;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = "marcos.ferrel.repository")
@EntityScan("marcos.ferrel.*")
@ComponentScan(basePackages = "marcos.ferrel")
public class Module40Application implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(Module40Application.class);
	
	@Autowired
	private ICustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Module40Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("StartApplication...");
		Customer customer = createCustomer();
		repository.save(customer);
	}

	private Customer createCustomer() {
		return Customer.builder()
				.name("SpringBoot Test")
				.cpf(1122334455L)
				.pNumber(99885566L)
				.email("test@springboot.test")
				.address("Test Street")
				.number(10)
				.city("Belo Horizonte")
				.state("MG")
				.build();
	}

}
