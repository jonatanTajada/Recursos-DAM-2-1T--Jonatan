package cargarDirectorios;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

//La carga de archivos y directorios se hace en un hilo secundario.
//Esto significa que la tarea pesada (listar archivos) no bloquea el hilo principal, que es el que se encarga de la interfaz gráfica (EDT - Event Dispatch Thread). ASINCRONA

public class CargarSinHilos2 extends JFrame {
	

	private static final long serialVersionUID = 6316360495311821030L;
	
	private JTextArea textArea;
	
	 public static void main(String[] args) {
	        new CargarSinHilos2();
	    }

    public CargarSinHilos2() {
    	
        setTitle("Cargar Directorios de C: Mejorado");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton cargarBtn = new JButton("Cargar Directorios");
        cargarBtn.addActionListener(e -> cargarDirectoriosEnSegundoPlano());
        add(cargarBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void cargarDirectoriosEnSegundoPlano() {
    	
    	//SwingWorker: es una clase diseñada para ejecutar tareas en segundo plano y actualizar la interfaz gráfica (GUI) 
    	//sin que la interfaz se quede bloqueada mientras se hace la operación.
    	
        SwingWorker<Void, String> worker = new SwingWorker<>() {
        	
            @Override
            protected Void doInBackground() {
                File unidadC = new File("C:\\");
                listarArchivos(unidadC);
                return null;
            }

            private void listarArchivos(File archivo) {
                if (archivo.isDirectory()) {
                    publish("Directorio: " + archivo.getAbsolutePath() + "\n");
                    File[] contenido = archivo.listFiles();
                    if (contenido != null) {
                        for (File f : contenido) {
                            listarArchivos(f);
                        }
                    }
                } else {
                    publish("Archivo: " + archivo.getAbsolutePath() + "\n");
                }
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                for (String line : chunks) {
                    textArea.append(line);
                }
            }
        };
        worker.execute();
    }

   
}
