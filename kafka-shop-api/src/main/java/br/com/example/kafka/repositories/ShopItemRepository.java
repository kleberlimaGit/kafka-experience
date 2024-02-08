package br.com.example.kafka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.kafka.entities.ShopItem;

public interface ShopItemRepository extends JpaRepository<ShopItem, Long>{

}
