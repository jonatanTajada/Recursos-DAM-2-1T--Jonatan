
package cargarDirectorios;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

// hasta que no termine de cargar, todo en el mismo hilo,  la tarea no mostrara la ventana, porque no uso SwingWorker. SINCRONA


public class CargarSinHilos extends JFrame {

    private static final long serialVersionUID = 6316360495311821030L;
    
    private JTextArea textArea;
    private boolean cancelado = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new CargarSinHilos());
    }

    public CargarSinHilos() {
    	
        setTitle("Cargar Directorios: Sin Hilos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton cancelarBtn = new JButton("Cancelar");
        cancelarBtn.addActionListener(e -> cancelarCarga());
        add(cancelarBtn, BorderLayout.SOUTH);

        // Lanzar la carga de archivos inmediatamente al abrir el JFrame en un directorio más pequeño.
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            cargarDirectorios();
        });
    }

    private void cargarDirectorios() { 
    	
        
        File unidadC = new File("C:\\Users\\Jonathan\\Desktop");
        listarArchivos(unidadC);
    }

    private void listarArchivos(File archivo) {
        if (cancelado) {
            return; 
        }

        if (archivo.isDirectory()) {
        	
            textArea.append("Directorio: " + archivo.getAbsolutePath() + "\n");
            File[] contenido = archivo.listFiles();
            if (contenido != null) {
                for (File f : contenido) {
                    listarArchivos(f);
                }
            }
        } else {
            textArea.append("Archivo: " + archivo.getAbsolutePath() + "\n");
        }
    }

    private void cancelarCarga() {
        cancelado = true; 
        textArea.append("Carga cancelada.\n");
    }
}
