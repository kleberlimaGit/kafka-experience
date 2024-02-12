package br.com.example.kafka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.kafka.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Product findByIdentifier(String identifier);
}
