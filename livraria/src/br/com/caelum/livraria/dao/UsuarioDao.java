package br.com.caelum.livraria.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Usuario;

@Stateless
public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;

	public Usuario buscaPeloLogin(String login) {
		return this.manager.createQuery("select u from Usuario u where u.login = :login", Usuario.class).setParameter("login", login).getSingleResult();
	}

	public void cadastrarUsuario(Usuario usuario) {
		manager.persist(usuario);
	}

}
