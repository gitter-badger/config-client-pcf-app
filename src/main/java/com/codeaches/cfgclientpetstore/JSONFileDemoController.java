package com.codeaches.cfgclientpetstore;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.spring.cloud.service.config.PlainTextConfigClient;

@RestController
@RefreshScope
public class JSONFileDemoController {

	@Autowired
	private PlainTextConfigClient configClient;

	@GetMapping("/petDetails")
	public String petDetails() throws IOException {

		try (InputStream input = configClient.getConfigFile("petDetails.json").getInputStream()) {
			return StreamUtils.copyToString(input, Charset.defaultCharset());
		}
	}
}
