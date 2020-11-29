package com.api.crowdfunding.controller;

import com.api.crowdfunding.exception.AppException;
import com.api.crowdfunding.functionsUtils.methodesUtils;
import com.api.crowdfunding.model.Role;
import com.api.crowdfunding.model.RoleName;
import com.api.crowdfunding.model.User;
import com.api.crowdfunding.payload.ApiResponse;
import com.api.crowdfunding.payload.JwtAuthenticationResponse;
import com.api.crowdfunding.payload.LoginRequest;
import com.api.crowdfunding.payload.SignUpRequest;
import com.api.crowdfunding.repository.RoleRepository;
import com.api.crowdfunding.repository.UserRepository;
import com.api.crowdfunding.security.JwtTokenProvider;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Instant;
import java.util.Collections;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        
        String token_user = methodesUtils.generateAlphanumericStringToken();   
        
        String  token_confir_register = methodesUtils.generateAlphanumericStringToken();   
        
        Instant instant = Instant.now();
		
	    long timestamp_expire_token_confirm_register = instant.toEpochMilli() + 86400000;	    
	    
        
        User user = new User(signUpRequest.getName(),signUpRequest.getPrenom(),signUpRequest.getSex(),signUpRequest.getDate_naissance(),token_user,signUpRequest.getUsername(),signUpRequest.getEmail(), signUpRequest.getPassword());
        
        user.setTimestamp_expire_token_confirm_register(timestamp_expire_token_confirm_register);
        
        user.setToken_confir_register(token_confir_register);


        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);
        
        /**********************************************************************/
        
        String USER__AGENT = "Mozilla/5.0";
		
	    String url = "https://mail-server-api.herokuapp.com/api/sendMailConfirmationInscription?token="+token_confir_register + "&email=" + user.getEmail();

        try {
        	
        	URL obj = new URL(url);
			
			 try {
				 
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				
			    con.setRequestMethod("GET");
		         
		        con.setRequestProperty("User-Agent", USER__AGENT);

		        int responseCode = con.getResponseCode();
		          
		        System.out.println("\nSending 'GET' request to URL : " + url);
		          
		        System.out.println("Response Code : " + responseCode);
		        
		        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		          
		        String inputLine;
		         
		        StringBuffer response = new StringBuffer();
		        
		        while ((inputLine = in.readLine()) != null) {
		          	
		              response.append(inputLine);
		          }
		         
		  		
		  		JSONObject resp = null;
		  		
				try {
					
					resp = new JSONObject(response.toString());
					
				} catch (JSONException e) {					
					e.printStackTrace();
				}
		  		
		  		try {
		  			
					if((boolean) resp.get("resMail")) {
						
						System.out.println(resp.get("resMail"));
					}
					
				} catch (JSONException e) {					
					e.printStackTrace();
				}    
		  		
		  		
		  	    in.close();  
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			 
			 
		} catch (MalformedURLException e) {		
			e.printStackTrace();
		}
        
        /****************************************************************/


        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
