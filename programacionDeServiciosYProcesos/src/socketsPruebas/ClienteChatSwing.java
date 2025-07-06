package socketsPruebas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//servidor simula como si fuera usuario

public class ClienteChatSwing extends VentanaChat {
	
	
    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;

    public ClienteChatSwing() {
        super("Cliente - Usuario 2");
        conectarAlServidor();
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });
    }

    private void conectarAlServidor() {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 12345);
                areaMensajes.append("Conectado al servidor.\n");

                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                salida = new PrintWriter(socket.getOutputStream(), true);

                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    areaMensajes.append("Servidor: " + mensaje + "\n");
                }
            } catch (IOException e) {
                areaMensajes.append("Error al conectar al servidor.\n");
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
        new ClienteChatSwing();
    }
}
