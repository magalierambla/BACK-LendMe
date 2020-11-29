package com.api.crowdfunding.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.OneToOne;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hearts_project_user")
public class heartProjectModel implements Serializable{
	
    

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   
    
    @Column(nullable = false)
    private String date_created;  
  
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date created_at;
    
    @Column(nullable = false)
    private Long timestamp;
    
    @Column(nullable = false)
    private String token;
    
    @OneToOne
    @JoinColumn(name = "token_project", referencedColumnName = "token", nullable = false)
    private project _project ;
    
    @OneToOne
    @JoinColumn(name = "token_user", referencedColumnName = "token", nullable = false)
    private User _user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate_created() {
		return date_created;
	}

	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public project get_project() {
		return _project;
	}

	public void set_project(project _project) {
		this._project = _project;
	}

	public User get_user() {
		return _user;
	}

	public void set_user(User _user) {
		this._user = _user;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "heartProjectModel [id=" + id + ", date_created=" + date_created + ", created_at=" + created_at
				+ ", timestamp=" + timestamp + ", token=" + token + ", _project=" + _project + ", _user=" + _user
				+ ", getId()=" + getId() + ", getDate_created()=" + getDate_created() + ", getTimestamp()="
				+ getTimestamp() + ", get_project()=" + get_project() + ", get_user()=" + get_user()
				+ ", getCreated_at()=" + getCreated_at() + ", getToken()=" + getToken() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	

	
  

}

