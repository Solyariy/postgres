package com.demodb.postgres.security.securityConfig;

import com.demodb.postgres.security.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
	
	private final AppUserService appUserService;
	private final Encoder encoder;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> {
							auth.requestMatchers("/").permitAll();
							auth.anyRequest().authenticated();
						}
				)
				.formLogin(l -> {
					l.loginProcessingUrl("/login");
					l.defaultSuccessUrl("/");
				})
				.logout(l -> {
					l.logoutUrl("/logout");
					l.logoutSuccessUrl("/");
				})
				.build();
	}
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(encoder);
		provider.setUserDetailsService(appUserService);
		return provider;
	}
	
}
