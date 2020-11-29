package com.api.crowdfunding.controller;

import static org.junit.Assert.assertNotNull;

import java.awt.PageAttributes.MediaType;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.crowdfunding.ComApiCrowdfunding;
import com.api.crowdfunding.model.adressReseauxSociauxProject;
import com.api.crowdfunding.model.project;
import com.api.crowdfunding.payload.AdressReseauxSociauxProjectRequest;
import com.api.crowdfunding.payload.ApiResponse;

import org.junit.Assert;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComApiCrowdfunding.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerTest {
	
	final String   Bearer_user = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5IiwiaWF0IjoxNjA1ODk4NTIzLCJleHAiOjE2MDY1MDMzMjN9.0gcI78YbW5cJ0wY7FpbvA1GKrgnlrfSVjM4NUwrvgCWbPgCrQqlCdG7Qnz8ctCe06vrFp5LYA35Blc5EFcSe4A"; 
	
	final String   Bearer_porteur = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1IiwiaWF0IjoxNjA1OTM4MzUzLCJleHAiOjE2MDY1NDMxNTN9.1Tk3uTV7wWsgfKjiGn_ddquxl8zigoBavmsUuqv6vtxhhwIiL_sSYUsK5Tfp5xgx2di1Nc4_k9tf_-qM_Gr8yA"; 
	
	@Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {   }
    
    @Test
    public void testGetAllCategories() {
    	
       HttpHeaders headers = new HttpHeaders();
       
       HttpEntity<String> entity = new HttpEntity<String>(null, headers);
       
       ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/visitor/all_categories_project",HttpMethod.GET, entity, String.class);  
       
       assertNotNull(response.getBody());
   }
    
 /*   @Test    
    public void testGetProjectByToken() {    	

    	HttpHeaders headers = new HttpHeaders();
    	
    	headers.set("Authorization", "Bearer "+ Bearer_user);
    	
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        
        project response = restTemplate.postForObject(getRootUrl() + "/api/projects/users/all_projects/VxEck1qxpgawHcnqNRkEsWJkjxHMalYNdVXppUuIe60THMx581C9HPiIhcOQ",entity, project.class);
        
        System.out.println("response.getToken() = " + response.getAfficheProject());
        
        assertNotNull(response);   	
    	
    	
    }
    
    
    
      @Test    
    public void testCreateLinkSocialByProject() {    	

    	HttpHeaders headers = new HttpHeaders();
    	
    	headers.set("Authorization", "Bearer "+ Bearer_porteur);
    	
    	AdressReseauxSociauxProjectRequest _linkSocialReq = new AdressReseauxSociauxProjectRequest();    	
    	
    	_linkSocialReq.setKeyMedia("key-maedia-test");
    	
    	_linkSocialReq.setLinkProject("link-project-test");
    	
    	_linkSocialReq.setValueMedia("value-media-test");
    	
        HttpEntity<AdressReseauxSociauxProjectRequest> entity = new HttpEntity<AdressReseauxSociauxProjectRequest>(_linkSocialReq, headers);
        
        adressReseauxSociauxProject _adress_social = restTemplate.postForObject(getRootUrl() + "/api/projects/users/VxEck1qxpgawHcnqNRkEsWJkjxHMalYNdVXppUuIe60THMx581C9HPiIhcOQ/adresses_sociaux/create",entity, adressReseauxSociauxProject.class);
        
        System.out.println("_adress_social.getToken() = " + _adress_social.getToken());
        
        System.out.println("_adress_social.getValueMedia() = " + _adress_social.getValueMedia());
        
        assertNotNull(_adress_social);  
        
       
    	
    	
    }
    
    @Test    
    public void testCreateLinkSocialByProjectSuccess() {    	

    	HttpHeaders headers = new HttpHeaders();
    	
    	headers.set("Authorization", "Bearer "+ Bearer_porteur);
    	
    	AdressReseauxSociauxProjectRequest _linkSocialReq = new AdressReseauxSociauxProjectRequest();    	
    	
    	_linkSocialReq.setKeyMedia("key-maedia-test");
    	
    	_linkSocialReq.setLinkProject("link-project-test");
    	
    	_linkSocialReq.setValueMedia("value-media-test");
    	
    	HttpEntity<AdressReseauxSociauxProjectRequest> request = new HttpEntity<>(_linkSocialReq, headers);
        
        ResponseEntity<String> result = this.restTemplate.postForEntity(getRootUrl() + "/api/projects/users/VxEck1qxpgawHcnqNRkEsWJkjxHMalYNdVXppUuIe60THMx581C9HPiIhcOQ/adresses_sociaux/create", request, String.class);
         
        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());  	
    	
    }
    
    @Test
    public void testDeleteLinkSocial() {
    	
         String toekn_link = "jUCcoGqEl1G1JBjc1AGtGqhAzlRHqQRHGcFgdbd0qa0Vgkj5rOkozS3rdBLw";   
         
         HttpHeaders headers = new HttpHeaders();
     	
     	 headers.set("Authorization", "Bearer "+ Bearer_porteur);
     	 
     	ApiResponse  response = new ApiResponse(true, "Link social delete Successfully");
     	
        HttpEntity<ApiResponse> request = new HttpEntity<>(null, headers);
        
        ResponseEntity<ApiResponse> result = this.restTemplate.postForEntity(getRootUrl() + "/api/projects/users/VxEck1qxpgawHcnqNRkEsWJkjxHMalYNdVXppUuIe60THMx581C9HPiIhcOQ/adresses_sociaux/"+toekn_link+"/delete", request, ApiResponse.class);
        
        System.out.println("result.getStatusCodeValue() = " + result.getStatusCodeValue());         
         
        assertNotNull(result);
    }*/

}
