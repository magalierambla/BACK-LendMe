package com.api.crowdfunding.security;

import com.api.crowdfunding.enumapp.sexUser;
import com.api.crowdfunding.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
    private Long id;

    private String name;
    
    private String prenom;
    
    private sexUser sex; 
    
    private String photoUser;
    
    private String token;
    
    private int nbr_projects;

    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;
    
    private Date date_naissance;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String name,String prenom,sexUser sex,String photoUser,Date date_naissance,String token,int nbr_projects, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.prenom = prenom;  
        this.sex = sex;
        this.photoUser = photoUser;
        this.date_naissance = date_naissance;
        this.token = token;
        this.nbr_projects = nbr_projects;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                user.getId(),
                user.getName(),
                user.getPrenom(),
                user.getSex(),
                user.getPhotoUser(),
                user.getDate_naissance(),
                user.getToken(),
                user.getNbr_projects(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    
    public int getNbrProjects() {
        return nbr_projects;
    }
    
    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

	public sexUser getSex() {
		
		return sex;
	}

	public String getPhotoUser() {
		
		return photoUser;
	}
	
	public Date getDateNaissance() {
        return date_naissance;
    }
}
