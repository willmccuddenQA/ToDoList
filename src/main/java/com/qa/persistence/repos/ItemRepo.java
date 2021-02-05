package com.qa.persistence.repos;

import org.springframework.stereotype.Repository;
import com.qa.persistence.domain.ItemDomain;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ItemRepo extends JpaRepository<ItemDomain,Long> {

}
