package br.com.caelum.ingresso.email;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class Token {

	//atributos
	@Id
	private String uuid;
	
	//@Email - **deprecated no hibernate
	private String email;

	
	//construtores
	public Token() {
	}

	public Token(String email) {
		this.email = email;
	}
	
	
	//métodos
	@PrePersist
	public void prePersist() {
		uuid = UUID.randomUUID().toString();
	}

	
	//getters and setters
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getEmail() {
		return email;
	}

	
	//set email com verificador simples
	public void setEmail(String email) {
		if(email.indexOf('@') > 0)
			this.email = email;
		
	}
}
