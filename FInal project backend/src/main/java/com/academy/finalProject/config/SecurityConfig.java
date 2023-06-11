package com.academy.finalProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.academy.finalProject.filters.JWTRequestFilter;
import com.academy.finalProject.service.CustomUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JWTRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.cors().disable();
		http.csrf().disable().authorizeRequests()
		.antMatchers("/authenticate").permitAll()
		.antMatchers("/userLogout").permitAll()
		.antMatchers("/refreshToken").permitAll()
		.antMatchers("/api/batch").hasRole("ADMIN")
		.antMatchers("/api/employee").hasRole("ADMIN")
		.antMatchers("/api/course").hasRole("ADMIN")
		.antMatchers("/api/trainingSession").hasAnyRole("TRAINER","ADMIN")
		.antMatchers("/api/quiz").hasAnyRole("TRAINER","ADMIN")
		.antMatchers("/api/takenQuiz").hasAnyRole("TRAINER","ADMIN","TRAINEE")
		.antMatchers("/api/trainee").hasAnyRole("TRAINER","ADMIN","TRAINEE")
		.anyRequest().authenticated()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {return NoOpPasswordEncoder.getInstance();}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
}
