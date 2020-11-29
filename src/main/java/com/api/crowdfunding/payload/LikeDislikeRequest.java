package com.api.crowdfunding.payload;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.api.crowdfunding.enumapp.likeDislikeProject;

public class LikeDislikeRequest {
	
	 
	 private String  statut_like_project;

	public String getStatut_like_project() {
		return statut_like_project;
	}

	public void setStatut_like_project(String statut_like_project) {
		this.statut_like_project = statut_like_project;
	}

}
