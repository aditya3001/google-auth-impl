package com.project.googleoauthimpl.config;

import java.util.List;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Component
public class CORSCustomiser {
	
	public void corsCustomiser(HttpSecurity http) throws Exception{
		http.cors(c->{
			CorsConfigurationSource source = s->{
				CorsConfiguration cc = new CorsConfiguration();
				cc.setAllowCredentials(true);
				cc.setAllowedOrigins(List.of("http://localhost:3000"));
				cc.setAllowedMethods(List.of("*"));
				cc.setAllowedHeaders(List.of("*"));
				return cc;
			};
			c.configurationSource(source);
		});
	}

}
