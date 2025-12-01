package org.neocities.aletheos.Library.repository;

import org.neocities.aletheos.Library.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
