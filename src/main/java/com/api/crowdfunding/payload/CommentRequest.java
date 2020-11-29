package com.api.crowdfunding.payload;



import com.api.crowdfunding.model.User;
import com.api.crowdfunding.model.project;

public class CommentRequest {	

    private String bodyComment;
    
    
    private String dateCreated;
    
   
    private Long timestamp;
    
 
    private project _project ;
    
 
    private User _user;


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

}
