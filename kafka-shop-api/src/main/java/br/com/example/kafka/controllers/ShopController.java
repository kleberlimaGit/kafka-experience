package br.com.example.kafka.controllers;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.example.kafka.entities.Shop;
import br.com.example.kafka.entities.ShopItem;
import br.com.example.kafka.entities.dto.ShopDTO;
import br.com.example.kafka.entities.dto.ShopDTORequest;
import br.com.example.kafka.repositories.ShopRepository;
import br.com.example.kafka.services.KafkaClient;
import br.com.example.kafka.services.ShopService;

@RestController
@RequestMapping("shop")
public class ShopController {

	@Autowired
	private ShopService shopService;
	
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private KafkaClient kafkaClient;
	
	@GetMapping
	public ResponseEntity<List<ShopDTO>> findAll(){
		List<ShopDTO> list = shopService.listAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<ShopDTO> saveShop(@RequestBody ShopDTORequest dto){
		ShopDTO shopDTO = shopService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(shopDTO.getId()).toUri();
		kafkaClient.sendMessage(shopDTO);
		return ResponseEntity.created(uri).body(shopDTO);
	}
}
