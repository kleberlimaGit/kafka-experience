package br.com.example.kafka.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.example.kafka.entities.dto.ShopDTO;
import br.com.example.kafka.entities.enums.Status;

@Entity
public class Shop implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String identifier;

	private Status status;

	@Column(name = "date_shop")
	private LocalDateTime dateShop;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "shop")
	private List<ShopItem> items = new ArrayList<>();

	public static Shop convert(ShopDTO shopDTO) {
		Shop shop = new Shop();
		shop.setIdentifier(shopDTO.getIdentifier());
		shop.setDateShop(shopDTO.getDateShop());
		shop.setStatus(shopDTO.getStatus());
		shopDTO.getItems().forEach(x -> shop.getItems().add(ShopItem.convert(x, shop)));
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

	public List<ShopItem> getItems() {
		return items;
	}
}
