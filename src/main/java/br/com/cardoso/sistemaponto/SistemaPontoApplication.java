package br.com.cardoso.sistemaponto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("br.com.cardoso.sistemaponto.persistence.repository")
@EntityScan("br.com.cardoso.sistemaponto.persistence.model")
@SpringBootApplication
public class SistemaPontoApplication {

	public static void main(String[] args) {
		System.setProperty("user.timezone", "GMT-3");
		SpringApplication.run(SistemaPontoApplication.class, args);
	}
}
