package com.ensah.gestionExamens.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;



@Configuration // Car cette classe contient des beans (annotées par @bean) qui seront crée
				// automatiquement par Spring
@EnableWebSecurity // Car c'est notre classe de gestion de sécurité donc on active Spring Security
public class AppSecurityConfig { // Il faut hériter de WebSecurityConfigurerAdapter

	// Logger
	Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

	/**
	 * 
	 * Nous avons choisi d'implémenter une gestion personnalisée de
	 * l'authentification Ainsi, nous devons indiquer à Spring Security quelle est
	 * notre gestionnaire d'authentification Ce gestionnaire est
	 * CustomAuthentificationService
	 * 
	 */
	

	
	
	@Bean
	public AuthenticationSuccessHandler uthenticationSuccessHandler() {
		return new RedirectionAfterAuthenticationSuccessHandler();
	}

	public AppSecurityConfig() {

		LOGGER.debug("AppSecurityConfig Initialisé");
	}

	// Configurer DaoAuthenticationProvider (Vous pouvez laisser cette configuration
	// telle quelle)
	
	@Bean
	public UserDetailsService users() {
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("admin"))
				.roles("ADMIN").build();
		return new InMemoryUserDetailsManager( admin);
	}
	
	
	
	

	

	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean // nécessaire car c'est Spring qui créer automatiquement cette classe de type
	// MySimpleUrlAuthenticationSuccessHandler
	public AuthenticationSuccessHandler redirectionAfterAuthenticationSuccessHandler() {
		return new RedirectionAfterAuthenticationSuccessHandler();
	}



	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// TODO : Configurer la securité de votre application

		http
		//.csrf(csrf -> csrf.disable())  // Disable CSRF
		.authorizeHttpRequests(
				authz -> authz.requestMatchers("/**")
				.hasRole("ADMIN")
				.anyRequest().permitAll()

		).formLogin(Customizer.withDefaults()

		).logout(logout -> logout.logoutUrl("/logout") // Endpoint de déconnexion
//				.deleteCookies("JSESSIONID") // Supprime le cookie JSESSIONID lors de la déconnexion
				.permitAll())
		.exceptionHandling(exception -> exception.accessDeniedPage("/access-denied") 
		);
		return http.build();

	}
}
