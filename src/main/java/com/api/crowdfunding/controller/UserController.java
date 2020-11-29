package com.api.crowdfunding.controller;

import com.api.crowdfunding.exception.ResourceNotFoundException;
import com.api.crowdfunding.model.StatistiquesChartsUsersModel;
import com.api.crowdfunding.model.User;
import com.api.crowdfunding.model.contactModel;
import com.api.crowdfunding.payload.*;
import com.api.crowdfunding.repository.UserRepository;
import com.api.crowdfunding.security.CurrentUser;
import com.api.crowdfunding.security.UserPrincipal;
import com.api.crowdfunding.services.UserService;
import com.api.crowdfunding.util.AppConstants;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

	@Autowired
	UserService _userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")   
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
    	logger.debug("currentUser.getSex()="+ currentUser.getSex().F);
        UserSummary userSummary = new UserSummary(currentUser.getUsername(), currentUser.getName(),currentUser.getPrenom(),currentUser.getSex(),currentUser.getPhotoUser(),currentUser.getDateNaissance(),currentUser.getToken(),currentUser.getNbrProjects(),currentUser.getEmail());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }
    

	
	
	@PutMapping("/user/update_profil")   
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	public ResponseEntity<?> updateDataCurrentUser(@CurrentUser UserPrincipal currentUser,@Valid @RequestBody UserUpdateProfilRequest  _userUpdateProfilRequest) {
		
		   
		    
		   Boolean checkUpdateProfil =  _userService.updateInfosUser(currentUser, _userUpdateProfilRequest);
		   
		   if (checkUpdateProfil) {

				return ResponseEntity.ok(new ApiResponse(true, "Send update data Profil Successfully"));

			} else {

				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	    	
	       
	}
	
	
	
    @PostMapping(value = "users/{token_user}")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	@ResponseBody
	public ResponseEntity<User> getInfosUser(@CurrentUser UserPrincipal currentUser,@PathVariable String token_user) {
		
		
		ResponseEntity<User> response = null;	 

		 try {		 

		       if(null==token_user || token_user.equals(0L)) {

		            	
		            	 
		            	 response = new ResponseEntity( new ApiResponse(false, "token user not read!"), HttpStatus.BAD_REQUEST);	

		        }else {
		            	 
		             User _user = _userService.getInfosUser(currentUser,token_user);			             
			             
			         if (_user != null) {

			            	 response = new ResponseEntity<User>(_user,HttpStatus.OK);

			     	} else {

			     		response = new ResponseEntity(new ApiResponse(false, "user not found !"), HttpStatus.INTERNAL_SERVER_ERROR);	
			     	}        
		            	 
		        }
		       
		 }catch(Exception e) {		

			  logger.error("System Error:",e.getMessage());		 

		      response = new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);		
		  }
		

		 return response;


	}
	
	
	
	
	@PostMapping(value = "listing_users")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	@ResponseBody
	public ResponseEntity<List<User>> getListUsers(@CurrentUser UserPrincipal currentUser) {

		 List<User> _users = _userService.getListUsers(currentUser);

		if (_users != null) {

			return ResponseEntity.ok(_users);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(value = "stat_users")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	@ResponseBody
	public ResponseEntity<List<StatistiquesChartsUsersModel>> getListStatistiquesUsers(@CurrentUser UserPrincipal currentUser) {

		 List<StatistiquesChartsUsersModel> _listStatistiquesUsers = _userService.getListStatistiquesUsers(currentUser);

		if (_listStatistiquesUsers != null) {

			return ResponseEntity.ok(_listStatistiquesUsers);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	/**************************************************************************************/
	
	
	@PostMapping(value = "listing_messages_contact")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	@ResponseBody
	public ResponseEntity<List<contactModel>> getListMessagesContacts(@CurrentUser UserPrincipal currentUser) {

		List<contactModel> _messages = _userService.getListMessageContactForVisitor(currentUser);

		if (_messages != null) {

			return ResponseEntity.ok(_messages);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	@PostMapping(value = "update_data_message_contact/{token_message}")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	@ResponseBody
	public ResponseEntity<?> updateDataMessageContact(@CurrentUser UserPrincipal currentUser,@PathVariable String token_message) {

		/*Boolean checkUpdateData = _userService.updateDataMessageContact(currentUser,token_message);*/
		
		Boolean checkUpdateData = true;

		if (checkUpdateData) {

			return ResponseEntity.ok(new ApiResponse(true, "Send update data Message Successfully"));

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	
	
	 /********************************************************************/  
	
	
	@PostMapping(value = "messagerie_interne/list_message/create")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<Boolean> createMessage(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody MessageInterneRequest  _messageRequest) {

		Boolean checkInsertData = _userService.createMessageForUser(currentUser,_messageRequest);

		if (checkInsertData != null) {

			return ResponseEntity.ok(checkInsertData);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	/*@PostMapping(value = "messagerie_interne/list_message/messages_envoyes")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<MessageInterneResponse>> listMessageInterneEnvoyes(@CurrentUser UserPrincipal currentUser) {

		List<MessageInterneResponse>  _messages = _userService.listMessageInterneEnvoyes(currentUser);

		if (_messages != null) {

			return ResponseEntity.ok(_messages);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	/*@PostMapping(value = "messagerie_interne/list_message/messages_recus")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<MessageInterneResponse>> listMessageInterneRecus(@CurrentUser UserPrincipal currentUser) {

		List<MessageInterneResponse>  _messages = _userService.listMessageInterneRecus(currentUser);

		if (_messages != null) {

			return ResponseEntity.ok(_messages);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}*/


}
