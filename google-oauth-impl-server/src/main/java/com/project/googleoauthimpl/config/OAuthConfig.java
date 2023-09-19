package com.project.googleoauthimpl.config;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.project.googleoauthimpl.filters.OAuthResponseFilter;
import com.project.googleoauthimpl.handler.OAuth2AuthenticationSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class OAuthConfig {
	
	@Bean
	public OAuthResponseFilter oAuthResponseFilter() {
		return new OAuthResponseFilter();
	}
	@Autowired
	private CORSCustomiser corsCustomiser;
	
	@Autowired
	private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
	
	 
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		corsCustomiser.corsCustomiser(http);
		http.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth->auth.requestMatchers("/getResource","/login").authenticated().anyRequest().permitAll())
		.addFilterAfter(oAuthResponseFilter(), OAuth2AuthorizationRequestRedirectFilter.class)
		.oauth2Login()
		.defaultSuccessUrl("/getResource")
		.successHandler(oAuth2AuthenticationSuccessHandler);
		return http.build();
	}
	
//	@Bean
//	  CorsConfigurationSource corsConfigurationSource() {
//	      CorsConfiguration configuration = new CorsConfiguration();
//	      configuration.setAllowedOrigins(Arrays.asList("*"));
//	      configuration.setAllowedMethods(Arrays.asList("POST", "PUT", "GET", "OPTIONS", "DELETE", "PATCH")); // or simply "*"
//	      configuration.setAllowedHeaders(Arrays.asList("*"));
//	      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	      source.registerCorsConfiguration("/**", configuration);
//	      return source;
//	  }
	
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
