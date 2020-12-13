package com.api.crowdfunding.payload;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.api.crowdfunding.enumapp.sexUser;
import com.api.crowdfunding.model.User;

public class UserResponse {	
 
    private String name;    
 
    private String prenom;       

  	private String photo_user;
	
    private String token;	
	
	private int nbr_projects;

    private String username;
    
    @Enumerated(EnumType.STRING)  
    private sexUser sex;
    
    public UserResponse(){}
    
    public UserResponse(User user){
    	
    	this.name = user.getName();
    	
    	this.prenom = user.getPrenom();
    	
    	this.photo_user = user.getPhotoUser();
    	
    	this.token = user.getToken();
    	
    	this.nbr_projects = user.getNbr_projects();    	
    	
    	this.username = user.getUsername();
    	
    	this.sex = user.getSex();
    }

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPhotoUser() {
		return photo_user;
	}

	public void setPhotoUser(String photoUser) {
		this.photo_user = photo_user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getNbr_projects() {
		return nbr_projects;
	}

	public void setNbr_projects(int nbr_projects) {
		this.nbr_projects = nbr_projects;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public sexUser getSex() {
		return sex;
	}

	public void setSex(sexUser sex) {
		this.sex = sex;
	}
    

}
