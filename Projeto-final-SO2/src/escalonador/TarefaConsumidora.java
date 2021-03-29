package escalonador;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class TarefaConsumidora implements Runnable {
	// Fila de processos
	BlockingQueue<Processo> prcs;
        
	// Fila ordenada de processos
	PriorityBlockingQueue<Processo> prcs2;
        
	// Quantidade de processos criados
	private int tam;
        
	// Quantum definido como 2 
	private int quantum = 2;
        
	// Variável que armazena o clock final
	private int clockFinal;
        	
        // Variavel de controle dos IFs no método run()
	private int num;
        
	// Variavel de controle do ultimo else if do método run()
	private int controle = 0;
        
	// Objeto Processo
	Processo processo;
        
	// Objeto Clock
	Clock clock;

	// Construtor para o algoritmo First-Come First-Served
	public TarefaConsumidora(BlockingQueue<Processo> prcs, int tam, Clock clock) {
		this.prcs = prcs;
		this.setTam(tam);
		this.clock = clock;
		// Variavel de controle para FCFS
		this.setNum(1);
	}

	// Construtor para o algoritmo Shortest Job First
	public TarefaConsumidora(PriorityBlockingQueue<Processo> prcs, int tam, Clock clock) {
		this.prcs2 = prcs;
		this.setTam(tam);
		this.clock = clock;
		// Variavel de controle para SJF
		this.setNum(2);
	}

	// Construtor para o algoritmo Round Robin
	public TarefaConsumidora(int tam, Clock clock, BlockingQueue<Processo> prcs) {
		this.prcs = prcs;
		this.setTam(tam);
		this.clock = clock;
		// Variavel de controle para Round Robin
		this.setNum(3);

	}

	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getQuantum() {
		return quantum;
	}

	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
 
	public int getControle() {
		return controle;
	}

	public void setControle(int controle) {
		this.controle = controle;
	}

	@Override
	public void run() {
		if (num == 1) {
			System.out.println("First Come First Served");
			// For responsável por retirar os processos da fila
			for (int i = 1; i <= tam; i++) {
				try {
					// O método take() retira o primeiro elemento da fila
					processo = prcs.take();
					// Soma o tamanho do processo com o clock atual
					clockFinal = processo.getTam() + clock.getNumClocks();
					// Espera o clock atual chegar no clockFinal, dando o efeito de executar o processo e retira-lo do escalonador
					while (clockFinal > clock.getNumClocks()) {
					}
					// Soma +1 ao número de processos já executados do objeto Clock
					clock.setNumProcessos(clock.getNumProcessos() + 1);
					System.out.println("Processo " + processo.getId() + " de tamanho " + processo.getTam()
							+ " retirado do escalonador");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			clock.throughputMedio();
                        System.exit(0);
                        
		} else if (num == 2) {
			System.out.println("Shortest Job First");
			for (int i = 1; i <= tam; i++) {
				try {
					processo = prcs2.take();
					// Soma o tamanho do processo com o clock atual
					clockFinal = processo.getTam() + clock.getNumClocks();
					while (clockFinal > clock.getNumClocks()) {
					}
					clock.setNumProcessos(clock.getNumProcessos() + 1);
					System.out.println("Processo " + processo.getId() + " de tamanho " + processo.getTam()
							+ " retirado do escalonador");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			clock.throughputMedio();
                        System.exit(0);

		} else if (num == 3) {
			System.out.println("Round Robin");
			// Executa enquanto "controle" for igual à 0
			while (controle == 0) {
				try {
					processo = prcs.take();
					clockFinal = processo.getTam() + clock.getNumClocks();
					while (clockFinal > clock.getNumClocks()) {
					}
					// Se o tamanho do processo for maior que o Quantum da CPU, o processo volta para o final da Fila
					if (processo.getTam() > getQuantum()) {
						System.out.println("Processo " + processo.getId() + " de tamanho " + processo.getTam()
								+ " volta para o final da fila");
						// Subtrai o Quantum do tamanho do processo
						processo.setTam(processo.getTam() - getQuantum());
                                                
						// Coloca o processo de volta no final da fila
						prcs.put(processo);
                                                
						// Se o tamanho do processo for menor que o Quantum
					} else {
						// Soma +1 ao número de processos já executados do objeto Clock
						clock.setNumProcessos(clock.getNumProcessos() + 1);

						System.out.println("Processo " + processo.getId() + " de tamanho " + processo.getTam()
								+ " retirado do escalonador");
					}
					// Se a Fila estiver vazia, atribui 1 á variavel controle e sai do While
					if (prcs.isEmpty()) {
						controle = 1;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			clock.throughputMedio();
                        System.exit(0);
		}

	}

}
