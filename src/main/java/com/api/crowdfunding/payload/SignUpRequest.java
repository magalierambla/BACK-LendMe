package com.api.crowdfunding.payload;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

import com.api.crowdfunding.enumapp.sexUser;
import com.fasterxml.jackson.annotation.JsonFormat;


public class SignUpRequest {
    @NotBlank(message="Le champ nom est obligatoire")
    @Size(max = 40,message="la taille de votre nom doit inferieur à 40 caractères")
    private String name;
    
    @NotBlank(message="Le champ prenom est obligatoire")
    @Size(max = 40,message="la taille de votre prenom doit inferieur à 40 caractères")
    private String prenom;
    
    @Enumerated(EnumType.STRING)  
    private sexUser sex;
    
    @NotNull(message = "Le champ de date de naissance est obligatoire")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Past(message = "Le format de date n'est pas correcte")
    private Date date_naissance; 

    @NotBlank(message="Le champ username est obligatoire")
    @Size(min = 3, max = 15,message="la taille de mot de passe doit être comprise entre 3 et 15 caractères")
    private String username;

    @NotBlank(message="Le champ email est obligatoire")
    @Size(max = 40,message="la taille de votre email  doit être inferieure à 40 caractères")
    @Email(message="Le format de email n'est pas correcte")
    private String email;

    @NotBlank(message="Le champ mot de passe est obligatoire")
    @Size(min = 6, max = 20,message="la taille de mot de passe doit être comprise entre 6 et 20 caractères")
    private String password;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public sexUser getSex() {
		return sex;
	}


	public void setSex(sexUser sex) {
		this.sex = sex;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}
	


}
