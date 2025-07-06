package socketsPruebas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


//servidor simula como si fuera usuario

public class ServidorChatSwing extends VentanaChat {
	
    private ServerSocket serverSocket;
    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;

    public ServidorChatSwing() {
    	
        super("Servidor - Usuario 1");
        configurarServidor();
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });
    }

    
    private void configurarServidor() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(12345);
                areaMensajes.append("Esperando conexión...\n");
                socket = serverSocket.accept();
                areaMensajes.append("Cliente conectado.\n");

                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                salida = new PrintWriter(socket.getOutputStream(), true);

                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    areaMensajes.append("Cliente: " + mensaje + "\n");
                }
            } catch (IOException e) {
                areaMensajes.append("Error en el servidor.\n");
            }
        }).start();
    }
    

    @Override
    protected void enviarMensaje() {
        String mensaje = campoEntrada.getText();
        if (!mensaje.isEmpty()) {
            salida.println(mensaje);
            areaMensajes.append("Tú: " + mensaje + "\n");
            campoEntrada.setText("");
        }
    }

    public static void main(String[] args) {
        new ServidorChatSwing();
    }
}
