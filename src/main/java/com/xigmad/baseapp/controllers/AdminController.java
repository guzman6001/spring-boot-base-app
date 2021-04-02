package com.xigmad.baseapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping(path = {"/inicio","/",""})
	public String Inicio() {
		return "admin/index";
	}
	
	@GetMapping(path = {"/login"})
	public String Login() {
		return "public/login";
	}
}
