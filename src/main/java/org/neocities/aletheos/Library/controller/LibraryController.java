package org.neocities.aletheos.Library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class LibraryController {
	@GetMapping("/books")
	public String publicAccess() {
		return ":3";
	}

	@GetMapping("/games")
	public String privateAccess() {
		return ":3c";
	}
}
