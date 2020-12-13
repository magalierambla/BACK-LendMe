package com.api.crowdfunding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.api.crowdfunding.enumapp.sexUser;
import com.api.crowdfunding.exception.AppException;
import com.api.crowdfunding.functionsUtils.methodesUtils;
import com.api.crowdfunding.model.Role;
import com.api.crowdfunding.model.RoleName;
import com.api.crowdfunding.model.User;
import com.api.crowdfunding.model.category_project;
import com.api.crowdfunding.model.porte_project;
import com.api.crowdfunding.model.statutProject;
import com.api.crowdfunding.payload.IntitialisationDataProject;
import com.api.crowdfunding.repository.RoleRepository;
import com.api.crowdfunding.repository.UserRepository;
import com.api.crowdfunding.repository.categoryProjectRepository;
import com.api.crowdfunding.repository.porteProjectRepository;
import com.api.crowdfunding.repository.projectRepository;
import com.api.crowdfunding.repository.statutProjectRepository;
import com.api.crowdfunding.services.ProjectService;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Collections;
import java.util.Optional;
import java.util.TimeZone;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
@EntityScan(basePackageClasses = {
		ComApiCrowdfunding.class,
		Jsr310JpaConverters.class
})
public class ComApiCrowdfunding implements ApplicationRunner {


	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
  	 

	@Autowired
	IntitialisationDataProject   _Service_initialisation_data;
	

	public static void main(String[] args) {
		SpringApplication.run(ComApiCrowdfunding.class, args);
	}
	
	@Override
    public void run(ApplicationArguments args) throws Exception {
		
		
		
		_Service_initialisation_data.Intitialisation();    
		
		
	}
	
	
	public static ApiInfo metadata(){
	    return new ApiInfoBuilder()
	            .title("Api-Crowdfunding")
	            .description("Api-Crowdfunding")
	            .version("1.x")
	            .build();
	}
	
	
	 @Bean
	 public Docket productApi() {
		 
	      return new Docket(DocumentationType.SWAGGER_2).apiInfo(metadata()).select().apis(RequestHandlerSelectors.basePackage("com.api.crowdfunding")).build();
	 }



}
