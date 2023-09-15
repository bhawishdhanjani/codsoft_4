package com.codsoft.codsofttask2;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
	
	
	@Bean
	public CommandLineRunner commandLineRunner(RestTemplate restTemplate) throws Exception{
		return args ->{
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter Currency Symbol you want to convert. eg PKR for Pakistan Rupe");
			String baseCurrency = scanner.nextLine();
			baseCurrency.trim().toUpperCase();
			System.out.println("Enter Currency Symbol in which you want to convert . eg PKR for Pakistan Rupe");
			String  targetCurrency = scanner.nextLine();
			targetCurrency.trim().toUpperCase();
			System.out.println("Enter amount of you want to convert");
			double amount = scanner.nextDouble();
			CurrencyRate currencyRate = restTemplate.getForObject("http://data.fixer.io/api/latest?access_key=38036180a0459b18e4e4672237c3a55f&symbols="+baseCurrency+","+targetCurrency+",EUR", CurrencyRate.class);
			double rate = currencyRate.getRates().get(targetCurrency)/currencyRate.getRates().get(baseCurrency);
			double convertedAmount = rate*amount;
			
			System.out.println("The "+amount+" "+baseCurrency+" to "+targetCurrency+" is "+convertedAmount);
			
			
			
		};
	}
	
	
	

}
