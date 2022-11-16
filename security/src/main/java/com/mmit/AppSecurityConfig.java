package com.mmit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
	
	@Bean
	public UserDetailsService userDetailService() {
		
		UserDetails user1 = User.withUsername("ngaih@gmail.com")
								.password("123")
								.roles("admin")
								.build();
		
		UserDetails user2 = User.withUsername("thang@gmail.com")
				.password("123")
				.roles("staff")
				.build();
		
		InMemoryUserDetailsManager provider = new InMemoryUserDetailsManager();
		provider.createUser(user1);
		provider.createUser(user2);
		
		return provider;
	}
	
	@Bean
	public SecurityFilterChain filterChange(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/","/css/**").permitAll()
			.antMatchers("/product/edit/**", "/product/delete/**").hasRole("admin")
			.anyRequest()
			.authenticated()
			.and()
			//.httpBasic()
			.formLogin()
			.and()
			.exceptionHandling()
				.accessDeniedPage("/403");
		
		return http.build();
			
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
