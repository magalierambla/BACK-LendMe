package com.api.crowdfunding.repository;

import java.util.Optional;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.api.crowdfunding.model.porte_project;

@Repository
@Table(name = "list_porte_project")
public interface  porteProjectRepository extends JpaRepository<porte_project, Long> {
	
	Optional<porte_project> findById(Long id);	

	Optional<porte_project> findByNom(String nomPorte); 	
	
	@Query(value = "SELECT * FROM  list_porte_project  WHERE id=?1", nativeQuery = true)
	Optional<porte_project> findById(String id);
	  

}


