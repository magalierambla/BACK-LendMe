package com.api.crowdfunding.repository;

import java.util.Optional;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.crowdfunding.model.*;



import java.util.List;

@Repository
@Table(name = "comments_project")
public interface  commentProjectRepository extends JpaRepository<commentProject, Long> {

	
	
	List<commentProject>  findBy_project(project  _project); 

}
