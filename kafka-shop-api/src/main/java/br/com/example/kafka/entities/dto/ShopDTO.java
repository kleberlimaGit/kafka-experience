package br.com.example.kafka.entities.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.example.kafka.entities.ShopItem;

public class ShopDTO {
	private String identifier;
	private String status;
	private LocalDate dateShop;
	private List<ShopItemDTO> items;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDateShop() {
		return dateShop;
	}

	public void setDateShop(LocalDate dateShop) {
		this.dateShop = dateShop;
	}

	public List<ShopItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ShopItemDTO> items) {
		this.items = items;
	}

}
