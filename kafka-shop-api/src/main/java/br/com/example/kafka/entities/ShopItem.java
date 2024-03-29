package br.com.example.kafka.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.example.kafka.entities.dto.ShopItemDTO;

@Entity(name = "shop_item")
public class ShopItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "product_identifier")
	private String productIdentifier;
	
	private Integer amount;
	
	private Float price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop_id")
	private Shop shop;

	public static ShopItem convert(ShopItemDTO shopItemDTO, Shop shop) {
		ShopItem shopItem = new ShopItem();
		shopItem.setProductIdentifier(shopItemDTO.getProductIdentifier());
		shopItem.setAmount(shopItemDTO.getAmount());
		shopItem.setPrice(shopItemDTO.getPrice());
		shopItem.setShop(shop);
		return shopItem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	
}
