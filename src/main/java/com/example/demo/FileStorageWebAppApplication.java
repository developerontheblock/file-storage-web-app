package com.example.demo;

import com.example.demo.properties.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableConfigurationProperties(StorageProperties.class)
public class FileStorageWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileStorageWebAppApplication.class, args);
	}

}
