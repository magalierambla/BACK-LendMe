package com.api.crowdfunding.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.crowdfunding.model.project;

import com.api.crowdfunding.model.User;





@Repository
@Table(name = "projects")
public interface  projectRepository extends JpaRepository<project, Long> {
	
	
	 List<project>  findBy_user(User _user); 
	 
	 Optional<project>  findByToken(String token); 
	 
	 @Query(value = "SELECT * FROM projects LEFT JOIN adminstrateurs ON projects.token_manager = adminstrateurs.token", nativeQuery = true)
	 List<project> findAllProjects();
	 
	 @Query(value = "SELECT * FROM projects    WHERE statut_project  IN ('2','5','6')  ", nativeQuery = true)
	 List<project> findAllProjectsForVisitor();
	
	 @Query(value = "SELECT  *  FROM  projects  WHERE  token_user=?1", nativeQuery = true)
	 List<project> findAllProjectByToken(String tokenUser);
	 
	 @Query(value = "SELECT  *  FROM  projects  WHERE  token_user=?1 AND token=?2", nativeQuery = true)
	 Optional<project>  findMyProjectByToken(String token_user, String tokenProject);
	 
	 @Query(value = "SELECT  *  FROM  projects  WHERE   token=?1", nativeQuery = true)
	 Optional<project> findProjectByTokenByOtherUser(String tokenProject);
	 
	 @Query(value = "SELECT * FROM  projects  WHERE  statut_project  IN ('2','5','6')  AND token_user != ?1", nativeQuery = true)
	 List<project> findAllProjectsByOtherUser(String tokenUser); 
	 
	 @Query(value = "SELECT * FROM  projects ", nativeQuery = true)
	 List<project> findAllProjectsByManagerOrAdmin(); 
	 
	 @Query(value = "SELECT * FROM  projects  WHERE  statut_project!=1  AND  statut_project!=3  AND statut_project!=4 AND  nom  LIKE   ?1  OR  description  LIKE   ?1 ", nativeQuery = true)
	 List<project> findAllProjectsByLikeTag(@Param("tag") String tag); 
	 
	 List<project> findByNomLike(String tag);
	 
	 @Query(value = "SELECT * FROM  projects  WHERE statut_project!=1  AND  statut_project!=3  AND statut_project!=4   ORDER BY total_vues DESC  LIMIT 4", nativeQuery = true)
	 List<project> findAllProjectsTopConsultation(); 
	 
	 @Query(value = "SELECT * FROM  projects   WHERE statut_project!=1  AND  statut_project!=3  AND statut_project!=4  ORDER BY total_dislike DESC  LIMIT 4", nativeQuery = true)
	 List<project> findAllProjectsTopLikes();
	 
	 
	 @Query(value = "SELECT * FROM  projects   WHERE statut_project!=1  AND  statut_project!=3  AND statut_project!=4  ORDER BY total_hearts DESC  LIMIT 4", nativeQuery = true)
	 List<project> findAllProjectsTopHearts();
	 
	 @Query(value = "SELECT * FROM  projects   WHERE statut_project!=1  AND  statut_project!=3  AND statut_project!=4  ORDER BY RAND()  LIMIT 1", nativeQuery = true)
	 project findProjectRandom(); 
	 
	 
	 @Query(value = "SELECT * FROM  projects   WHERE statut_project!=1  AND  statut_project!=3  AND statut_project!=4  AND  id_category=?1", nativeQuery = true)
	 List<project> findAllProjectsByCategory(Long id_category); 
	 
	 
	 @Query(value = "SELECT * FROM  projects   WHERE statut_project!=1  AND  statut_project!=3  AND statut_project!=4  AND  id_category=?1  ORDER BY RAND()  LIMIT 4", nativeQuery = true)
	 List<project> findCustumProjectsByCategory(Long id_category);
	 
	 
	 // SELECT * FROM  projects  WHERE nom  LIKE   '%1%'  OR  description  LIKE   '%1%'
	 
     // SELECT * FROM `projects` WHERE statut_project!=1  AND  statut_project!=3  AND statut_project!=4  ORDER BY RAND()  LIMIT 1
	 
	 
	  
	
	
	  
	   
	  /*  @Query(value = "SELECT  *  FROM  users  WHERE  login=?1 ", nativeQuery = true)
	   user checkExistMailUser(String login);
	   
	   @Query(value = "SELECT  *  FROM  users  WHERE  token=?1 ", nativeQuery = true)
	   Optional<user> checkExistUserByToken(String token);*/

}
