package org.neocities.aletheos.Library.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HttpServletBean;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	@Order(1) // this is only necessary if you have more than one SecurityFilterChain-returning bean
// This is called when an attempt is made to connect to an endpoint
	public SecurityFilterChain publicAccessChain(HttpSecurity http) throws Exception {
		http
			.securityMatcher("/api/books") // "The security matcher determines which chain(bean) handles the request"
// Q: Why would I want to disable this?
// A: Because it can often block POST/PUT/DELETE requests in a REST API
			.csrf(csrf -> csrf.disable()) // equivalent to AbstractHttpConfigurer::disable
			.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(
				(authorize) -> { authorize.anyRequest().permitAll(); }
			)
// formLogin is for session based authentication, think normal-ass websites, and should be considered unsafe when csrf is disabled
// httpBasic is stateless authentication, using cached credentials(valid in realm until canceled), and should be used in REST APIs
// pick one! (logout is nonsensical with httpBasic since there is no session)
// ...as per HTTP specifications... a failed auth resets the stored header(failed auth prompts the browser to request new credentials)
//			.formLogin(Customizer.withDefaults()) // Bring back default login screen
//			.logout(Customizer.withDefaults()) // Bring back default logout screen(unnecessary?)
			.httpBasic((_) -> {}) // same as Customizer.withDefaults()
		;
		return http.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain privateAccessChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.securityMatcher("/api/games")
			.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(a -> a.anyRequest().authenticated())
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//			.formLogin(Customizer.withDefaults())
//			.logout(Customizer.withDefaults())
/*
			.sessionManagement(
				session -> session
					.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
					.invalidSessionUrl("/login")
					.sessionFixation(sf -> sf.changeSessionId())
					.maximumSessions(1)
					.maxSessionsPreventsLogin(false)
					.expiredUrl("/login?expired")
			)
*/
		;
		return http.build();
	}
	// Why does adding this bean to the chain cause calls to the above /api/games endpoint to redirect to /login?
	// Shouldn't this only ever run on requests that don't match api/games and api/books?
	@Bean
	@Order(3)
	public SecurityFilterChain webbApp(HttpSecurity http) throws Exception {
		http
			.securityMatcher("/**")
			.authorizeHttpRequests(a -> a.anyRequest().authenticated())
			.formLogin(Customizer.withDefaults())
			.logout(Customizer.withDefaults())
		;
		return http.build();
	}
}
