package com.api.crowdfunding.payload;

import com.api.crowdfunding.model.commentProject;

public class CommentProjectResponse {
	
	private String token;
	
	private String bodyComment;
	
    private String dateCreated;
    
    private Long timestamp;
    
    private UserResponse  user;
    
    public CommentProjectResponse(commentProject  _comment_projectBdd,UserResponse _user) {	
	  
		
		this.setBodyComment(_comment_projectBdd.getBodyComment());
		
		this.setDateCreated(_comment_projectBdd.getDateCreated());
		
		this.setToken(_comment_projectBdd.getToken());
		
		this.setTimestamp(_comment_projectBdd.getTimestamp());
		
		this.setUser(_user);    	
    	
    	
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBodyComment() {
		return bodyComment;
	}

	public void setBodyComment(String bodyComment) {
		this.bodyComment = bodyComment;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	} 

}
