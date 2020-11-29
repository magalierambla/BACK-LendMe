package com.api.crowdfunding.payload;

import javax.persistence.Column;

public class NewProjectRequest {
	
	
	private String titre; 
    
	   
    private String description;
    
   
    private String photos;  
   
    public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getPhotos() {
		return photos;
	}


	public void setPhotos(String photos) {
		this.photos = photos;
	}



}
