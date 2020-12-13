package com.api.crowdfunding.services;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.crowdfunding.controller.UserController;
import com.api.crowdfunding.functionsUtils.methodesUtils;
import com.api.crowdfunding.model.StatistiquesChartsUsersModel;
import com.api.crowdfunding.model.User;
import com.api.crowdfunding.model.contactModel;
import com.api.crowdfunding.model.messageModel;
import com.api.crowdfunding.payload.MessageContactRequest;
import com.api.crowdfunding.payload.MessageInterneRequest;
import com.api.crowdfunding.payload.MessageInterneResponse;
import com.api.crowdfunding.payload.UserResponse;
import com.api.crowdfunding.payload.UserUpdateProfilRequest;
import com.api.crowdfunding.repository.UserRepository;
import com.api.crowdfunding.repository.contactVisitorRepository;
import com.api.crowdfunding.repository.messageRepository;
import com.api.crowdfunding.security.CurrentUser;
import com.api.crowdfunding.security.UserPrincipal;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository _userRepository;
	
	@Autowired
	contactVisitorRepository  _contactVisitorRepository;
	
	@Autowired
	messageRepository  _messageRepository;
	
    @Autowired
    PasswordEncoder passwordEncoder;
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	

	
	private MessageInterneResponse getMessageResponse(String tokenUserCurrent,messageModel _message) {	
		
		
		MessageInterneResponse  _messageRep  = new MessageInterneResponse();
	    
	    String tokenUserExp = _message.get_userExp();
	    		
	    String tokenUserDest = 	_message.get_userDest();
	    
	    boolean isEqualExp = (tokenUserCurrent.equals(tokenUserExp));
	    
	    boolean isEqualDest = (tokenUserCurrent.equals(tokenUserDest));
	    
	    Optional<User> userCurrentOpt = _userRepository.checkExistUserByToken(tokenUserCurrent);
	    
		_messageRep.setToken(_message.getToken());
		    
		_messageRep.setBody_message(_message.getBodyMessage());
		    
		_messageRep.setDate_created(_message.getDateCreated());			    
		    
		_messageRep.setDateConsultation(_message.getDateConsultation());
		    
		_messageRep.setTimestamp(_message.getTimestamp());

		if (isEqualDest) {         	
	    	
	    	Optional<User>  userExpOpt = _userRepository.checkExistUserByToken(tokenUserExp);    	
	    
	    	
	    	if(userExpOpt.isPresent()) { 		
	    		
	    		UserResponse  _userDestResp = new UserResponse(userCurrentOpt.get());   		
	    		
	    		User  userExpBdd = userExpOpt.get();
	    		
	    		UserResponse  _userExpResp = new UserResponse(userExpBdd);
	    		
	    		_messageRep.set_userDest(_userDestResp);
	    		
	    		_messageRep.set_userExp(_userExpResp);    		
	    		
	    		
	    	}else {    		
	    		
	    		
	    		return null;
	    	}  
			
		}
		
       if (isEqualExp) {		
 	       
	    	
	    	Optional<User>  userDestOpt = _userRepository.checkExistUserByToken(tokenUserDest);
	    	
	    	if(userDestOpt.isPresent()) {
	    		
	    	    UserResponse  _userExpResp = new UserResponse(userCurrentOpt.get());   		
	    		
	    		User  userDestBdd = userDestOpt.get();
	    		
	    		UserResponse  _userDestResp = new UserResponse(userDestBdd);
	    		
	    		_messageRep.set_userDest(_userDestResp);
	    		
	    		_messageRep.set_userExp(_userExpResp);    		
	    		
	    		
	    	}else {    		
	    		
	    		
	    		return null;
	    	} 
			
		}   
		
		
		return _messageRep;	
	}
	
	/****************************************************************************************/
	
	@Override
	public Boolean  createMessageContactForVisitor( MessageContactRequest  _message) {
		
		Instant instant = Instant.now();

		long _timestamp = instant.toEpochMilli();

		SimpleDateFormat formater = null;

		Date aujourdhui = new Date();

		formater = new SimpleDateFormat("EEEE, d MMM yyyy  'à' hh:mm:ss");

		// System.out.println(formater.format(aujourdhui));
		
		String newToken = methodesUtils.generateAlphanumericStringToken();
		
		contactModel _messageBdd = new contactModel();
		
		_messageBdd.setToken(newToken);
		
		_messageBdd.setDate_created(formater.format(aujourdhui));
		
		_messageBdd.setDescription(_message.getDescription());
		
		_messageBdd.setEmail(_message.getEmail());
		
		_messageBdd.setSujet(_message.getSujet());		
		
		_messageBdd.setTimestamp_created(_timestamp);
		
		
		_contactVisitorRepository.save(_messageBdd);
		
		
		
		return true;
		
		
	}
	
	@Override
	public List<contactModel>  getListMessageContactForVisitor(@CurrentUser UserPrincipal currentUser) {
		
		List<contactModel>   _list_messages = _contactVisitorRepository.findAll();
		
		return _list_messages;
		
		
	}
	
	@Override
	public List<StatistiquesChartsUsersModel> getListStatistiquesUsers(@CurrentUser UserPrincipal currentUser) {		
		
		
	 	   List<StatistiquesChartsUsersModel> listStatistiquesChartsUsers =   new ArrayList<StatistiquesChartsUsersModel>(); 	 
	 	   
	 	   
	 	   Calendar calendrier;
	 	    
	 	   calendrier = Calendar.getInstance();
	 		
	 	   int yearCurrent = calendrier.get(Calendar.YEAR);	
	 	  
	 	   
	 	   /****************************************************************/
	 	   
	 	   StatistiquesChartsUsersModel _statistiquesChartsUsers = new StatistiquesChartsUsersModel();
	 	   
	 	   int  _nbrUsersYearCurrent =  _userRepository.countNbrUsersForYearCurrent();   	   
	 	   
	 	   _statistiquesChartsUsers.setNbrUsers(_nbrUsersYearCurrent);
	 	   
	 	   String yearCurrentString = String.valueOf(yearCurrent);
	 	   
	 	   _statistiquesChartsUsers.setYear(yearCurrentString);
	 	   
	 	   listStatistiquesChartsUsers.add(_statistiquesChartsUsers);
	 		
	 		
	 	   
	 	   /****************************************************************/
	 	   
	 	   StatistiquesChartsUsersModel _statistiquesChartsUsers1 = new StatistiquesChartsUsersModel();
	 	   
	 	   int yearLast1 = yearCurrent-1;	
	 		
	 	  String  yearLast1String = String.valueOf(yearLast1);
	 	   
	 	  int  _nbrUsersYearLast1 =  _userRepository.countNbrUsersForLast1Year();   	
	 	  
	 	  _statistiquesChartsUsers1.setYear(yearLast1String);
	 	   
	 	  _statistiquesChartsUsers1.setNbrUsers(_nbrUsersYearLast1);   
	 	 
	 	   
	 	   listStatistiquesChartsUsers.add(_statistiquesChartsUsers1);   
	 	   
	 	   
	 	   /****************************************************************/
	 	   
	 	   StatistiquesChartsUsersModel _statistiquesChartsUsers2 = new StatistiquesChartsUsersModel();
	 	  
	 	  int yearLast2 = yearCurrent-2;	
	 		
	 	  String  yearLast2String = String.valueOf(yearLast2);
	 	   
	 	  int  _nbrUsersYearLast2 =  _userRepository.countNbrUsersForLast2Year();   	   
	 	   
	 	  _statistiquesChartsUsers2.setYear(yearLast2String);
	 	   
	 	  _statistiquesChartsUsers2.setNbrUsers(_nbrUsersYearLast2);   
	 	 
	 	   
	 	   listStatistiquesChartsUsers.add(_statistiquesChartsUsers2);
	 	   
	 	   
	 	   /****************************************************************/
	 	   
	 	   StatistiquesChartsUsersModel _statistiquesChartsUsers3 = new StatistiquesChartsUsersModel();
	 	  
	 	   int yearLast3 = yearCurrent-3;	
	 		
	 	   String  yearLast3String = String.valueOf(yearLast3);
	 		   
	 	   int  _nbrUsersYearLast3 =  _userRepository.countNbrUsersForLast3Year();   	   
	 		   
	 	  _statistiquesChartsUsers3.setYear(yearLast3String);
	 		   
	 	  _statistiquesChartsUsers3.setNbrUsers(_nbrUsersYearLast3);   
	 		 
	 		   
	 	   listStatistiquesChartsUsers.add(_statistiquesChartsUsers3);
	 	   
	 	   
	 	   
	 	   /****************************************************************/
	 	   
	 	   StatistiquesChartsUsersModel _statistiquesChartsUsers4 = new StatistiquesChartsUsersModel();
	 	  
	 	   int yearLast4 = yearCurrent-4;	
	 		
	 	   String  yearLast4String = String.valueOf(yearLast4);
	 		   
	 	   int  _nbrUsersYearLast4 =  _userRepository.countNbrUsersForLast4Year();   	   
	 		   
	 	  _statistiquesChartsUsers4.setYear(yearLast4String);
	 		   
	 	  _statistiquesChartsUsers4.setNbrUsers(_nbrUsersYearLast4);   
	 		 
	 		   
	 	   listStatistiquesChartsUsers.add(_statistiquesChartsUsers4);
	 	   
	 	   
	 	   
	 	   /****************************************************************/  
	 	   
	 	   return listStatistiquesChartsUsers;
		
		
	}
	
	
	@Override
    public User    getInfosUser(@CurrentUser UserPrincipal currentUser, String token_user) {	
		
		
		Optional<User>  _userOpt = _userRepository.checkExistUserByToken(token_user);
		
		
		if(_userOpt.isPresent()) {
			
			User _userBdd = _userOpt.get();
			
			return _userBdd;
			
			
		}else {
			
			return null;
		}
		
	}
    
    
	
	@Override
    public Boolean   updateInfosUser(@CurrentUser UserPrincipal currentUser, UserUpdateProfilRequest  _userUpdateProfilRequest) {	
		
		
		Optional<User>  _userOpt = _userRepository.checkExistUserByToken(currentUser.getToken());
		
		
		if(_userOpt.isPresent()) {
			
			User _userBdd = _userOpt.get();
			
			if(!_userUpdateProfilRequest.getUsername().isEmpty()) {
				
				 _userBdd.setUsername(_userUpdateProfilRequest.getUsername());
			}
			
				if(!_userUpdateProfilRequest.getPrenom().isEmpty()) {
				
				_userBdd.setPrenom(_userUpdateProfilRequest.getPrenom());
			}
			
				if(!_userUpdateProfilRequest.getEmail().isEmpty()) {
				
				 _userBdd.setEmail(_userUpdateProfilRequest.getEmail());
				
			}
			
			if(!_userUpdateProfilRequest.getName().isEmpty()) {
				
				_userBdd.setName(_userUpdateProfilRequest.getName());
				
			}
			
	   	   if(!_userUpdateProfilRequest.getPassword().isEmpty()) {
				
				
				 _userBdd.setPassword(passwordEncoder.encode(_userUpdateProfilRequest.getPassword()));
			}
			
			if(!_userUpdateProfilRequest.getPhoto_user().isEmpty()) {
				
				_userBdd.setPhotoUser(_userUpdateProfilRequest.getPhoto_user());
				
			}
			
		  if(!_userUpdateProfilRequest.getSex().toString().isEmpty()) {
				
				 _userBdd.setSex(_userUpdateProfilRequest.getSex());
				
			}	
			
			
			_userRepository.save(_userBdd);
			
			return true;
			
			
		}else {
			
			return false;
		}
		
	}


	@Override
	public List<User>    getListUsers(@CurrentUser UserPrincipal currentUser) {	
		
		List<User> usersBdd = _userRepository.findUsersProject();			
		
		return usersBdd;
		
		
		
	}
	
	/****************************************************************************/
	
   @Override
   public Boolean  createMessageForUser( @CurrentUser UserPrincipal currentUser, MessageInterneRequest  _messageRequest) {
		
		Instant instant = Instant.now();

		long _timestamp = instant.toEpochMilli();

		SimpleDateFormat formater = null;

		Date aujourdhui = new Date();

		formater = new SimpleDateFormat("EEEE, d MMM yyyy  'à' hh:mm:ss");

		// System.out.println(formater.format(aujourdhui));
		
		String newToken = methodesUtils.generateAlphanumericStringToken();
		
		Optional<User>  _userDestOpt = _userRepository.checkExistUserByToken(_messageRequest.get_userDest());
		
		if(_userDestOpt.isPresent()) {
			
			String tokenUserCurrent = currentUser.getToken();
			
			String token1 = new String(tokenUserCurrent);

			String token2 = new String(_messageRequest.get_userDest());

			boolean isEqual = (token1.equals(token2));

			if (!isEqual) {
				
				messageModel  _new_message = new messageModel();
				
				_new_message.setToken(newToken);
				
				_new_message.set_userDest(_messageRequest.get_userDest());
				
				_new_message.set_userExp(tokenUserCurrent);				
				
				_new_message.setBodyMessage(_messageRequest.getBodyMessage());
				
				_new_message.setDateCreated(_messageRequest.getDateCreated());
				
				_new_message.setTimestamp(_timestamp);		
			
				_messageRepository.save(_new_message);
				
				return true;
				
			}else {
				
				return false;
			}
			
			
			
			
		}else {
			
			
			return false;
		}
		
		
		
		
	}

  @Override
  public List<MessageInterneResponse> listMessageInterneEnvoyes(@CurrentUser UserPrincipal currentUser) {		


	    String tokenUserCurrent = currentUser.getToken();			

	    List<messageModel> _messagesBdd = _messageRepository.findAllMessagesEnvoyesByTokenUser(tokenUserCurrent);
	    
	    List<MessageInterneResponse> _messages = new ArrayList<MessageInterneResponse>();

			if (_messagesBdd.size() > 0) {

				for (int index = 0; index < _messagesBdd.size(); index++) {
					
					_messages.add(this.getMessageResponse(tokenUserCurrent,_messagesBdd.get(index))) ;

				}

				return _messages;

			} else {

				// System.out.println("aucun adress-social-no-existe");

				return null;
			}		

	
   }
  
	
  @Override
  public List<MessageInterneResponse> listMessageInterneRecus(@CurrentUser UserPrincipal currentUser) {		


	    String tokenUserCurrent = currentUser.getToken();			

	    List<messageModel> _messagesBdd = _messageRepository.findAllMessagesRecusByTokenUser(tokenUserCurrent);
	    
	    List<MessageInterneResponse> _messages = new ArrayList<MessageInterneResponse>();

			if (_messagesBdd.size() > 0) {

				for (int index = 0; index < _messagesBdd.size(); index++) {
					
					_messages.add(this.getMessageResponse(tokenUserCurrent,_messagesBdd.get(index))) ;

				}

				return _messages;

			} else {

				// System.out.println("aucun adress-social-no-existe");

				return null;
			}		

	
 }
  
  @Override
  public List<MessageInterneResponse> listMessageInterneRecusNonLus(@CurrentUser UserPrincipal currentUser) {		


	    String tokenUserCurrent = currentUser.getToken();	
	    
	    System.out.println("listMessageInterneRecusNonLus");

	    List<messageModel> _messagesBdd = _messageRepository.findAllMessagesNonLusByTokenUser(tokenUserCurrent);
	    
	    List<MessageInterneResponse> _messages = new ArrayList<MessageInterneResponse>();

			if (_messagesBdd.size() > 0) {

				for (int index = 0; index < _messagesBdd.size(); index++) {
					
					_messages.add(this.getMessageResponse(tokenUserCurrent,_messagesBdd.get(index))) ;

				}

				return _messages;

			} else {

				// System.out.println("aucun adress-social-no-existe");

				return null;
			}		

	
 }
  
  
  @Override
  public MessageInterneResponse    getDataMessageInterne(@CurrentUser UserPrincipal currentUser, String token_message) {		


	    String tokenUserCurrent = currentUser.getToken();			

	    Optional<messageModel>  _messagesOpt = _messageRepository.findMessageByToken(token_message);   
	    

		if (_messagesOpt.isPresent()) {
			
			    messageModel  _messageBdd = _messagesOpt.get();	
			    
			    
			    /***************************************************************************/
			    
			    
			    logger.info("_message.getDateConsultation().length() = " + _messageBdd.getDateConsultation());
	    		
			    String tokenUserDest = 	_messageBdd.get_userDest();	
			    
			    boolean isEqualDest = (tokenUserCurrent.equals(tokenUserDest));
			    
			    if (isEqualDest) { 			    	
			    	
					   if(_messageBdd.getDateConsultation() == null ){
						   
						   logger.info("dateConsultation is vide");
					    	
					    	Instant instant = Instant.now();

							long _timestamp = instant.toEpochMilli();

							SimpleDateFormat formater = null;

							Date aujourdhui = new Date();

						    formater = new SimpleDateFormat("EEEE, d MMM yyyy  'à' hh:mm:ss");
							
						    _messageBdd.setDateConsultation(formater.format(aujourdhui));
							
						    _messageBdd.setTimestampConsultation(_timestamp);
							
							_messageRepository.save(_messageBdd);			    	
					    	
					    }	
			    	
			    }		    

			    
			    /***************************************************************************/
			
				
				MessageInterneResponse  _message =  this.getMessageResponse(tokenUserCurrent, _messageBdd);			

				return _message;

			} else {

				// System.out.println("aucun adress-social-no-existe");

				return null;
		}		

	
  }
  
  
  @Override
  public int    countMessageInterneRecusNonLus(@CurrentUser UserPrincipal currentUser) {		


	    String tokenUserCurrent = currentUser.getToken();			

	    int  _nbrmessages = _messageRepository.countMessageNonLusByTokenUser(tokenUserCurrent);   
	    
        return  _nbrmessages;

	
  }
	
	
	
	/***************************************************************************/

}
