package com.xigmad.baseapp.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.xigmad.baseapp.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserService user;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	private String[] allowedPaths= new String[] {"/","/users/add","/users/save","/login","/security/login","/css/**","/images/**","/js/**","/bucket-ico-fonts/**","/bs3/**","/font-awesome/**"};   
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder password = new BCryptPasswordEncoder();
		return password;
	}

	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception  {
		auth.userDetailsService(user).passwordEncoder(passEncoder);
	}
	
	@Override
	protected void configure (HttpSecurity http) throws Exception{

		http
		.authorizeRequests().antMatchers(allowedPaths).permitAll().and()
		.authorizeRequests().anyRequest().authenticated().and()
		.formLogin().loginPage("/security/login").permitAll()
		.defaultSuccessUrl("/admin/inicio").failureUrl("/auth-error")
			.usernameParameter("username").passwordParameter("password").and()
		.logout().logoutUrl("/logout").permitAll().logoutSuccessUrl("/")
		.and().rememberMe().key("uniqueAndSecret");
		
	}
}
