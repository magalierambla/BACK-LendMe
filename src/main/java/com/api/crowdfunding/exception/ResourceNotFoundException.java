package com.api.crowdfunding.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.api.crowdfunding.controller.ProjectsController;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
    private String resourceName;
    
    private String fieldName;
    
    private Object fieldValue;
    
    private static final Logger logger = LoggerFactory.getLogger(ResourceNotFoundException.class);


    public ResourceNotFoundException( String resourceName, String fieldName, Object fieldValue) {
    	
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        
        this.resourceName = resourceName;
        
        this.fieldName = fieldName;
        
        this.fieldValue = fieldValue;
        
        logger.error("System Error-ResourceNotFoundException:");		 
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
