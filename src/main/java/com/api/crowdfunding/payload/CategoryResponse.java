package com.api.crowdfunding.payload;

import javax.persistence.Column;

import org.hibernate.annotations.ColumnDefault;

public class CategoryResponse {
	
    
    private String token;  

    
    private String nom;  
    
 
    private int nbr_projects;


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


	public int getNbr_projects() {
		return nbr_projects;
	}


	public void setNbr_projects(int nbr_projects) {
		this.nbr_projects = nbr_projects;
	}  
    
    

}
