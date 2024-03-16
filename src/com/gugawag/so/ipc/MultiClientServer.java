package com.gugawag.so.ipc;

/**
 * Time-of-day server listening to port 6013.
 *
 * Figure 3.21
 *
 * @author Silberschatz, Gagne, and Galvin. Pequenas alterações feitas por Gustavo Wagner (gugawag@gmail.com)
 * Operating System Concepts  - Ninth Edition
 * Copyright John Wiley & Sons - 2013.
 */
import java.net.*;
import java.io.*;
import java.util.Date;

public class MultiClientServer{
	public static void main(String[] args)  {
		try {
			ServerSocket sock = new ServerSocket(6013); //Cria um novo ServerSocket que escuta na porta 6013

			System.out.println("======= Servidor iniciado =======\n");
			System.out.println("=== LUIZ GONZAGA DE LIMA NETO ===\n");
			// escutando por conexões
			while (true) {
				Socket client = sock.accept(); //Espera por uma conexão com o socket e a aceita
				// Se chegou aqui, foi porque algum cliente se comunicou

                //Agora vamos criar uma nova thread para tratar a comunicação com o cliente
                new Thread(() -> {
                    try{
                        System.out.println("Servidor recebeu comunicação do ip: " + client.getInetAddress() + "-" + client.getPort());
                        PrintWriter pout = new PrintWriter(client.getOutputStream(), true); //Cria um novo PrintWriter que converte os caracteres em bytes para o output stream do socket do cliente
                        // Escreve a data atual no socket
                        pout.println(new Date().toString() + "-Bom dia Cliente!");

                        InputStream in = client.getInputStream();
                        BufferedReader bin = new BufferedReader(new InputStreamReader(in));
                        String line;
                        while((line = bin.readLine()) != null){
                            System.out.println("\nO cliente me disse:\n" + line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
			}
		}
		catch (IOException ioe) {
				System.err.println(ioe);
		}
	}
}
