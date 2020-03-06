package br.com.caelum.ingresso.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Permissao implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//atributos
	@Id
	private String nome;
	
	//construtores
	public Permissao (String nome) {
		this.nome = nome;
	}
	
	/**
	@deprecated hibernate only
	*/
	public Permissao() {
		
	}
	
	//métodos
	
	//retorna o nome da permissão
	@Override
	public String getAuthority() {
		return nome;
	}

	//getters and setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
