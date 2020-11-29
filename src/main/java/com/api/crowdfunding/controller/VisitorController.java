package com.api.crowdfunding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.crowdfunding.services.ProjectService;
import com.api.crowdfunding.services.UserService;
import com.api.crowdfunding.model.category_project;
import com.api.crowdfunding.model.porte_project;
import com.api.crowdfunding.model.project;
import com.api.crowdfunding.model.statutProject;
import com.api.crowdfunding.payload.ApiResponse;
import com.api.crowdfunding.payload.MessageContactRequest;
import com.api.crowdfunding.payload.ProjectResponseForVisitor;
import com.api.crowdfunding.payload.ProjetRequest;

import java.util.List;

import javax.validation.Valid;



@RestController
@RequestMapping("/api/visitor")
public class VisitorController {

	@Autowired
	ProjectService _projectService;
	
	@Autowired
	UserService _userService;

	private static final Logger logger = LoggerFactory.getLogger(ProjectsController.class);


	
	@GetMapping(value = "all_categories_project")	
	@ResponseBody
	public ResponseEntity<List<category_project>> getListCategoriesProject() {

		List<category_project> _listCategorieProjectBdd = _projectService.getListCategoriesProject();
		 
		if (_listCategorieProjectBdd != null) {

			return ResponseEntity.ok(_listCategorieProjectBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	@GetMapping(value = "custom_categories_project")	
	@ResponseBody
	public ResponseEntity<List<category_project>> getListCustomCategoriesProject() {

		List<category_project> _listCategorieProjectBdd = _projectService.getListCustomCategoriesProject();
		 
		if (_listCategorieProjectBdd != null) {

			return ResponseEntity.ok(_listCategorieProjectBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	    /********************************************************************/    
	

	@GetMapping(value = "all_portes_project")	
	@ResponseBody
	public ResponseEntity<List<porte_project>> getListPortesProject() {

		List<porte_project> _listPortesProjectBdd = _projectService.getListPortesProject();
		 
		if (_listPortesProjectBdd != null) {

			return ResponseEntity.ok(_listPortesProjectBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}	
	
	  /********************************************************************/    
	
	@GetMapping(value = "all_status_project")	
	@ResponseBody
	public ResponseEntity<List<statutProject>> getListStatusProject() {

		List<statutProject> _listStatusProjectBdd = _projectService.getListStatusProject();
		 
		if (_listStatusProjectBdd != null) {

			return ResponseEntity.ok(_listStatusProjectBdd);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}	
	
	
	  /********************************************************************/   
	
	@GetMapping(value = "projects/search_by_tag/{tag}")	
	@ResponseBody
	public ResponseEntity<List<ProjectResponseForVisitor>> searchProjectsByTag(@PathVariable  String tag) {

		List<ProjectResponseForVisitor> _projects = _projectService.findAllProjectsByLikeTag(tag);

		if (_projects != null) {

			return ResponseEntity.ok(_projects);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	  /********************************************************************/  
	
	@GetMapping(value = "projects/{nom_category}")	
	@ResponseBody
	public ResponseEntity<List<ProjectResponseForVisitor>> getAllProjectsByCategory(@PathVariable  String nom_category) {

		List<ProjectResponseForVisitor> _projects = _projectService.getAllProjectsByCategory(nom_category);

		if (_projects != null) {

			return ResponseEntity.ok(_projects);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping(value = "custum_projects/{nom_category}")	
	@ResponseBody
	public ResponseEntity<List<ProjectResponseForVisitor>> getRandomProjectsByCategory(@PathVariable  String nom_category) {

		List<ProjectResponseForVisitor> _projects = _projectService.getRandomProjectsByCategory(nom_category);

		if (_projects != null) {

			return ResponseEntity.ok(_projects);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping(value = "projects_top_consulte")	
	@ResponseBody
	public ResponseEntity<List<ProjectResponseForVisitor>> getAllProjectsTopConsulte() {

		List<ProjectResponseForVisitor> _projects = _projectService.getAllProjectsTopConsulte();

		if (_projects != null) {

			return ResponseEntity.ok(_projects);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping(value = "projects_top_hearts")	
	@ResponseBody
	public ResponseEntity<List<ProjectResponseForVisitor>>  getAllProjectsTopHearts() {

		List<ProjectResponseForVisitor> _projects = _projectService.getAllProjectsTopHearts();

		if (_projects != null) {

			return ResponseEntity.ok(_projects);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping(value = "projects_top_like")	
	@ResponseBody
	public ResponseEntity<List<ProjectResponseForVisitor>> getAllProjectsTopLike() {

		List<ProjectResponseForVisitor> _projects = _projectService.getAllProjectsTopLike();

		if (_projects != null) {

			return ResponseEntity.ok(_projects);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	@GetMapping(value = "random_project")	
	@ResponseBody
	public ResponseEntity<ProjectResponseForVisitor> getRandomProject() {

		ProjectResponseForVisitor _project = _projectService.getRandomProject();

		if (_project != null) {

			return ResponseEntity.ok(_project);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping(value = "data_project/{token_project}")	
	@ResponseBody
	public ResponseEntity<ProjectResponseForVisitor> getInfosProjectForVisitor(@PathVariable  String token_project) {

		ProjectResponseForVisitor _project = _projectService.getInfosProjectForVisitor(token_project);

		if (_project != null) {

			return ResponseEntity.ok(_project);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	
	  /********************************************************************/  
	
	@PostMapping(value = "create_message_contact")	
	@ResponseBody
	public ResponseEntity<?> createMessageContactForVisitor(@Valid @RequestBody  MessageContactRequest  _message) {

		Boolean checkSaveMessage = _userService.createMessageContactForVisitor(_message);

		if (checkSaveMessage) {

			return ResponseEntity.ok(new ApiResponse(true, "Send Message Successfully"));

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}

