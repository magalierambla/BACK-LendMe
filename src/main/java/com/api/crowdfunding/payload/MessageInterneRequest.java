package com.api.crowdfunding.payload;



public class MessageInterneRequest {
	
	private String bodyMessage;
	
	private String dateCreated;
	
	private String _userDest;

	public String getBodyMessage() {
		return bodyMessage;
	}

	public void setBodyMessage(String bodyMessage) {
		this.bodyMessage = bodyMessage;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String get_userDest() {
		return _userDest;
	}

	public void set_userDest(String _userDest) {
		this._userDest = _userDest;
	}  
	
	

}
