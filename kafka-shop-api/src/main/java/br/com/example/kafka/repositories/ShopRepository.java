package br.com.example.kafka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.kafka.entities.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long>{

}
