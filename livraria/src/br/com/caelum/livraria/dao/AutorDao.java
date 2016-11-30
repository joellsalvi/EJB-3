package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.com.caelum.livraria.interceptors.LogInterceptor;
import br.com.caelum.livraria.modelo.Autor;

@Stateless // Session beans são Thread Safe
@TransactionManagement(TransactionManagementType.CONTAINER) // opcional - padrão
@Interceptors({LogInterceptor.class})//Usa-se desta forma ou pelo XML - Ver ejb-jar.xml
public class AutorDao {

	@PersistenceContext
	private EntityManager manager;

	/**
	 * TransactionManagement deve ser BEAN possui os métodos clássicos
	 * relacionados com o gerenciamento da transação como begin(), commit() e
	 * rollback(). O problema é que exige um tratamento excessivo de exceções
	 * checked que poluem muito o código.
	 */
	@Inject
	private UserTransaction tx;

	// Também chamado de callback
	@PostConstruct
	void aposCriacao() {
		System.out.println("AutorDAO criado");
	}

	/**
	 * TransactionAttributeType.SUPPORTS e
	 * TransactionAttributeType.NOT_SUPPORTED Com o atributo configurado para
	 * SUPPORTS, o código será executado com ou sem transação. Já com
	 * NOT_SUPPORTED o código deverá ser executado sem transação, caso alguma
	 * transação esteja aberta, ela será suspensa temporariamente até a
	 * execução do método acabar.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED) // opcional -
																// padrão
	public void salva(Autor autor) {
		System.out.println("Salvando autor: " + autor.getNome());
		// try {
		// Thread.sleep(20000); // 20 segundos
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

		// try {
		// tx.begin();
		// manager.persist(autor);
		// tx.commit();
		// }catch(Exception e) {
		// e.printStackTrace();
		// }

		// TODO testar TransactionAttributeType.SUPPORTS e
		// TransactionAttributeType.NOT_SUPPORTED
		manager.persist(autor);
		System.out.println("Salvou autor: " + autor.getNome());
		// throw new RuntimeException();
	}

	public List<Autor> findAll() {
		return manager.createQuery("select a from Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.manager.find(Autor.class, autorId);
		return autor;
	}

}
