package PruebasExamenesPrimerTrimestre;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class FormAccesoMR implements ActionListener {

	private final int ANCHO = 650;
	private final int ALTURA = 550;

	private JLabel lblMensaje;
	private JButton btnSi;
	private JButton btnNo;

	private JFrame ventana;
	private boolean respuesta;

	public FormAccesoMR() {

		// configurar ventana
		ventana = new JFrame();
		ventana.setTitle("Acceso de movilidad reducida");
		ventana.setSize(ANCHO, ALTURA);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout());

		// panel norte

		JPanel panelNorte = new JPanel();
		ventana.add(panelNorte, BorderLayout.NORTH);

		lblMensaje = new JLabel("¿La atraccion tiene acceso de movilidad reducida?");
		panelNorte.add(lblMensaje);

		// panel center
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(10, 10, 10, 10));
		ventana.add(panelCenter, BorderLayout.CENTER);

		btnSi = new JButton("Si");
		btnNo = new JButton("No");

		panelCenter.add(btnSi);
		panelCenter.add(btnNo);

		// ***crear eventos***

		// boton si y no
		anadirListenerBotones();

		ventana.setVisible(true);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent evento) {
		
		if (evento.getSource() == btnSi) {
			respuesta=true;
		} else if(evento.getSource() == btnNo){
			respuesta=false;
		}
		
		JOptionPane.showMessageDialog(ventana, "Respuesta: " + (respuesta ? "Si" : "No"), "Confirmacion: ", JOptionPane.INFORMATION_MESSAGE );
		ventana.dispose();

	}

	private void anadirListenerBotones() {
		btnSi.addActionListener(this);
		btnNo.addActionListener(this);
	}
	
//------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new FormAccesoMR();
				
			}
		});
	}

}
