package br.com.caelum.ingresso.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {

	//métodos
	
	//action para '/login' - retorna para a 'view' login
	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
