package PrimerosEjerciciosTarea1Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmPersonas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPersonas frame = new FrmPersonas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmPersonas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label labelNombre = new Label("Nombre");
		labelNombre.setBounds(25, 28, 70, 22);
		contentPane.add(labelNombre);
		
		TextField textNombre = new TextField();
		textNombre.setBounds(110, 28, 195, 22);
		contentPane.add(textNombre);
		
		Label labelApellido = new Label("Apellido");
		labelApellido.setBounds(25, 74, 84, 22);
		contentPane.add(labelApellido);
		
		TextField textApellido = new TextField();
		textApellido.setBounds(81, 74, 224, 22);
		contentPane.add(textApellido);
		
		Label labelEdad = new Label("Edad");
		labelEdad.setBounds(25, 120, 84, 22);
		contentPane.add(labelEdad);
		
		TextField textEdad = new TextField();
		textEdad.setBounds(110, 120, 195, 22);
		contentPane.add(textEdad);
		
		Label labelPeso = new Label("Peso");
		labelPeso.setBounds(25, 165, 84, 22);
		contentPane.add(labelPeso);
		
		TextField textPeso = new TextField();
		textPeso.setBounds(110, 165, 195, 22);
		contentPane.add(textPeso);
		
		Button btnNuevo = new Button("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNuevo.setBounds(364, 85, 92, 42);
		contentPane.add(btnNuevo);
		
		Button btnGuardar = new Button("Guardar");
		btnGuardar.setBounds(364, 28, 92, 40);
		contentPane.add(btnGuardar);
		
		Button btnCerrar = new Button("Cerrar");
		btnCerrar.setBounds(364, 147, 92, 40);
		contentPane.add(btnCerrar);
	}
}
