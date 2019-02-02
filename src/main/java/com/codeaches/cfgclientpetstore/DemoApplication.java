package com.codeaches.cfgclientpetstore;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.spring.cloud.service.config.PlainTextConfigClient;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RestController
	@RefreshScope
	class PropertiesDemoController {

		@Value("${pet:none}")
		public String pet;

		@GetMapping("/pet")
		public String pet() {
			return String.format("My pet is %s", pet);
		}
	}

	@RestController
	@RefreshScope
	class JSONFileDemoController {

		@Autowired
		private PlainTextConfigClient configClient;

		@GetMapping("/petDetails")
		public String petDetails() throws IOException {

			try (InputStream input = configClient.getConfigFile("pet.json").getInputStream()) {
				return StreamUtils.copyToString(input, Charset.defaultCharset());
			}
		}
	}
}
