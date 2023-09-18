package com.project.googleoauthimpl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class OAuthConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf->csrf.disable())
		.cors(withDefaults())
		.authorizeHttpRequests(auth->auth.requestMatchers("/getResource","/login").authenticated())
		.oauth2Login();
//		.oauth2Client(withDefaults());
		return http.build();
	}
	
	@Bean
	  CorsConfigurationSource corsConfigurationSource() {
	      CorsConfiguration configuration = new CorsConfiguration();
	      configuration.setAllowedOrigins(Arrays.asList("*"));
	      configuration.setAllowedMethods(Arrays.asList("POST", "PUT", "GET", "OPTIONS", "DELETE", "PATCH")); // or simply "*"
	      configuration.setAllowedHeaders(Arrays.asList("*"));
	      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	      source.registerCorsConfiguration("/**", configuration);
	      return source;
	  }
	
	@Bean
	public OAuth2AuthorizedClientManager authorizedClientManager(
	        ClientRegistrationRepository clientRegistrationRepository,
	        OAuth2AuthorizedClientRepository authorizedClientRepository) {
	    OAuth2AuthorizedClientProvider authorizedClientProvider =
	        OAuth2AuthorizedClientProviderBuilder.builder()
	            .authorizationCode()
	            .refreshToken()
	            .build();

	    DefaultOAuth2AuthorizedClientManager authorizedClientManager =
	        new DefaultOAuth2AuthorizedClientManager(
	            clientRegistrationRepository, authorizedClientRepository);
	    authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

	    return authorizedClientManager;
	}
}
