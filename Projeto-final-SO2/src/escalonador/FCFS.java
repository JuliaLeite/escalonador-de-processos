package escalonador;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class FCFS {

	// Construtor da classe FCGS
	public FCFS(BlockingQueue<Processo> prcs, int tam, Clock clock, ArrayList<Integer> tamanhos) {
                // Inicia a tarefa produtora
		Thread t2 = new Thread(new TarefaProdutora(prcs, tam, tamanhos));
                // Inicia a tarefa consumidora
		Thread t3 = new Thread(new TarefaConsumidora(prcs, tam, clock));
		t2.start();
		t3.start();
	}

}
