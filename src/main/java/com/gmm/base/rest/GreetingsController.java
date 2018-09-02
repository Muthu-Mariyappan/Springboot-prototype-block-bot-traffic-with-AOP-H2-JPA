package com.gmm.base.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gmm.services.IPEntryService;

// Actual business logic
@RestController
public class GreetingsController {

	@Autowired
	IPEntryService ipEntryService;

	// This function returns greetings with the name value
	@GetMapping("/greet") // response, request parameter to support AOP - good to have
	public String greetUser(HttpServletRequest request, @RequestParam("name") String name,
			HttpServletResponse response) {
		String msg = "Warm Welcomes " + name;
		return msg;
	}

}