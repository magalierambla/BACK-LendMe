package com.api.crowdfunding.payload;

import com.api.crowdfunding.model.newsProjectModel;

public class NewsProjectResponse {
	
	private String token;
	
	private String titre; 
	
	private String description;
	
	private String photos;
	
	private Long timestamp;
	
	private String date_created; 
	
	public NewsProjectResponse(newsProjectModel  _news_projectBdd) {
		
        this.setDescription(_news_projectBdd.getDescription());
		
		this.setPhotos(_news_projectBdd.getPhotos());
		
		this.setTitre(_news_projectBdd.getTitre());
		
		this.setToken(_news_projectBdd.getToken());
		
		this.setTimestamp(_news_projectBdd.getTimestamp());
		
		this.setDate_created(_news_projectBdd.getDate_created());
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

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

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getDate_created() {
		return date_created;
	}

	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}

}
