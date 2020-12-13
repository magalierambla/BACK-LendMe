package com.api.crowdfunding.payload;



public class MessageInterneResponse {
	
	private String token;
	
	private UserResponse _userDest;  
	 
	private UserResponse _userExp; 
	 
	private String body_message;	    
	   
	private String date_created;
	
	private Long timestamp;   
	
	private String dateConsultation;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserResponse get_userDest() {
		return _userDest;
	}

	public void set_userDest(UserResponse _userDest) {
		this._userDest = _userDest;
	}

	public UserResponse get_userExp() {
		return _userExp;
	}

	public void set_userExp(UserResponse _userExp) {
		this._userExp = _userExp;
	}

	public String getBody_message() {
		return body_message;
	}

	public void setBody_message(String body_message) {
		this.body_message = body_message;
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

	public String getDateConsultation() {
		return dateConsultation;
	}

	public void setDateConsultation(String dateConsultation) {
		this.dateConsultation = dateConsultation;
	}
	
	
	
	

}
