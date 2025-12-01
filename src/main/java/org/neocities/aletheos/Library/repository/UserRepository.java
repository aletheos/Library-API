package org.neocities.aletheos.Library.repository;

import org.neocities.aletheos.Library.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
	Optional<AppUser> findByUsername(String username);
}
