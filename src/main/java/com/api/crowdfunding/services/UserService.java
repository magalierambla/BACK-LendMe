package com.api.crowdfunding.services;

import java.util.List;

import com.api.crowdfunding.model.StatistiquesChartsUsersModel;
import com.api.crowdfunding.model.User;
import com.api.crowdfunding.model.contactModel;
import com.api.crowdfunding.payload.MessageContactRequest;
import com.api.crowdfunding.payload.MessageInterneRequest;
import com.api.crowdfunding.payload.MessageInterneResponse;
import com.api.crowdfunding.payload.UserUpdateProfilRequest;
import com.api.crowdfunding.security.CurrentUser;
import com.api.crowdfunding.security.UserPrincipal;

public interface UserService {
	
	
	 Boolean  createMessageContactForVisitor( MessageContactRequest  _message);
	 
	 List<contactModel> getListMessageContactForVisitor(@CurrentUser UserPrincipal currentUser);
	 
	 List<StatistiquesChartsUsersModel> getListStatistiquesUsers(@CurrentUser UserPrincipal currentUser);
	 
	 User    getInfosUser(@CurrentUser UserPrincipal currentUser, String token_user);
	 
	 Boolean   updateInfosUser(@CurrentUser UserPrincipal currentUser, UserUpdateProfilRequest  _userUpdateProfilRequest);
	 
	 List<User>    getListUsers(@CurrentUser UserPrincipal currentUser);
	 
	 Boolean  createMessageForUser( @CurrentUser UserPrincipal currentUser, MessageInterneRequest  _messageRequest);
	 
	 List<MessageInterneResponse> listMessageInterneEnvoyes(@CurrentUser UserPrincipal currentUser);
	 
	 List<MessageInterneResponse> listMessageInterneRecus(@CurrentUser UserPrincipal currentUser);
	 
	 MessageInterneResponse    getDataMessageInterne(@CurrentUser UserPrincipal currentUser, String token_message);
	 
	 int    countMessageInterneRecusNonLus(@CurrentUser UserPrincipal currentUser);
	 
	 List<MessageInterneResponse> listMessageInterneRecusNonLus(@CurrentUser UserPrincipal currentUser);
}
