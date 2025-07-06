package PruebasExamenesPrimerTrimestre;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class FormMatricula implements ActionListener {

	private JFrame ventana;

	private JLabel lblMatricula;
	private JTextField txtMatricula;
	private JButton btnMatricular;

	private JTable tabla;
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	
	private ArrayList<Barco> listaBarcos;

	public FormMatricula() {
		
		listaBarcos = new ArrayList<Barco>();

		// configurar ventana
		ventana = new JFrame();
		ventana.setTitle("Matriculacion barco");
		ventana.setSize(650, 550);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(new BorderLayout());

		// panel norte
		JPanel panelNorte = new JPanel();
		panelNorte.setLayout(new BorderLayout());

		lblMatricula = new JLabel("Matricula: ");
		txtMatricula = new JTextField(15);

		btnMatricular = new JButton("Matricular");

		panelNorte.add(lblMatricula, BorderLayout.WEST);
		panelNorte.add(txtMatricula, BorderLayout.CENTER);
		panelNorte.add(btnMatricular, BorderLayout.SOUTH);

		ventana.add(panelNorte, BorderLayout.NORTH);

		// panel central
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());

		String[] colunmNames = { "Matricula", "Nombre", "Eslora", "Cabinas" };
		dtm = new DefaultTableModel(colunmNames, 0);
		tabla = new JTable(dtm);
		jsp = new JScrollPane(tabla);

		panelCentral.add(jsp, BorderLayout.CENTER);

		ventana.add(panelCentral, BorderLayout.CENTER);

		// añadir listener al boton
		anadirListenerBotones();

		ventana.setVisible(true);
		
	}//cierre de constructor

	
	//metodo añadir listener al bton
	private void anadirListenerBotones() {
		btnMatricular.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		String matricula = txtMatricula.getText().trim();
		
		if (existeBarcoPorMatricula(matricula)) {
			JOptionPane.showMessageDialog(ventana, "Error: La matricula '" + matricula + "' ya esta registrada en nuestra base de datos, introduce una nueva",
					"Matricula duplicada", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		//recoger datos
		String nombre = JOptionPane.showInputDialog(ventana, "Introduce el nombre del barco:");
		String esloraStr = JOptionPane.showInputDialog(ventana, "Introduce el total de metros de eslora:");
		String cabinaStr = JOptionPane.showInputDialog(ventana, "Introduce el numero de cabinas:");
		
		try {
			
			float eslora = Float.parseFloat(esloraStr);
			int cabina = Integer.parseInt(cabinaStr);
			
			if (nombre == null || nombre.isEmpty() || esloraStr == null || esloraStr.isEmpty() || cabinaStr == null || cabinaStr.isEmpty()) {
			    JOptionPane.showMessageDialog(ventana, "Por favor, completa todos los datos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
			    return;
			}

			
			Barco barcoNuevo = new Barco(matricula, nombre, eslora, cabina);
			listaBarcos.add(barcoNuevo);
			
			actualizadTabla();
			
			txtMatricula.setText("");
			
		} catch (NumberFormatException e2) {
			 JOptionPane.showMessageDialog(ventana, "Error: Asegúrate de introducir números válidos para la eslora y las cabinas.", 
					 "Error de entrada", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	
	

	//metodo comprobar marticula si existe
	private boolean existeBarcoPorMatricula(String matricula) {
		
		for (Barco barco : listaBarcos) {
			if (barco.getMatricula().equalsIgnoreCase(matricula)) {
				return true;
			}
		}
		return false;
	}
	
	//metodo para actualizar tabla con los barcos matriculados
	private void actualizadTabla() {
		dtm.setRowCount(0);
		for (Barco barco : listaBarcos) {
			Object[] rowData = {barco.getMatricula(), barco.getNombre(), barco.getEslora(), barco.getCabinas()};
			dtm.addRow(rowData);
		}
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new FormMatricula();
			}
		});
		
	}


}
