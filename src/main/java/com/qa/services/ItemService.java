package com.qa.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.persistence.domain.ItemDomain;
import com.qa.persistence.domain.ListDomain;
import com.qa.persistence.dtos.ItemDTO;
import com.qa.persistence.repos.ItemRepo;
import com.qa.persistence.repos.ListRepo;

@Service
public class ItemService {

	private ItemRepo itemRepo;
	private ModelMapper mapper;
	private ListRepo listRepo;

	ItemService(ItemRepo itemRepo, ModelMapper mapper, ListRepo listRepo) {
		super();
		this.itemRepo = itemRepo;
		this.mapper = mapper;
		this.listRepo = listRepo;
	}

	private ItemDTO mapToDTO(ItemDomain item) {
		return this.mapper.map(item, ItemDTO.class);
	}

	public ItemDomain addItem(ItemDomain item) {
		return this.itemRepo.save(item);
		
//		Optional<ListDomain> listOptional = this.listRepo.findById(item.getMyListId());
//		ListDomain list = listOptional.get();
//		ItemDomain itemWithList = item;
//		itemWithList.setList(list);
//		return this.itemRepo.save(itemWithList);
		
		// ItemDomain saved = this.itemRepo.save(item);
		// return this.mapToDTO(saved);
	}

	public List<ItemDomain> getAllItems() {
		return this.itemRepo.findAll().stream().collect(Collectors.toList());
//    	return this.itemRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public ItemDomain updateItem(Long id, ItemDomain item) {
		Optional<ItemDomain> existingOptional = this.itemRepo.findById(id);
		ItemDomain existing = existingOptional.get();

		existing.setDescription(item.getDescription());
		existing.setCompleteBy(item.getCompleteBy());
		existing.setCompleteStatus(item.getCompleteStatus());
		existing.setList(item.getList());

		return this.itemRepo.save(existing);

//    	Optional<ItemDomain> existingOptional = this.itemRepo.findById(id);
//        ItemDomain existing = existingOptional.get();
//
//        existing.setDescription(item.getDescription());
//        existing.setCompleteBy(item.getCompleteBy());
//        existing.setCompleteStatus(item.getCompleteStatus());
//
//        ItemDomain updated =  this.itemRepo.save(existing);
//        return this.mapToDTO(updated);
	}

	public boolean removeItem(Long id) {
		this.itemRepo.deleteById(id);
		boolean exists = this.itemRepo.existsById(id);
		return !exists;
	}

	public ItemDomain getOneItem(Long id) {
		return this.itemRepo.findById(id).orElseThrow();
//		return this.mapToDTO(itemRepo.findById(id).orElseThrow());
	}
}
