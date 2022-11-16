package com.mmit.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public MyUserDetailsService myUserDetailsService() {
		return new MyUserDetailsService();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(myUserDetailsService());
		return provider;
	}
	
	@Bean
	public SecurityFilterChain filterChange(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", 
					"/shop",
					"/shop/products/**",
					"/cart/detail",
					"/login",
					"/register",
					"/shop/user/save",
					"/css/**",
					"/app/**",
					"/fonts/**",
					"/images/**",
					"/js/**",
					"/scss/**",
					"/uploads/**",
					"/shop/category/{id}"
				).permitAll()
		.antMatchers("/cart/place-order").hasAnyRole("admin", "customer")
			.antMatchers("/admin/**")
			.hasAnyRole("admin")
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
			.logout()
				.logoutSuccessUrl("/")
				.permitAll()
			.and()
			.exceptionHandling()
				.accessDeniedPage("/403");
		
		return http.build();
			
	}
	
}
