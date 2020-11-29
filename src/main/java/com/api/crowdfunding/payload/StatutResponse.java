package com.api.crowdfunding.payload;

import javax.persistence.Column;

public class StatutResponse {
	
 
    private String token;
  
    private String nom;

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
