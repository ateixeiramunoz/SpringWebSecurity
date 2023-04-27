package com.eoi.springwebsecurity;

import com.eoi.springwebsecurity.filemanagement.services.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringWebSecurityApplication implements CommandLineRunner {

	@Autowired
	FileSystemStorageService fileSystemStorageService;
	public static void main(String[] args) {
		SpringApplication.run(SpringWebSecurityApplication.class, args);
	}

	/**
	 * Callback used to run the bean.
	 *
	 * @param args incoming main method arguments
	 * @throws Exception on error
	 */
	@Override
	public void run(String... args) throws Exception {
		fileSystemStorageService.init();
	}
}
