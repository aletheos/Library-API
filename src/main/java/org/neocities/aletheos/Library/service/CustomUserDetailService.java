package org.neocities.aletheos.Library.service;

import org.neocities.aletheos.Library.entity.AppUser;
import org.neocities.aletheos.Library.entity.CustomUserDetails;
import org.neocities.aletheos.Library.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
	private final UserRepository userRepository;

	public CustomUserDetailService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("User not found!"));

		return new CustomUserDetails(user);
	}
}
