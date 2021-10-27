package com.project.osa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class Osa1Application {

	public static void main(String[] args) {
		SpringApplication.run(Osa1Application.class, args);


	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/korisnici").permitAll()
				.antMatchers(HttpMethod.POST, "/api/kupci").permitAll()
				.antMatchers(HttpMethod.POST, "/api/prodavci").permitAll()
				
				.antMatchers(HttpMethod.GET, "/api/artikli").hasAnyAuthority("ROLE_PRODAVAC", "ROLE_KUPAC")
				//.antMatchers(HttpMethod.GET, "/api/artikli").hasAuthority("ROLE_PRODAVAC")
				.antMatchers(HttpMethod.GET, "/api/artikli/*").hasAuthority("ROLE_PRODAVAC")
				.antMatchers(HttpMethod.POST, "/api/artikli/*").hasAuthority("ROLE_PRODAVAC")
				.antMatchers(HttpMethod.DELETE, "/api/artikli/*").hasAuthority("ROLE_PRODAVAC")
				.antMatchers(HttpMethod.GET, "/api/prodavaci/*").hasAuthority("ROLE_PRODAVAC")
				
				.antMatchers(HttpMethod.GET, "/api/prodavaci").hasAuthority("ROLE_KUPAC")
				//.antMatchers(HttpMethod.GET, "/api/artikli").hasAuthority("ROLE_KUPAC")
				
				//.antMatchers(HttpMethod.GET, "/api/korisnici/blokiranje").hasAuthority("ROLE_ADMINISTRATOR")
				
				
				
				//.antMatchers(HttpMethod.POST, "/api/artikli").hasRole("PRODAVAC")
				//.antMatchers(HttpMethod.POST, "/api/artikli/*").hasRole("PRODAVAC")
				//.antMatchers(HttpMethod.GET, "/api/stavke").hasAuthority("ROLE_USER")
				//.antMatchers(HttpMethod.GET, "/api/stavke").hasRole("ROLE_USER")
				//.antMatchers(HttpMethod.GET, "/api/akcije").permitAll()
				//.antMatchers(HttpMethod.GET, "/api/prodavci").hasAuthority("ROLE_ADMINISTRATOR")
				.anyRequest().authenticated();
		}
	}


}


