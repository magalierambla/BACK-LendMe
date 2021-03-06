package com.api.crowdfunding.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.persistence.OneToOne;


@Entity
@Table(name = "list_statut_project")
public class statutProject implements Serializable{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private String nom;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }  
    
    public statutProject() {}
    
    public statutProject(Long id, String nom){
    	
    	this.id  =id;
    	
    	this.nom = nom;      	
    	
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
    
    
    
    
  

}