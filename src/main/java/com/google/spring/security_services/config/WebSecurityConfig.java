package com.google.spring.security_services.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	@Autowired
	private UserDetailsService detailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(request -> request.requestMatchers("/user/saveUser", "/user/login").permitAll()
						.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
		return httpSecurity.build();
	}

	// @Bean
	/*
	 * public UserDetailsService userDetailsService() { UserDetails userDetails =
	 * User.withUsername("sushant").password("{noop}password").roles("USER").build()
	 * ; return new InMemoryUserDetailsManager(userDetails); }
	 */

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(14);
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(detailsService);
		// authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
