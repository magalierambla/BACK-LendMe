package com.api.crowdfunding.payload;

import com.api.crowdfunding.model.linkImageProject;

public class LinkImageProjectResponse {
	
	private String token;
	
	private String link;
	
	public LinkImageProjectResponse() {}
	
	public LinkImageProjectResponse(linkImageProject  _image_projectBdd) {		
		
        this.setToken(_image_projectBdd.getToken());
		
		this.setLink(_image_projectBdd.getLink());
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
