package com.api.crowdfunding.payload;



import com.api.crowdfunding.model.User;
import com.api.crowdfunding.model.project;

public class QuestionAideRequest {

  
    private String bodyAide;
    

    private String dateCreated;
    
   
    private Long timestamp;

    private UserRequest _userDest;


	public String getBodyAide() {
		return bodyAide;
	}


	public void setBodyAide(String bodyAide) {
		this.bodyAide = bodyAide;
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


	public UserRequest get_userDest() {
		return _userDest;
	}


	public void set_userDest(UserRequest _userDest) {
		this._userDest = _userDest;
	}

}
