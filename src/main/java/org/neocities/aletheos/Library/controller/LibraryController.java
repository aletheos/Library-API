package org.neocities.aletheos.Library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LibraryController {
	@GetMapping
	public String getMessage() {
		return ":3";
	}
}
