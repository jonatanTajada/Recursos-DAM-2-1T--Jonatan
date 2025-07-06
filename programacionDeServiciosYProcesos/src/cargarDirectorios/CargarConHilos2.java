package cargarDirectorios;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CargarConHilos2 extends JFrame {
  
	private static final long serialVersionUID = 5703050007059597462L;
	
	private JTextArea textArea;
	
	   public static void main(String[] args) {
	        new CargarConHilos2();
	    }

    public CargarConHilos2() {
    	
        setTitle("Cargar Directorios de C: Con Hilos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton cargarBtn = new JButton("Cargar Directorios");
        
        cargarBtn.addActionListener(e -> {
            Thread hilo = new Thread(() -> cargarDirectorios());
            hilo.start();
        });
        add(cargarBtn, BorderLayout.SOUTH);

        JButton salirBtn = new JButton("Salir");
        salirBtn.addActionListener(e -> System.exit(0));
        add(salirBtn, BorderLayout.NORTH);

        setVisible(true);
    }

    
    private void cargarDirectorios() {
        File unidadC = new File("C:\\");
        listarArchivos(unidadC);
    }

    
    private void listarArchivos(File archivo) {
    	
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

 
}
