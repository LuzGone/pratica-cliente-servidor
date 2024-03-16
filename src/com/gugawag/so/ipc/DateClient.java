package com.gugawag.so.ipc;

/**
 * Client program requesting current date from server.
 *
 * Figure 3.22
 *
 * @author Silberschatz, Gagne and Galvin. Pequenas alterações feitas por Gustavo Wagner (gugawag@gmail.com)
 * Operating System Concepts  - Eighth Edition
 */ 

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class DateClient {
	public static void main(String[] args)  {
		try {
			Scanner scanner = new Scanner(System.in);
			// this could be changed to an IP name or address other than the localhost
			Socket sock = new Socket("localhost",6013); //Cria um novo stream socket na porta 6013 no host inidicado
			InputStream in = sock.getInputStream(); //Pega o input stream do socket utilizado para ler os bytes do socket
			BufferedReader bin = new BufferedReader(new InputStreamReader(in)); //Cria um novo BufferedReader que lê os caracteres do input stream

			System.out.println("=== Cliente iniciado ===\n");

			String line = bin.readLine(); //Lê uma linha de texto do BufferedReader
			System.out.println("O servidor me disse:" + line);

			PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);
			pout.println("Olá Servidor sou um cliente aleatório.");

			String mensagem = scanner.nextLine();

			pout.println("Cliente Aleatório - " + mensagem);
				
			sock.close(); //Fecha o socket
		}
		catch (IOException ioe) {
				System.err.println(ioe);
		}
	}
}
