package com.api.crowdfunding.payload;

public class CommentProjectResponse {
	
	private String token;
	
	private String bodyComment;
	
    private String dateCreated;
    
    private Long timestamp;
    
    private UserResponse  user;

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
