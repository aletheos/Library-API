package org.neocities.aletheos.Library.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LibraryController {
	@GetMapping("/books")
	public String publicAccess() {
		return "Hello " + SecurityContextHolder.getContext().getAuthentication().getName() + " :3";
	}

	@GetMapping("/games")
	public String privateAccess() {
		return "Greetings " + SecurityContextHolder.getContext().getAuthentication().getName() + " :3c";
	}
}
