package com.codeaches.cfgclientpetstore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RestController
	@RefreshScope
	class DemoController {

		@Value("${favourite.pet:none}")
		public String pet;

		@GetMapping("/favouritePet")
		public String favouritePet() {
			return String.format("My favourite pet is %s", pet);
		}
	}
}
