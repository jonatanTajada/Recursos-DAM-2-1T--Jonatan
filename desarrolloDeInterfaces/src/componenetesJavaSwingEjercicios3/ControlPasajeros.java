package componenetesJavaSwingEjercicios3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ControlPasajeros extends JFrame {


	private static final long serialVersionUID = 3337671763602504156L;
	private JComboBox<String> procedenciaComboBox;
	private JSpinner bultosSpinner;
	private JButton sortearButton;
	private JPanel resultadoPanel;
	private int revisados = 0;
	private int noRevisados = 0;
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ControlPasajeros control = new ControlPasajeros();
			control.setVisible(true);
		});
	}

	

	public ControlPasajeros() {
		setTitle("Revisados: 0  No revisados: 0");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		// Crear componentes
		JLabel bultosLabel = new JLabel("Cantidad de bultos");
		bultosSpinner = new JSpinner(new SpinnerNumberModel(1, 0, 10, 1));
		JLabel procedenciaLabel = new JLabel("Procedencia");
		procedenciaComboBox = new JComboBox<>(new String[] { "Interior", "Exterior" });
		sortearButton = new JButton("Sortear");
		resultadoPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(getBackground());
				g.fillOval(50, 20, 100, 100);
			}
		};
		resultadoPanel.setPreferredSize(new Dimension(150, 150));

		// Añadir componentes al layout
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(bultosLabel, gbc);
		gbc.gridx = 1;
		add(bultosSpinner, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(procedenciaLabel, gbc);
		gbc.gridx = 1;
		add(procedenciaComboBox, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		add(sortearButton, gbc);

		gbc.gridy = 3;
		add(resultadoPanel, gbc);

		// Evento del botón "Sortear"
		sortearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				realizarSorteo();
			}
		});
	}

	private void realizarSorteo() {
		int bultos = (int) bultosSpinner.getValue();
		if (bultos == 0) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un bulto.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Random random = new Random();
		int sorteo = random.nextInt(3) + 1;

		if (bultos > 5 || sorteo == 1) {
			resultadoPanel.setBackground(Color.RED);
			revisados++;
		} else {
			resultadoPanel.setBackground(Color.GREEN);
			noRevisados++;
		}

		// Actualizar título de la ventana
		setTitle("Revisados: " + revisados + "  No revisados: " + noRevisados);

		// Restablecer el número de bultos a cero después del sorteo
		bultosSpinner.setValue(0);

		// Actualizar la visualización del círculo
		resultadoPanel.repaint();
	}

}