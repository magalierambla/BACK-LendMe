package com.api.crowdfunding.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.api.crowdfunding.security.CurrentUser;
import com.api.crowdfunding.security.UserPrincipal;
import com.api.crowdfunding.services.ProjectService;

import com.api.crowdfunding.enumapp.likeDislikeProject;
import com.api.crowdfunding.enumapp.sexUser;
import com.api.crowdfunding.exception.BadRequestException;
import com.api.crowdfunding.exception.ResourceNotFoundException;
import com.api.crowdfunding.model.QuestionAidesProjectModel;
import com.api.crowdfunding.model.StatistiquesChartsDislikeProjectModel;
import com.api.crowdfunding.model.StatistiquesChartsHeartsProjectModel;
import com.api.crowdfunding.model.StatistiquesChartsLikeProjectModel;
import com.api.crowdfunding.model.StatistiquesChartsVueProjectModel;
import com.api.crowdfunding.model.User;
import com.api.crowdfunding.model.adressReseauxSociauxProject;
import com.api.crowdfunding.model.category_project;
import com.api.crowdfunding.model.commentProject;
import com.api.crowdfunding.model.favorisProjectUserModel;
import com.api.crowdfunding.model.fondInvestorModel;
import com.api.crowdfunding.model.heartProjectModel;
import com.api.crowdfunding.model.investisseursProjectModel;
import com.api.crowdfunding.model.likeDislikeProjectModel;
import com.api.crowdfunding.model.linkImageProject;
import com.api.crowdfunding.model.newsProjectModel;
import com.api.crowdfunding.model.porte_project;
import com.api.crowdfunding.model.project;
import com.api.crowdfunding.model.statutProject;
import com.api.crowdfunding.model.vueProjectModel;
import com.api.crowdfunding.payload.AddFondProjectRequest;
import com.api.crowdfunding.payload.AdressReseauxSociauxProjectRequest;
import com.api.crowdfunding.payload.AdressReseauxSociauxProjectResponse;
import com.api.crowdfunding.payload.ApiResponse;
import com.api.crowdfunding.payload.CommentProjectResponse;
import com.api.crowdfunding.payload.CommentRequest;
import com.api.crowdfunding.payload.CommissionProjectRequest;
import com.api.crowdfunding.payload.DemandeInvestisRequest;
import com.api.crowdfunding.payload.LikeDislikeRequest;
import com.api.crowdfunding.payload.LinkImageProjectResponse;
import com.api.crowdfunding.payload.LinkImageRequest;
import com.api.crowdfunding.payload.NewProjectRequest;
import com.api.crowdfunding.payload.ProjetRequest;
import com.api.crowdfunding.payload.QuestionAideRequest;
import com.api.crowdfunding.payload.TypeStatistiqeRequest;
import com.api.crowdfunding.payload.VueProjectRequest;
import com.api.crowdfunding.repository.categoryProjectRepository;
import com.api.crowdfunding.repository.porteProjectRepository;

@RestController
@RequestMapping("/api/projects")
public class ProjectsController {

	@Autowired
	ProjectService _projectService;
	
	@Autowired
	porteProjectRepository _porteProjectRepository;

	@Autowired
	categoryProjectRepository _categorieProjectRepository;

	private static final Logger logger = LoggerFactory.getLogger(ProjectsController.class);

