package br.com.caelum.ingresso.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@ImportResource
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	//atributos
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//métodos
		
	//implanta restrições para URIs
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable().authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/compra/**").hasRole("COMPRADOR")
				.antMatchers("/usuario/**").permitAll()	
				.antMatchers("/filme/**").permitAll()
				.antMatchers("/sessao/**/lugares").permitAll()
				.antMatchers("/magic/**").permitAll()
				.antMatchers("/").permitAll()
			
			.anyRequest()
				.authenticated()
				
			.and()
				.formLogin()
					.usernameParameter("email")
					.loginPage("/login")
					.permitAll()
					
			.and()
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.permitAll();
	}

	//libera arquivos estáticos
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)	throws	Exception	{
					auth.userDetailsService(userDetailsService).passwordEncoder(new	BCryptPasswordEncoder());
	}
	
	
}
