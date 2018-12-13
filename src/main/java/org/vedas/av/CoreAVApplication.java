package org.vedas.av;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.vedas")
public class CoreAVApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreAVApplication.class, args);
	}
}
