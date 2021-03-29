package escalonador;

public class Processo implements Comparable<Processo> {
	// Variável que registra o número de identificação do processo
	private int id;
	// Tamanho do processo
	private int tam;

	// Construtor da classe
	public Processo(int tam, int id) {
		this.setId(id);
		this.setTam(tam);
	}

	// Retorna o tamanho do processo
	public int getTam() {
		return tam;
	}

	// Atribui o tamanho do processo
	public void setTam(int tam) {
		this.tam = tam;
	}

	// Retorna a identificação do processo
	public int getId() {
		return id;
	}

	// Atribui a identificação do processo
	public void setId(int id) {
		this.id = id;
	}

	@Override
	// Método necessário na implementação da PriorityBlockingQueue
	// Compara o tamanho dos processos para realoca-los na fila
	public int compareTo(Processo p) {

		if (this.tam > p.getTam()) {
			return 1;
		} else if (this.tam < p.getTam()) {
			return -1;
		} else {
			return 0;
		}
	}

}
