package com.api.crowdfunding.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.crowdfunding.enumapp.likeDislikeProject;
import com.api.crowdfunding.enumapp.statutDemandeInvest;
import com.api.crowdfunding.exception.BadRequestException;
import com.api.crowdfunding.functionsUtils.methodesUtils;
import com.api.crowdfunding.model.QuestionAidesProjectModel;
import com.api.crowdfunding.model.StatistiquesChartsDislikeProjectModel;
import com.api.crowdfunding.model.StatistiquesChartsHeartsProjectModel;
import com.api.crowdfunding.model.StatistiquesChartsLikeProjectModel;
import com.api.crowdfunding.model.StatistiquesChartsVueProjectModel;
import com.api.crowdfunding.model.User;
import com.api.crowdfunding.model.adressReseauxSociauxProject;
import com.api.crowdfunding.model.category_project;
import com.api.crowdfunding.model.commentProject;
import com.api.crowdfunding.model.commissionProjectModel;
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
import com.api.crowdfunding.payload.CategoryResponse;
import com.api.crowdfunding.payload.CommentProjectResponse;
import com.api.crowdfunding.payload.CommentRequest;
import com.api.crowdfunding.payload.CommissionProjectRequest;
import com.api.crowdfunding.payload.DemandeInvestisRequest;
import com.api.crowdfunding.payload.LinkImageProjectResponse;
import com.api.crowdfunding.payload.LinkImageRequest;
import com.api.crowdfunding.payload.NewProjectRequest;
import com.api.crowdfunding.payload.NewsProjectResponse;
import com.api.crowdfunding.payload.ProjectResponseForVisitor;
import com.api.crowdfunding.payload.ProjetRequest;
import com.api.crowdfunding.payload.QuestionAideRequest;
import com.api.crowdfunding.payload.StatutResponse;
import com.api.crowdfunding.payload.TypeStatistiqeRequest;
import com.api.crowdfunding.payload.UserResponse;
import com.api.crowdfunding.payload.VueProjectRequest;
import com.api.crowdfunding.repository.QuestionAidesProjectRepository;
import com.api.crowdfunding.repository.UserRepository;
import com.api.crowdfunding.repository.adressreseauxSocialRepository;
import com.api.crowdfunding.repository.categoryProjectRepository;
import com.api.crowdfunding.repository.commentProjectRepository;
import com.api.crowdfunding.repository.commisionProjectRepository;
import com.api.crowdfunding.repository.favorisProjectUserRepository;
import com.api.crowdfunding.repository.fondInvestorRepository;
import com.api.crowdfunding.repository.heartsProjectRepository;
import com.api.crowdfunding.repository.investisseursProjectRepository;
import com.api.crowdfunding.repository.likeDislikeProjectRepository;
import com.api.crowdfunding.repository.linkImagesProjectRepository;
import com.api.crowdfunding.repository.newsProjectRepository;
import com.api.crowdfunding.repository.porteProjectRepository;
import com.api.crowdfunding.repository.projectRepository;
import com.api.crowdfunding.repository.statutProjectRepository;
import com.api.crowdfunding.repository.vueProjectRepository;
import com.api.crowdfunding.security.CurrentUser;
import com.api.crowdfunding.security.UserPrincipal;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	projectRepository _projectRepository;

	@Autowired
	porteProjectRepository _porteProjectRepository;

	@Autowired
	categoryProjectRepository _categorieProjectRepository;

	@Autowired
	statutProjectRepository _statutProjectRepository;

	@Autowired
	UserRepository _userRepository;

	@Autowired
	adressreseauxSocialRepository _adressreseauxSocialRepository;

	@Autowired
	linkImagesProjectRepository _linkImagesProjectRepository;

	@Autowired
	commentProjectRepository _commentProjectRepository;

	@Autowired
	QuestionAidesProjectRepository _QuestionsAidesProjectRepository;

	@Autowired
	investisseursProjectRepository _investisseursProjectRepository;

	@Autowired
	fondInvestorRepository _fondInvestorRepository;

	@Autowired
	favorisProjectUserRepository _favorisProjectUserRepository;

	@Autowired
	heartsProjectRepository _heartsProjectRepository;

	@Autowired
	likeDislikeProjectRepository _likeDislikeProjectRepository;

	@Autowired
	vueProjectRepository _vueProjectRepository;

	@Autowired
	newsProjectRepository _newsProjectRepository;
	
	@Autowired
	commisionProjectRepository _commisionProjectRepository;	
	
	


	@Override
	public List<project> getAllProjectsByCurrentUser(@CurrentUser UserPrincipal currentUser) {

		String tokenUserCurrent = currentUser.getToken();

		System.out.println("tokenUserCurrent = " + tokenUserCurrent);
		
		List<project> _projects = new ArrayList<project>();
		
		if(currentUser.getAuthorities().toArray()[0].toString() == "ROLE_MANAGER") {
			
			System.out.println("entre-route roles-utilisateurs"+ currentUser.getAuthorities().toArray()[0].toString());
			
			 _projects = _projectRepository.findAllProjectsByManagerOrAdmin();
			 
		}else {
			
			 _projects = _projectRepository.findAllProjectsByOtherUser(tokenUserCurrent);
			
		}	
		

		if (_projects.size() > 0) {

			System.out.println("_projects.size()>0");

			for (int index = 0; index < _projects.size(); index++) {

				if (_projects.get(index).getManager_project() != null) {

					User _manager_project = new User();

					/***********************************************************************/

					_manager_project.setName(_projects.get(index).getManager_project().getName());

					_manager_project.setPrenom(_projects.get(index).getManager_project().getPrenom());

					_manager_project.setPhotoUser(_projects.get(index).getManager_project().getPhotoUser());

					_manager_project.setUsername(_projects.get(index).getManager_project().getUsername());

					_projects.get(index).setManager_project(_manager_project);

					/***********************************************************************/

					/***********************************************************************/

				}

			}

			return _projects;

		} else {

			return null;
		}

	}
	
	
	@Override
	public List<project> getMyProjectsByCurrentUser(@CurrentUser UserPrincipal currentUser) {

		String tokenUserCurrent = currentUser.getToken();

		System.out.println("tokenUserCurrent = " + tokenUserCurrent);

		List<project> _projects = _projectRepository.findAllProjectByToken(tokenUserCurrent);

		if (_projects.size() > 0) {

			System.out.println("_projects.size()>0");

			for (int index = 0; index < _projects.size(); index++) {

				if (_projects.get(index).getManager_project() != null) {

					User _manager_project = new User();

					/***********************************************************************/

					_manager_project.setName(_projects.get(index).getManager_project().getName());

					_manager_project.setPrenom(_projects.get(index).getManager_project().getPrenom());

					_manager_project.setPhotoUser(_projects.get(index).getManager_project().getPhotoUser());

					_manager_project.setUsername(_projects.get(index).getManager_project().getUsername());

					_projects.get(index).setManager_project(_manager_project);

					/***********************************************************************/

					/***********************************************************************/

				}

			}

			return _projects;

		} else {

			return null;
		}

	}
	
	
	@Override
	public List<project> getAllProjectsByUser(@CurrentUser UserPrincipal currentUser, String token_user) {

		String tokenUserCurrent = currentUser.getToken();

		System.out.println("tokenUserCurrent = " + tokenUserCurrent);

		List<project> _projects = _projectRepository.findAllProjectByToken(token_user);

		if (_projects.size() > 0) {

			System.out.println("_projects.size()>0");

			for (int index = 0; index < _projects.size(); index++) {

				if (_projects.get(index).getManager_project() != null) {

					User _manager_project = new User();

					/***********************************************************************/

					_manager_project.setName(_projects.get(index).getManager_project().getName());

					_manager_project.setPrenom(_projects.get(index).getManager_project().getPrenom());

					_manager_project.setPhotoUser(_projects.get(index).getManager_project().getPhotoUser());

					_manager_project.setUsername(_projects.get(index).getManager_project().getUsername());

					_projects.get(index).setManager_project(_manager_project);

					/***********************************************************************/					

				}

			}

			return _projects;

		} else {

			return null;
		}

	}
	
	
	@Override
	public List<project> getAllContribProjectsByUser(@CurrentUser UserPrincipal currentUser, String token_user) {
		
	    String tokenUserCurrent = currentUser.getToken();

		List<investisseursProjectModel> _list_investisseurs = _investisseursProjectRepository.findAllContribProjectByToken(token_user);
		
		List<project> _projects = new ArrayList<project>();

		if (_list_investisseurs.size() > 0) {

			for (int index = 0; index < _list_investisseurs.size(); index++) {

				User _manager_project = new User();	
				
				

				_list_investisseurs.get(index).get_project().setManager_project(_manager_project);

				User porteur_project = new User();

				porteur_project.setName(_list_investisseurs.get(index).get_project().get_user().getName());

				porteur_project.setPrenom(_list_investisseurs.get(index).get_project().get_user().getPrenom());

				porteur_project.setPhotoUser(_list_investisseurs.get(index).get_project().get_user().getPhotoUser());

				porteur_project.setUsername(_list_investisseurs.get(index).get_project().get_user().getUsername());

				_list_investisseurs.get(index).get_project().set_user(porteur_project);

				User investisseur_project = new User();

				investisseur_project.setName(_list_investisseurs.get(index).get_userProjectInvest().getName());

				investisseur_project.setPrenom(_list_investisseurs.get(index).get_userProjectInvest().getPrenom());

				investisseur_project.setPhotoUser(_list_investisseurs.get(index).get_userProjectInvest().getPhotoUser());

				investisseur_project.setUsername(_list_investisseurs.get(index).get_userProjectInvest().getUsername());
				
				investisseur_project.setToken(_list_investisseurs.get(index).get_userProjectInvest().getToken());
				
				investisseur_project.setSex(_list_investisseurs.get(index).get_userProjectInvest().getSex());

				_list_investisseurs.get(index).set_userProjectInvest(investisseur_project);
				
				_projects.add(_list_investisseurs.get(index).get_project());

			}

			return _projects;

		} else {

			System.out.println("aucun projet-no-existe");

			return null;
		}

	

     }
	
	@Override
	public project getInfosMyProjectByCurrentUser(@CurrentUser UserPrincipal currentUser, String tokenProject) {

		String tokenUserCurrent = currentUser.getToken();

		System.out.println("tokenUserCurrent = " + tokenUserCurrent);

		Optional<project> _project = _projectRepository.findMyProjectByToken(tokenUserCurrent, tokenProject);

		if (_project.isPresent()) {

			project projectBdd = _project.get();
			
			if(projectBdd.getManager_project()!=null) {
				
				User _manager_project = new User();

				_manager_project.setName(projectBdd.getManager_project().getName());

				_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

				_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

				_manager_project.setUsername(projectBdd.getManager_project().getUsername());
				
				_manager_project.setToken(projectBdd.getManager_project().getToken());

				projectBdd.setManager_project(_manager_project);
			}

			

			return projectBdd;

		} else {

			return null;
		}

	}

	@Override
	public project getInfosProjectByOtherUser(@CurrentUser UserPrincipal currentUser, String tokenProject) {

		String tokenUserCurrent = currentUser.getToken();

		System.out.println("tokenUserCurrent+getInfosProjectByOtherUser = " + tokenUserCurrent);

		Optional<project> _project = _projectRepository.findProjectByTokenByOtherUser(tokenProject);

		if (_project.isPresent()) {

			project projectBdd = _project.get();
			
			if(projectBdd.getManager_project()!=null) {
				
				User _manager_project = new User();

				_manager_project.setName(projectBdd.getManager_project().getName());

				_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

				_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

				_manager_project.setUsername(projectBdd.getManager_project().getUsername());

				projectBdd.setManager_project(_manager_project);				
				
			}
			
			/****************************************************************/

		    User _porteur_project = new User();

			_porteur_project.setName(projectBdd.get_user().getName());

			_porteur_project.setPrenom(projectBdd.get_user().getPrenom());

			_porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

			_porteur_project.setUsername(projectBdd.get_user().getUsername());
			
			_porteur_project.setToken(projectBdd.get_user().getToken());
			

            if(currentUser.getAuthorities().toArray()[0].toString() == "ROLE_MANAGER"  ||  currentUser.getAuthorities().toArray()[0].toString() == "ROLE_ADMIN") {
	
	
            	_porteur_project.setDate_naissance(projectBdd.get_user().getDate_naissance());
            	
            	_porteur_project.setSex(projectBdd.get_user().getSex());
	
             }
			
			projectBdd.set_user(_porteur_project);

			return projectBdd;

		} else {

			return null;
		}

	}
	
	
	@Override
	public project createProject(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody ProjetRequest newProject) {
		
		try {
			
			String tokenUserCurrent = currentUser.getToken();

			Optional<User> userProject = _userRepository.checkExistUserByToken(tokenUserCurrent);

			User userBdd = userProject.get();

			project projectBdd = new project();

			String newToken = methodesUtils.generateAlphanumericStringToken();

			projectBdd.setToken(newToken);

			projectBdd.setNom(newProject.getNom());

			projectBdd.setDescription(newProject.getDescription());

			projectBdd.setMontant_minimun(newProject.getMontant_minimun());

			projectBdd.setDate_limite_collecte(newProject.getDate_limite_collecte());

			projectBdd.setAfficheProject(newProject.getAfficheProject());

			projectBdd.setContrePartieProject(newProject.getContrePartieProject());

			projectBdd.set_user(userBdd);
			
			 Optional<category_project> categoryProjectOpt = _categorieProjectRepository.findByToken(newProject.getToken_category());
			 
			 projectBdd.setCategoryProject(categoryProjectOpt.get());

			statutProject _statutProject = new statutProject((long) 1, "ATTENTE");

			projectBdd.set_statut_project(_statutProject);
			
			 Optional<porte_project> porteProjectOpt = _porteProjectRepository.findById(newProject.getId_porte_project());

			projectBdd.set_porte_project(porteProjectOpt.get());

			return _projectRepository.save(projectBdd);
			
		}catch(BadRequestException e) {
			
			 System.out.println("Error-service-BadRequestException"+ e.getMessage());
			 
			 return null;
			
		}catch (JsonParseException e) {
			
			System.out.println("Error-service-JsonParseException"+ e.getMessage());
			
            e.printStackTrace();
            
            return null;
        }



	}

	@Override
	public project updateProject(@CurrentUser UserPrincipal currentUser, String token_project,@Valid @RequestBody ProjetRequest updateProject) {

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> projectBdd = _projectRepository.findByToken(token_project);

		if (projectBdd.isPresent()) {

			project projectBddUpdate = projectBdd.get();

			String tokenUserProject = projectBddUpdate.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (isEqual) {

				projectBddUpdate.setNom(updateProject.getNom());

				projectBddUpdate.setDescription(updateProject.getDescription());

				projectBddUpdate.setMontant_minimun(updateProject.getMontant_minimun());

				projectBddUpdate.setDate_limite_collecte(updateProject.getDate_limite_collecte());

				projectBddUpdate.setAfficheProject(updateProject.getAfficheProject());

				projectBddUpdate.setContrePartieProject(updateProject.getContrePartieProject());
				
				 Optional<category_project> categoryProjectOpt = _categorieProjectRepository.findByToken(updateProject.getToken_category());

				projectBddUpdate.setCategoryProject(categoryProjectOpt.get());
				
				Optional<porte_project> porteProjectOpt = _porteProjectRepository.findById(updateProject.getId_porte_project());

				projectBddUpdate.set_porte_project(porteProjectOpt.get());
			
				return _projectRepository.save(projectBddUpdate);

			} else {

				// System.out.println("non-userbdd exist");

				return null;
			}

		} else {

			return null;
		}

	}

	@Override
	public project updateProjectByManagerProject(@CurrentUser UserPrincipal currentUser, String token_project,String token_statut) {
		
		System.out.println("service-updateProjectByManagerProject");

		String tokenUserCurrent = currentUser.getToken();

		Optional<User> managerProject = _userRepository.checkExistUserByToken(tokenUserCurrent);

		Optional<project> projectBdd = _projectRepository.findByToken(token_project);

		if (projectBdd.isPresent()) {			
			
			Optional<statutProject>  _statutProjectOpt = _statutProjectRepository.findByToken(token_statut);
			
			if(_statutProjectOpt.isPresent()) {
				
				project projectBddUpdate = projectBdd.get();			

				Optional<category_project> _category_project = _categorieProjectRepository.findByNom(projectBddUpdate.getCategoryProject().getNom());

				if (managerProject.get().equals(projectBddUpdate.getManager_project())) {

					System.out.println("C'est le manager de projet");

					if (_category_project.isPresent()) {

						category_project category_projectBdd = _category_project.get();

						int nbrProjectsByCategorie = category_projectBdd.getNbr_projects();

						if (projectBddUpdate.get_statut_project().getId() == 3) { // projet annule

							/*****************
							 * decrementer et mettre a jour le nombre de projet par categorie
							 ***********************/

							nbrProjectsByCategorie = nbrProjectsByCategorie - 1;

							category_projectBdd.setNbr_projects(nbrProjectsByCategorie);

							_categorieProjectRepository.save(category_projectBdd);

							/*****************
							 * decrementer et mettre a jour le nombre de projet par utilisateur
							 ***********************/

							User userBdd = projectBddUpdate.get_user();

							int nbr_projects = userBdd.getNbr_projects();

							if (nbr_projects > 0) {

								nbr_projects = nbr_projects - 1;

								_userRepository.save(userBdd);
							}

							/******************************************************************/

						}

					}

				} else {

					System.out.println("n'est pas  le manager de projet");

					if (_category_project.isPresent()) {

						category_project category_projectBdd = _category_project.get();

						int nbrProjectsByCategorie = category_projectBdd.getNbr_projects();

						if (projectBddUpdate.get_statut_project().getId() == 2) { // projet valide

							/*****************
							 * incrementer et mettre a jour le nombre de projet par categorie
							 ***********************/

							nbrProjectsByCategorie = nbrProjectsByCategorie + 1;

							category_projectBdd.setNbr_projects(nbrProjectsByCategorie);

							_categorieProjectRepository.save(category_projectBdd);

							/*****************
							 * incrementer et mettre a jour le nombre de projet par utilisateur
							 ***********************/

							User userBdd = projectBddUpdate.get_user();

							int nbr_projects = userBdd.getNbr_projects();

							nbr_projects = nbr_projects + 1;

							_userRepository.save(userBdd);

							/******************************************************************/

						}

					}

					projectBddUpdate.setManager_project(managerProject.get());

				}

				projectBddUpdate.set_statut_project(_statutProjectOpt.get());

				return _projectRepository.save(projectBddUpdate);
				
				
			}else {
				
				return null;
			}		



		} else {

			return null;
		}

	}
	
	
	@Override
	public Boolean checkCommissionProject(@CurrentUser UserPrincipal currentUser, String token_project) {	
		

		String tokenUserCurrent = currentUser.getToken();

		Optional<User> managerProject = _userRepository.checkExistUserByToken(tokenUserCurrent);

		Optional<project> projectOpt = _projectRepository.findByToken(token_project);

		if (projectOpt.isPresent()) {			
				
				project projectBdd = projectOpt.get();				

				if (managerProject.get().equals(projectBdd.getManager_project())) {

					System.out.println("C'est le manager de projet");
					
					Optional<commissionProjectModel> _commissionProjectOpt= _commisionProjectRepository.findCheckCommissionProject(token_project);
					
					if(_commissionProjectOpt.isPresent()) {
						
						return true;
						
					}else {
						
						return false;
						
					}				
				

				} else {

					System.out.println("ce n'est pas  le manager de projet");
					
					return false;			

				}

		} else {

			return false;
		}

	}
	
	@Override
	public Boolean addCommissionProject(@CurrentUser UserPrincipal currentUser,String token_project,  @Valid @RequestBody  CommissionProjectRequest   _commissionProjectRequest) {	
		
		Instant instant = Instant.now();

		long _timestamp = instant.toEpochMilli();

		SimpleDateFormat formater = null;

		Date aujourdhui = new Date();

		formater = new SimpleDateFormat("EEEE, d MMM yyyy  'à' hh:mm:ss");

		// System.out.println(formater.format(aujourdhui));
		

		String tokenUserCurrent = currentUser.getToken();

		Optional<User> managerProject = _userRepository.checkExistUserByToken(tokenUserCurrent);

		Optional<project> projectOpt = _projectRepository.findByToken(token_project);

		if (projectOpt.isPresent()) {			
				
				project projectBdd = projectOpt.get();				

				if (managerProject.get().equals(projectBdd.getManager_project())) {

					System.out.println("C'est le manager de projet");
					
					Optional<commissionProjectModel> _commissionProjectOpt= _commisionProjectRepository.findCheckCommissionProject(token_project);
					
					if(!_commissionProjectOpt.isPresent()) {
						
						commissionProjectModel  _new_commissionProject = new commissionProjectModel();
						
						String newToken = methodesUtils.generateAlphanumericStringToken();
						
						_new_commissionProject.setToken(newToken);
						
						_new_commissionProject.setAmount(_commissionProjectRequest.getAmount());
						
						_new_commissionProject.setTimestamp(_timestamp);
						
						_new_commissionProject.set_project(projectBdd);
						
						_new_commissionProject.setDate_created(_commissionProjectRequest.getDate_created());
						
						_commisionProjectRepository.save(_new_commissionProject);	
						
						
						return true;
						
					}else {
						
						return false;
						
					}				
				

				} else {

					System.out.println("ce n'est pas  le manager de projet");
					
					return false;			

				}

		} else {

			return false;
		}

	}

	/*********************************************************************************************/
	
	@Override
	public List<AdressReseauxSociauxProjectResponse> getlistAdressSociauxByProject(String token_project) {		

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();				

		    List<adressReseauxSociauxProject> _linksBdd = _adressreseauxSocialRepository.findBy_project(projectBdd);
		    
		    List<AdressReseauxSociauxProjectResponse> _links = new ArrayList<AdressReseauxSociauxProjectResponse>();

				if (_linksBdd.size() > 0) {

					for (int index = 0; index < _linksBdd.size(); index++) {
						
						AdressReseauxSociauxProjectResponse  _adressReseauxSociauxProjectResponse = new AdressReseauxSociauxProjectResponse(_linksBdd.get(index));
						
						_links.add(_adressReseauxSociauxProjectResponse) ;

					}

					return _links;

				} else {

					// System.out.println("aucun adress-social-no-existe");

					return null;
				}

			

		} else {

			// System.out.println("non-project exist");

			return null;
		}

	}

	@Override
	public AdressReseauxSociauxProjectResponse createAdressReseauSocialProject(@CurrentUser UserPrincipal currentUser,String token_project, @Valid @RequestBody AdressReseauxSociauxProjectRequest _linkSocialReq) {

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			String tokenUserProject = projectBdd.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (isEqual) {

				adressReseauxSociauxProject _linkSocialBdd = new adressReseauxSociauxProject();
				
				String newToken = methodesUtils.generateAlphanumericStringToken();
				
				_linkSocialBdd.setToken(newToken);

				_linkSocialBdd.setKeyMedia(_linkSocialReq.getKeyMedia());

				_linkSocialBdd.setLinkProject(_linkSocialReq.getLinkProject());

				_linkSocialBdd.setValueMedia(_linkSocialReq.getValueMedia());

				_linkSocialBdd.set_project(projectBdd);

				adressReseauxSociauxProject _linkSocialBddupdate = _adressreseauxSocialRepository.save(_linkSocialBdd);

				/**********************************************************/
				
				
				
				AdressReseauxSociauxProjectResponse _linkSocialResp = new AdressReseauxSociauxProjectResponse(_linkSocialBddupdate);			

				return _linkSocialResp;

			} else {

				// System.out.println("non-userbdd exist");

				return null;
			}

		} else {

			return null;
		}

	}

	@Override
	public Boolean deleteAdressReseauSocialProject(@CurrentUser UserPrincipal currentUser, String token_project,String token_address) {

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			String tokenUserProject = projectBdd.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (isEqual) {

				Optional<adressReseauxSociauxProject> _linkSocialOpt = _adressreseauxSocialRepository.findByToken(token_address);

				if (_linkSocialOpt.isPresent()) {

					adressReseauxSociauxProject _linkSocialBdd = _linkSocialOpt.get();

					_adressreseauxSocialRepository.deleteAdressReseauxSociauxProject(_linkSocialBdd.getId());

					return true;

				} else {

					return false;
				}

			} else {

				// System.out.println("non-userbdd exist");

				return false;
			}

		} else {

			return false;
		}

	}

	/************************************************************************************************************/

	@Override
	public List<LinkImageProjectResponse> getlistLinksImagesByProject(String token_project) {
		
		

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();			

			List<linkImageProject> _linksBdd = _linkImagesProjectRepository.findBy_project(projectBdd);
			
			List<LinkImageProjectResponse> _links = new ArrayList<LinkImageProjectResponse>();

				if (_linksBdd.size() > 0) {

					for (int index = 0; index < _linksBdd.size(); index++) {
						
						LinkImageProjectResponse _linkImageProjectResponse = new LinkImageProjectResponse(_linksBdd.get(index));
						
						_links.add(_linkImageProjectResponse);

						
					}					
				

					return _links;

				} else {

					System.out.println("aucune link-images-no-existe");

					return null;
				}

		

		} else {

			System.out.println("non-project exist");

			return null;
		}

	}

	@Override
	public LinkImageProjectResponse createLinkImageProject(@CurrentUser UserPrincipal currentUser, String token_project,@Valid @RequestBody LinkImageRequest _linkImageReq) {

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			String tokenUserProject = projectBdd.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (isEqual) {

				linkImageProject _linkImageBdd = new linkImageProject();
				
				String newToken = methodesUtils.generateAlphanumericStringToken();
				
				_linkImageBdd.setToken(newToken);

				_linkImageBdd.setLink(_linkImageReq.getLink());

				_linkImageBdd.set_project(projectBdd);

				linkImageProject _linkImageProjectBddupdate = _linkImagesProjectRepository.save(_linkImageBdd);

				/**********************************************************/
				LinkImageProjectResponse _linkImageResp = new LinkImageProjectResponse(_linkImageProjectBddupdate);					

				return _linkImageResp;

			} else {

				// System.out.println("non-userbdd exist");

				return null;
			}

		} else {

			return null;
		}

	}

	@Override
	public Boolean deleteLinkImageProject(@CurrentUser UserPrincipal currentUser, String token_project, String token_image) {

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			String tokenUserProject = projectBdd.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (isEqual) {

				Optional<linkImageProject> _linkImageOpt = _linkImagesProjectRepository.findByToken(token_image);

				if (_linkImageOpt.isPresent()) {

					linkImageProject _linkImageBdd = _linkImageOpt.get();

					_linkImagesProjectRepository.deleteLinkImageProject(_linkImageBdd.getId());

					return true;

				} else {

					return false;
				}

			} else {

				// System.out.println("non-userbdd exist");

				return false;
			}

		} else {

			return false;
		}

	}

	/************************************************************************************************************/

	@Override
	public List<CommentProjectResponse> getlistCommentsByProject(@CurrentUser UserPrincipal currentUser, String token_project) {

		// System.out.println("entre-service");

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			List<commentProject> _commentsBdd = _commentProjectRepository.findBy_project(projectBdd);
			
			List<CommentProjectResponse> _comments = new ArrayList<CommentProjectResponse>();

			if ( _commentsBdd.size() > 0) {

				for (int index = 0; index <  _commentsBdd.size(); index++) {
					
					UserResponse _user = new UserResponse(_commentsBdd.get(index).get_user());
					
					CommentProjectResponse  _comment_response = new CommentProjectResponse(_commentsBdd.get(index),_user);
					
					_comments.add(_comment_response);				

				}

				return _comments;

			} else {

				System.out.println("aucune link-images-no-existe");

				return null;
			}

		} else {

			System.out.println("non-project exist");

			return null;
		}

	}

	@Override
	public CommentProjectResponse createCommentProject(@CurrentUser UserPrincipal currentUser, String token_project,
			@Valid @RequestBody CommentRequest _comment) {

		String tokenUserCurrent = currentUser.getToken();

		Optional<User> userProject = _userRepository.checkExistUserByToken(tokenUserCurrent);

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			commentProject _commentBdd = new commentProject();
			
			String newToken = methodesUtils.generateAlphanumericStringToken();

			_commentBdd.setToken(newToken);

			_commentBdd.setBodyComment(_comment.getBodyComment());

			_commentBdd.setDateCreated(_comment.getDateCreated());

			_commentBdd.setTimestamp(_comment.getTimestamp());

			_commentBdd.set_user(userProject.get());

			_commentBdd.set_project(projectBdd);

			commentProject _commentProjectBddupdate = _commentProjectRepository.save(_commentBdd);

			/***************
			 * inscrementer le nombre de commentaires par project
			 **************************/

			int nbr_comments = projectBdd.getTotal_comments();

			nbr_comments = nbr_comments + 1;

			projectBdd.setTotal_comments(nbr_comments);

			_projectRepository.save(projectBdd);

			/**********************************************************/
			
			UserResponse _user = new UserResponse(_commentProjectBddupdate.get_user());			
			
			CommentProjectResponse    _commentProjectResp =  new CommentProjectResponse(_commentProjectBddupdate,_user);	

			/*********************************************************/

			return _commentProjectResp;

		} else {

			return null;
		}

	}

	/************************************************************************************************************/

	@Override
	public QuestionAidesProjectModel createQuestionAideByProject(@CurrentUser UserPrincipal currentUser,String token_project, @Valid @RequestBody QuestionAideRequest _questionProject) {

		String tokenUserCurrent = currentUser.getToken();

		Optional<User> userCurrent = _userRepository.checkExistUserByToken(tokenUserCurrent);

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			Optional<User> userDest = _userRepository.checkExistUserByToken(_questionProject.get_userDest().getToken());

			if (userDest.isPresent()) {

				User userDestBdd = userDest.get();

				QuestionAidesProjectModel _questionBdd = new QuestionAidesProjectModel();

				_questionBdd.setBodyAide(_questionProject.getBodyAide());

				_questionBdd.setDateCreated(_questionProject.getDateCreated());

				_questionBdd.set_userDest(userDestBdd);

				_questionBdd.setTimestamp(_questionProject.getTimestamp());

				_questionBdd.set_userExp(userCurrent.get());

				_questionBdd.set_project(projectBdd);

				QuestionAidesProjectModel _questiontProjectBddupdate = _QuestionsAidesProjectRepository.save(_questionBdd);

				/**********************************************************/
				
				if(projectBdd.getManager_project()!=null) {
					
					User _manager_project = new User();

					_manager_project.setName(projectBdd.getManager_project().getName());

					_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

					_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

					_manager_project.setUsername(projectBdd.getManager_project().getUsername());
					
					_manager_project.setToken(projectBdd.getManager_project().getToken());

					projectBdd.setManager_project(_manager_project);
					
				}
				

				User porteur_project = new User();

				porteur_project.setName(projectBdd.get_user().getName());

				porteur_project.setPrenom(projectBdd.get_user().getPrenom());

				porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

				porteur_project.setUsername(projectBdd.get_user().getUsername());

				projectBdd.set_user(porteur_project);

				_questiontProjectBddupdate.set_project(projectBdd);

				User userExpQuestion = new User();

				userExpQuestion.setName(_questiontProjectBddupdate.get_userExp().getName());

				userExpQuestion.setPrenom(_questiontProjectBddupdate.get_userExp().getPrenom());

				userExpQuestion.setUsername(_questiontProjectBddupdate.get_userExp().getUsername());

				userExpQuestion.setPhotoUser(_questiontProjectBddupdate.get_userExp().getPhotoUser());

				userExpQuestion.setToken(_questiontProjectBddupdate.get_userExp().getToken());

				_questiontProjectBddupdate.set_userExp(userExpQuestion);

				User userDestQuestion = new User();

				userDestQuestion.setName(_questiontProjectBddupdate.get_userDest().getName());

				userDestQuestion.setPrenom(_questiontProjectBddupdate.get_userDest().getPrenom());

				userDestQuestion.setUsername(_questiontProjectBddupdate.get_userDest().getUsername());

				userDestQuestion.setPhotoUser(_questiontProjectBddupdate.get_userDest().getPhotoUser());

				userDestQuestion.setToken(_questiontProjectBddupdate.get_userDest().getToken());

				_questiontProjectBddupdate.set_userExp(userDestQuestion);

				/*********************************************************/

				return _questiontProjectBddupdate;

			} else {

				return null;
			}

		} else {

			return null;
		}

	}

	@Override
	public List<QuestionAidesProjectModel> getListQuestionsAidesByProject(@CurrentUser UserPrincipal currentUser,
			String token_project) {

		// System.out.println("entre-service");

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			List<QuestionAidesProjectModel> _questions = _QuestionsAidesProjectRepository.findByProject(token_project);

			if (_questions.size() > 0) {

				for (int index = 0; index < _questions.size(); index++) {
					
					if(projectBdd.getManager_project()!=null) {
						
						User _manager_project = new User();

						_manager_project.setName(projectBdd.getManager_project().getName());

						_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

						_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

						_manager_project.setUsername(projectBdd.getManager_project().getUsername());

						projectBdd.setManager_project(_manager_project);
						
					}
					

					User porteur_project = new User();

					porteur_project.setName(projectBdd.get_user().getName());

					porteur_project.setPrenom(projectBdd.get_user().getPrenom());

					porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

					porteur_project.setUsername(projectBdd.get_user().getUsername());

					projectBdd.set_user(porteur_project);

					User userExpQuestion = new User();

					userExpQuestion.setName(_questions.get(index).get_userExp().getName());

					userExpQuestion.setPrenom(_questions.get(index).get_userExp().getPrenom());

					userExpQuestion.setUsername(_questions.get(index).get_userExp().getUsername());

					userExpQuestion.setPhotoUser(_questions.get(index).get_userExp().getPhotoUser());

					userExpQuestion.setToken(_questions.get(index).get_userExp().getToken());

					userExpQuestion.setRoles(_questions.get(index).get_userExp().getRoles());
					
					userExpQuestion.setSex(_questions.get(index).get_userExp().getSex());

					_questions.get(index).set_userExp(userExpQuestion);

					User userDestQuestion = new User();

					userDestQuestion.setName(_questions.get(index).get_userDest().getName());

					userDestQuestion.setPrenom(_questions.get(index).get_userDest().getPrenom());

					userDestQuestion.setUsername(_questions.get(index).get_userDest().getUsername());

					userDestQuestion.setPhotoUser(_questions.get(index).get_userDest().getPhotoUser());

					userDestQuestion.setToken(_questions.get(index).get_userDest().getToken());

					userDestQuestion.setRoles(_questions.get(index).get_userDest().getRoles());
					
					userDestQuestion.setSex(_questions.get(index).get_userDest().getSex());

					_questions.get(index).set_userDest(userDestQuestion);

				}

				return _questions;

			} else {

				System.out.println("aucune questions-aides-no-existe");

				return null;
			}

		} else {

			System.out.println("non-project exist");

			return null;
		}

	}

	/*************************************************************************/

	@Override
	public investisseursProjectModel createDemandeInvestProject(@CurrentUser UserPrincipal currentUser,
			String token_project) {

		Instant instant = Instant.now();

		long _timestamp = instant.toEpochMilli();

		SimpleDateFormat formater = null;

		Date aujourdhui = new Date();

		formater = new SimpleDateFormat("EEEE, d MMM yyyy  'à' hh:mm:ss");

		// System.out.println(formater.format(aujourdhui));

		String tokenUserCurrent = currentUser.getToken();

		Optional<User> userCurrent = _userRepository.checkExistUserByToken(tokenUserCurrent);

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			User userCurrentBdd = userCurrent.get();

			Optional<investisseursProjectModel> InvestisseursProjectOpt = _investisseursProjectRepository
					.check_invest_project(token_project, tokenUserCurrent);

			if (InvestisseursProjectOpt.isPresent()) {

				return null;

			} else {

				investisseursProjectModel _demandeInvestProject = new investisseursProjectModel();

				String newToken = methodesUtils.generateAlphanumericStringToken();

				_demandeInvestProject.setToken(newToken);

				statutDemandeInvest _statutDemandeInvest = statutDemandeInvest.ATTENTE;

				_demandeInvestProject.setStatutDemande(_statutDemandeInvest);

				_demandeInvestProject.set_project(projectBdd);

				_demandeInvestProject.set_userProjectInvest(userCurrentBdd);

				_demandeInvestProject.setDate_created(formater.format(aujourdhui));

				_demandeInvestProject.setTimestamp(_timestamp);

				investisseursProjectModel _demandeInvestProjectBdd = _investisseursProjectRepository
						.save(_demandeInvestProject);

				/*****************************************************************/
				
				if(projectBdd.getManager_project()!=null) {
					
					User _manager_project = new User();

					_manager_project.setName(projectBdd.getManager_project().getName());

					_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

					_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

					_manager_project.setUsername(projectBdd.getManager_project().getUsername());

					projectBdd.setManager_project(_manager_project);
				}

				

				User porteur_project = new User();

				porteur_project.setName(projectBdd.get_user().getName());

				porteur_project.setPrenom(projectBdd.get_user().getPrenom());

				porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

				porteur_project.setUsername(projectBdd.get_user().getUsername());

				projectBdd.set_user(porteur_project);

				_demandeInvestProjectBdd.set_project(projectBdd);

				/****************************************************************/

				return _demandeInvestProjectBdd;
			}

		} else {

			return null;

		}

	}

	@Override
	public investisseursProjectModel updateDemandeInvestProject(@CurrentUser UserPrincipal currentUser,
			String token_project, String token_demande, DemandeInvestisRequest _demandeInvest) {

		SimpleDateFormat formater = null;

		Date aujourdhui = new Date();

		formater = new SimpleDateFormat("EEEE, d MMM yyyy  'à' hh:mm:ss");

		// System.out.println(formater.format(aujourdhui));

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			String tokenUserProject = projectBdd.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (isEqual) {

				Optional<investisseursProjectModel> InvestisseursProjectOpt = _investisseursProjectRepository
						.check_invest_project_by_token(token_project, token_demande);

				if (InvestisseursProjectOpt.isPresent()) {

					investisseursProjectModel _investisseursProjectBdd = InvestisseursProjectOpt.get();

					boolean isEqualValide = (_demandeInvest.getStatut_demande().toString().equals("VALIDE"));

					boolean isEqualAnnule = (_demandeInvest.getStatut_demande().toString().equals("ANNULE"));

					System.out.println(
							"_demandeInvestProject.getStatutDemande()=" + _investisseursProjectBdd.getStatutDemande());

					if (isEqualValide) {

						System.out.println("isEqualValide=" + isEqualValide);

						statutDemandeInvest _statutDemandeInvest = statutDemandeInvest.VALIDE;

						_investisseursProjectBdd.setStatutDemande(_statutDemandeInvest);

						_investisseursProjectBdd.setDate_update(formater.format(aujourdhui));

						_investisseursProjectRepository.save(_investisseursProjectBdd);

						_investisseursProjectBdd = _investisseursProjectRepository.save(_investisseursProjectBdd);

						int nbr_investisseurs_project = projectBdd.getTotal_investisseurs();

						nbr_investisseurs_project = nbr_investisseurs_project + 1;

						projectBdd.setTotal_investisseurs(nbr_investisseurs_project);

						_projectRepository.save(projectBdd);

					} else if (isEqualAnnule) {

						statutDemandeInvest _statutDemandeInvest = statutDemandeInvest.ANNULE;

						_investisseursProjectBdd.setStatutDemande(_statutDemandeInvest);

						_investisseursProjectBdd.setDate_update(formater.format(aujourdhui));

						_investisseursProjectRepository.save(_investisseursProjectBdd);

						_investisseursProjectBdd = _investisseursProjectRepository.save(_investisseursProjectBdd);

					}
					
					if(projectBdd.getManager_project()!=null) {
						
						User _manager_project = new User();

						_manager_project.setName(projectBdd.getManager_project().getName());

						_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

						_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

						_manager_project.setUsername(projectBdd.getManager_project().getUsername());

						projectBdd.setManager_project(_manager_project);
					}
					

					User porteur_project = new User();

					porteur_project.setName(projectBdd.get_user().getName());

					porteur_project.setPrenom(projectBdd.get_user().getPrenom());

					porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

					porteur_project.setUsername(projectBdd.get_user().getUsername());

					projectBdd.set_user(porteur_project);

					User investisseur_project = new User();

					investisseur_project.setName(projectBdd.get_user().getName());

					investisseur_project.setPrenom(projectBdd.get_user().getPrenom());

					investisseur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

					investisseur_project.setUsername(projectBdd.get_user().getUsername());

					_investisseursProjectBdd.set_userProjectInvest(investisseur_project);

					return _investisseursProjectBdd;

				} else {

					return null;
				}

			} else {

				return null;
			}

		} else {

			return null;

		}

	}

	@Override
	public Boolean checkInvestProject(@CurrentUser UserPrincipal currentUser, String token_project) {

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _projectOpt = _projectRepository.findByToken(token_project);

		if (_projectOpt.isPresent()) {

			Optional<investisseursProjectModel> InvestisseursProjectOpt = _investisseursProjectRepository
					.check_invest_project(token_project, tokenUserCurrent);

			if (InvestisseursProjectOpt.isPresent()) {

				return true;

			} else {

				return false;
			}

		} else {

			return null;
		}

	}
	
	@Override
	public investisseursProjectModel getInfosInvestProject(@CurrentUser UserPrincipal currentUser, String token_project) {

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _projectOpt = _projectRepository.findByToken(token_project);

		if (_projectOpt.isPresent()) {

			Optional<investisseursProjectModel> InvestisseursProjectOpt = _investisseursProjectRepository
					.check_invest_project(token_project, tokenUserCurrent);

			if (InvestisseursProjectOpt.isPresent()) {
				
				investisseursProjectModel  _investisseursProjectBdd = InvestisseursProjectOpt.get();

				return _investisseursProjectBdd;

			} else {

				return null;
			}

		} else {

			return null;
		}

	}
	
	@Override
	public List<investisseursProjectModel> getlistMyProjectsInvest(@CurrentUser UserPrincipal currentUser) {
		
		    String tokenUserCurrent = currentUser.getToken();

			List<investisseursProjectModel> _list_investisseurs = _investisseursProjectRepository.findAllContribProjectByToken(tokenUserCurrent);

			if (_list_investisseurs.size() > 0) {

				for (int index = 0; index < _list_investisseurs.size(); index++) {

					User _manager_project = new User();				

					_list_investisseurs.get(index).get_project().setManager_project(_manager_project);

					User porteur_project = new User();

					porteur_project.setName(_list_investisseurs.get(index).get_project().get_user().getName());

					porteur_project.setPrenom(_list_investisseurs.get(index).get_project().get_user().getPrenom());

					porteur_project.setPhotoUser(_list_investisseurs.get(index).get_project().get_user().getPhotoUser());

					porteur_project.setUsername(_list_investisseurs.get(index).get_project().get_user().getUsername());

					_list_investisseurs.get(index).get_project().set_user(porteur_project);

					User investisseur_project = new User();

					investisseur_project.setName(_list_investisseurs.get(index).get_userProjectInvest().getName());

					investisseur_project.setPrenom(_list_investisseurs.get(index).get_userProjectInvest().getPrenom());

					investisseur_project.setPhotoUser(_list_investisseurs.get(index).get_userProjectInvest().getPhotoUser());

					investisseur_project.setUsername(_list_investisseurs.get(index).get_userProjectInvest().getUsername());
					
					investisseur_project.setToken(_list_investisseurs.get(index).get_userProjectInvest().getToken());
					
					investisseur_project.setSex(_list_investisseurs.get(index).get_userProjectInvest().getSex());

					_list_investisseurs.get(index).set_userProjectInvest(investisseur_project);

				}

				return _list_investisseurs;

			} else {

				System.out.println("aucun projet-no-existe");

				return null;
			}

		

	}

	@Override
	public List<investisseursProjectModel> getlistInvestisseursByProjectForUser(@CurrentUser UserPrincipal currentUser,
			String token_project) {

		// System.out.println("entre-service");

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			List<investisseursProjectModel> _list_investisseurs = _investisseursProjectRepository
					.findAllInvestiteursProjectByProject(token_project);

			if (_list_investisseurs.size() > 0) {

				for (int index = 0; index < _list_investisseurs.size(); index++) {
					
					if(projectBdd.getManager_project()!=null) {
						
						User _manager_project = new User();

						_manager_project.setName(projectBdd.getManager_project().getName());

						_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

						_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

						_manager_project.setUsername(projectBdd.getManager_project().getUsername());

						projectBdd.setManager_project(_manager_project);
						
						
					}

				

					User porteur_project = new User();

					porteur_project.setName(projectBdd.get_user().getName());

					porteur_project.setPrenom(projectBdd.get_user().getPrenom());

					porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

					porteur_project.setUsername(projectBdd.get_user().getUsername());

					projectBdd.set_user(porteur_project);

					User investisseur_project = new User();

					investisseur_project.setName(_list_investisseurs.get(index).get_userProjectInvest().getName());

					investisseur_project.setPrenom(_list_investisseurs.get(index).get_userProjectInvest().getPrenom());

					investisseur_project.setPhotoUser(_list_investisseurs.get(index).get_userProjectInvest().getPhotoUser());

					investisseur_project.setUsername(_list_investisseurs.get(index).get_userProjectInvest().getUsername());
					
					investisseur_project.setToken(_list_investisseurs.get(index).get_userProjectInvest().getToken());
					
					investisseur_project.setSex(_list_investisseurs.get(index).get_userProjectInvest().getSex());

					_list_investisseurs.get(index).set_userProjectInvest(investisseur_project);

				}

				return _list_investisseurs;

			} else {

				System.out.println("aucune questions-aides-no-existe");

				return null;
			}

		} else {

			System.out.println("non-project exist");

			return null;
		}

	}

	@Override
	public List<investisseursProjectModel> getlistInvestisseursByProjectForPorteurProject(
			@CurrentUser UserPrincipal currentUser, String token_project) {

		// System.out.println("entre-service");

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			String tokenUserProject = projectBdd.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (isEqual) {

				List<investisseursProjectModel> _list_investisseurs = _investisseursProjectRepository
						.findBy_project(projectBdd);

				if (_list_investisseurs.size() > 0) {

					for (int index = 0; index < _list_investisseurs.size(); index++) {
						
						if(projectBdd.getManager_project()!=null) {
							
							User _manager_project = new User();

							_manager_project.setName(projectBdd.getManager_project().getName());

							_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

							_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

							_manager_project.setUsername(projectBdd.getManager_project().getUsername());

							projectBdd.setManager_project(_manager_project);
							
							
						}						

						_list_investisseurs.get(index).set_project(projectBdd);

						User investisseur_project = new User();

						investisseur_project.setName(_list_investisseurs.get(index).get_userProjectInvest().getName());

						investisseur_project.setPrenom(_list_investisseurs.get(index).get_userProjectInvest().getPrenom());

						investisseur_project.setPhotoUser(_list_investisseurs.get(index).get_userProjectInvest().getPhotoUser());

						investisseur_project.setUsername(_list_investisseurs.get(index).get_userProjectInvest().getUsername());
						
						investisseur_project.setToken(_list_investisseurs.get(index).get_userProjectInvest().getToken());
						
						investisseur_project.setSex(_list_investisseurs.get(index).get_userProjectInvest().getSex());

						_list_investisseurs.get(index).set_userProjectInvest(investisseur_project);

					}

					return _list_investisseurs;

				} else {

					System.out.println("aucun investiteur -no-existe");

					return null;
				}

			} else {

				return null;
			}

		} else {

			System.out.println("non-project exist");

			return null;
		}

	}

	@Override
	public fondInvestorModel createFondInvestProject(UserPrincipal currentUser, String token_project,
			String token_demande, @Valid AddFondProjectRequest _fondInvest) {
		Instant instant = Instant.now();

		long _timestamp = instant.toEpochMilli();

		SimpleDateFormat formater = null;

		Date aujourdhui = new Date();

		formater = new SimpleDateFormat("EEEE, d MMM yyyy  'à' hh:mm:ss");

		// System.out.println(formater.format(aujourdhui));

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			Optional<investisseursProjectModel> _investisseursProjectOpt = _investisseursProjectRepository
					.check_invest_project(token_project, tokenUserCurrent);

			if (_investisseursProjectOpt.isPresent()) {

				fondInvestorModel newFondInvestorBdd = new fondInvestorModel();

				investisseursProjectModel _investisseursProjectBdd = _investisseursProjectOpt.get();

				String newToken = methodesUtils.generateAlphanumericStringToken();

				newFondInvestorBdd.setToken(newToken);

				newFondInvestorBdd.set_investisseurProject(_investisseursProjectOpt.get());

				newFondInvestorBdd.setAmount(_fondInvest.getAmount());

				newFondInvestorBdd.setTimestamp(_timestamp);

				newFondInvestorBdd.setDateCreated(formater.format(aujourdhui));

				_fondInvestorRepository.save(newFondInvestorBdd);

				/*****************************
				 * mettre a jour le capital collecte de projet
				 ********************************************/

				int totalFons = projectBdd.getTotal_fonds();

				totalFons = (int) (totalFons + _fondInvest.getAmount());

				projectBdd.setTotal_fonds(totalFons);

				_projectRepository.save(projectBdd);
				
				/*****************************
				 * mettre a jour les fons investis concernant l'investisteur  de projet
				 ********************************************/
				
				int totalFonsInvest = _investisseursProjectBdd.getTotal_fonds();

				totalFonsInvest = (int) (totalFonsInvest + _fondInvest.getAmount());

				_investisseursProjectBdd.setTotal_fonds(totalFonsInvest);

				_investisseursProjectRepository.save(_investisseursProjectBdd);

				/***************************************************************/
				
				if(projectBdd.getManager_project()!=null) {
					
					User _manager_project = new User();

					_manager_project.setName(projectBdd.getManager_project().getName());

					_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

					_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

					_manager_project.setUsername(projectBdd.getManager_project().getUsername());

					projectBdd.setManager_project(_manager_project);					
					
				}
				

				User porteur_project = new User();

				porteur_project.setName(projectBdd.get_user().getName());

				porteur_project.setPrenom(projectBdd.get_user().getPrenom());

				porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

				porteur_project.setUsername(projectBdd.get_user().getUsername());

				projectBdd.set_user(porteur_project);

				_investisseursProjectBdd.set_project(projectBdd);

				User investisseur_project = new User();

				investisseur_project.setName(_investisseursProjectBdd.get_userProjectInvest().getName());

				investisseur_project.setPrenom(_investisseursProjectBdd.get_userProjectInvest().getPrenom());

				investisseur_project.setPhotoUser(_investisseursProjectBdd.get_userProjectInvest().getPhotoUser());

				investisseur_project.setUsername(_investisseursProjectBdd.get_userProjectInvest().getUsername());

				_investisseursProjectBdd.set_userProjectInvest(investisseur_project);

				newFondInvestorBdd.set_investisseurProject(_investisseursProjectBdd);

				/***************************************************************/

				return newFondInvestorBdd;

			} else {

				return null;
			}

		} else {

			return null;

		}

	}

	@Override
	public List<fondInvestorModel> getListFondInvestByProjectForPorteurProject(@CurrentUser UserPrincipal currentUser,String token_project) {

		// System.out.println("entre-service");

		Optional<project> _project = _projectRepository.findByToken(token_project);
		
		String tokenUserCurrent = currentUser.getToken();

		if (_project.isPresent()) {

			project projectBdd = _project.get();	
		

			String tokenUserProject = projectBdd.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (isEqual) {
				
				List<fondInvestorModel> _listFondsInvestorBdd = _fondInvestorRepository
						.findAllFondsInvestByTokenProject(token_project);

				if (_listFondsInvestorBdd.size() > 0) {

					for (int index = 0; index < _listFondsInvestorBdd.size(); index++) {
						
						if(projectBdd.getManager_project()!=null) {
							
							User _manager_project = new User();

							_manager_project.setName(projectBdd.getManager_project().getName());

							_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

							_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

							_manager_project.setUsername(projectBdd.getManager_project().getUsername());

							projectBdd.setManager_project(_manager_project);
							
						}

						

						User porteur_project = new User();

						porteur_project.setName(projectBdd.get_user().getName());

						porteur_project.setPrenom(projectBdd.get_user().getPrenom());

						porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

						porteur_project.setUsername(projectBdd.get_user().getUsername());

						projectBdd.set_user(porteur_project);

						User investisseur_project = new User();

						investisseur_project.setName(_listFondsInvestorBdd.get(index).get_investisseurProject()
								.get_userProjectInvest().getName());

						investisseur_project.setPrenom(_listFondsInvestorBdd.get(index).get_investisseurProject()
								.get_userProjectInvest().getPrenom());

						investisseur_project.setPhotoUser(_listFondsInvestorBdd.get(index).get_investisseurProject()
								.get_userProjectInvest().getPhotoUser());

						investisseur_project.setUsername(_listFondsInvestorBdd.get(index).get_investisseurProject()
								.get_userProjectInvest().getUsername());
						
						investisseur_project.setToken(_listFondsInvestorBdd.get(index).get_investisseurProject()
								.get_userProjectInvest().getToken());
						
						investisseur_project.setSex(_listFondsInvestorBdd.get(index).get_investisseurProject()
								.get_userProjectInvest().getSex());

						_listFondsInvestorBdd.get(index).get_investisseurProject()
								.set_userProjectInvest(investisseur_project);
						
						

						_listFondsInvestorBdd.get(index)
								.set_investisseurProject(_listFondsInvestorBdd.get(index).get_investisseurProject());

					}

					return _listFondsInvestorBdd;
				
				
			}else {
				
				return null;
			}



			} else {

				System.out.println("aucune fonds-invest-no-existe");

				return null;
			}

		} else {

			System.out.println("non-project exist");

			return null;
		}

	}
	
	@Override
	public List<fondInvestorModel> getListFondInvestByProjectForInvestisseurProject(@CurrentUser UserPrincipal currentUser,String token_project) {

		// System.out.println("entre-service");
		
		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();
			
			Optional<investisseursProjectModel> _investisseursProjectOpt = _investisseursProjectRepository.check_invest_project(token_project, tokenUserCurrent);

			if (_investisseursProjectOpt.isPresent()) {

				fondInvestorModel newFondInvestorBdd = new fondInvestorModel();

				investisseursProjectModel _investisseursProjectBdd = _investisseursProjectOpt.get();
				
				List<fondInvestorModel> _listFondsInvestorBdd = _fondInvestorRepository.findAllFondsInvestByInvestisseur(_investisseursProjectBdd.getToken());

				if (_listFondsInvestorBdd.size() > 0) {

					for (int index = 0; index < _listFondsInvestorBdd.size(); index++) {

						User _manager_project = new User();

						_manager_project.setName(projectBdd.getManager_project().getName());

						_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

						_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

						_manager_project.setUsername(projectBdd.getManager_project().getUsername());

						projectBdd.setManager_project(_manager_project);

						User porteur_project = new User();

						porteur_project.setName(projectBdd.get_user().getName());

						porteur_project.setPrenom(projectBdd.get_user().getPrenom());

						porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

						porteur_project.setUsername(projectBdd.get_user().getUsername());

						projectBdd.set_user(porteur_project);

						User investisseur_project = new User();

						investisseur_project.setName(_listFondsInvestorBdd.get(index).get_investisseurProject()
								.get_userProjectInvest().getName());

						investisseur_project.setPrenom(_listFondsInvestorBdd.get(index).get_investisseurProject()
								.get_userProjectInvest().getPrenom());

						investisseur_project.setPhotoUser(_listFondsInvestorBdd.get(index).get_investisseurProject()
								.get_userProjectInvest().getPhotoUser());

						investisseur_project.setUsername(_listFondsInvestorBdd.get(index).get_investisseurProject()
								.get_userProjectInvest().getUsername());
						
						investisseur_project.setToken(_listFondsInvestorBdd.get(index).get_investisseurProject()
								.get_userProjectInvest().getToken());
						
						investisseur_project.setSex(_listFondsInvestorBdd.get(index).get_investisseurProject()
								.get_userProjectInvest().getSex());

						_listFondsInvestorBdd.get(index).get_investisseurProject()
								.set_userProjectInvest(investisseur_project);
						
						

						_listFondsInvestorBdd.get(index)
								.set_investisseurProject(_listFondsInvestorBdd.get(index).get_investisseurProject());

					}

					return _listFondsInvestorBdd;

				} else {

					System.out.println("aucune fonds-invest-no-existe");

					return null;
				}
				
				
			}else {
				
				return null;
			}		



		} else {

			System.out.println("non-project exist");

			return null;
		}

	}

	/************************************************************************/

	@Override
	public favorisProjectUserModel createFavorisProject(UserPrincipal currentUser, String token_project) {

		Instant instant = Instant.now();

		long _timestamp = instant.toEpochMilli();

		SimpleDateFormat formater = null;

		Date aujourdhui = new Date();

		formater = new SimpleDateFormat("EEEE, d MMM yyyy  'à' hh:mm:ss");

		// System.out.println(formater.format(aujourdhui));

		String tokenUserCurrent = currentUser.getToken();

		Optional<User> userCurrent = _userRepository.checkExistUserByToken(tokenUserCurrent);

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			User userCurrentBdd = userCurrent.get();

			Optional<favorisProjectUserModel> _favorisProjectOpt = _favorisProjectUserRepository
					.findCheckFavorisProject(tokenUserCurrent, token_project);

			if (!_favorisProjectOpt.isPresent()) {

				favorisProjectUserModel newFavorisProjectUserBdd = new favorisProjectUserModel();

				String newToken = methodesUtils.generateAlphanumericStringToken();

				newFavorisProjectUserBdd.setToken(newToken);

				newFavorisProjectUserBdd.set_project(projectBdd);

				newFavorisProjectUserBdd.set_user(userCurrentBdd);

				newFavorisProjectUserBdd.setTimestamp(_timestamp);

				newFavorisProjectUserBdd.setDate_created(formater.format(aujourdhui));

				_favorisProjectUserRepository.save(newFavorisProjectUserBdd);

				/***************
				 * incrementer le nombre de favoris par project
				 **************************/

				String tokenUserProject = projectBdd.get_user().getToken();

				String token1 = new String(tokenUserCurrent);

				String token2 = new String(tokenUserProject);

				boolean isEqual = (token1.equals(token2));

				if (!isEqual) {

					int nbr_favoris = projectBdd.getTotal_favoris();

					nbr_favoris = nbr_favoris + 1;

					projectBdd.setTotal_favoris(nbr_favoris);

					_projectRepository.save(projectBdd);

				}

				/***************************************************************/

				User _manager_project = new User();

				_manager_project.setName(projectBdd.getManager_project().getName());

				_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

				_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

				_manager_project.setUsername(projectBdd.getManager_project().getUsername());

				projectBdd.setManager_project(_manager_project);

				User porteur_project = new User();

				porteur_project.setName(projectBdd.get_user().getName());

				porteur_project.setPrenom(projectBdd.get_user().getPrenom());

				porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

				porteur_project.setUsername(projectBdd.get_user().getUsername());

				projectBdd.set_user(porteur_project);

				newFavorisProjectUserBdd.set_project(projectBdd);

				/***************************************************************/

				return newFavorisProjectUserBdd;

			} else {

				return null;
			}

		} else {

			return null;

		}

	}

	@Override
	public Boolean deleteFavorisProject(@CurrentUser UserPrincipal currentUser, String token_project) {

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			Optional<favorisProjectUserModel> _favoris_project_Opt = _favorisProjectUserRepository
					.findCheckFavorisProject(tokenUserCurrent, token_project);

			if (_favoris_project_Opt.isPresent()) {

				favorisProjectUserModel _favoris_projectBdd = _favoris_project_Opt.get();

				_favorisProjectUserRepository.deleteById(_favoris_projectBdd.getId());

				/***************
				 * Mettre a jour le nombre de favoris par project
				 **************************/

				String tokenUserProject = projectBdd.get_user().getToken();

				String token1 = new String(tokenUserCurrent);

				String token2 = new String(tokenUserProject);

				boolean isEqual = (token1.equals(token2));

				if (!isEqual) {

					int nbr_favoris = _favorisProjectUserRepository.countFavorisByProjectByUser(projectBdd.getToken(),
							tokenUserCurrent);

					projectBdd.setTotal_favoris(nbr_favoris);

					_projectRepository.save(projectBdd);

				}

				/********************************************************/

				return true;

			} else {

				return false;
			}

		} else {

			return false;
		}

	}

	@Override
	public Boolean checkFavorisProject(@CurrentUser UserPrincipal currentUser, String token_project) {

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			Optional<favorisProjectUserModel> _favoris_project_Opt = _favorisProjectUserRepository
					.findCheckFavorisProject(tokenUserCurrent, token_project);

			if (_favoris_project_Opt.isPresent()) {

				return true;

			} else {

				return false;
			}

		} else {

			return false;
		}

	}

	@Override
	public List<favorisProjectUserModel> getListFavorisProjects(@CurrentUser UserPrincipal currentUser) {

		// System.out.println("entre-service");	
		

		String tokenUserCurrent = currentUser.getToken();
		
		System.out.println("tokenUserCurrent = "+ tokenUserCurrent);

		List<favorisProjectUserModel> _listFavorisProjectsBdd = _favorisProjectUserRepository.findAllFavorisProjectByUser(tokenUserCurrent);

		if (_listFavorisProjectsBdd.size() > 0) {

			for (int index = 0; index < _listFavorisProjectsBdd.size(); index++) {

				
				
		 if(_listFavorisProjectsBdd.get(index).get_project().getManager_project()!= null) {
					
					
					User _manager_project = new User();
					
				 _manager_project
					.setName(_listFavorisProjectsBdd.get(index).get_project().getManager_project().getName());

			     _manager_project
					.setPrenom(_listFavorisProjectsBdd.get(index).get_project().getManager_project().getPrenom());

			     _manager_project.setPhotoUser(
					_listFavorisProjectsBdd.get(index).get_project().getManager_project().getPhotoUser());

			     _manager_project.setUsername(
					_listFavorisProjectsBdd.get(index).get_project().getManager_project().getUsername()); 

			     _listFavorisProjectsBdd.get(index).get_project().setManager_project(_manager_project);

			     User porteur_project = new User();

			     porteur_project
					.setName(_listFavorisProjectsBdd.get(index).get_project().get_user().getName());

			     porteur_project
					.setPrenom(_listFavorisProjectsBdd.get(index).get_project().get_user().getPrenom());

			     porteur_project.setPhotoUser(
					_listFavorisProjectsBdd.get(index).get_project().get_user().getPhotoUser());

			     porteur_project.setUsername(
					_listFavorisProjectsBdd.get(index).get_project().get_user().getUsername());
			
			     _listFavorisProjectsBdd.get(index).get_project().set_user(porteur_project);
					
				}			

			}

			return _listFavorisProjectsBdd;

		} else {

			System.out.println("aucune favoris-projects-no-existe");

			return null;
		}

	}

	/*******************************************************************************/

	@Override
	public heartProjectModel createHeartProject(UserPrincipal currentUser, String token_project) {

		Instant instant = Instant.now();

		long _timestamp = instant.toEpochMilli();

		SimpleDateFormat formater = null;

		Date aujourdhui = new Date();

		formater = new SimpleDateFormat("EEEE, d MMM yyyy  'à' hh:mm:ss");

		// System.out.println(formater.format(aujourdhui));

		String tokenUserCurrent = currentUser.getToken();

		Optional<User> userCurrent = _userRepository.checkExistUserByToken(tokenUserCurrent);

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			User userCurrentBdd = userCurrent.get();

			String tokenUserProject = projectBdd.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (!isEqual) {

				System.out.println("Le porteur de projet est different de utilisateur courant");

				Optional<heartProjectModel> _heartProjectOpt = _heartsProjectRepository
						.findCheckHeartProject(tokenUserCurrent, token_project);

				if (!_heartProjectOpt.isPresent()) {
					
					System.out.println("Le Heart de projet n'est pas present");

					heartProjectModel newHeartProjectBdd = new heartProjectModel();

					String newToken = methodesUtils.generateAlphanumericStringToken();

					newHeartProjectBdd.setToken(newToken);

					newHeartProjectBdd.set_project(projectBdd);

					newHeartProjectBdd.set_user(userCurrentBdd);

					newHeartProjectBdd.setTimestamp(_timestamp);

					newHeartProjectBdd.setDate_created(formater.format(aujourdhui));

					_heartsProjectRepository.save(newHeartProjectBdd);

					/***************
					 * inscrementer le nombre de hearts par project
					 **************************/

					int nbr_hearts = projectBdd.getTotal_hearts();

					nbr_hearts = nbr_hearts + 1;

					projectBdd.setTotal_hearts(nbr_hearts);

					_projectRepository.save(projectBdd);

					/***************************************************************/

					User _manager_project = new User();

					_manager_project.setName(projectBdd.getManager_project().getName());

					_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

					_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

					_manager_project.setUsername(projectBdd.getManager_project().getUsername());

					projectBdd.setManager_project(_manager_project);

					User porteur_project = new User();

					porteur_project.setName(projectBdd.get_user().getName());

					porteur_project.setPrenom(projectBdd.get_user().getPrenom());

					porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

					porteur_project.setUsername(projectBdd.get_user().getUsername());

					projectBdd.set_user(porteur_project);

					newHeartProjectBdd.set_project(projectBdd);

					/***************************************************************/

					return newHeartProjectBdd;

				} else {

					return null;
				}

			} else {

				System.out.println("Le porteur de projet est egale de utilisateur courant");

				return null;
			}

		} else {

			return null;

		}

	}

	@Override
	public Boolean deleteHeartProject(@CurrentUser UserPrincipal currentUser, String token_project) {

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			String tokenUserProject = projectBdd.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (!isEqual) {

				Optional<heartProjectModel> _heartProjectOpt = _heartsProjectRepository
						.findCheckHeartProject(tokenUserCurrent, token_project);

				if (_heartProjectOpt.isPresent()) {

					_heartsProjectRepository.deleteHeartProject(tokenUserCurrent, token_project);

					/***************
					 * Mettre a jour le nombre de hearts par project
					 **************************/

					int totalHearts = _heartsProjectRepository.countHeartByProjectByUser(projectBdd.getToken(),
							tokenUserCurrent);

					projectBdd.setTotal_hearts(totalHearts);

					_projectRepository.save(projectBdd);

					/***************************************************************/

					return true;

				} else {

					return false;
				}

			} else {

				System.out.println("Le porteur de projet est egale de utilisateur courant");

				return false;
			}

		} else {

			System.out.println("projet n'est existe pas ");

			return false;
		}

	}

	
	@Override
	public List<heartProjectModel> getListHeartsProject(@CurrentUser UserPrincipal currentUser, String token_project) {

		// System.out.println("entre-service");

		List<heartProjectModel> _listHeartsProjectsBdd = _heartsProjectRepository.findAllHeartsProject(token_project);

		if (_listHeartsProjectsBdd.size() > 0) {

			for (int index = 0; index < _listHeartsProjectsBdd.size(); index++) {

				User _manager_project = new User();

				_manager_project
						.setName(_listHeartsProjectsBdd.get(index).get_project().getManager_project().getName());

				_manager_project
						.setPrenom(_listHeartsProjectsBdd.get(index).get_project().getManager_project().getPrenom());

				_manager_project.setPhotoUser(
						_listHeartsProjectsBdd.get(index).get_project().getManager_project().getPhotoUser());

				_manager_project.setUsername(
						_listHeartsProjectsBdd.get(index).get_project().getManager_project().getUsername());

				_listHeartsProjectsBdd.get(index).get_project().setManager_project(_manager_project);

				User porteur_project = new User();

				porteur_project.setName(_listHeartsProjectsBdd.get(index).get_project().get_user().getName());

				porteur_project
						.setPrenom(_listHeartsProjectsBdd.get(index).get_project().get_user().getPrenom());

				porteur_project.setPhotoUser(
						_listHeartsProjectsBdd.get(index).get_project().get_user().getPhotoUser());

				porteur_project.setUsername(
						_listHeartsProjectsBdd.get(index).get_project().get_user().getUsername());

				_listHeartsProjectsBdd.get(index).get_project().set_user(porteur_project);

			}

			return _listHeartsProjectsBdd;

		} else {

			System.out.println("aucune hearts-projects-no-existe");

			return null;
		}

	}

	
	@Override
	public Boolean checkHeartProject(@CurrentUser UserPrincipal currentUser, String token_project) {

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			Optional<heartProjectModel> _heart_project_Opt = _heartsProjectRepository
					.findCheckHeartProject(tokenUserCurrent, token_project);

			if (_heart_project_Opt.isPresent()) {

				return true;

			} else {

				return false;
			}

		} else {

			return false;
		}

	}

	/***********************************************************************************************************************************/

	@Override
	public likeDislikeProjectModel createLike_Dislike_Project(UserPrincipal currentUser, String token_project,
			likeDislikeProject _like_dislike) {

		Instant instant = Instant.now();

		long _timestamp = instant.toEpochMilli();

		SimpleDateFormat formater = null;

		Date aujourdhui = new Date();

		formater = new SimpleDateFormat("EEEE, d MMM yyyy  'à' hh:mm:ss");

		// System.out.println(formater.format(aujourdhui));

		String tokenUserCurrent = currentUser.getToken();

		Optional<User> userCurrent = _userRepository.checkExistUserByToken(tokenUserCurrent);

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			User userCurrentBdd = userCurrent.get();

			String tokenUserProject = projectBdd.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (!isEqual) {

				System.out.println("Le porteur de projet est different de utilisateur courant");

				Optional<likeDislikeProjectModel> _like_dislike_Project_Opt = _likeDislikeProjectRepository
						.findCheckLikeProject(tokenUserCurrent, token_project);

				if (!_like_dislike_Project_Opt.isPresent()) {

					likeDislikeProjectModel newLikeDislikeProjectBdd = new likeDislikeProjectModel();

					String newToken = methodesUtils.generateAlphanumericStringToken();

					newLikeDislikeProjectBdd.setToken(newToken);

					newLikeDislikeProjectBdd.set_project(projectBdd);

					newLikeDislikeProjectBdd.set_user(userCurrentBdd);

					newLikeDislikeProjectBdd.setTimestamp(_timestamp);

					newLikeDislikeProjectBdd.setDate_created(formater.format(aujourdhui));

					newLikeDislikeProjectBdd.setStatut_like_project(_like_dislike);

					_likeDislikeProjectRepository.save(newLikeDislikeProjectBdd);

					/***************
					 * inscrementer le nombre de hearts par project
					 **************************/

					int totalLike = _likeDislikeProjectRepository.countLikeProject(projectBdd.getToken());

					int totalDisLike = _likeDislikeProjectRepository.countDisLikeProject(projectBdd.getToken());

					projectBdd.setTotal_like(totalLike);

					projectBdd.setTotal_dislike(totalDisLike);

					_projectRepository.save(projectBdd);

					/***************************************************************/

					User _manager_project = new User();

					_manager_project.setName(projectBdd.getManager_project().getName());

					_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

					_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

					_manager_project.setUsername(projectBdd.getManager_project().getUsername());

					projectBdd.setManager_project(_manager_project);

					User porteur_project = new User();

					porteur_project.setName(projectBdd.get_user().getName());

					porteur_project.setPrenom(projectBdd.get_user().getPrenom());

					porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

					porteur_project.setUsername(projectBdd.get_user().getUsername());

					projectBdd.set_user(porteur_project);

					newLikeDislikeProjectBdd.set_project(projectBdd);

					/***************************************************************/

					return newLikeDislikeProjectBdd;

				} else {

					return null;
				}

			} else {

				System.out.println("Le porteur de projet est egale de utilisateur courant");

				return null;
			}

		} else {

			return null;

		}

	}

	@Override
	public Boolean deleteLikeDislikeProject(@CurrentUser UserPrincipal currentUser, String token_project) {

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			String tokenUserProject = projectBdd.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (!isEqual) {

				Optional<likeDislikeProjectModel> _like_dislike_ProjectOpt = _likeDislikeProjectRepository
						.findCheckLikeProject(tokenUserCurrent, token_project);

				if (_like_dislike_ProjectOpt.isPresent()) {

					_likeDislikeProjectRepository.deleteLikeProject(tokenUserCurrent, token_project);

					/***************
					 * Mettre a jour le nombre de hearts par project
					 **************************/

					/*****************************
					 * mettre a jour le nombre de like et dislike de projet
					 ********************************************/

					int totalLike = _likeDislikeProjectRepository.countLikeProject(token_project);

					int totalDisLike = _likeDislikeProjectRepository.countDisLikeProject(token_project);

					projectBdd.setTotal_like(totalLike);

					projectBdd.setTotal_dislike(totalDisLike);

					_projectRepository.save(projectBdd);

					/***************************************************************/

					return true;

				} else {

					return false;
				}

			} else {

				System.out.println("Le porteur de projet est egale de utilisateur courant");

				return false;
			}

		} else {

			System.out.println("projet n'est existe pas ");

			return false;
		}

	}

	@Override
	public likeDislikeProjectModel checkLikeDislikeProject(@CurrentUser UserPrincipal currentUser, String token_project) {

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			Optional<likeDislikeProjectModel> _like_dislike_project_Opt = _likeDislikeProjectRepository
					.findCheckLikeProject(tokenUserCurrent, token_project);

			if (_like_dislike_project_Opt.isPresent()) {
				
				likeDislikeProjectModel  _likeDislikeProjectModelBdd = _like_dislike_project_Opt.get();

				return _likeDislikeProjectModelBdd;

			} else {

				return null;
			}

		} else {

			return null;
		}

	}

	
	@Override
	public likeDislikeProjectModel updateLike_Dislike_Project(UserPrincipal currentUser, String token_project,
			likeDislikeProject _like_dislike) {

		SimpleDateFormat formater = null;

		Date aujourdhui = new Date();

		formater = new SimpleDateFormat("EEEE, d MMM yyyy  'à' hh:mm:ss");

		// System.out.println(formater.format(aujourdhui));

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			String tokenUserProject = projectBdd.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (!isEqual) {

				System.out.println("Le porteur de projet est different de utilisateur courant");

				Optional<likeDislikeProjectModel> _like_dislike_Project_Opt = _likeDislikeProjectRepository
						.findCheckLikeProject(tokenUserCurrent, token_project);

				if (_like_dislike_Project_Opt.isPresent()) {

					likeDislikeProjectModel updateLikeDislikeProjectBdd = _like_dislike_Project_Opt.get();

					updateLikeDislikeProjectBdd.setDate_update(formater.format(aujourdhui));

					updateLikeDislikeProjectBdd.setStatut_like_project(_like_dislike);

					_likeDislikeProjectRepository.save(updateLikeDislikeProjectBdd);

					/***************
					 * inscrementer le nombre de hearts par project
					 **************************/

					int totalLike = _likeDislikeProjectRepository.countLikeProject(projectBdd.getToken());

					int totalDisLike = _likeDislikeProjectRepository.countDisLikeProject(projectBdd.getToken());

					projectBdd.setTotal_like(totalLike);

					projectBdd.setTotal_dislike(totalDisLike);

					_projectRepository.save(projectBdd);

					/***************************************************************/

					User _manager_project = new User();

					_manager_project.setName(projectBdd.getManager_project().getName());

					_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

					_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

					_manager_project.setUsername(projectBdd.getManager_project().getUsername());

					projectBdd.setManager_project(_manager_project);

					User porteur_project = new User();

					porteur_project.setName(projectBdd.get_user().getName());

					porteur_project.setPrenom(projectBdd.get_user().getPrenom());

					porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

					porteur_project.setUsername(projectBdd.get_user().getUsername());

					projectBdd.set_user(porteur_project);

					updateLikeDislikeProjectBdd.set_project(projectBdd);

					/***************************************************************/

					return updateLikeDislikeProjectBdd;

				} else {

					return null;
				}

			} else {

				System.out.println("Le porteur de projet est egale de utilisateur courant");

				return null;
			}

		} else {

			return null;

		}

	}

	@Override
	public List<likeDislikeProjectModel> getListLikesDislikesProject(@CurrentUser UserPrincipal currentUser,
			String token_project) {

		// System.out.println("entre-service");

		List<likeDislikeProjectModel> _listLikeDislikeProjectsBdd = _likeDislikeProjectRepository
				.findAllLikesDislikesProject(token_project);

		if (_listLikeDislikeProjectsBdd.size() > 0) {

			for (int index = 0; index < _listLikeDislikeProjectsBdd.size(); index++) {

				User _manager_project = new User();

				_manager_project
						.setName(_listLikeDislikeProjectsBdd.get(index).get_project().getManager_project().getName());

				_manager_project.setPrenom(
						_listLikeDislikeProjectsBdd.get(index).get_project().getManager_project().getPrenom());

				_manager_project.setPhotoUser(
						_listLikeDislikeProjectsBdd.get(index).get_project().getManager_project().getPhotoUser());

				_manager_project.setUsername(
						_listLikeDislikeProjectsBdd.get(index).get_project().getManager_project().getUsername());

				_listLikeDislikeProjectsBdd.get(index).get_project().setManager_project(_manager_project);

				User porteur_project = new User();

				porteur_project
						.setName(_listLikeDislikeProjectsBdd.get(index).get_project().get_user().getName());

				porteur_project.setPrenom(
						_listLikeDislikeProjectsBdd.get(index).get_project().get_user().getPrenom());

				porteur_project.setPhotoUser(
						_listLikeDislikeProjectsBdd.get(index).get_project().get_user().getPhotoUser());

				porteur_project.setUsername(
						_listLikeDislikeProjectsBdd.get(index).get_project().get_user().getUsername());

				_listLikeDislikeProjectsBdd.get(index).get_project().set_user(porteur_project);

			}

			return _listLikeDislikeProjectsBdd;

		} else {

			System.out.println("aucune likes-dislike-projects-no-existe");

			return null;
		}

	}

	/*********************************************************************************/

	@Override
	public vueProjectModel createOrUpdateVueProject(UserPrincipal currentUser, String token_project,
			VueProjectRequest _vueProject) {

		Instant instant = Instant.now();

		long _timestamp = instant.toEpochMilli();

		SimpleDateFormat formater = null;

		Date aujourdhui = new Date();

		formater = new SimpleDateFormat("EEEE, d MMM yyyy  'à' hh:mm:ss");

		// System.out.println(formater.format(aujourdhui));

		String tokenUserCurrent = currentUser.getToken();

		Optional<User> userCurrent = _userRepository.checkExistUserByToken(tokenUserCurrent);

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			User userCurrentBdd = userCurrent.get();

			String tokenUserProject = projectBdd.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (!isEqual) {

				System.out.println("Le porteur de projet est different de utilisateur courant");

				Optional<vueProjectModel> _vueProjectOpt = _vueProjectRepository.findCheckVueProject(tokenUserCurrent,
						token_project);

				if (!_vueProjectOpt.isPresent()) {

					System.out.println("La vue n'est pas presente au bdd");

					vueProjectModel newVueProjectBdd = new vueProjectModel();

					String newToken = methodesUtils.generateAlphanumericStringToken();

					newVueProjectBdd.setToken(newToken);

					newVueProjectBdd.set_project(projectBdd);

					newVueProjectBdd.set_user(userCurrentBdd);

					newVueProjectBdd.setTimestamp(_timestamp);

					newVueProjectBdd.setDate_created(formater.format(aujourdhui));

					newVueProjectBdd.setDate_consultation(formater.format(aujourdhui));

					newVueProjectBdd.setIp_adress(_vueProject.getIp_adress());

					_vueProjectRepository.save(newVueProjectBdd);

					/***************
					 * inscrementer le nombre de hearts par project
					 **************************/

					int totalVues = _vueProjectRepository.countVuesProject(token_project);

					projectBdd.setTotal_vues(totalVues);

					_projectRepository.save(projectBdd);

					_projectRepository.save(projectBdd);

					/***************************************************************/

					User _manager_project = new User();

					_manager_project.setName(projectBdd.getManager_project().getName());

					_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

					_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

					_manager_project.setUsername(projectBdd.getManager_project().getUsername());

					projectBdd.setManager_project(_manager_project);

					User porteur_project = new User();

					porteur_project.setName(projectBdd.get_user().getName());

					porteur_project.setPrenom(projectBdd.get_user().getPrenom());

					porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

					porteur_project.setUsername(projectBdd.get_user().getUsername());

					projectBdd.set_user(porteur_project);

					newVueProjectBdd.set_project(projectBdd);

					/***************************************************************/

					return newVueProjectBdd;

				} else {

					vueProjectModel updateVueProjectBdd = _vueProjectOpt.get();

					updateVueProjectBdd.setIp_adress(_vueProject.getIp_adress());

					updateVueProjectBdd.setDate_update(formater.format(aujourdhui));

					_vueProjectRepository.save(updateVueProjectBdd);

					/***************************************************************/

					User _manager_project = new User();

					_manager_project.setName(projectBdd.getManager_project().getName());

					_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

					_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

					_manager_project.setUsername(projectBdd.getManager_project().getUsername());

					projectBdd.setManager_project(_manager_project);

					User porteur_project = new User();

					porteur_project.setName(projectBdd.get_user().getName());

					porteur_project.setPrenom(projectBdd.get_user().getPrenom());

					porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

					porteur_project.setUsername(projectBdd.get_user().getUsername());

					projectBdd.set_user(porteur_project);

					updateVueProjectBdd.set_project(projectBdd);

					/***************************************************************/

					return updateVueProjectBdd;

				}

			} else {

				System.out.println("Le porteur de projet est egale de utilisateur courant");

				return null;
			}

		} else {

			return null;

		}

	}

	/***************************************************************************************/

	@Override
	public newsProjectModel createNewProject(UserPrincipal currentUser, String token_project,
			NewProjectRequest _newProject) {

		Instant instant = Instant.now();

		long _timestamp = instant.toEpochMilli();

		SimpleDateFormat formater = null;

		Date aujourdhui = new Date();

		formater = new SimpleDateFormat("EEEE, d MMM yyyy  'à' hh:mm:ss");

		// System.out.println(formater.format(aujourdhui));

		String tokenUserCurrent = currentUser.getToken();

		Optional<User> userCurrent = _userRepository.checkExistUserByToken(tokenUserCurrent);

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			User userCurrentBdd = userCurrent.get();

			String tokenUserProject = projectBdd.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (isEqual) {

				System.out.println("Le porteur de projet est celui de utilisateur courant");

				newsProjectModel newNewProjectBdd = new newsProjectModel();

				String newToken = methodesUtils.generateAlphanumericStringToken();

				newNewProjectBdd.setToken(newToken);

				newNewProjectBdd.set_project(projectBdd);

				newNewProjectBdd.setTimestamp(_timestamp);

				newNewProjectBdd.setDate_created(formater.format(aujourdhui));

				newNewProjectBdd.setPhotos(_newProject.getPhotos());

				newNewProjectBdd.setDescription(_newProject.getDescription());

				newNewProjectBdd.setTitre(_newProject.getTitre());

				_newsProjectRepository.save(newNewProjectBdd);

				/***************************************************************/

				User _manager_project = new User();

				_manager_project.setName(projectBdd.getManager_project().getName());

				_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

				_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

				_manager_project.setUsername(projectBdd.getManager_project().getUsername());

				projectBdd.setManager_project(_manager_project);

				User porteur_project = new User();

				porteur_project.setName(projectBdd.get_user().getName());

				porteur_project.setPrenom(projectBdd.get_user().getPrenom());

				porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

				porteur_project.setUsername(projectBdd.get_user().getUsername());

				projectBdd.set_user(porteur_project);

				newNewProjectBdd.set_project(projectBdd);

				/***************************************************************/

				return newNewProjectBdd;

			} else {

				System.out.println("Le porteur de projet est egale de utilisateur courant");

				return null;
			}

		} else {

			return null;

		}

	}

	@Override
	public newsProjectModel updateNewProject(UserPrincipal currentUser, String token_project, String token_news,
			NewProjectRequest _newProject) {

		SimpleDateFormat formater = null;

		Date aujourdhui = new Date();

		formater = new SimpleDateFormat("EEEE, d MMM yyyy  'à' hh:mm:ss");

		// System.out.println(formater.format(aujourdhui));

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			String tokenUserProject = projectBdd.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (isEqual) {

				System.out.println("Le porteur de projet est celui de utilisateur courant");

				Optional<newsProjectModel> _news_Project_Opt = _newsProjectRepository
						.findCheckNewsProjectByToken(token_news);

				if (_news_Project_Opt.isPresent()) {

					newsProjectModel _news_ProjectBdd = _news_Project_Opt.get();

					_news_ProjectBdd.setPhotos(_newProject.getPhotos());

					_news_ProjectBdd.setDescription(_newProject.getDescription());

					_news_ProjectBdd.setTitre(_newProject.getTitre());

					_news_ProjectBdd.setDate_update(formater.format(aujourdhui));

					_newsProjectRepository.save(_news_ProjectBdd);

					/***************************************************************/

					User _manager_project = new User();

					_manager_project.setName(projectBdd.getManager_project().getName());

					_manager_project.setPrenom(projectBdd.getManager_project().getPrenom());

					_manager_project.setPhotoUser(projectBdd.getManager_project().getPhotoUser());

					_manager_project.setUsername(projectBdd.getManager_project().getUsername());

					projectBdd.setManager_project(_manager_project);

					User porteur_project = new User();

					porteur_project.setName(projectBdd.get_user().getName());

					porteur_project.setPrenom(projectBdd.get_user().getPrenom());

					porteur_project.setPhotoUser(projectBdd.get_user().getPhotoUser());

					porteur_project.setUsername(projectBdd.get_user().getUsername());

					projectBdd.set_user(porteur_project);

					_news_ProjectBdd.set_project(projectBdd);

					/***************************************************************/

					return _news_ProjectBdd;

				} else {

					return null;
				}

			} else {

				System.out.println("Le porteur de projet est egale de utilisateur courant");

				return null;
			}

		} else {

			return null;

		}

	}

	@Override
	public Boolean deleteNewProject(UserPrincipal currentUser, String token_project, String token_news) {

		String tokenUserCurrent = currentUser.getToken();

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			project projectBdd = _project.get();

			String tokenUserProject = projectBdd.get_user().getToken();

			String token1 = new String(tokenUserCurrent);

			String token2 = new String(tokenUserProject);

			boolean isEqual = (token1.equals(token2));

			if (isEqual) {

				System.out.println("Le porteur de projet est celui de utilisateur courant");

				Optional<newsProjectModel> _news_Project_Opt = _newsProjectRepository
						.findCheckNewsProjectByToken(token_news);

				if (_news_Project_Opt.isPresent()) {

					newsProjectModel _news_ProjectBdd = _news_Project_Opt.get();

					_newsProjectRepository.deleteById(_news_ProjectBdd.getId());

					return true;

				} else {

					return false;
				}

			} else {

				System.out.println("Le porteur de projet est egale de utilisateur courant");

				return false;
			}

		} else {

			return false;

		}

	}

	@Override
	public List<newsProjectModel> getListNewsProject(@CurrentUser UserPrincipal currentUser, String token_project) {

		// System.out.println("entre-service");

		List<newsProjectModel> _listNewsProjectsBdd = _newsProjectRepository.findAllNewsProjectByToken(token_project);

		if (_listNewsProjectsBdd.size() > 0) {

			for (int index = 0; index < _listNewsProjectsBdd.size(); index++) {

				User _manager_project = new User();

				_manager_project.setName(_listNewsProjectsBdd.get(index).get_project().getManager_project().getName());

				_manager_project
						.setPrenom(_listNewsProjectsBdd.get(index).get_project().getManager_project().getPrenom());

				_manager_project.setPhotoUser(
						_listNewsProjectsBdd.get(index).get_project().getManager_project().getPhotoUser());

				_manager_project
						.setUsername(_listNewsProjectsBdd.get(index).get_project().getManager_project().getUsername());

				_listNewsProjectsBdd.get(index).get_project().setManager_project(_manager_project);

				User porteur_project = new User();

				porteur_project.setName(_listNewsProjectsBdd.get(index).get_project().get_user().getName());

				porteur_project
						.setPrenom(_listNewsProjectsBdd.get(index).get_project().get_user().getPrenom());

				porteur_project.setPhotoUser(
						_listNewsProjectsBdd.get(index).get_project().get_user().getPhotoUser());

				porteur_project
						.setUsername(_listNewsProjectsBdd.get(index).get_project().get_user().getUsername());

				_listNewsProjectsBdd.get(index).get_project().set_user(porteur_project);

			}

			return _listNewsProjectsBdd;

		} else {

			System.out.println("aucune news-projects-no-existe");

			return null;
		}

	}

	/***************************************************************************/

	@Override
	public List<StatistiquesChartsHeartsProjectModel> getListStatistiquesHeartsProject(@CurrentUser UserPrincipal currentUser, String token_project, TypeStatistiqeRequest _type_stat) {

		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			if (_type_stat.getType_statistique().equals(new String("ByMonthByYear"))
					|| _type_stat.getType_statistique().equals(new String("Last12Month"))) {

				List<StatistiquesChartsHeartsProjectModel> listStatistiquesHeartsProject = new ArrayList<StatistiquesChartsHeartsProjectModel>();

				if (_type_stat.getType_statistique().equals(new String("ByMonthByYear"))) {

					HashMap<Integer, String> nameDay = new HashMap<Integer, String>();

					int month = _type_stat.getMonth();

					int year = _type_stat.getYear();

					/************************ DATES ***************************************/

					if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
							|| month == 12) {

						for (int i = 1; i <= 31; i++) {

							nameDay.put(i, "Jour-" + i);

							// System.out.println("***numJour = ***" + i +"***month = ***" + month +
							// "***year = ***" + year);

						}

					}

					if (month == 2) {

						for (int i = 1; i <= 29; i++) {

							nameDay.put(i, "Jour-" + i);

							// System.out.println("***numJour = ***" + i +"***month = ***" + month +
							// "***year = ***" + year);

						}

					}

					if (month == 4 || month == 6 || month == 9 || month == 11) {

						for (int i = 1; i <= 30; i++) {

							nameDay.put(i, "Jour-" + i);

							// System.out.println("***numJour = ***" + i +"***month = ***" + month +
							// "***year = ***" + year);

						}

					}

					for (int i = 1; i <= nameDay.size(); i++) {

						System.out.println("***index = ***" + i + "***jour = ***" + nameDay.get(i) + "***month  = ***"
								+ month + "***year  = ***" + year);

						StatistiquesChartsHeartsProjectModel _statistiquesHeart_project = new StatistiquesChartsHeartsProjectModel();

						// _statistiquesHeart_project.setMonth(nameMonth.get(month) + '-'+ year);

						_statistiquesHeart_project.setYear(year);

						_statistiquesHeart_project.setDay(nameDay.get(i));

						int _nbrHeartsProject = _heartsProjectRepository.countHeartProjectByMonthANDYearByDays(i, month,
								year, token_project);

						_statistiquesHeart_project.setNbrHearts(_nbrHeartsProject);

						listStatistiquesHeartsProject.add(_statistiquesHeart_project);

						_statistiquesHeart_project = null;

					}
					

				}

				if (_type_stat.getType_statistique().equals(new String("Last12Month"))) {

					HashMap<Integer, String> nameMonth = new HashMap<Integer, String>();

					nameMonth.put(1, "Janv");

					nameMonth.put(2, "Fev");

					nameMonth.put(3, "Mars");

					nameMonth.put(4, "Avril");

					nameMonth.put(5, "Mai");

					nameMonth.put(6, "Juin");

					nameMonth.put(7, "Juil");

					nameMonth.put(8, "Aout");

					nameMonth.put(9, "Sept");

					nameMonth.put(10, "Oct");

					nameMonth.put(11, "Nov");

					nameMonth.put(12, "Dec");

					Calendar calendrier;

					calendrier = Calendar.getInstance();

					int monthCurrent = calendrier.get(Calendar.MONTH);

					int yearCurrent = calendrier.get(Calendar.YEAR);

					int month;

					int year;

					String numMonth;

					int _nbrHeartsProject;

					monthCurrent = monthCurrent + 1;

					// System.out.println("***month = = ***" + nameMonth.get(monthCurrent) +
					// "***year = ***" + yearCurrent);

					for (int i = 0; i < 12; i++) {

						if (monthCurrent - i <= 0) {

							month = 12 + (monthCurrent - i);

							year = yearCurrent - 1;

						} else {

							month = monthCurrent - i;

							year = yearCurrent;

						}

						if (month >= 1 && month <= 9) {

							numMonth = "0" + month;

						} else {

							numMonth = Integer.toString(month);

						}

						StatistiquesChartsHeartsProjectModel _statistiquesHeart_project = new StatistiquesChartsHeartsProjectModel();

						_statistiquesHeart_project.setMonth(nameMonth.get(month) + '-' + year);

						_statistiquesHeart_project.setYear(year);

						_nbrHeartsProject = _heartsProjectRepository.countHeartProjectByMonthANDYear(token_project,
								numMonth, year);

						_statistiquesHeart_project.setNbrHearts(_nbrHeartsProject);

						listStatistiquesHeartsProject.add(_statistiquesHeart_project);

						_statistiquesHeart_project = null;

						System.out.println(
								"month =" + nameMonth.get(month) + "/year  =" + year + "/numMth  =" + numMonth);

					}

					

				}
				
				return listStatistiquesHeartsProject;

			} else {

				return null;

			}

		} else {

			return null;
		}

	}

	@Override
	public List<StatistiquesChartsVueProjectModel> getListStatistiquesVuesProject(@CurrentUser UserPrincipal currentUser, String token_project, TypeStatistiqeRequest _type_stat) {
		
		
		
		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			if (_type_stat.getType_statistique().equals(new String("ByMonthByYear"))
					|| _type_stat.getType_statistique().equals(new String("Last12Month"))) {

				List<StatistiquesChartsVueProjectModel> listStatistiquesVuesProject = new ArrayList<StatistiquesChartsVueProjectModel>();

				if (_type_stat.getType_statistique().equals(new String("ByMonthByYear"))) {

					HashMap<Integer, String> nameDay = new HashMap<Integer, String>();

					int month = _type_stat.getMonth();

					int year = _type_stat.getYear();

					/************************ DATES ***************************************/

					if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
							|| month == 12) {

						for (int i = 1; i <= 31; i++) {

							nameDay.put(i, "Jour-" + i);

							// System.out.println("***numJour = ***" + i +"***month = ***" + month +
							// "***year = ***" + year);

						}

					}

					if (month == 2) {

						for (int i = 1; i <= 29; i++) {

							nameDay.put(i, "Jour-" + i);

							// System.out.println("***numJour = ***" + i +"***month = ***" + month +
							// "***year = ***" + year);

						}

					}

					if (month == 4 || month == 6 || month == 9 || month == 11) {

						for (int i = 1; i <= 30; i++) {

							nameDay.put(i, "Jour-" + i);

							// System.out.println("***numJour = ***" + i +"***month = ***" + month +
							// "***year = ***" + year);

						}

					}

					for (int i = 1; i <= nameDay.size(); i++) {

						System.out.println("***index = ***" + i + "***jour = ***" + nameDay.get(i) + "***month  = ***" + month
								+ "***year  = ***" + year);

						StatistiquesChartsVueProjectModel _statistiquesVues_project = new StatistiquesChartsVueProjectModel();

						// _statistiquesHeart_project.setMonth(nameMonth.get(month) + '-'+ year);

						_statistiquesVues_project.setYear(year);

						_statistiquesVues_project.setDay(nameDay.get(i));

						int _nbrVuesProject = _vueProjectRepository.countVuesProjectByMonthANDYearByDays(i, month, year,
								token_project);

						_statistiquesVues_project.setNbrVues(_nbrVuesProject);

						listStatistiquesVuesProject.add(_statistiquesVues_project);

						_statistiquesVues_project = null;

					}
					

				}

				if (_type_stat.getType_statistique().equals(new String("Last12Month"))) {

					HashMap<Integer, String> nameMonth = new HashMap<Integer, String>();

					nameMonth.put(1, "Janv");

					nameMonth.put(2, "Fev");

					nameMonth.put(3, "Mars");

					nameMonth.put(4, "Avril");

					nameMonth.put(5, "Mai");

					nameMonth.put(6, "Juin");

					nameMonth.put(7, "Juil");

					nameMonth.put(8, "Aout");

					nameMonth.put(9, "Sept");

					nameMonth.put(10, "Oct");

					nameMonth.put(11, "Nov");

					nameMonth.put(12, "Dec");

					Calendar calendrier;

					calendrier = Calendar.getInstance();

					int monthCurrent = calendrier.get(Calendar.MONTH);

					int yearCurrent = calendrier.get(Calendar.YEAR);

					int month;

					int year;

					String numMonth;

					int _nbrVuesProject ;

					monthCurrent = monthCurrent + 1;

					// System.out.println("***month = = ***" + nameMonth.get(monthCurrent) +
					// "***year = ***" + yearCurrent);

					for (int i = 0; i < 12; i++) {

						if (monthCurrent - i <= 0) {

							month = 12 + (monthCurrent - i);

							year = yearCurrent - 1;

						} else {

							month = monthCurrent - i;

							year = yearCurrent;

						}

						if (month >= 1 && month <= 9) {

							numMonth = "0" + month;

						} else {

							numMonth = Integer.toString(month);

						}

						StatistiquesChartsVueProjectModel _statistiquesvue_project = new StatistiquesChartsVueProjectModel();
				 		
				 		_statistiquesvue_project.setMonth(nameMonth.get(month) + '-'+ year);
				 		
				 		_statistiquesvue_project.setYear(year);
				 		
				 		_nbrVuesProject =  _vueProjectRepository.countVuesProjectByMonthANDYear(token_project, numMonth, year);   
				 		
				 		_statistiquesvue_project.setNbrVues(_nbrVuesProject);
				 		
				 		listStatistiquesVuesProject.add(_statistiquesvue_project);
				 		
				 		_statistiquesvue_project =null;
				 		 
				 		 System.out.println("month =" + nameMonth.get(month) + "/year  =" + year + "/numMth  =" + numMonth);

					}

					

				}
				
				return listStatistiquesVuesProject;

			} else {

				return null;

			}

		} else {

			return null;
		}
		


	}

	@Override
	public List<StatistiquesChartsLikeProjectModel> getListStatistiquesLikesProject(
			@CurrentUser UserPrincipal currentUser, String token_project, TypeStatistiqeRequest _type_stat) {
		
		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			if (_type_stat.getType_statistique().equals(new String("ByMonthByYear"))
					|| _type_stat.getType_statistique().equals(new String("Last12Month"))) {

				List<StatistiquesChartsLikeProjectModel> listStatistiquesLikesProject = new ArrayList<StatistiquesChartsLikeProjectModel>();

				if (_type_stat.getType_statistique().equals(new String("ByMonthByYear"))) {

					HashMap<Integer, String> nameDay = new HashMap<Integer, String>();

					int month = _type_stat.getMonth();

					int year = _type_stat.getYear();

					/************************ DATES ***************************************/

					if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
							|| month == 12) {

						for (int i = 1; i <= 31; i++) {

							nameDay.put(i, "Jour-" + i);

							// System.out.println("***numJour = ***" + i +"***month = ***" + month +
							// "***year = ***" + year);

						}

					}

					if (month == 2) {

						for (int i = 1; i <= 29; i++) {

							nameDay.put(i, "Jour-" + i);

							// System.out.println("***numJour = ***" + i +"***month = ***" + month +
							// "***year = ***" + year);

						}

					}

					if (month == 4 || month == 6 || month == 9 || month == 11) {

						for (int i = 1; i <= 30; i++) {

							nameDay.put(i, "Jour-" + i);

							// System.out.println("***numJour = ***" + i +"***month = ***" + month +
							// "***year = ***" + year);

						}

					}

					for (int i = 1; i <= nameDay.size(); i++) {

						// System.out.println("***jour = ***" + i + "***month = ***" + month + "***year
						// = ***" + year);

						StatistiquesChartsLikeProjectModel _statistiquesLike_project = new StatistiquesChartsLikeProjectModel();

						// _statistiquesHeart_project.setMonth(nameMonth.get(month) + '-'+ year);

						_statistiquesLike_project.setYear(year);

						_statistiquesLike_project.setDay(nameDay.get(i));

						int _nbrLikesProject = _likeDislikeProjectRepository.countLikeProjectByMonthANDYearByDays(i, month,
								year, token_project);

						_statistiquesLike_project.setNbrLikes(_nbrLikesProject);

						listStatistiquesLikesProject.add(_statistiquesLike_project);

						_statistiquesLike_project = null;

					}
					

				}

				if (_type_stat.getType_statistique().equals(new String("Last12Month"))) {

					HashMap<Integer, String> nameMonth = new HashMap<Integer, String>();

					nameMonth.put(1, "Janv");

					nameMonth.put(2, "Fev");

					nameMonth.put(3, "Mars");

					nameMonth.put(4, "Avril");

					nameMonth.put(5, "Mai");

					nameMonth.put(6, "Juin");

					nameMonth.put(7, "Juil");

					nameMonth.put(8, "Aout");

					nameMonth.put(9, "Sept");

					nameMonth.put(10, "Oct");

					nameMonth.put(11, "Nov");

					nameMonth.put(12, "Dec");

					Calendar calendrier;

					calendrier = Calendar.getInstance();

					int monthCurrent = calendrier.get(Calendar.MONTH);

					int yearCurrent = calendrier.get(Calendar.YEAR);

					int month;

					int year;

					String numMonth;

				 	int  _nbrLikesProject;
				 	   
				  	monthCurrent = monthCurrent +1 ;
				  	   
				  	//  System.out.println("***month = = ***" + nameMonth.get(monthCurrent)  + "***year  = ***" + yearCurrent);
				  	  
				  	  for (int i=0;i<12;i++) {  
				  		 		 
				  		
				  		 
				  		 if(monthCurrent-i<=0) {
				  			 
				  			 month = 12+(monthCurrent-i);
				  			 
				  		     year = yearCurrent-1;
				  			 
				  		 }else {
				  			 
				  			 month = monthCurrent-i;
				  			 
				 	 		 year = yearCurrent;
				  			 
				  		 }
				  		 
				  		 if(month>=1 && month<=9) {
				  			 
				  			numMonth = "0"+ month;
				  			 
				  		 }else {
				  			 
				  			numMonth = Integer.toString(month);
				  			 
				  		 }
				  		 
				  		StatistiquesChartsLikeProjectModel _statistiqueslike_project = new StatistiquesChartsLikeProjectModel();
				  		
				  		_statistiqueslike_project.setMonth(nameMonth.get(month) + '-'+ year);
				  		
				  		_statistiqueslike_project.setYear(year);
				  		
				  		_nbrLikesProject =  _likeDislikeProjectRepository.countLikesProjectByMonthANDYear(token_project, numMonth, year);   
				  		
				  		_statistiqueslike_project.setNbrLikes(_nbrLikesProject);
				  		
				  		listStatistiquesLikesProject.add(_statistiqueslike_project);
				  		
				  		_statistiqueslike_project =null;
				  		 
				  		 System.out.println("month =" + nameMonth.get(month) + "/year  =" + year + "/numMth  =" + numMonth);
				  		  
				  	  }

					

				}
				
				return listStatistiquesLikesProject;

			} else {

				return null;

			}

		} else {

			return null;
		}
		
		
		

	}

	@Override
	public List<StatistiquesChartsDislikeProjectModel> getListStatistiquesDisLikesProject(
			@CurrentUser UserPrincipal currentUser, String token_project, TypeStatistiqeRequest _type_stat) {
		
		Optional<project> _project = _projectRepository.findByToken(token_project);

		if (_project.isPresent()) {

			if (_type_stat.getType_statistique().equals(new String("ByMonthByYear"))
					|| _type_stat.getType_statistique().equals(new String("Last12Month"))) {

				List<StatistiquesChartsDislikeProjectModel> listStatistiquesDisLikesProject = new ArrayList<StatistiquesChartsDislikeProjectModel>();

				if (_type_stat.getType_statistique().equals(new String("ByMonthByYear"))) {

					HashMap<Integer, String> nameDay = new HashMap<Integer, String>();

					int month = _type_stat.getMonth();

					int year = _type_stat.getYear();

					/************************ DATES ***************************************/

					if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
							|| month == 12) {

						for (int i = 1; i <= 31; i++) {

							nameDay.put(i, "Jour-" + i);

							// System.out.println("***numJour = ***" + i +"***month = ***" + month +
							// "***year = ***" + year);

						}

					}

					if (month == 2) {

						for (int i = 1; i <= 29; i++) {

							nameDay.put(i, "Jour-" + i);

							// System.out.println("***numJour = ***" + i +"***month = ***" + month +
							// "***year = ***" + year);

						}

					}

					if (month == 4 || month == 6 || month == 9 || month == 11) {

						for (int i = 1; i <= 30; i++) {

							nameDay.put(i, "Jour-" + i);

							// System.out.println("***numJour = ***" + i +"***month = ***" + month +
							// "***year = ***" + year);

						}

					}

					for (int i = 1; i <= nameDay.size(); i++) {

						// System.out.println("***jour = ***" + i + "***month = ***" + month + "***year
						// = ***" + year);

						StatistiquesChartsDislikeProjectModel _statistiquesDislike_project = new StatistiquesChartsDislikeProjectModel();

						// _statistiquesHeart_project.setMonth(nameMonth.get(month) + '-'+ year);

						_statistiquesDislike_project.setYear(year);

						_statistiquesDislike_project.setDay(nameDay.get(i));

						int _nbrDislikesProject = _likeDislikeProjectRepository.countDislikeProjectByMonthANDYearByDays(i,month, year, token_project);

						_statistiquesDislike_project.setNbrDislikes(_nbrDislikesProject);

						listStatistiquesDisLikesProject.add(_statistiquesDislike_project);

						_statistiquesDislike_project = null;

					}
					

				}

				if (_type_stat.getType_statistique().equals(new String("Last12Month"))) {

					HashMap<Integer, String> nameMonth = new HashMap<Integer, String>();

					nameMonth.put(1, "Janv");

					nameMonth.put(2, "Fev");

					nameMonth.put(3, "Mars");

					nameMonth.put(4, "Avril");

					nameMonth.put(5, "Mai");

					nameMonth.put(6, "Juin");

					nameMonth.put(7, "Juil");

					nameMonth.put(8, "Aout");

					nameMonth.put(9, "Sept");

					nameMonth.put(10, "Oct");

					nameMonth.put(11, "Nov");

					nameMonth.put(12, "Dec");

					Calendar calendrier;

					calendrier = Calendar.getInstance();

					int monthCurrent = calendrier.get(Calendar.MONTH);

					int yearCurrent = calendrier.get(Calendar.YEAR);

					int month;

					int year;

					String numMonth;

				 	   int  _nbrDislikesProject;
				 	   
				  	  monthCurrent = monthCurrent +1 ;
				  	   
				  	//  System.out.println("***month = = ***" + nameMonth.get(monthCurrent)  + "***year  = ***" + yearCurrent);
				  	  
				  	  for (int i=0;i<12;i++) {  
				  		 		 
				  		
				  		 
				  		 if(monthCurrent-i<=0) {
				  			 
				  			 month = 12+(monthCurrent-i);
				  			 
				  		     year = yearCurrent-1;
				  			 
				  		 }else {
				  			 
				  			 month = monthCurrent-i;
				  			 
				 	 		 year = yearCurrent;
				  			 
				  		 }
				  		 
				  		 if(month>=1 && month<=9) {
				  			 
				  			numMonth = "0"+ month;
				  			 
				  		 }else {
				  			 
				  			numMonth = Integer.toString(month);
				  			 
				  		 }
				  		 
				  		StatistiquesChartsDislikeProjectModel _statistiquesDislikke_project = new StatistiquesChartsDislikeProjectModel();
				  		
				  		_statistiquesDislikke_project.setMonth(nameMonth.get(month) + '-'+ year);
				  		
				  		_statistiquesDislikke_project.setYear(year);
				  		
				  		_nbrDislikesProject =  _likeDislikeProjectRepository.countDislikesProjectByMonthANDYear(token_project, numMonth, year);   
				  		
				  		_statistiquesDislikke_project.setNbrDislikes(_nbrDislikesProject);
				  		
				  		listStatistiquesDisLikesProject.add(_statistiquesDislikke_project);
				  		
				  		_statistiquesDislikke_project =null;
				  		 
				  		 System.out.println("month =" + nameMonth.get(month) + "/year  =" + year + "/numMth  =" + numMonth);
				  		  
				  	  }

					

				}
				
				return listStatistiquesDisLikesProject;

			} else {

				return null;

			}

		} else {

			return null;
		}
	

	}

	/****************************************************************************************************/
	
	@Override
	public List<category_project> getListCategoriesProject() {		

		List<category_project> _listCategoryProjectsBdd = _categorieProjectRepository.findAll();
	

			return _listCategoryProjectsBdd;		

	}
	
	
	@Override
	public List<category_project> getListCustomCategoriesProject() {		

		List<category_project> _listCategoryProjectsBdd = _categorieProjectRepository.findCustumCategories();
	

			return _listCategoryProjectsBdd;		

	}
	
	/****************************************************************************************************/
	
	@Override
	public List<porte_project> getListPortesProject() {		

		List<porte_project> _listPortesProjectsBdd = _porteProjectRepository.findAll();
	

			return _listPortesProjectsBdd;		

	}
	
	
	
	/****************************************************************************************************/
	
	@Override
	public List<statutProject> getListStatusProject() {		

		List<statutProject> _listStatusProjectsBdd = _statutProjectRepository.findAll();
	

			return  _listStatusProjectsBdd;		

	}
	
	
	/****************************************************************************************************/
	
	
	@Override
	public List<ProjectResponseForVisitor> findAllProjectsByLikeTag(String tag) {		

		List<project> _projects_Bdd =  _projectRepository.findAllProjectsByLikeTag("%"+ tag +"%");
		
		List<ProjectResponseForVisitor> _projects = new ArrayList<ProjectResponseForVisitor>();
		
		for (int i = 0; i < _projects_Bdd.size(); i++) {			
			
			
			 UserResponse    _new_user =  new UserResponse(_projects_Bdd.get(i).get_user()); 		
			
			ProjectResponseForVisitor _project_response = new ProjectResponseForVisitor(_projects_Bdd.get(i),_new_user);
			
			_projects.add(_project_response);		
			
			
		}	

			return _projects;		

	}
	
	
	/****************************************************************************************************/
	
	@Override
	public List<ProjectResponseForVisitor> getAllProjectsByCategory(String nom_category) {	
		
         Optional<category_project> _category = _categorieProjectRepository.findCategorieByNom(nom_category);
    	
    	if(_category.isPresent()) { 
     		 
    		category_project categoryBdd = _category.get();
    		
    		Long id_category = categoryBdd.getId();
    		
    		List<project> _projects_Bdd =  _projectRepository.findAllProjectsByCategory(id_category);
    		
    		List<ProjectResponseForVisitor> _projects = new ArrayList<ProjectResponseForVisitor>();
    		
    		for (int i = 0; i < _projects_Bdd.size(); i++) {			
    			
    			
    			 UserResponse    _new_user =  new UserResponse(_projects_Bdd.get(i).get_user());      			
    			
    			ProjectResponseForVisitor _project_response = new ProjectResponseForVisitor(_projects_Bdd.get(i),_new_user);
    			
    			_projects.add(_project_response);		
    			
    			
    		}	

    			return _projects;	
    		
    		
    	}else {
    		
    		return null;
    	}			

	}
	
	@Override
	public List<ProjectResponseForVisitor> getRandomProjectsByCategory(String nom_category) {		

        Optional<category_project> _category = _categorieProjectRepository.findCategorieByNom(nom_category);
    	
      	if(_category.isPresent()) { 
    		 
   		   category_project categoryBdd = _category.get();
   		
   		   Long id_category = categoryBdd.getId();
   		
   		   List<project> _projects_Bdd =  _projectRepository.findCustumProjectsByCategory(id_category);
   		
   		   List<ProjectResponseForVisitor> _projects = new ArrayList<ProjectResponseForVisitor>();
   		
   		   for (int i = 0; i < _projects_Bdd.size(); i++) {			
   			
   			
   			    UserResponse    _new_user =  new UserResponse(_projects_Bdd.get(i).get_user());    			
   			    
   			    ProjectResponseForVisitor _project_response = new ProjectResponseForVisitor(_projects_Bdd.get(i),_new_user);
   			
   			    _projects.add(_project_response);		
   			
   			
   		 }	

   		 return _projects;	
   		
   		
   	}else {
   		
   		return null;
   	}	

  }
	
  
   @Override
   public List<ProjectResponseForVisitor> getAllProjectsTopConsulte() {		

		List<project> _projects_Bdd =  _projectRepository.findAllProjectsTopConsultation();
   		
		   List<ProjectResponseForVisitor> _projects = new ArrayList<ProjectResponseForVisitor>();
		
		   for (int i = 0; i < _projects_Bdd.size(); i++) {			
			
			
			    UserResponse    _new_user =  new UserResponse(_projects_Bdd.get(i).get_user()); 
			
			    
			    ProjectResponseForVisitor _project_response = new ProjectResponseForVisitor(_projects_Bdd.get(i),_new_user);
			
			    _projects.add(_project_response);		
			
			
		 }	

		 return _projects;	
		

  }
  
   @Override
   public List<ProjectResponseForVisitor> getAllProjectsTopHearts() {		

		List<project> _projects_Bdd =  _projectRepository.findAllProjectsTopHearts();
   		
		   List<ProjectResponseForVisitor> _projects = new ArrayList<ProjectResponseForVisitor>();
		
		   for (int i = 0; i < _projects_Bdd.size(); i++) {			
			
			
			   UserResponse    _new_user =  new UserResponse(_projects_Bdd.get(i).get_user()); 		
			    
			    
			   ProjectResponseForVisitor _project_response = new ProjectResponseForVisitor(_projects_Bdd.get(i),_new_user);
			
			    _projects.add(_project_response);		
			
			
		 }	

		 return _projects;	
		

  }
  
   @Override
   public List<ProjectResponseForVisitor> getAllProjectsTopLike() {		

		List<project> _projects_Bdd =  _projectRepository.findAllProjectsTopLikes();
   		
		   List<ProjectResponseForVisitor> _projects = new ArrayList<ProjectResponseForVisitor>();
		
		   for (int i = 0; i < _projects_Bdd.size(); i++) {			
			
			
			     UserResponse    _new_user =  new UserResponse(_projects_Bdd.get(i).get_user()); 		
			    
			    
			    ProjectResponseForVisitor _project_response = new ProjectResponseForVisitor(_projects_Bdd.get(i),_new_user);
			
			    _projects.add(_project_response);		
			
			
		 }	

		 return _projects;	
		

  }
  
   @Override
   public  ProjectResponseForVisitor  getRandomProject() {		

		  project _project_Bdd =  _projectRepository.findProjectRandom();
   		
		  UserResponse    _new_user =  new UserResponse(_project_Bdd.get_user());		  
          
          ProjectResponseForVisitor _project_response = new ProjectResponseForVisitor(_project_Bdd,_new_user);

		 return _project_response;	
		

  }	
  
   @Override
   public  ProjectResponseForVisitor  getInfosProjectForVisitor(String token_project) {		

	  Optional<project>  _projectOpt =  _projectRepository.findByToken(token_project);
	  
	  if(_projectOpt.isPresent()) {
		  
		  project _project_Bdd = _projectOpt.get();
		  
		  UserResponse    _new_user =   new UserResponse(_project_Bdd.get_user());	    
	      
	      ProjectResponseForVisitor _project_response = new ProjectResponseForVisitor(_project_Bdd,_new_user);
	      
	      /************************** list comments -projects *******************************/
	      
	      List<commentProject> _comments = _commentProjectRepository.findBy_project(_project_Bdd);

			if (_comments.size() > 0) {
				
				
				for (int index = 0; index < _comments.size(); index++) {					
					
					  UserResponse    _user_comment = new UserResponse(_comments.get(index).get_user());					
					
					
					  CommentProjectResponse  _comment_response = new CommentProjectResponse(_comments.get(index),_user_comment);			
					
					
					  _project_response.get_comments().add(_comment_response);

				}
	      
			}
	      
	      /************************list news-project ********************************/
			
			List<newsProjectModel> _listNewsProjectsBdd = _newsProjectRepository.findAllNewsProjectByToken(token_project);

			if (_listNewsProjectsBdd.size() > 0) {

				for (int index = 0; index < _listNewsProjectsBdd.size(); index++) {

					NewsProjectResponse  _news_response = new NewsProjectResponse(_listNewsProjectsBdd.get(index));				
					
					_project_response.get_news_project().add(_news_response);
					
					
				}				

			}		
			
			
			/*************************list Links-images-projects****************************************/
			
			List<linkImageProject> _links = _linkImagesProjectRepository.findBy_project(_project_Bdd);

			if (_links.size() > 0) {

				for (int index = 0; index < _links.size(); index++) {

					LinkImageProjectResponse  _link_image_response = new LinkImageProjectResponse(_links.get(index));				
					
					
					_project_response.get_links_images().add(_link_image_response);				
				}		

			}
			
		      /***************************list adress-sociaux by project  n'est aps visible  au visiteur ***************************/
			
		/*	List<adressReseauxSociauxProject> _list_adresse_social = _adressreseauxSocialRepository.findBy_project(_project_Bdd);

			if (_list_adresse_social.size() > 0) {

				for (int index = 0; index < _list_adresse_social.size(); index++) {

					AdressReseauxSociauxProjectResponse    _adresse_social_response = this.getAdressReseauxSociauxProjectResponse(_list_adresse_social.get(index));				
					
					
					_project_response.get_adress_sociaux_project().add(_adresse_social_response);				
				}		

			}*/
			
			
		/***********************************************************************************************/

		 return _project_response;	
		  
	  }else {
		  
		  
		  return null;
	  }	 
	
}	
	
	/***********************************************************************************************/

}
