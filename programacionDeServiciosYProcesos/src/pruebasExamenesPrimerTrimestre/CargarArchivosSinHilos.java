package pruebasExamenesPrimerTrimestre;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class CargarArchivosSinHilos {

	
	private JFrame ventana;
	private JTextArea txtArea;
	private JScrollPane scrollPane;
	private JButton btnSalir;
	
	
	public CargarArchivosSinHilos() {
		
		//configurar ventana
		ventana = new JFrame();
		ventana.setTitle("Cargar archivos sin hilos");
		ventana.setSize(650, 550);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout());
		
		
		//panel center
		JPanel panelCenter = new JPanel();
		ventana.add(panelCenter, BorderLayout.CENTER);
		
		txtArea = new JTextArea(20,40);
		txtArea.setEditable(false);
		scrollPane = new JScrollPane(txtArea);
	
		panelCenter.add(scrollPane);
		
		//panel sur
		JPanel panelSur = new JPanel();
		ventana.add(panelSur, BorderLayout.SOUTH);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(e -> System.exit(0));
		
		panelSur.add(btnSalir);
		
		
		
		ventana.setVisible(true);
		File directorio = new File("C:\\Users\\Jonathan\\Desktop");
		cargarArchivos(directorio);
		
		
	}
	
	
	  private void cargarArchivos(File directorio) {
		  
	        if (directorio.exists() && directorio.isDirectory()) {
	            for (File archivo : directorio.listFiles()) {
	                txtArea.append(archivo.getName() + "\n");
	            }
	        } else {
	            txtArea.append("No se pudo acceder al directorio o no es un directorio válido.\n");
	        }
	    }
	
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()-> new CargarArchivosSinHilos());
	}
	
	
	
	
}
