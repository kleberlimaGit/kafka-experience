package br.com.example.kafka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.example.kafka.entities.Shop;
import br.com.example.kafka.entities.dto.ShopDTO;
import br.com.example.kafka.repositories.ShopRepository;

@Service
public class ReceiveKafkaMessage {

	@Autowired
	private ShopRepository shopRepository;

	private final Logger log = LoggerFactory.getLogger(ReceiveKafkaMessage.class);

	private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

	@KafkaListener(topics = SHOP_TOPIC_EVENT_NAME, groupId = "group")
	public void listenShopEvents(ShopDTO shopDTO) {
		try {
			log.info("Status da compra recebida no tópico: {}.", shopDTO.getIdentifier());
			Shop shop = shopRepository.findByIdentifier(shopDTO.getIdentifier());
			shop.setStatus(shopDTO.getStatus());
			shopRepository.save(shop);
		} catch (Exception e) {
			log.error("Erro no processamento da compra {}", shopDTO.getIdentifier());
		}
	}
}
