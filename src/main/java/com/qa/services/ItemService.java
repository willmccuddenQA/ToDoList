package com.qa.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.persistence.domain.ItemDomain;
import com.qa.persistence.dtos.ItemDTO;
import com.qa.persistence.repos.ItemRepo;

@Service
public class ItemService {

	private ItemRepo itemRepo;
	private ModelMapper mapper;

	ItemService(ItemRepo itemRepo, ModelMapper mapper) {
		super();
		this.itemRepo = itemRepo;
		this.mapper = mapper;
	}

	private ItemDTO mapToDTO(ItemDomain item) {
		return this.mapper.map(item, ItemDTO.class);
	}

	public ItemDTO addItem(ItemDomain item) {
		ItemDomain saved = this.itemRepo.save(item);
		return this.mapToDTO(saved);
	}

	public List<ItemDTO> getAllItems() {
    	return this.itemRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public ItemDTO updateItem(Long id, ItemDomain item) {

    	Optional<ItemDomain> existingOptional = this.itemRepo.findById(id);
        ItemDomain existing = existingOptional.get();

        existing.setDescription(item.getDescription());
        existing.setCompleteBy(item.getCompleteBy());
        existing.setCompleteStatus(item.getCompleteStatus());

        ItemDomain updated =  this.itemRepo.save(existing);
        return this.mapToDTO(updated);
	}

	public boolean removeItem(Long id) {
		this.itemRepo.deleteById(id);
		boolean exists = this.itemRepo.existsById(id);
		return !exists;
	}

	public ItemDTO getOneItem(Long id) {
		return this.mapToDTO(itemRepo.findById(id).orElseThrow());
	}
}
