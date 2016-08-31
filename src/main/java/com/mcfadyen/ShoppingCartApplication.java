package com.mcfadyen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.mcfadyen.service.ProductService;
/**
 * 
 * Classe responsável por iniciar o serviço
 *
 * @since 30 de ago de 2016 
 * @author Guilherme de Araujo Carvalho <guilhermecarvalho@finchsolucoes.com.br>
 *
 */
@SpringBootApplication
@ComponentScan("com.mcfadyen")
public class ShoppingCartApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ShoppingCartApplication.class, args);

		ProductService productService = applicationContext.getBean(ProductService.class);
		productService.saveInitialBatch();
	}
}
