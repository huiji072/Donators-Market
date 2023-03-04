package com.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);

//		while(true) {
//			try {
//				String url = System.getenv("donator-url");
//				System.out.println(url);
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				throw new RuntimeException(e);
//			}
//
//		}

	}



}
