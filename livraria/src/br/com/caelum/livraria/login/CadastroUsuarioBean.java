package br.com.caelum.livraria.login;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@Named
@RequestScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDao dao;

	private Usuario usuario = new Usuario();

	public String efetuarCadastro() {
		String mensagem = null;
		String redirect = null;
		Severity typeMessage = null;
		System.out.println("Cadastrando usuário");

		if (usuario.getSenha().equals(usuario.getConfirmarSenha())) {
			dao.cadastrarUsuario(usuario);
			mensagem = "Usuário Cadastrado com sucesso!";
			redirect = "login";// ?faces-redirect=true";
			typeMessage = FacesMessage.SEVERITY_INFO;
		} else {
			mensagem = "Usuário não Cadastrado, Senha e ConfirmarSenha não coincidem";
			redirect = "novoUsuario";// ?faces-redirect=true";
			typeMessage = FacesMessage.SEVERITY_ERROR;
		}

		// criaMensagem(mensagem, typeMessage);
		return redirect;
	}

	public String teste() {
		System.out.println("entrou");
		return "";
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
