package br.com.caelum.ingresso.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.UsuarioDao;
import br.com.caelum.ingresso.email.EmailNovoUsuario;
import br.com.caelum.ingresso.email.Mailer;
import br.com.caelum.ingresso.email.Token;
import br.com.caelum.ingresso.helper.TokenHelper;
import br.com.caelum.ingresso.model.Usuario;
import br.com.caelum.ingresso.model.form.ConfirmacaoLoginForm;

@Controller
public class UsuarioController {

	// atributos
	@Autowired
	private Mailer mailer;

	@Autowired
	private TokenHelper tokenHelper;
	
	@Autowired
	private UsuarioDao usuarioDao;

	// métodos

	// mapeamento GET
	@GetMapping("/usuario/request")
	public ModelAndView formSolicitacaoDeAcesso() {
		return new ModelAndView("usuario/form-email");
	}

	// mapeamento POST - envia email com token para usuário
	@PostMapping("/usuario/request")
	@Transactional
	public ModelAndView solicitacaoDeAcesso(String email) {
		ModelAndView view = new ModelAndView("usuario/adicionado");
		Token token = tokenHelper.generateFrom(email);
		mailer.send(new EmailNovoUsuario(token));
		return view;
	}

	// recebe o parâmetro uuid da request - Caso o token não esteja presente,
	// retorna para página de login
	@GetMapping("/usuario/validate")
	public ModelAndView validaLink(@RequestParam("uuid") String uuid) {
		Optional<Token> optionalToken = tokenHelper.getTokenFrom(uuid);
		if (!optionalToken.isPresent()) {
			ModelAndView view = new ModelAndView("redirect:/login");
			view.addObject("msg", "O token do link utilizado não foi encontrado!");
			return view;
		}
		Token token = optionalToken.get();
		ConfirmacaoLoginForm confirmacaoLoginForm = new ConfirmacaoLoginForm(token);
		ModelAndView view = new ModelAndView("usuario/confirmacao");
		view.addObject("confirmacaoLoginForm", confirmacaoLoginForm);
		return view;
	}

	@PostMapping("/usuario/cadastrar")
	@Transactional
	public ModelAndView cadastrar(ConfirmacaoLoginForm form) {
		ModelAndView view = new ModelAndView("redirect:/login");
		if (form.isValid()) {
			Usuario usuario = form.toUsuario(usuarioDao, passwordEncoder);
			usuarioDao.save(usuario);
			view.addObject("msg", "Usuario cadastrado com sucesso!");
			return view;
		}
		view.addObject("msg", "O token do link utilizado não foi encontrado!");
		return view;
	}

	// getters and setters

	public Mailer getMailer() {
		return mailer;
	}

	public void setMailer(Mailer mailer) {
		this.mailer = mailer;
	}

	public TokenHelper getTokenHelper() {
		return tokenHelper;
	}

	public void setTokenHelper(TokenHelper tokenHelper) {
		this.tokenHelper = tokenHelper;
	}

}
