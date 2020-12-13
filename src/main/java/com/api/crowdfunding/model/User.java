package com.api.crowdfunding.model;

import com.api.crowdfunding.enumapp.sexUser;
import com.api.crowdfunding.model.audit.DateAudit;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
public class User extends DateAudit {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String name;
    
    @Column(nullable = false)
    @Size(max = 40)
    private String prenom;
    
    @Enumerated(EnumType.STRING)  
    private sexUser sex; 
    
    @Column(nullable = true)
  	private String photoUser;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)          
    private Date date_naissance; 
    
    @Column(nullable = false)
    private String token_confir_register;   

    
    @Column(nullable = false)
    private Long timestamp_expire_token_confirm_register;
   

	@Column(nullable = false)
    private String token;
	
	@ColumnDefault("0")
	private int nbr_projects;
	
	@ColumnDefault("0")
	private int is_active;

    @NotBlank
    @Size(max = 15)
    private String username;

    //@NaturalId // on ne peut pas changer sa valeur
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() { }

    public User(String name,String prenom,sexUser sex,Date date_naissance,String token, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.prenom = prenom;
        this.sex = sex;
        this.date_naissance = date_naissance;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
	
	public String getToken_confir_register() {
		return token_confir_register;
	}

	public void setToken_confir_register(String token_confir_register) {
		this.token_confir_register = token_confir_register;
	}

	public Long getTimestamp_expire_token_confirm_register() {
		return timestamp_expire_token_confirm_register;
	}

	public void setTimestamp_expire_token_confirm_register(Long timestamp_expire_token_confirm_register) {
		this.timestamp_expire_token_confirm_register = timestamp_expire_token_confirm_register;
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

	public int getIs_active() {
		return is_active;
	}

	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date_naissance == null) ? 0 : date_naissance.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + is_active;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + nbr_projects;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((photoUser == null) ? 0 : photoUser.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((timestamp_expire_token_confirm_register == null) ? 0
				: timestamp_expire_token_confirm_register.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((token_confir_register == null) ? 0 : token_confir_register.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (date_naissance == null) {
			if (other.date_naissance != null)
				return false;
		} else if (!date_naissance.equals(other.date_naissance))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (is_active != other.is_active)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nbr_projects != other.nbr_projects)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (photoUser == null) {
			if (other.photoUser != null)
				return false;
		} else if (!photoUser.equals(other.photoUser))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (sex != other.sex)
			return false;
		if (timestamp_expire_token_confirm_register == null) {
			if (other.timestamp_expire_token_confirm_register != null)
				return false;
		} else if (!timestamp_expire_token_confirm_register.equals(other.timestamp_expire_token_confirm_register))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (token_confir_register == null) {
			if (other.token_confir_register != null)
				return false;
		} else if (!token_confir_register.equals(other.token_confir_register))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
}