package com.codeaches.configclientpcfapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class PropertiesDemoController {

	@Value("${pet}")
	public String pet;

	@GetMapping("/pet")
	public String pet() {
		return String.format("My pet is %s", pet);
	}
}