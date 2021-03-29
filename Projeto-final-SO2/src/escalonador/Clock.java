package escalonador;

import java.util.ArrayList;

public class Clock {
	// Variavel que armazena o numero de clocks
	private int numClocks = 0;
	// variavel que armazena o numero de processos que sairam do escalonador
	private int numProcessos = 0;
	// Variavel que recebe a quantidade de processos criados
	private int tam;
	// Lista de inteiros do tamanho da quantidade de processos craidos
	ArrayList<Integer> throughput = new ArrayList<>(tam);

	// Construtor que recebe a quantidade de processos criados
	public Clock(int tam) {
		this.setTam(tam);
	}

	// Método sincronizado que retorna o número de clocks
	public synchronized int getNumClocks() {
		return numClocks;
	}

	// Método que retorna o número de processos que saíram do escalonador
	public int getNumProcessos() {
		return numProcessos;
	}

	// Método que atribui o número de processos que já sairam do escalonador
	public void setNumProcessos(int numProcessos) {
		this.numProcessos = numProcessos;
	}

	// Método que retorna a quantidade de processos criados
	public int getTam() {
		return tam;
	}

	// Método que atribui a quantidade de processos criados
	public void setTam(int tam) {
		this.tam = tam;
	}

	// Método sincronizado que incrementa o numClocks
	public synchronized void incrementaClock() {
		numClocks++;
		System.out.println("Clocks: " + numClocks);
		if (getNumProcessos() <= tam) {
			throughput.add(getNumProcessos());

		}
	}

	// Método responsável por gerar o Throughput Médio
	public void throughputMedio() {
		// Cria uma variavel para armazenar o Throughput Médio
		float throughputMedio = 0;
		for (int i = 0; i < throughput.size(); i++) {
			throughputMedio += throughput.get(i);
		}
		// Divide a soma dos elementos pelo tamanho total da Lista
		throughputMedio = throughputMedio / throughput.size();

		System.out.println("O throughput médio é: " + throughputMedio);
	}

}
