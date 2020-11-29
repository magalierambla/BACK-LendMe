package com.api.crowdfunding.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;



@Entity
@Table(name = "list_category_project")
public class category_project implements Serializable{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String token;  

    @Column(nullable = false)
    private String nom;  
    
    @ColumnDefault("0")
    private int nbr_projects;    
    
    
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

	public int getNbr_projects() {
		return nbr_projects;
	}

	public void setNbr_projects(int nbr_projects) {
		this.nbr_projects = nbr_projects;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}  
    
  

}
