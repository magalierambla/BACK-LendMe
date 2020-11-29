package com.api.crowdfunding.payload;

import javax.persistence.Column;

public class AdressReseauxSociauxProjectResponse {
	
	 
	    private String token;

	   
	    private String keyMedia;
	    
	  
	    private String valueMedia;
	    
	   
	    private String linkProject;


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
