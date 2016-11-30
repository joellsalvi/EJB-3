package br.com.caelum.agendador;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class AgendadorService {
	
	// A virgula separa os tempos que queremos q seja executado, ex: 9,18 = Executar ás 9 e as 18.
	// * Significa todo
	// */10 Significa a cada 10 segundos neste caso
	// A aplicação por padrão persiste agendamentos, para q o mesmo não pare, para q isso não ocorra, use persistent=false
	@Schedule(hour="*", minute="*", second="*/10", persistent=false)
	void realizaTarefaAgendada() {
		System.out.println("Executando tarefa agendada periodicamente");
	}

}