	@PostMapping(value = "users/all_projects_by_current_user")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<project>> getAllProjectsByCurrentUser(@CurrentUser UserPrincipal currentUser) {

		List<project> _projects = _projectService.getAllProjectsByCurrentUser(currentUser);

		if (_projects != null) {

			return ResponseEntity.ok(_projects);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(value = "users/my_projects_by_current_user")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<project>> getMyProjects(@CurrentUser UserPrincipal currentUser) {

		List<project> _projects = _projectService.getMyProjectsByCurrentUser(currentUser);

		if (_projects != null) {

			return ResponseEntity.ok(_projects);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	@PostMapping(value = "users/{token_user}/all_projects")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	@ResponseBody
	public ResponseEntity<List<project>> getAllProjectsByUser(@CurrentUser UserPrincipal currentUser, @PathVariable String token_user) {

		List<project> _projects = _projectService.getAllProjectsByUser(currentUser,token_user);

		if (_projects != null) {

			return ResponseEntity.ok(_projects);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(value = "users/{token_user}/all_contrib_projects")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	@ResponseBody
	public ResponseEntity<List<project>> getAllContribProjectsByUser(@CurrentUser UserPrincipal currentUser, @PathVariable String token_user) {

		List<project> _projects = _projectService.getAllContribProjectsByUser(currentUser,token_user);

		if (_projects != null) {

			return ResponseEntity.ok(_projects);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(value = "users/my_projects_by_current_user/{token_project}")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<project> getInfosMyProjectByCurrentUser(@CurrentUser UserPrincipal currentUser,
			@PathVariable String token_project) {

		project _project = _projectService.getInfosMyProjectByCurrentUser(currentUser, token_project);

		if (_project != null) {

			return ResponseEntity.ok(_project);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(value = "users/all_projects/{token_project}")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<project> getInfosProjectByOtherUser(@CurrentUser UserPrincipal currentUser,
			@PathVariable String token_project) {

		project _project = _projectService.getInfosProjectByOtherUser(currentUser, token_project);

		if (_project != null) {

			return ResponseEntity.ok(_project);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(value = "users/create_project")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<project> createProject(@CurrentUser UserPrincipal currentUser,@Valid @RequestBody ProjetRequest newProject,Errors errors) {
		
		List<ApiResponse> listErrors = new ArrayList<ApiResponse>();
		
		ResponseEntity<project> response = null;    
		
		Date aujourdhui = new Date();
		
	   /*if (!newProject.getDate_limite_collecte().toString().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
	    	 
		      System.out.println("Format date est invalide ");
	    	 
	          }else {
		 
		 System.out.println("Format date est valide ");
	    }*/
		
		
	
		
		
     if (errors.hasErrors()) {
    		
			System.out.println("Errors count : " + errors.getErrorCount());
			
			for (ObjectError objectError : errors.getAllErrors()) {
				
				System.out.println("objectError.getDefaultMessage() : " + objectError.getDefaultMessage());
				
				System.out.println("objectError.getObjectName() : " + objectError.getObjectName());
			}
			
			
			
			if(errors.hasFieldErrors("contrePartieProject")) {
				
				ObjectError objectError = errors.getFieldError("contrePartieProject");
				
				listErrors.add(new ApiResponse(false, objectError.getDefaultMessage(),"contrePartieProject"));		
			}			
			
			
			if(errors.hasFieldErrors("montant_minimun")) {
				
				ObjectError objectError = errors.getFieldError("montant_minimun");
				
				listErrors.add(new ApiResponse(false, objectError.getDefaultMessage(),"montant_minimun"));	
				
				
			}
			
			if(errors.hasFieldErrors("nom")) {
				
				 ObjectError objectError = errors.getFieldError("nom");
				 
				 listErrors.add(new ApiResponse(false, objectError.getDefaultMessage(),"nom"));				
				
			}
			
			if(errors.hasFieldErrors("description")) {
				
				ObjectError objectError = errors.getFieldError("description");
				
				listErrors.add(new ApiResponse(false, objectError.getDefaultMessage(),"description"));		
				
				
			}
			
			if(errors.hasFieldErrors("date_limite_collecte")) {
				
				 ObjectError objectError = errors.getFieldError("date_limite_collecte");
				 
				 listErrors.add(new ApiResponse(false, objectError.getDefaultMessage(),"date_limite_collecte"));		
				
			}		
			
			
     }
     
     /***************************************************************/
     
     if( newProject.getDate_limite_collecte().compareTo(aujourdhui) <= 0) {
    	 
    	 listErrors.add(new ApiResponse(false, "Date limite de collecte doit etre superieur a la date actuel","date_limite_collecte"));		
    	 
     }
     
     /***************************************************************/
     
     Optional<category_project> categoryProjectReq = _categorieProjectRepository.findByToken(newProject.getToken_category());
     
     if(!categoryProjectReq.isPresent()) {
    	 
    	 listErrors.add(new ApiResponse(false, "Categorie de projet  doit etre renseigné","category_project"));		
     }
     
     
     Optional<porte_project> porteProjectReq = _porteProjectRepository.findById(newProject.getId_porte_project());
     
     if(!porteProjectReq.isPresent()) {
    	 
    	 listErrors.add(new ApiResponse(false, "porte de projet  doit etre renseigné","porte_project"));		
     } 
     
     
     /***************************************************************/
     
     
     
     if(listErrors.size()>0) {
    	 
    	 
    	 return new ResponseEntity(listErrors,HttpStatus.BAD_REQUEST); //  code 400
    	 
     }  
		
		
	try {
		
		    project _project = _projectService.createProject(currentUser, newProject);

		    if (_project != null) {

			     return ResponseEntity.ok(_project);

		    } else {

			     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		
		}catch(BadRequestException e) {
			
			
			  logger.error("System Error:",e.getMessage());		 

		      response = new ResponseEntity<project>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
		return response;
		
		
		

		

	}

	@PostMapping(value = "users/{token_project}/update")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<project> updateProject(@CurrentUser UserPrincipal currentUser,@PathVariable String token_project, @Valid @RequestBody ProjetRequest updateProject,Errors errors) {

        List<ApiResponse> listErrors = new ArrayList<ApiResponse>();
        
        ResponseEntity<project> response = null;      
		
		Date aujourdhui = new Date();
		
	     if (errors.hasErrors()) {
	    		
				System.out.println("Errors count : " + errors.getErrorCount());
				
				for (ObjectError objectError : errors.getAllErrors()) {
					
					System.out.println("objectError.getDefaultMessage() : " + objectError.getDefaultMessage());
					
					System.out.println("objectError.getObjectName() : " + objectError.getObjectName());
				}
				
				
				
				if(errors.hasFieldErrors("contrePartieProject")) {
					
					ObjectError objectError = errors.getFieldError("contrePartieProject");
					
					listErrors.add(new ApiResponse(false, objectError.getDefaultMessage(),"contrePartieProject"));		
				}			
				
				
				if(errors.hasFieldErrors("montant_minimun")) {
					
					ObjectError objectError = errors.getFieldError("montant_minimun");
					
					listErrors.add(new ApiResponse(false, objectError.getDefaultMessage(),"montant_minimun"));	
					
					
				}
				
				if(errors.hasFieldErrors("nom")) {
					
					 ObjectError objectError = errors.getFieldError("nom");
					 
					 listErrors.add(new ApiResponse(false, objectError.getDefaultMessage(),"nom"));				
					
				}
				
				if(errors.hasFieldErrors("description")) {
					
					ObjectError objectError = errors.getFieldError("description");
					
					listErrors.add(new ApiResponse(false, objectError.getDefaultMessage(),"description"));		
					
					
				}
				
				if(errors.hasFieldErrors("date_limite_collecte")) {
					
					 ObjectError objectError = errors.getFieldError("date_limite_collecte");
					 
					 listErrors.add(new ApiResponse(false, objectError.getDefaultMessage(),"date_limite_collecte"));		
					
				}		
				
				
	     }
	     
	     /***************************************************************/
	     
	     
	     if(null==token_project || token_project.isEmpty()) {
	    	 
	    	 
	    	 listErrors.add(new ApiResponse(false, "Le token de projet n'est pas renseigné","token_projet")); 	 
	    	 
	     }
	     
	     if( updateProject.getDate_limite_collecte().compareTo(aujourdhui) <= 0) {
	    	 
	    	 listErrors.add(new ApiResponse(false, "Date limite de collecte doit etre superieur a la date actuel","date_limite_collecte"));		
	    	 
	     }	     
	    
	     
	     Optional<category_project> categoryProjectReq = _categorieProjectRepository.findByToken(updateProject.getToken_category());
	     
	     if(!categoryProjectReq.isPresent()) {
	    	 
	    	 listErrors.add(new ApiResponse(false, "Categorie de projet  doit etre renseigné","category_project"));		
	     }
	     
	     
	     Optional<porte_project> porteProjectReq = _porteProjectRepository.findById(updateProject.getId_porte_project());
	     
	     if(!porteProjectReq.isPresent()) {
	    	 
	    	 listErrors.add(new ApiResponse(false, "porte de projet  doit etre renseigné","porte_project"));		
	     } 
	     
	     
	     /***************************************************************/
	     
	     
	     
	     if(listErrors.size()>0) {
	    	 
	    	 
	    	 return new ResponseEntity(listErrors,HttpStatus.BAD_REQUEST); //  code 400
	    	 
	     }
		
		
			
			
			try {
			
				project _project = _projectService.updateProject(currentUser, token_project, updateProject);

			    if (_project != null) {

				     return ResponseEntity.ok(_project);

			    } else {

				     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			    }
			
			}catch(BadRequestException e) {
				
				
				  logger.error("System Error:",e.getMessage());		 

			      response = new ResponseEntity<project>(HttpStatus.INTERNAL_SERVER_ERROR);	
			}
			
			return response;

	}

	@PostMapping(value = "manager/{token_project}/update_statut_project/{token_statut}")
	@PreAuthorize("hasRole('MANAGER')")
	@ResponseBody
	public ResponseEntity<project> updateProjectByManagerProject(@CurrentUser UserPrincipal currentUser,@PathVariable String token_project, @PathVariable String  token_statut) {
		
		System.out.println("controller-manager/{token_project}/update_statut_project/{token_statut}");

		project _project = _projectService.updateProjectByManagerProject(currentUser, token_project, token_statut);

		if (_project != null) {

			return ResponseEntity.ok(_project);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(value = "manager/{token_project}/check_commission_project")
	@PreAuthorize("hasRole('MANAGER')")
	@ResponseBody
	public ResponseEntity<?> checkCommissionProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project) {

		Boolean checkCommssion = _projectService.checkCommissionProject(currentUser,token_project);

		if (checkCommssion ) {

			return ResponseEntity.ok(new ApiResponse(true, "check_commission_project Successfully"));

		} else {

			return ResponseEntity.ok(new ApiResponse(false, "check_commission_project echec"));
		}

	}
	
	@PostMapping(value = "manager/{token_project}/list_commission_project/create")
	@PreAuthorize("hasRole('MANAGER')")
	@ResponseBody
	public ResponseEntity<?> createCommissionProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project, @Valid @RequestBody  CommissionProjectRequest   _commissionProjectRequest) {
		
		System.out.println("manager/{token_project}/list_commission_project/create");

		Boolean checkCommssion = _projectService.addCommissionProject(currentUser,token_project,_commissionProjectRequest);

		if (checkCommssion ) {

			return ResponseEntity.ok(new ApiResponse(true, "add_commission_project Successfully"));

		} else {

			return ResponseEntity.ok(new ApiResponse(false, "add_commission_project echec"));
		}

	}

	/*******************************************************************************************/
	
	
	@PostMapping(value = "users/{token_project}/adresses_sociaux")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<AdressReseauxSociauxProjectResponse>> getlistAdressSociauxByProject(@CurrentUser UserPrincipal currentUser,@PathVariable String token_project) {	
		

		List<AdressReseauxSociauxProjectResponse> _listAdressReseauxSociauxProject = _projectService.getlistAdressSociauxByProject(token_project);

		if (_listAdressReseauxSociauxProject != null) {

			return ResponseEntity.ok(_listAdressReseauxSociauxProject);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	

	@PostMapping(value = "users/{token_project}/adresses_sociaux/create")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<AdressReseauxSociauxProjectResponse> createAdressReseauSocialProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@Valid @RequestBody AdressReseauxSociauxProjectRequest _linkSocialReq) {

		AdressReseauxSociauxProjectResponse _linkSocialResp = _projectService.createAdressReseauSocialProject(currentUser,token_project,_linkSocialReq);

		if (_linkSocialResp != null) {

			return ResponseEntity.ok(_linkSocialResp);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	@PostMapping(value = "users/{token_project}/adresses_sociaux/{token_address}/delete")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<?> deleteAdressReseauSocialProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@PathVariable String token_address) {

		Boolean checkDelete = _projectService.deleteAdressReseauSocialProject(currentUser,token_project,token_address);

		if (checkDelete) {

			return ResponseEntity.ok(new ApiResponse(true, "Link social delete Successfully"));

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}	
	

	/***********************************************************************************/
	
	@PostMapping(value = "users/{token_project}/links_images")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<LinkImageProjectResponse>> getlistLinksImagesByProject(@CurrentUser UserPrincipal currentUser,@PathVariable String token_project) {	
		

		List<LinkImageProjectResponse> _listLinksImagesProject = _projectService.getlistLinksImagesByProject(token_project);

		if (_listLinksImagesProject != null) {

			return ResponseEntity.ok(_listLinksImagesProject);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	

	@PostMapping(value = "users/{token_project}/links_images/create")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<LinkImageProjectResponse> createLinkImageProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@Valid @RequestBody LinkImageRequest _linkImageReq) {

		LinkImageProjectResponse  _linkImage = _projectService.createLinkImageProject(currentUser,token_project,_linkImageReq);

		if (_linkImage != null) {

			return ResponseEntity.ok(_linkImage);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	@PostMapping(value = "users/{token_project}/links_images/{token_image}/delete")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<?> deleteLinkImageProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@PathVariable String token_image) {

		Boolean checkDelete = _projectService.deleteLinkImageProject(currentUser,token_project,token_image);

		if (checkDelete) {

			return ResponseEntity.ok(new ApiResponse(true, "Link image delete Successfully"));

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}	
	
	
	
	/***********************************************************************************/
	
	@PostMapping(value = "users/{token_project}/comments")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<CommentProjectResponse>> getlistCommentsByProject(@CurrentUser UserPrincipal currentUser,@PathVariable String token_project) {	
		

		List<CommentProjectResponse> _listCommentsProject = _projectService.getlistCommentsByProject(currentUser,token_project);

		if (_listCommentsProject != null) {

			return ResponseEntity.ok(_listCommentsProject);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	

	@PostMapping(value = "users/{token_project}/comments/create")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<CommentProjectResponse> createCommentProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@Valid @RequestBody CommentRequest _comment) {

		CommentProjectResponse _commentRep = _projectService.createCommentProject(currentUser,token_project, _comment);

		if (_commentRep != null) {

			return ResponseEntity.ok(_commentRep);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	/***********************************************************************************/
	
	@PostMapping(value = "users/{token_project}/questions_aides_project")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<QuestionAidesProjectModel>> getListQuestionsAidesByProject(@CurrentUser UserPrincipal currentUser,@PathVariable String token_project) {	
		
		
		// System.out.println("entre-route roles-utilisateurs"+ currentUser.getAuthorities().toArray()[0].toString());
		
		
		List<QuestionAidesProjectModel> _listQuestionsProject = _projectService.getListQuestionsAidesByProject(currentUser,token_project);

		if (_listQuestionsProject != null) {

			return ResponseEntity.ok(_listQuestionsProject);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	

    @PostMapping(value = "users/{token_project}/questions_aides_project/create")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<QuestionAidesProjectModel> createQuestionAideByProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@Valid @RequestBody QuestionAideRequest  _question) {	
		

		QuestionAidesProjectModel _questionDb = _projectService.createQuestionAideByProject(currentUser,token_project, _question);

		if (_questionDb != null) {

			return ResponseEntity.ok(_questionDb);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	/***********************************************************************************/
    
    
	@PostMapping(value = "users/my_projects_invest")
	@PreAuthorize("hasAnyRole('USER')")
	@ResponseBody
	public ResponseEntity<List<investisseursProjectModel>> getlistMyProjectsInvest(@CurrentUser UserPrincipal currentUser) {	
	

		List<investisseursProjectModel> _listInvestisseursProject = _projectService.getlistMyProjectsInvest(currentUser);

		if (_listInvestisseursProject != null) {

			return ResponseEntity.ok(_listInvestisseursProject);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
    
    
	@PostMapping(value = "users/{token_project}/investisseurs_project_for_user")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<investisseursProjectModel>> getlistInvestisseursByProjectForUserProject(@CurrentUser UserPrincipal currentUser,@PathVariable String token_project) {	
	

		List<investisseursProjectModel> _listInvestisseursProject = _projectService.getlistInvestisseursByProjectForUser(currentUser,token_project);

		if (_listInvestisseursProject != null) {

			return ResponseEntity.ok(_listInvestisseursProject);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(value = "users/{token_project}/investisseurs_project_for_porteur")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<investisseursProjectModel>> getlistInvestisseursByProjectForPorteurProject(@CurrentUser UserPrincipal currentUser,@PathVariable String token_project) {	


		List<investisseursProjectModel> _listInvestisseursProject = _projectService.getlistInvestisseursByProjectForPorteurProject(currentUser,token_project);

		if (_listInvestisseursProject != null) {

			return ResponseEntity.ok(_listInvestisseursProject);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
    
    
	@PostMapping(value = "users/{token_project}/investisseurs_project/create_demande_invest")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<investisseursProjectModel> createDemandeInvestProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project) {

		investisseursProjectModel  _demandeInvestProjectBdd = _projectService.createDemandeInvestProject(currentUser,token_project);

		if (_demandeInvestProjectBdd != null) {

			return ResponseEntity.ok(_demandeInvestProjectBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	@PostMapping(value = "users/{token_project}/investisseurs_project/check_invest_project")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<?> checkDemandeInvestProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project) {

		Boolean  checkInvestProject = _projectService.checkInvestProject(currentUser,token_project);

		if (checkInvestProject) {

			return ResponseEntity.ok(new ApiResponse(true, "L'utilisateur est un investiteur confirme ou attente de projet"));

		} else {

			return ResponseEntity.ok(new ApiResponse(false, "L'utilisateur n'est pas  un investiteur de projet"));
		}

	}
	
	@PostMapping(value = "users/{token_project}/investisseurs_project/get_infos_invest_project")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<investisseursProjectModel> getInfosInvestProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project) {

		investisseursProjectModel  _investisseursProject = _projectService.getInfosInvestProject(currentUser,token_project);

		if (_investisseursProject!=null) {

			return ResponseEntity.ok(_investisseursProject);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	@PostMapping(value = "users/{token_project}/investisseurs_project/{token_demande}/update_demande_invest")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<investisseursProjectModel> updateDemandeInvestProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@PathVariable String token_demande,@Valid @RequestBody DemandeInvestisRequest  _demandeInvest) {

		investisseursProjectModel  _demandeInvestProjectBdd = _projectService.updateDemandeInvestProject(currentUser,token_project,token_demande, _demandeInvest);

		if (_demandeInvestProjectBdd != null) {

			return ResponseEntity.ok(_demandeInvestProjectBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(value = "users/{token_project}/investisseurs_project/{token_demande}/fonds_invest_project/create")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<fondInvestorModel> createFondInvestProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@PathVariable String token_demande,@Valid @RequestBody AddFondProjectRequest  _fondInvest) {

		fondInvestorModel  _fondInvestorBdd = _projectService.createFondInvestProject(currentUser,token_project,token_demande,_fondInvest);

		if (_fondInvestorBdd != null) {

			return ResponseEntity.ok(_fondInvestorBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	@PostMapping(value = "users/{token_project}/investisseurs_project/log_fonds_invest_project_for_investisseur")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<fondInvestorModel>> getListFondInvestByProjectForInvestisseurProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project) {	
	

		List<fondInvestorModel>  _listFondsInvestorBdd = _projectService.getListFondInvestByProjectForInvestisseurProject(currentUser,token_project);

		if (_listFondsInvestorBdd != null) {

			return ResponseEntity.ok(_listFondsInvestorBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	} 
	
	@PostMapping(value = "users/{token_project}/investisseurs_project/log_fonds_invest_project_for_porteur_project")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<fondInvestorModel>> getListFondInvestByProjectForPorteurProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project) {	
	

		List<fondInvestorModel>  _listFondsInvestorBdd = _projectService.getListFondInvestByProjectForPorteurProject(currentUser,token_project);

		if (_listFondsInvestorBdd != null) {

			return ResponseEntity.ok(_listFondsInvestorBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	} 
    
    
    
    /***********************************************************************************/
	
	@PostMapping(value = "users/{token_project}/favoris_projects/create")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<favorisProjectUserModel> createFavorisProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project) {

		favorisProjectUserModel  _favorisProjectBdd = _projectService.createFavorisProject(currentUser,token_project);

		if (_favorisProjectBdd != null) {

			return ResponseEntity.ok(_favorisProjectBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(value = "users/{token_project}/favoris_projects/delete")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<?> deleteFavorisProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project) {

		Boolean checkDelete = _projectService.deleteFavorisProject(currentUser,token_project);

		if (checkDelete) {

			return ResponseEntity.ok(new ApiResponse(true, "favoris project  delete Successfully"));

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	@PostMapping(value = "users/{token_project}/favoris_projects/check")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<?> checkFavorisProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project) {

		Boolean checkFavoris = _projectService.checkFavorisProject(currentUser,token_project);

		if (checkFavoris) {

			return ResponseEntity.ok(new ApiResponse(true, "check favoris Successfully"));

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(value = "users/favoris_projects")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<favorisProjectUserModel>> getListFavorisProjects(@CurrentUser UserPrincipal currentUser) {	


		List<favorisProjectUserModel>  _listFavorisProjectsBdd = _projectService.getListFavorisProjects(currentUser);

		if (_listFavorisProjectsBdd != null) {

			return ResponseEntity.ok(_listFavorisProjectsBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	} 
	
	          /*******************************************************************/
	
	
	@PostMapping(value = "users/{token_project}/hearts_project/create")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<heartProjectModel> createHeartsProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project) {

		heartProjectModel  _heartsProjectBdd = _projectService.createHeartProject(currentUser,token_project);

		if (_heartsProjectBdd != null) {

			return ResponseEntity.ok(_heartsProjectBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}	
	
	@PostMapping(value = "users/{token_project}/hearts_project/delete")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<?> deleteHeartsProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project) {	
		

		Boolean checkDelete = _projectService.deleteHeartProject(currentUser,token_project);

		if (checkDelete) {

			return ResponseEntity.ok(new ApiResponse(true, "Heart project  delete Successfully"));

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(value = "users/{token_project}/hearts_project")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<heartProjectModel>> getListHeartsProject(@CurrentUser UserPrincipal currentUser,@PathVariable String token_project) {	


		List<heartProjectModel>  _listHeartsProjectsBdd = _projectService.getListHeartsProject(currentUser,token_project);

		if (_listHeartsProjectsBdd != null) {

			return ResponseEntity.ok(_listHeartsProjectsBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	} 
	
	@PostMapping(value = "users/{token_project}/hearts_project/check")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<?> checkHeartProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project) {

		Boolean checkHeart = _projectService.checkHeartProject(currentUser,token_project);

		if (checkHeart) {

			return ResponseEntity.ok(new ApiResponse(true, "check heart Successfully"));

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	               /**************************************************/
	
	@PostMapping(value = "users/{token_project}/like_dislike_project/create")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<likeDislikeProjectModel> createLike_Dislike_Project(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@Valid @RequestBody LikeDislikeRequest _like_dislike) {
		
		
		
		 if(_like_dislike.getStatut_like_project().equals(new String("LIKE")) || _like_dislike.getStatut_like_project().equals(new String("DISLIKE")) ) {		 
			 
			      System.out.println("_like_dislike.getStatut_like_project() =" + _like_dislike.getStatut_like_project());		    
			 
			      likeDislikeProject  _statut_like;
			      
			      likeDislikeProjectModel  _likeDislikeProjectBdd = new likeDislikeProjectModel ();
			 
			     
			      if(_like_dislike.getStatut_like_project().equals(new String("LIKE"))) {
			    	  
			    	   _statut_like = likeDislikeProject.LIKE;
			    	   
			    	     _likeDislikeProjectBdd = _projectService.createLike_Dislike_Project(currentUser,token_project,_statut_like);
			    	   
			    	   if (_likeDislikeProjectBdd == null) {

			    		   return new ResponseEntity<>(HttpStatus.NOT_FOUND);

						}
			      }
			      
                  if(_like_dislike.getStatut_like_project().equals(new String("DISLIKE"))) {
			    	  
			    	   _statut_like = likeDislikeProject.DISLIKE;
			    	   
			    	     _likeDislikeProjectBdd = _projectService.createLike_Dislike_Project(currentUser,token_project,_statut_like);
			    	   
			    	   if (_likeDislikeProjectBdd == null) {

			    		   return new ResponseEntity<>(HttpStatus.NOT_FOUND);

						} 
			      }		    
   		  
                   
                    return ResponseEntity.ok(_likeDislikeProjectBdd);

				
   	      }else {
   	    	  
   	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   	    	  
   	      }
		


	}
	
	
	@PostMapping(value = "users/{token_project}/like_dislike_project/delete")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<?> deleteLike_Dislike_Project(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project) {		
		

		Boolean checkDelete = _projectService.deleteLikeDislikeProject(currentUser,token_project);

		if (checkDelete) {

			return ResponseEntity.ok(new ApiResponse(true, "Like-dislike project  delete Successfully"));

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(value = "users/{token_project}/like_dislike_project/check")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<likeDislikeProjectModel> checkLikeDislikeProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project) {

		likeDislikeProjectModel _likeDislikeProjectBdd = _projectService.checkLikeDislikeProject(currentUser,token_project);

		if (_likeDislikeProjectBdd!= null) {

			return ResponseEntity.ok(_likeDislikeProjectBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(value = "users/{token_project}/like_dislike_project/update")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<likeDislikeProjectModel> updateLikeDislikeProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@Valid @RequestBody LikeDislikeRequest _like_dislike) {

		 if(_like_dislike.getStatut_like_project().equals(new String("LIKE")) || _like_dislike.getStatut_like_project().equals(new String("DISLIKE")) ) {		 
			 
		      System.out.println("_like_dislike.getStatut_like_project() =" + _like_dislike.getStatut_like_project());		    
		 
		      likeDislikeProject  _statut_like;
		      
		      likeDislikeProjectModel  _likeDislikeProjectBdd = new likeDislikeProjectModel ();
		 
		     
		      if(_like_dislike.getStatut_like_project().equals(new String("LIKE"))) {
		    	  
		    	   _statut_like = likeDislikeProject.LIKE;
		    	   
		    	     _likeDislikeProjectBdd = _projectService.updateLike_Dislike_Project(currentUser,token_project,_statut_like);
		    	   
		    	   if (_likeDislikeProjectBdd == null) {

		    		   return new ResponseEntity<>(HttpStatus.NOT_FOUND);

					}
		      }
		      
             if(_like_dislike.getStatut_like_project().equals(new String("DISLIKE"))) {
		    	  
		    	   _statut_like = likeDislikeProject.DISLIKE;
		    	   
		    	     _likeDislikeProjectBdd = _projectService.updateLike_Dislike_Project(currentUser,token_project,_statut_like);
		    	   
		    	   if (_likeDislikeProjectBdd == null) {

		    		   return new ResponseEntity<>(HttpStatus.NOT_FOUND);

					} 
		      }		    
		  
              
               return ResponseEntity.ok(_likeDislikeProjectBdd);

			
	      }else {
	    	  
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	  
	      }

	}
	
	@PostMapping(value = "users/{token_project}/like_dislike_project")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<likeDislikeProjectModel>> getListLikeDislikeProject(@CurrentUser UserPrincipal currentUser,@PathVariable String token_project) {	
		
		System.out.println("entre-route roles-utilisateurs"+ currentUser.getAuthorities().toArray()[0].toString());


		List<likeDislikeProjectModel>  _list_like_dislikeProjectsBdd = _projectService.getListLikesDislikesProject(currentUser,token_project);

		if (_list_like_dislikeProjectsBdd != null) {

			return ResponseEntity.ok(_list_like_dislikeProjectsBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	} 
	
	               /**************************************************/
	
	@PostMapping(value = "users/{token_project}/vues_project/create_or_update")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<vueProjectModel> createOrUpdateVueProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@Valid @RequestBody VueProjectRequest _vueProject) {

		vueProjectModel  _vueProjectBdd = _projectService.createOrUpdateVueProject(currentUser,token_project,_vueProject);

		if (_vueProjectBdd != null) {

			return ResponseEntity.ok(_vueProjectBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}	
	
	
	                /**************************************************/
	
	
	@PostMapping(value = "users/{token_project}/news_project/create")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<newsProjectModel> createNewsProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@Valid @RequestBody NewProjectRequest _newProject) {

		newsProjectModel  _newProjectBdd = _projectService.createNewProject(currentUser,token_project,_newProject);

		if (_newProjectBdd != null) {

			return ResponseEntity.ok(_newProjectBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}	
	
	@PostMapping(value = "users/{token_project}/news_project/{token_news}/update")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<newsProjectModel> updateNewsProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@PathVariable String token_news,@Valid @RequestBody NewProjectRequest _updateProject) {

		newsProjectModel  _newProjectBdd = _projectService.updateNewProject(currentUser,token_project,token_news,_updateProject);

		if (_newProjectBdd != null) {

			return ResponseEntity.ok(_newProjectBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(value = "users/{token_project}/news_project/{token_news}/delete")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public ResponseEntity<?> deleteNewsProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@PathVariable String token_news) {		
		
		Boolean deleteNews = _projectService.deleteNewProject(currentUser,token_project,token_news);

		if (deleteNews) {

			return ResponseEntity.ok(new ApiResponse(true, "delete news  Successfully"));

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		

	}
	
	@PostMapping(value = "users/{token_project}/news_project")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<newsProjectModel>> getListNewsProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project) {

		List<newsProjectModel>  _listNewsProjectsBdd = _projectService.getListNewsProject(currentUser,token_project);

		if (_listNewsProjectsBdd != null) {

			return ResponseEntity.ok(_listNewsProjectsBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	} 
	
	              /********************************************************************/
	
	@PostMapping(value = "users/{token_project}/stat_hearts_project")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<StatistiquesChartsHeartsProjectModel>> getListStatistiquesHeartsProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@Valid @RequestBody TypeStatistiqeRequest _type_stat) {

		 List<StatistiquesChartsHeartsProjectModel> _listStatistiquesHeartsProjectBdd = _projectService.getListStatistiquesHeartsProject(currentUser,token_project,_type_stat);

		if (_listStatistiquesHeartsProjectBdd != null) {

			return ResponseEntity.ok(_listStatistiquesHeartsProjectBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(value = "users/{token_project}/stat_vues_project")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<StatistiquesChartsVueProjectModel>> getListStatistiquesVuesProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@Valid @RequestBody TypeStatistiqeRequest _type_stat) {

		 List<StatistiquesChartsVueProjectModel> _listStatistiquesVuesProjectBdd = _projectService.getListStatistiquesVuesProject(currentUser,token_project,_type_stat);

		if (_listStatistiquesVuesProjectBdd != null) {

			return ResponseEntity.ok(_listStatistiquesVuesProjectBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(value = "users/{token_project}/stat_likes_project")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<StatistiquesChartsLikeProjectModel>> getListStatistiquesLikesProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@Valid @RequestBody TypeStatistiqeRequest _type_stat) {

		 List<StatistiquesChartsLikeProjectModel> _listStatistiquesLikesProjectBdd = _projectService.getListStatistiquesLikesProject(currentUser,token_project,_type_stat);

		if (_listStatistiquesLikesProjectBdd != null) {

			return ResponseEntity.ok(_listStatistiquesLikesProjectBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(value = "users/{token_project}/stat_dislikes_project")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	@ResponseBody
	public ResponseEntity<List<StatistiquesChartsDislikeProjectModel>> getListStatistiquesDisLikesProject(@CurrentUser UserPrincipal currentUser, @PathVariable String token_project,@Valid @RequestBody TypeStatistiqeRequest _type_stat) {

		 List<StatistiquesChartsDislikeProjectModel> _listStatistiquesDisLikesProjectBdd = _projectService.getListStatistiquesDisLikesProject(currentUser,token_project,_type_stat);

		if (_listStatistiquesDisLikesProjectBdd != null) {

			return ResponseEntity.ok(_listStatistiquesDisLikesProjectBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}	
	
	
	    /********************************************************************/   	
 

}
