package com.api.crowdfunding.repository;

import java.util.Optional;

import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.crowdfunding.model.adressReseauxSociauxProject;
import com.api.crowdfunding.model.linkImageProject;
import com.api.crowdfunding.model.project;

import java.util.List;

@Repository
@Table(name = "list_link_images_project")
public interface  linkImagesProjectRepository extends JpaRepository<linkImageProject, Long> {
	

	Optional<linkImageProject> findByLink(String linkImage);
	
	Optional<linkImageProject> findByToken(String token_link_image); 
	
	List<linkImageProject>  findBy_project(project  _project); 
	
	@Transactional
    @Modifying
    @Query(value = "DELETE FROM  list_link_images_project    WHERE  id=?1", nativeQuery = true)
    void  deleteLinkImageProject(Long id);

}

