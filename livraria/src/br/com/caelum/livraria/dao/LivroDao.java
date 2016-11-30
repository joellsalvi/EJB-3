package br.com.caelum.livraria.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Livro;

@Stateless
public class LivroDao {

	@PersistenceContext
	private EntityManager manager;

	public void salva(Livro livro) {
		manager.persist(livro);
	}

	public List<Livro> todosLivros() {
		return manager.createQuery("select l from Livro l", Livro.class).getResultList();
	}

	public List<Livro> findByTitulo(String titulo) {
		TypedQuery<Livro> query = manager.createQuery("select l from Livro l where l.titulo like :titulo", Livro.class);
		query.setParameter("titulo", "%" + titulo + "%");
		return query.getResultList() == null ? new ArrayList<Livro>() : query.getResultList();
	}

}
