package com.api.crowdfunding.payload;

import java.util.Date;

import com.api.crowdfunding.model.category_project;
import com.api.crowdfunding.model.porte_project;
import com.api.crowdfunding.model.statutProject;

public class ProjetRequest {
	
    private String nom;    
    
    private String description;   
  
    private Long montant_minimun;   
   	    
    private Date date_limite_collecte;    
    
    private String contrePartieProject;	  

    private String afficheProject;    
  
    private category_project categoryProject;  

    private porte_project  _porte_project ;

    

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

	public category_project getCategoryProject() {
		return categoryProject;
	}

	public void setCategoryProject(category_project categoryProject) {
		this.categoryProject = categoryProject;
	}

	public porte_project get_porte_project() {
		return _porte_project;
	}

	public void set_porte_project(porte_project _porte_project) {
		this._porte_project = _porte_project;
	}


}
