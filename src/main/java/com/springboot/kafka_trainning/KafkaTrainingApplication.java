package com.springboot.kafka_trainning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class KafkaTrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaTrainingApplication.class, args);
	}

}
