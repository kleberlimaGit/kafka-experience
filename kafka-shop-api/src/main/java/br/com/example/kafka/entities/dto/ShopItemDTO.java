package br.com.example.kafka.entities.dto;

import br.com.example.kafka.entities.ShopItem;

public class ShopItemDTO {

	private String productIdentifier;
	
	private Integer amount;
	
	private Float price;
	
	private Long idShop;
	
	
	public static ShopItemDTO convert(ShopItem shopItem, Long idShop) {
		ShopItemDTO shopItemDTO = new ShopItemDTO();
		shopItemDTO.setProductIdentifier(
		shopItem.getProductIdentifier());
		shopItemDTO.setAmount(shopItem.getAmount());
		shopItemDTO.setPrice(shopItem.getPrice());
		shopItemDTO.setIdShop(idShop);
		return shopItemDTO;
		}
	
	public String getProductIdentifier() {
		return productIdentifier;
	}

	public void setProductIdentifier(String productIdentifier) {
		this.productIdentifier = productIdentifier;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Long getIdShop() {
		return idShop;
	}

	public void setIdShop(Long idShop) {
		this.idShop = idShop;
	}
	
	
}
