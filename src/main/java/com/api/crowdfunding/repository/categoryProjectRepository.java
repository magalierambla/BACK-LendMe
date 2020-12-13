package com.api.crowdfunding.repository;

import java.util.Optional;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.crowdfunding.model.User;
import com.api.crowdfunding.model.category_project;
import com.api.crowdfunding.model.project;

import java.util.List;

@Repository
@Table(name = "list_category_project")
public interface  categoryProjectRepository extends JpaRepository<category_project, Long> {
	
	Optional<category_project> findByToken(String token);
	
	@Query(value = "SELECT * FROM  list_category_project  WHERE id=?1", nativeQuery = true)
	Optional<category_project> findById(String id);

	Optional<category_project> findByNom(String nomCategorie); 
	
	 @Query(value = "SELECT * FROM  list_category_project   ORDER BY nbr_projects  DESC  LIMIT 5", nativeQuery = true)
	 List<category_project> findCustumCategories(); 
	 
	 @Query(value = "SELECT * FROM  list_category_project   WHERE nom=?1", nativeQuery = true)
	 Optional<category_project> findCategorieByNom(String nom_category); 

}
