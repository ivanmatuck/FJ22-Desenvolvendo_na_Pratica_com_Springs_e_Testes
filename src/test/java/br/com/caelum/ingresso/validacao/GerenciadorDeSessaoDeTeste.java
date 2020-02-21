package br.com.caelum.ingresso.validacao;

import java.time.Duration;
import java.time.LocalTime;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;

public class GerenciadorDeSessaoDeTeste {

	private Filme rogueOne;
	private Sala sala3D;
	private Sessao sessaoDasDez;
	private Sessao sessaoDasTreze;
	private Sessao sessaoDasDezoito;
	
	@Before
	public void preparaSessoes() {
		this.rogueOne = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI");
		this.sala3D = new Sala("Sala 3D");
		
		this.sessaoDasDez = new Sessao(LocalTime.parse("10:00:00"), rogueOne, sala3D);
		this.sessaoDasTreze = new Sessao(LocalTime.parse("13:00:00"), rogueOne, sala3D);
		this.sessaoDasDezoito = new Sessao(LocalTime.parse("18:00:00"), rogueOne, sala3D);
	}
	
	@Test
	public void garanteQueNao

}
