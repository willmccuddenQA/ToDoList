package com.qa.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.persistence.domain.ListDomain;
import com.qa.persistence.dtos.ListDTO;
import com.qa.persistence.repos.ListRepo;

@Service
public class ListService {

	private ListRepo listRepo;
	private ModelMapper mapper;
	
	public ListService(ListRepo listRepo, ModelMapper mapper) {
		super();
		this.listRepo = listRepo;
		this.mapper = mapper;
	}
	
	private ListDTO mapToDTO(ListDomain list) {
		return this.mapper.map(list, ListDTO.class);
	}
	
	public ListDTO addList(ListDomain list) {
		ListDomain saved = this.listRepo.save(list);
		return mapToDTO(saved);
	}

	public List<ListDTO> getAllLists() {
		return this.listRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public ListDTO updateList(Long id, ListDomain list) {
		Optional<ListDomain> existingOptional = this.listRepo.findById(id);
		ListDomain existing = existingOptional.get();

		existing.setTitle(list.getTitle());

		ListDomain updated = this.listRepo.save(existing);
		return this.mapToDTO(updated);
	}

	public boolean removeList(Long id) {
		this.listRepo.deleteById(id);
		boolean exists = this.listRepo.existsById(id);
		return !exists;
	}

	public ListDTO getOneList(Long id) {
		return this.mapToDTO(listRepo.findById(id).orElseThrow());
	}
	
	
	
	
}
