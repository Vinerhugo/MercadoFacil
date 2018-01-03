package br.una.prova.config;

import javax.persistence.Access;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http)throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/","/contato","/main","/img/**","/booklet/**","/css/**","/js/**","/cufon/**",
				"/ajuda","/vfolheto","/vpromocao","/fonts/**","/images/**","/themes/**")
		.permitAll()
		.anyRequest()
		.authenticated()
	.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
	.and()
		.logout()
		.permitAll();
		
	}
 
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryAuthentication = auth.inMemoryAuthentication();
			inMemoryAuthentication.withUser("admin").password("admin").roles("USER");
			inMemoryAuthentication.withUser("viner").password("123").roles("USER");
	}

}
