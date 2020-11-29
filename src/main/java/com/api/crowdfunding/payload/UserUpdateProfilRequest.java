package com.api.crowdfunding.payload;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.api.crowdfunding.enumapp.sexUser;

public class UserUpdateProfilRequest {
	
	
	@Size(max = 40)
	private String name;    
    
	
	@Size(max = 40)
	private String prenom;       

  	private String photo_user;
	
  	
    @Size(max = 40)
    @Email
  	private String email; 
    
    @Enumerated(EnumType.STRING)  
    private sexUser sex;
    
    
    @Size(max = 100)
    private String password;
    
    
    @Size(max = 15)
    private String username;

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

	public String getPhoto_user() {
		return photo_user;
	}

	public void setPhoto_user(String photo_user) {
		this.photo_user = photo_user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public sexUser getSex() {
		return sex;
	}

	public void setSex(sexUser sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
    
    

}
