package com.api.crowdfunding.payload;



import com.api.crowdfunding.model.statutProject;

public class StatutResponse {
	
 
    private String token;
  
    private String nom;
    
    public StatutResponse(statutProject  _statut_projectBdd){
    	
         this.setToken(_statut_projectBdd.getToken());
		
		 this.setNom(_statut_projectBdd.getNom());
    	
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
