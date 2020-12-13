package com.api.crowdfunding.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "messagerie_interne")  
@EntityListeners(AuditingEntityListener.class)
public class  messageModel implements Serializable{ 	
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(nullable = false)
    private String token;

    @Column(columnDefinition="TEXT",nullable = false)   
    private String body_message;
    
    @Column(nullable = false)
    private String date_created;
    
    @Column(nullable = false)
    private Long timestamp;     
    
    
    @Column(nullable = false)
    private String _user_exp;      
    
 
    @Column(nullable = false)  
    private String _user_dest;  
    
	
    @Column(nullable = true)
	 private Long timestampConsultation;
	 
    @Column(nullable = true)
	 private String dateConsultation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBodyMessage() {
		return body_message;
	}

	public void setBodyMessage(String bodyMessage) {
		this.body_message = bodyMessage;
	}

	public String getDateCreated() {
		return date_created;
	}

	public void setDateCreated(String dateCreated) {
		this.date_created = dateCreated;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String get_userExp() {
		return _user_exp;
	}

	public void set_userExp(String _userExp) {
		this._user_exp = _userExp;
	}

	public String get_userDest() {
		return _user_dest;
	}

	public void set_userDest(String _userDest) {
		this._user_dest = _userDest;
	}

	public Long getTimestampConsultation() {
		return timestampConsultation;
	}

	public void setTimestampConsultation(Long timestampConsultation) {
		this.timestampConsultation = timestampConsultation;
	}

	public String getDateConsultation() {
		return dateConsultation;
	}

	public void setDateConsultation(String dateConsultation) {
		this.dateConsultation = dateConsultation;
	}

	
  

}
