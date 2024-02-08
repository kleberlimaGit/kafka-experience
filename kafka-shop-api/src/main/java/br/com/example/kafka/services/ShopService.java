package br.com.example.kafka.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.example.kafka.entities.Shop;
import br.com.example.kafka.entities.dto.ShopDTO;
import br.com.example.kafka.entities.dto.ShopDTORequest;
import br.com.example.kafka.repositories.ShopRepository;

@Service
public class ShopService {

	@Autowired
	private ShopRepository shopRepository;
	
	public List<ShopDTO> listAll(){
		return shopRepository
				.findAll()
				.stream()
				.map(shop -> ShopDTO.convert(shop))
				.collect(Collectors.toList()); 
	}
	
	public ShopDTO insert(ShopDTORequest dto) {
		ShopDTO shopDTO = ShopDTO.createNewShopDTO(dto);
		Shop shop = Shop.convert(shopDTO);
		shop = shopRepository.save(shop);
		return ShopDTO.convert(shop);
	}
}
