package com.api.crowdfunding.payload;

import java.util.Date;

import javax.persistence.Temporal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.api.crowdfunding.model.category_project;
import com.api.crowdfunding.model.porte_project;
import com.fasterxml.jackson.annotation.JsonFormat;


public class ProjetRequest {
	
	@NotBlank(message="Le champ nom est obligatoire")
	private String nom;    
    
	@NotBlank(message="Le champ description est obligatoire")
	@Size(min = 6,message="la taille de description doit être superieur à 6 caractères")
	private String description;   
  

	@Min(value = 1, message = "Le montant minimun est de 1 euros")
	private Long montant_minimun;  	    
 
 

    private Date date_limite_collecte;    
    
    @NotBlank(message="Le champ contre partie de projet est obligatoire")
    private String contrePartieProject;	  

    @NotBlank(message="Le champ affiche de projet est obligatoire")
    private String afficheProject;    
  
   
    @NotBlank(message="Le champ categorie de projet est obligatoire")
    private String token_category;      
    
   
    private Long  id_porte_project ;

    

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getMontant_minimun() {
		return montant_minimun;
	}

	public void setMontant_minimun(Long montant_minimun) {
		this.montant_minimun = montant_minimun;
	}

	public Date getDate_limite_collecte() {
		return date_limite_collecte;
	}

	public void setDate_limite_collecte(Date date_limite_collecte) {
		this.date_limite_collecte = date_limite_collecte;
	}

	public String getContrePartieProject() {
		return contrePartieProject;
	}

	public void setContrePartieProject(String contrePartieProject) {
		this.contrePartieProject = contrePartieProject;
	}

	public String getAfficheProject() {
		return afficheProject;
	}

	public void setAfficheProject(String afficheProject) {
		this.afficheProject = afficheProject;
	}

	public String getToken_category() {
		return token_category;
	}

	public void setToken_category(String token_category) {
		this.token_category = token_category;
	}

	public Long getId_porte_project() {
		return id_porte_project;
	}

	public void setId_porte_project(Long id_porte_project) {
		this.id_porte_project = id_porte_project;
	}




}
