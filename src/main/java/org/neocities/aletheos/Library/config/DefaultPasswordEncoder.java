package org.neocities.aletheos.Library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DefaultPasswordEncoder {
	@Bean
	PasswordEncoder deafultPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
