package org.neocities.aletheos.Library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean // there can bean only one
	public SecurityFilterChain publicAccessChain(HttpSecurity http) throws Exception {
		http
// Q: Why would I want to disable this?
// A: Because it can often block POST/PUT/DELETE requests in a REST API
			.csrf(csrf -> csrf.disable()) // equivalent to AbstractHttpConfigurer::disable
			.authorizeHttpRequests(
				(authorize) -> {
					authorize
						.requestMatchers("/api/books/**").permitAll()
						.requestMatchers("/api/games/**").authenticated()
					;
				}
			)
// formLogin is for session based authentication, think normal-ass websites, and should be considered unsafe when csrf is disabled
// httpBasic is stateless authentication, using cached credentials(valid in realm until canceled), and should be used in REST APIs
// pick one! (logout is nonsensical with httpBasic since there is no session)
// ...as per HTTP specifications, a failed auth resets the stored header... so says GPT
//			.formLogin(Customizer.withDefaults()) // Bring back default login screen
//			.logout(Customizer.withDefaults()) // Bring back default logout screen(unnecessary?)
			.httpBasic((_) -> {}) // same as Customizer.withDefaults()
		;
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = User
			.withUsername("user")
			.password(passwordEncoder().encode("4321"))
			.roles("USER")
			.build();
		return new InMemoryUserDetailsManager(userDetails);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
