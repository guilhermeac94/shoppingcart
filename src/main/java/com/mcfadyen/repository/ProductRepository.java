package com.mcfadyen.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mcfadyen.domain.Product;

/**
 * 
 * ProductRepository
 *
 * @since 30 de ago de 2016
 * @author Guilherme de Araujo Carvalho <guilhermecarvalho@finchsolucoes.com.br>
 *
 */
public interface ProductRepository extends MongoRepository<Product, String> {

}
