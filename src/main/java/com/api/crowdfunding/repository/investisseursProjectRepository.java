package com.api.crowdfunding.repository;

import java.util.Optional;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.crowdfunding.model.*;



import java.util.List;

@Repository
@Table(name = "investisseurs_project")
public interface  investisseursProjectRepository extends JpaRepository<investisseursProjectModel, Long> {

	
	
	List<investisseursProjectModel>  findBy_project(project  _project); 
	
	
	@Query(value = "SELECT  *  FROM  investisseurs_project   WHERE  token_project=?1  AND  token=?2 ", nativeQuery = true)
	Optional<investisseursProjectModel> check_invest_project_by_token(String token_project, String token_damande);
	
	@Query(value = "SELECT  *  FROM  investisseurs_project   WHERE  token_project=?1  AND  token_invest=?2 ", nativeQuery = true)
	Optional<investisseursProjectModel> check_invest_project(String token_project, String token_invest);
	
	@Query(value = "SELECT  *  FROM  investisseurs_project  WHERE   token_invest=?1", nativeQuery = true)
    List<investisseursProjectModel> findAllContribProjectByToken(String tokenUser);
    
    @Query(value = "SELECT  *  FROM  investisseurs_project  WHERE   token_project=?1 AND statut_demande ='VALIDE' ", nativeQuery = true)
    List<investisseursProjectModel> findAllInvestiteursProjectByProject(String tokenProject);


}
