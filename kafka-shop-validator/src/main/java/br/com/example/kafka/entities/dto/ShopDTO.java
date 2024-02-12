package br.com.example.kafka.entities.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.example.kafka.entities.enums.Status;

public class ShopDTO {
	private String identifier;
	private Status status;
	private LocalDateTime dateShop;
	private List<ShopItemDTO> items = new ArrayList<>();

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
