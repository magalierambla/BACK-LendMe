package com.api.crowdfunding.repository;


import java.util.List;
import java.util.Optional;

import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.crowdfunding.model.favorisProjectUserModel;
import com.api.crowdfunding.model.project;

@Repository
@Table(name = "favoris_project_user")
public interface  favorisProjectUserRepository extends JpaRepository<favorisProjectUserModel, Long> {	
	
	
	
     @Query(value = "SELECT  *  FROM  favoris_project_user   WHERE  token_user=?1 ", nativeQuery = true)
	 List<favorisProjectUserModel>  findAllFavorisProjectByUser(String tokenUser);
	 
	 @Query(value = "SELECT  COUNT(*)  FROM  favoris_project_user   WHERE   token_project=?1  AND token_user=?2", nativeQuery = true)
	 int   countFavorisByProjectByUser(String tokenProject,String tokenUser);
	  
	 
	 @Query(value = "SELECT  *  FROM  favoris_project_user   WHERE  token_user=?1  AND token_project=?2", nativeQuery = true)
	 Optional<favorisProjectUserModel>  findCheckFavorisProject(String tokenUser,String tokenProject);
	 
	 @Transactional
	 @Modifying
	 @Query(value = "DELETE FROM  favoris_project_user    WHERE  token_user=?1  AND token_project=?2", nativeQuery = true)
	 void  deleteFavorisProject(String tokenUser,String tokenProject);
	 
}
