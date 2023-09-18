package com.project.googleoauthimpl.controller;

import java.util.Map;

import org.springframework.security.oauth2.client.OAuth2AuthorizationContext;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WebController {
	
	
	@GetMapping("getResource")
	public String getResource() {
		System.out.println(OAuth2AuthorizationContext.USERNAME_ATTRIBUTE_NAME);
		return "Success";
	}
	
	@GetMapping("login")
	public Map<String, Object> login(OAuth2AuthenticationToken oAuth2AuthenticationToken){
		return oAuth2AuthenticationToken.getPrincipal().getAttributes();
	}
	
	@GetMapping("unauth")
	public String getUnprotectedData(){
		return "Data";
	}
}
