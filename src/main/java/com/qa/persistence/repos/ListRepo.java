package com.qa.persistence.repos;

import org.springframework.stereotype.Repository;
import com.qa.persistence.domain.ListDomain;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ListRepo extends JpaRepository<ListDomain,Long> {
	
}
