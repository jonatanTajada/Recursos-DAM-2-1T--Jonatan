package socketsPruebas2;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorChat {
	private static final int PUERTO = 12345;
	private static final List<PrintWriter> clientes = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("Servidor de chat iniciado...");
		try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Nuevo cliente conectado.");
				new HiloCliente(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static class HiloCliente extends Thread {
		
		private final Socket socket;
		private PrintWriter salida;

		public HiloCliente(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
				salida = new PrintWriter(socket.getOutputStream(), true);
				synchronized (clientes) {
					clientes.add(salida);
				}

				String mensaje;
				while ((mensaje = entrada.readLine()) != null) {
					System.out.println("Mensaje recibido: " + mensaje);
					if (mensaje.contains("ha salido del chat")) {
						break;
					}
					retransmitirMensaje(mensaje);
				}
			} catch (IOException e) {
				System.out.println("Error al manejar cliente.");
			} finally {
				synchronized (clientes) {
					clientes.remove(salida);
				}
				System.out.println("Cliente desconectado.");
			}
		}

		private void retransmitirMensaje(String mensaje) {
			synchronized (clientes) {
				for (PrintWriter cliente : clientes) {
					cliente.println(mensaje);
				}
			}
		}
	}
}
