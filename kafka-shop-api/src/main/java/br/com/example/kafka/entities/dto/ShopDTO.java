package br.com.example.kafka.entities.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.example.kafka.entities.Shop;
import br.com.example.kafka.entities.enums.Status;

public class ShopDTO {
	private Long id;
	private String identifier;
	private Status status;
	private LocalDateTime dateShop;
	private List<ShopItemDTO> items = new ArrayList<>();

	public static ShopDTO convert(Shop shop) {
		ShopDTO shopDTO = new ShopDTO();
		shopDTO.setId(shop.getId());
		shopDTO.setIdentifier(shop.getIdentifier());
		shopDTO.setDateShop(shop.getDateShop());
		shopDTO.setStatus(shop.getStatus());
		shop.getItems().forEach(x -> shopDTO.getItems().add(ShopItemDTO.convert(x,shop.getId())));
		return shopDTO;
	}
	
	public static ShopDTO createNewShopDTO(ShopDTORequest dto) {
		ShopDTO shopDTO = new ShopDTO();
		shopDTO.setIdentifier(UUID.randomUUID().toString());
		shopDTO.setDateShop(LocalDateTime.now());
		shopDTO.setStatus(Status.PENDING);
		dto.getItems().forEach(x -> shopDTO.getItems().add(x));
		return shopDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getDateShop() {
		return dateShop;
	}

	public void setDateShop(LocalDateTime dateShop) {
		this.dateShop = dateShop;
	}

	public List<ShopItemDTO> getItems() {
		return items;
	}

}
