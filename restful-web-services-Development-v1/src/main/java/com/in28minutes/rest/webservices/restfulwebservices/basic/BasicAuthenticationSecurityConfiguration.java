//http://localhost:8080/h2-console
//package com.in28minutes.rest.webservices.restfulwebservices.basic;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// * Configures basic authentication security for the application.
// */
//@Configuration
//public class BasicAuthenticationSecurityConfiguration {
//
//    /**
//     * Configures the security filter chain for the application.
//     *
//     * @param http the HTTP security object
//     * @return the security filter chain
//     * @throws Exception if an error occurs during configuration
//     */
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .cors() // Enable CORS
//                .and()
//                .authorizeHttpRequests(authorize ->
//                        authorize
//                                .antMatchers("/h2-console/**").permitAll() // Allow access to H2 Console
//                                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow OPTIONS requests for all endpoints
//                                .anyRequest().authenticated() // Require authentication for all other requests
//                )
//                .httpBasic(Customizer.withDefaults()) // Configure basic authentication
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Disable session creation
//                )
//                .csrf().disable() // Disable CSRF protection (since we're using stateless authentication)
//                .headers(headers ->
//                        headers.frameOptions().disable() // Disable X-Frame-Options for H2 Console
//                )
//                .build();
//    }
//   }
    
//}http://localhost:8080/h2-console
package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class BasicAuthenticationSecurityConfiguration {
	
	//Filter chain
	// authenticate all requests
	//basic authentication
	//disabling csrf
	//stateless rest api
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//1: Response to preflight request doesn't pass access control check
		//2: basic auth
		return 
				http
					.authorizeHttpRequests(
						auth -> 
							auth
							.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
							.anyRequest().authenticated()
						)
					.httpBasic(Customizer.withDefaults())
					.sessionManagement(
						session -> session.sessionCreationPolicy
						(SessionCreationPolicy.STATELESS))
					.csrf().disable()
					.build();
	}

}