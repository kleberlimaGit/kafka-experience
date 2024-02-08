package br.com.example.kafka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.example.kafka.entities.dto.ShopDTO;

@Service
public class KafkaClient {

	@Autowired
	private KafkaTemplate<String, ShopDTO> kafkaTemplate;
	
	private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";
	
	public void sendMessage(ShopDTO msg) {
		kafkaTemplate.send(SHOP_TOPIC_NAME, msg);
	}
}
