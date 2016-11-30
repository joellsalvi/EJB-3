package br.com.caelum.livraria.webservice;

import java.rmi.RemoteException;

public class TesteWSByHttpRequest {

	public static void main(String[] args) throws RemoteException {
		
		LivrariaWS ws = new LivrariaWSProxy();
		
		Livro[] livrosPeloNome = ws.getLivrosPeloNome("Opressor");
		
		if(livrosPeloNome != null) {
			for(Livro livro : livrosPeloNome) {
				System.out.println("Livro( Título: "+livro.getTitulo()+", Autor: "+livro.getAutor().getNome()+" )");
			}
		}
		
	}

}
