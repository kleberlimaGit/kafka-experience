package br.com.example.kafka.entities.dto;

import java.util.List;

public class ShopDTORequest {
	private List<ShopItemDTO> items;

	public List<ShopItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ShopItemDTO> items) {
		this.items = items;
	}
	
	
}
