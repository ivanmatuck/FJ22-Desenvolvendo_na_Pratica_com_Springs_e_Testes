package br.com.caelum.ingresso.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.email.Token;

@Repository
public class TokenDao {
	@PersistenceContext
	private EntityManager manager;

	public void save(Token token) {
		manager.persist(token);
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public Optional<Token> findByUuid(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
