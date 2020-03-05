package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.annotation.*;
import org.springframework.stereotype.Component;

@Component
@SessionScope
public class Carrinho {

	//atributos
	private List<Ingresso> ingressos = new ArrayList<>();
	
	//métodos
	
	//adiciona ingresso no carrinho
	public void add(Ingresso ingresso) {
		ingressos.add(ingresso);
	}
	
	//verifica se um lugar está ou não no carrinho
	public boolean isSelecionado(Lugar lugar) {
		return ingressos.stream().map(Ingresso::getLugar).anyMatch(lugarDoIngresso -> lugarDoIngresso.equals(lugar));
	}

	public BigDecimal getTotal() {
		return ingressos.stream().map(Ingresso::getPreco).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}
	
	public Compra toCompra() {
		return new Compra(ingressos);
	}
	
	public void limpa() {
		this.ingressos.clear();
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}
	
	

}
