package escalonador;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class TarefaProdutora implements Runnable {
	// BLockingQueue de processos
	BlockingQueue<Processo> prcs;
	// Lista de tamanho dos processos
	ArrayList<Integer> tamanhos;
	// Quantidade de processos criados
	private int tam;

	// Construtor da classe
	public TarefaProdutora(BlockingQueue<Processo> prcs, int tam, ArrayList<Integer> tamanhos) {
		this.prcs = prcs;
		this.setTam(tam);
		this.tamanhos = tamanhos;

	}

	// Retorna a quantidade de processos criados
	public int getTam() {
		return tam;
	}

	// Atribui a quantidade de processos criados
	public void setTam(int tam) {
		this.tam = tam;
	}

	@Override
	public void run() {
		// For reponsável por criar os processos e coloca-los na BlockingQueue
		for (int i = 0; i < tam; i++) {
			try {
				prcs.put(new Processo(tamanhos.get(i), i + 1));
				System.out.println("Processo " + (i + 1) + " adicionando a fila");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
