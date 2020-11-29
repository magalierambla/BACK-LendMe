package com.api.crowdfunding.repository;

import java.util.Optional;

import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.crowdfunding.model.adressReseauxSociauxProject;
import com.api.crowdfunding.model.project;
import com.api.crowdfunding.model.User;

import java.util.List;

@Repository
@Table(name = "adress_reseaux_sociaux_project")
public interface  adressreseauxSocialRepository extends JpaRepository<adressReseauxSociauxProject, Long> {
	

	Optional<adressReseauxSociauxProject> findById(String id); 
	
	Optional<adressReseauxSociauxProject> findByKeyMedia(String linkSocial); 
	
	Optional<adressReseauxSociauxProject> findByToken(String token_adresss); 
	
	List<adressReseauxSociauxProject>  findBy_project(project  _project); 
	
	@Transactional
    @Modifying
    @Query(value = "DELETE FROM  adress_reseaux_sociaux_project    WHERE  id=?1", nativeQuery = true)
    void  deleteAdressReseauxSociauxProject(Long id);

}

