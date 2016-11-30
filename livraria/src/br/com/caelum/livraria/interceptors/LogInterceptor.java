package br.com.caelum.livraria.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LogInterceptor {

	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		long millis = System.currentTimeMillis();

		/*
		 * Executa antes;
		 */
		Object proceed = context.proceed(); //executa o método em questão que foi interceptado
		/*
		 * Executa depois;
		 */

		String nomeMetodo = context.getMethod().getName();
		String nomeClasse = context.getTarget().getClass().getSimpleName();

		System.out.println("Método: " + nomeMetodo + ", NomeClasse: " + nomeClasse + "| Tempo de resposta: " + (System.currentTimeMillis() - millis));

		return proceed;
	}

}
