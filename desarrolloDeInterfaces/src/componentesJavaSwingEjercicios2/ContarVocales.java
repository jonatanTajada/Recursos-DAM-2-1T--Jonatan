package componentesJavaSwingEjercicios2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class ContarVocales extends JFrame implements KeyListener{


	private static final long serialVersionUID = -2138134708025196672L;
	JTextArea areaEntrada;
	JTextArea areaSalida;
	JLabel lblVocales;
	private int contadorVocales;
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ContarVocales ventana = new ContarVocales();
				ventana.setVisible(true);
			}
		});
	}


	public ContarVocales() {
		
		//configurar el jframe
		setTitle("Diseño de interfaces: Ventana Eventos del Teclado");
		setSize(550, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		//crear panel norte
		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("EVENTOS DEl TECLADO", JLabel.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		panelNorte.add(lblTitulo);
		
		//panel central
		JPanel panelTexto = new JPanel();
		panelTexto.setLayout(new GridLayout(2, 1));
		
		   //AREA DE ENTRADA
		areaEntrada = new JTextArea("Esto es una prueba para contar vocales:\n");
		areaEntrada.addKeyListener(this);
		panelTexto.add(new JScrollPane(areaEntrada));
		
		  //AREA DE SALIDA
		areaSalida = new JTextArea();
		areaSalida.setEditable(false);
		panelTexto.add(new JScrollPane(areaSalida));
		
		add(panelTexto, BorderLayout.CENTER);
		
		//panel sur
		JPanel panelSur = new JPanel();
		panelSur.setLayout(new BorderLayout());
		
		JLabel lblInfo = new JLabel("Para salir presione la tecla Escape ", JLabel.LEFT);
		lblVocales = new JLabel("Numero de vocales: 0", JLabel.RIGHT);
		
		panelSur.add(lblInfo, BorderLayout.WEST);
		panelSur.add(lblVocales, BorderLayout.EAST);
		
		add(panelSur, BorderLayout.SOUTH);
		
		
		
		
	}
	
	
	//metodos de la interfaz KeyListener
	@Override
	public void keyTyped(KeyEvent e) {
		
		char keyChar = e.getKeyChar();
		
		if (esVocal(keyChar)) {
			areaSalida.append(keyChar + " ");
			contadorVocales++;
			lblVocales.setText("Numero Vocales: " + contadorVocales);
		}
	}
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	//meto para verificar si es una vocal
	private boolean esVocal(char c) {
        return "aeiouáéíóúAEIOUÁÉÍÓÚ".indexOf(c) != -1;
    }
	
}
