package escalonador;

public class TarefaContadora implements Runnable {
	// Cria uma referência do Clock
	private Clock clock;

	// Construtor recebe um objeto clock
	public TarefaContadora(Clock clock) {
		this.setClock(clock);
	}

	// Retorna o objeto clock
	public Clock getClock() {
		return clock;
	}

	// Atribui o clock do construtor a referência da Classe
	public void setClock(Clock clock) {
		this.clock = clock;
	}

	@Override
	public void run() {
		// Durante toda a execução do programa chama o método incrementaClock();
		while (true) {
			// Metodo que incrementa a variavel numClocks do objeto Clock
			clock.incrementaClock();
			try {
				// Coloca a thread para dormir por 1 segundo.
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
