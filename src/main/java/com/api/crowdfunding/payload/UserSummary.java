package com.api.crowdfunding.payload;

import java.util.Date;

import com.api.crowdfunding.enumapp.sexUser;

public class UserSummary {   
    private String username;
    private String name;
    private String prenom;
    private sexUser sex;  
    private String photoUser;
    private Date date_naissance;
    private String token;    
    private int nbr_projects;
    private String email;

    public UserSummary(String username, String name, String prenom,sexUser sex,String photoUser,Date date_naissance, String token,int nbr_projects, String email) {        
        this.username = username;
        this.name = name;
        this.prenom = prenom;
        this.sex= sex;
        this.photoUser = photoUser;
        this.date_naissance = date_naissance;
        this.token = token;
        this.nbr_projects = nbr_projects;
        this.email = email;
    }

   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    
    public sexUser getSex() {
		return sex;
	}

	public void setSex(sexUser sex) {
		this.sex = sex;
	}
	
	public String getPhotoUser() {
		return photoUser;
	}


	public void setPhotoUser(String photoUser) {
		this.photoUser = photoUser;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
}
