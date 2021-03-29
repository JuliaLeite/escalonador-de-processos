package escalonador;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    
	private static Scanner sc;

	public static void main(String[] args) {

		int tam = 10;
                
		ArrayList<Integer> tamanhos = new ArrayList<>();
		// BlockingQueue para FCFS e RR
		BlockingQueue<Processo> prcs = new ArrayBlockingQueue<Processo>(tam);
		// PriorityBlockinQueue para SJF
		PriorityBlockingQueue<Processo> prcs2 = new PriorityBlockingQueue<Processo>(tam);

		// tamanho pré-definido dos 10 processos
		tamanhos.add(2);
		tamanhos.add(1);
		tamanhos.add(3);
		tamanhos.add(1);
		tamanhos.add(3);
		tamanhos.add(2);
		tamanhos.add(3);
		tamanhos.add(1);
		tamanhos.add(1);
		tamanhos.add(2);
                
                // Objeto clock recebem a quantidade total de processos
		Clock clock = new Clock(tam);

		sc = new Scanner(System.in);
		int x;

		System.out.println("Escolha qual algoritmo será executado:");
		System.out.println("1 - First Come First Served");
		System.out.println("2 - Shortest Job First");
		System.out.println("3 - Round Robin");
		x = sc.nextInt();

		// Switch escolhe um dos algoritmos
		switch (x) {
		case 1:
			Thread t1 = new Thread(new TarefaContadora(clock));
			t1.start();
			FCFS fcfs = new FCFS(prcs, tam, clock, tamanhos);
			break;
		case 2:
			Thread t1_1 = new Thread(new TarefaContadora(clock));
			t1_1.start();
			SJF sjf = new SJF(prcs2, tam, clock, tamanhos);
			break;
		case 3:
			Thread t1_2 = new Thread(new TarefaContadora(clock));
			t1_2.start();
			RR rr = new RR(prcs, tam, clock, tamanhos);
			break;
		default:
			System.out.println("Número inválido");
			break;
		}

	}

}
