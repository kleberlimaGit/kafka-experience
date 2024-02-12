package br.com.example.kafka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.example.kafka.entities.Product;
import br.com.example.kafka.entities.dto.ShopDTO;
import br.com.example.kafka.entities.dto.ShopItemDTO;
import br.com.example.kafka.entities.enums.Status;
import br.com.example.kafka.repositories.ProductRepository;

@Service
public class ReceiveKafkaMessage {
	private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";
	private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private KafkaTemplate<String, ShopDTO> kafkaTemplate;
	
	Logger log = LoggerFactory.getLogger(ReceiveKafkaMessage.class);

	@KafkaListener(topics = SHOP_TOPIC_NAME, groupId = "group")
	public void listenShopTopic(ShopDTO shopDTO) {
		try {
			log.info("Compra recebida no tópico: {}.", shopDTO.getIdentifier());
			boolean success = true;
			for (ShopItemDTO item : shopDTO.getItems()) {
				Product product = productRepository.findByIdentifier(item.getProductIdentifier());
				if (!isValidShop(item, product)) {
					shopError(shopDTO);
					success = false;
					break;
				}
			}
			if (success) {
				shopSuccess(shopDTO);
			}
		} catch (Exception e) {
			log.error("Erro no processamento da compra {}", shopDTO.getIdentifier());
		}
	}

	// valida se a compra possui algum erro
	private boolean isValidShop(ShopItemDTO item, Product product) {
		return product != null && product.getAmount() >= item.getAmount();
	}

	// Envia uma mensagem para o Kafka indicando erro na compra
	private void shopError(ShopDTO shopDTO) {
		log.info("Erro no processamento da compra {}.", shopDTO.getIdentifier());
		shopDTO.setStatus(Status.ERROR);
		kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDTO);
	}

	// Envia uma mensagem para o Kafka indicando sucesso na compra
	private void shopSuccess(ShopDTO shopDTO) {
		log.info("Compra {} efetuada com sucesso.", shopDTO.getIdentifier());
		shopDTO.setStatus(Status.SUCCSESS);
		kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDTO);
	}

}
