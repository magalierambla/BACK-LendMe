package com.api.crowdfunding.payload;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.crowdfunding.enumapp.sexUser;
import com.api.crowdfunding.exception.AppException;
import com.api.crowdfunding.functionsUtils.methodesUtils;
import com.api.crowdfunding.model.Role;
import com.api.crowdfunding.model.RoleName;
import com.api.crowdfunding.model.User;
import com.api.crowdfunding.model.category_project;
import com.api.crowdfunding.model.porte_project;
import com.api.crowdfunding.model.statutProject;
import com.api.crowdfunding.repository.RoleRepository;
import com.api.crowdfunding.repository.UserRepository;
import com.api.crowdfunding.repository.categoryProjectRepository;
import com.api.crowdfunding.repository.porteProjectRepository;
import com.api.crowdfunding.repository.statutProjectRepository;



@Service
public  class IntitialisationDataProject {
	
	
	String[] listCategorie = {"Art & Photo","BD","Enfance & Educ.","Artisanat & Cuisine","Film et vidéo","Sports","Santé & Bien-être","Technologie","Autres projets"};
	
	String[] listStatut = {"Attente","Valide","Annule","En cours","Termine","Renouvele"};
	
	String[] listPorteProject = {"moi-même","mon association","mon entreprise"};
	
	String[] listSuperAdmin = {"super_admin_1@yopmail.com"};
	
	String[] listManagers = {"manager_1@yopmail.com"};
	
    @Autowired
    private porteProjectRepository  _porteProjectRepository;
    
    @Autowired
    private categoryProjectRepository  _categorieProjectRepository;
    
    
    @Autowired
    private statutProjectRepository  _statutProjectRepository;
    
    @Autowired
    private UserRepository   _userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleRepository roleRepository;
    
    
    
    
    public void Intitialisation() throws Exception {
    	
    	
    	IntitialisationDataCategoryProject();
    	
    	IntitialisationDataStatutsProject();
    	
    	IntitialisationDataPortesProject();
    	
    	IntitialisationDataProfilSperAdminProject();
    	
    	IntitialisationDataProfilManagerProject();
    	
    	System.out.println("***Generated-Data-Project***");
    	
    	
    }
    

	
	
	public void IntitialisationDataCategoryProject() {	
		
       
		
		for(int i=0; i<listCategorie.length; i++) { 
			
			category_project categorieProject = new category_project();		
			
			
			 Optional<category_project>  categorieBdd = _categorieProjectRepository.findByNom(listCategorie[i]);
			 
			 if(!categorieBdd.isPresent()) { 
				 
				 categorieProject.setNom(listCategorie[i]);
				 
				 _categorieProjectRepository.save(categorieProject);       
				 
			 }		
			
		
		} 
		
	}
	
	public void IntitialisationDataStatutsProject() {	
		
       
		
		for(int i=0; i<listStatut.length; i++) { 
			
			statutProject statutProject = new statutProject();		
			
			
			 Optional<statutProject>  statutBdd = _statutProjectRepository.findByNom(listStatut[i]);
			 
			 if(!statutBdd.isPresent()) { 
				 
				 statutProject.setNom(listStatut[i]);
				 
				 _statutProjectRepository.save(statutProject);       
				 
			 }		
			
		
		} 	
		
	}
	
	public void IntitialisationDataPortesProject() {	
		
       
		
		for(int i=0; i<listPorteProject.length; i++) { 
			
			porte_project porteProject = new porte_project();		
			
			
			 Optional<porte_project>  porteProjectBdd = _porteProjectRepository.findByNom(listPorteProject[i]);
			 
			 if(!porteProjectBdd.isPresent()) { 
				 
				 porteProject.setNom(listPorteProject[i]);
				 
				 _porteProjectRepository.save(porteProject);       
				 
			 }		
			
		
		} 		
		
	}
	
	public void IntitialisationDataProfilManagerProject() throws Exception {	
		
       
		
		for(int i=0; i<listManagers.length; i++) { 		
			
			
			
			Boolean checkPresentAdmin = _userRepository.existsByEmail(listManagers[i]);
			 
			 if(!checkPresentAdmin) { 
		
				 
				   sexUser _sex = sexUser.F;
				 
			   	   String token_user = methodesUtils.generateAlphanumericStringToken();   
			        
			       String  token_confir_register = methodesUtils.generateAlphanumericStringToken();   
			        
			        Instant instant = Instant.now();
					
				    long timestamp_expire_token_confirm_register = instant.toEpochMilli() + 86400000;	 			    
				 
				    
				    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				    
			        
			        User user = new User("Rambla","Magalie",_sex,formatter.parse("1978-03-17"),token_user,"Mag",listManagers[i],"123456");
			        
			        user.setTimestamp_expire_token_confirm_register(timestamp_expire_token_confirm_register);
			        
			        user.setToken_confir_register(token_confir_register);

			        user.setPhotoUser("https://i.imgur.com/Ne454Ai.jpg");
			        
			        user.setIs_active(1);
			        
			        user.setPassword(passwordEncoder.encode("123456"));

			        Role userRole = roleRepository.findByName(RoleName.ROLE_MANAGER)
			                .orElseThrow(() -> new AppException("User Role not set."));

			        user.setRoles(Collections.singleton(userRole));

			        User result = _userRepository.save(user);
				 
			 }		
			
		
		} 	
		
		
	}
	
	public void IntitialisationDataProfilSperAdminProject() throws Exception {	
		
       
		
		for(int i=0; i<listSuperAdmin.length; i++) { 		
			
			
			
			Boolean checkPresentAdmin = _userRepository.existsByEmail(listSuperAdmin[i]);
			 
			 if(!checkPresentAdmin) { 
		
				 
				   sexUser _sex = sexUser.H;
				 
			   	   String token_user = methodesUtils.generateAlphanumericStringToken();   
			        
			       String  token_confir_register = methodesUtils.generateAlphanumericStringToken();   
			        
			        Instant instant = Instant.now();
					
				    long timestamp_expire_token_confirm_register = instant.toEpochMilli() + 86400000;	 			    
				 
				    
				    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				    
			        
			        User user = new User("Chirac","Patrick",_sex,formatter.parse("1972-03-15"),token_user,"Super-admin",listSuperAdmin[i],"123456");
			        
			        user.setTimestamp_expire_token_confirm_register(timestamp_expire_token_confirm_register);
			        
			        user.setToken_confir_register(token_confir_register);

			        user.setPhotoUser("https://i.imgur.com/Ne454Ai.jpg");
			        
			        user.setIs_active(1);
			        
			        user.setPassword(passwordEncoder.encode("123456"));

			        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
			                .orElseThrow(() -> new AppException("User Role not set."));

			        user.setRoles(Collections.singleton(userRole));

			        User result = _userRepository.save(user);
				 
			 }		
			
		
		} 	
		
		
	}

}
