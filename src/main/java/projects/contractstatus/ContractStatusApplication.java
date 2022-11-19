package projects.contractstatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ContractStatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContractStatusApplication.class, args);
	}

}
