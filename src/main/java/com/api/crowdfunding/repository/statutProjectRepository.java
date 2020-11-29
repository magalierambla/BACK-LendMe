package com.api.crowdfunding.repository;

import java.util.Optional;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.api.crowdfunding.model.statutProject;

@Repository
@Table(name = "list_statut_project")
public interface  statutProjectRepository extends JpaRepository<statutProject, Long> {
	

	Optional<statutProject> findByNom(String nomPorte); 
	
	@Query(value = "SELECT * FROM  list_statut_project  WHERE id=?1", nativeQuery = true)
	Optional<statutProject> findById(String id);
	
	@Query(value = "SELECT * FROM  list_statut_project  WHERE token=?1", nativeQuery = true)
	Optional<statutProject> findByToken(String token);

}