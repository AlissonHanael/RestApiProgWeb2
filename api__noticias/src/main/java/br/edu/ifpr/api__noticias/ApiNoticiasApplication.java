package br.edu.ifpr.api__noticias;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import br.edu.ifpr.api__noticias.Services.FileStorageService;
import jakarta.annotation.Resource;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ApiNoticiasApplication implements CommandLineRunner {

	@Resource
	FileStorageService _fileStorageService;

	public static void main(String[] args) {
		SpringApplication.run(ApiNoticiasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		_fileStorageService.init();

	}
}
