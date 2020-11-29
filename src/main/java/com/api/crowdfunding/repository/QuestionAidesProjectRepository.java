package com.api.crowdfunding.repository;


import java.util.Optional;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.api.crowdfunding.model.QuestionAidesProjectModel;


import java.util.List;

@Repository
@Table(name = "questions_aides_project")
public interface  QuestionAidesProjectRepository extends JpaRepository<QuestionAidesProjectModel, Long> {
	

	// Optional<adressReseauxSociauxProject> findByKeyMedia(String linkSocial); 
	
	// List<adressReseauxSociauxProject>  findBy_project(project  _project); 
	
	@Query(value = "SELECT  *  FROM  questions_aides_project  WHERE  token_project=?1 ", nativeQuery = true)
    List<QuestionAidesProjectModel> findByProject(String tokenProject);

}

