package com.api.crowdfunding.repository;

import java.util.Optional;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.crowdfunding.model.*;



import java.util.List;

@Repository
@Table(name = "fonds_investisseurs_paypal")
public interface  fondInvestorRepository extends JpaRepository<fondInvestorModel, Long> {
	
	
	
	@Query(value = "SELECT  *  FROM  fonds_investisseurs_paypal,investisseurs_project  WHERE  fonds_investisseurs_paypal.token_investisseur_project = investisseurs_project.token AND   investisseurs_project.token_project=?1 ", nativeQuery = true)
    List<fondInvestorModel> findAllFondsInvestByTokenProject(String tokenProject);
    
    @Query(value = "SELECT  *  FROM  fonds_investisseurs_paypal  WHERE     token_investisseur_project=?1   ", nativeQuery = true)
    List<fondInvestorModel> findAllFondsInvestByInvestisseur(String tokenInvest);
	
	// List<fondInvestorModel>  findBy_user(User  _user); 
	
	@Query(value = "SELECT  *  FROM  fonds_investisseurs_paypal  WHERE  token=?1 ", nativeQuery = true)
    Optional<fondInvestorModel> checkExistFondProjectByToken(String token);

}
