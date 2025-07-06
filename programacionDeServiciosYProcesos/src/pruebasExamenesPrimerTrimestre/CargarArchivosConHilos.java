package pruebasExamenesPrimerTrimestre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class CargarArchivosConHilos {

    private JFrame ventana;
    private JTextArea txtArea;
    private JScrollPane scrollPane;
    private JButton btnSalir;

    public CargarArchivosConHilos() {
    	
        // Configurar ventana
        ventana = new JFrame();
        ventana.setTitle("Cargar archivos con hilos");
        ventana.setSize(650, 550);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());

        // Panel center
        JPanel panelCenter = new JPanel();
        ventana.add(panelCenter, BorderLayout.CENTER);

        txtArea = new JTextArea(20, 40);
        txtArea.setEditable(false);
        txtArea.setBackground(Color.BLACK);
        txtArea.setForeground(Color.WHITE);
        scrollPane = new JScrollPane(txtArea);

        panelCenter.add(scrollPane); 

        // Panel sur
        JPanel panelSur = new JPanel();
        ventana.add(panelSur, BorderLayout.SOUTH);

        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> System.exit(0));

        panelSur.add(btnSalir);

        ventana.setVisible(true);

        File directorio = new File("C:\\Users\\Jonathan\\Desktop");
        cargarArchivosConHilos(directorio);
    }

    private void cargarArchivosConHilos(File directorio) {
        
    	Thread hiloCarga = new Thread(() -> {
    		if (directorio.exists() && directorio.isDirectory()) {
				
    			for (File archivo : directorio.listFiles()) {
					SwingUtilities.invokeLater(() -> txtArea.append(archivo.getName() + "\n"));
				}
    			
			}else {
				SwingUtilities.invokeLater(() -> txtArea.append("No se pudo accder al directorio o no es un directorio valido.\n"));
			}
    		
    		
    	});
    	
    	hiloCarga.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CargarArchivosConHilos());
    }
}

