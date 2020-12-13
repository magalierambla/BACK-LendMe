package com.api.crowdfunding.payload;

import javax.persistence.Column;

import com.api.crowdfunding.model.adressReseauxSociauxProject;

public class AdressReseauxSociauxProjectResponse {
	
	 
	    private String token;

	   
	    private String keyMedia;
	    
	  
	    private String valueMedia;
	    
	   
	    private String linkProject;
	    
	    public AdressReseauxSociauxProjectResponse() {}
	    
	    public AdressReseauxSociauxProjectResponse(adressReseauxSociauxProject  _adressReseauxSociauxBdd) {
	    	
	    	this.setKeyMedia(_adressReseauxSociauxBdd.getKeyMedia());
			
	    	this.setLinkProject(_adressReseauxSociauxBdd.getLinkProject());
			
	    	this.setToken(_adressReseauxSociauxBdd.getToken());
			
	    	this.setValueMedia(_adressReseauxSociauxBdd.getValueMedia());
	    	
	    	
	    }


		public String getToken() {
			return token;
		}


		public void setToken(String token) {
			this.token = token;
		}


		public String getKeyMedia() {
			return keyMedia;
		}


		public void setKeyMedia(String keyMedia) {
			this.keyMedia = keyMedia;
		}


		public String getValueMedia() {
			return valueMedia;
		}


		public void setValueMedia(String valueMedia) {
			this.valueMedia = valueMedia;
		}


		public String getLinkProject() {
			return linkProject;
		}


		public void setLinkProject(String linkProject) {
			this.linkProject = linkProject;
		}

}
