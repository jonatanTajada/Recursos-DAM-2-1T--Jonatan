package cargarDirectorios;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

//SwingWorker:
				//es una clase que facilita la ejecucion de tareas en segundo plano en aplicaciones Swing.
				//Permite realizar operaciones largas sin bloquear la interfaz gráfica (GUI),
				//y ofrece métodos como `publish()` para actualizar la GUI de manera segura mientras la tarea se ejecuta.

//El método `process()` recibe una lista de elementos llamada `chunks` que son los datos
//que se han publicado desde el método `doInBackground()` usando `publish()`.
//Cada vez que se llama a `publish()`, los datos se acumulan en esta lista (`chunks`).
//El método `process()` se ejecuta en el hilo de la interfaz gráfica (EDT), lo que significa
//que es seguro actualizar la GUI desde aquí. En este caso, se recorren los `chunks`
//(cada línea de texto) y se añaden al `textArea`.



public class CargarConHilos extends JFrame {

    private static final long serialVersionUID = 5703050007059597462L;
    
    private JTextArea textArea;
    private SwingWorker<Void, String> worker;

    public static void main(String[] args) {
        new CargarConHilos();
    }

    
    public CargarConHilos() {
    	
        setTitle("Cargar Directorios de C: Con Hilos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton cancelarBtn = new JButton("Cancelar");
        cancelarBtn.addActionListener(e -> cancelarCarga());
        add(cancelarBtn, BorderLayout.SOUTH);

        
        setVisible(true);
        cargarDirectoriosEnSegundoPlano();
    }

    private void cargarDirectoriosEnSegundoPlano() {
    	
        worker = new SwingWorker<>() {
        	
            @Override
            protected Void doInBackground() {
                File unidadC = new File("C:\\");
                listarArchivos(unidadC);
                return null;
            }

            
            private void listarArchivos(File archivo) {
            	
                if (isCancelled()) {
                    publish("Carga cancelada.\n");
                    return;
                }

                if (archivo.isDirectory()) {
                	
                    publish("Directorio: " + archivo.getAbsolutePath() + "\n");
                    File[] contenido = archivo.listFiles();
                    if (contenido != null) {
                        for (File f : contenido) {
                            if (isCancelled()) break;
                            listarArchivos(f);
                        }
                    }
                } else {
                    publish("Archivo: " + archivo.getAbsolutePath() + "\n");
                }
            }

            @Override
            protected void process(java.util.List<String> datos) {
                for (String line : datos) {
                    textArea.append(line);
                }
            }
        };

        worker.execute();
    }

    private void cancelarCarga() {
        if (worker != null && !worker.isCancelled()) {
            worker.cancel(true); 
            textArea.append("Carga cancelada.\n");
        }
    }
}
