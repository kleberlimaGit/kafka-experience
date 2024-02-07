package br.com.example.kafka.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.example.kafka.entities.dto.ShopDTO;

public class Shop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String identifier;
	
	private String status;
	@Column(name = "date_shop")
	
	private LocalDate dateShop;
	
	@OneToMany(fetch = FetchType.EAGER,
	cascade = CascadeType.ALL,
	mappedBy = "shop")
	private List<ShopItem> items;
	
	public static Shop convert(ShopDTO shopDTO) {
		Shop shop = new Shop();
		shop.setIdentifier(shopDTO.getIdentifier());
		shop.setStatus(shopDTO.getStatus());
		shop.setDateShop(shopDTO.getDateShop());
		shopDTO.getItems().forEach(x -> shop.addItem(ShopItem.convert(x)));
		return shop;
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

	public List<ShopItem> getItems() {
		return items;
	}

	public void addItem(ShopItem item) {
		getItems().add(item);
	}
	
}
