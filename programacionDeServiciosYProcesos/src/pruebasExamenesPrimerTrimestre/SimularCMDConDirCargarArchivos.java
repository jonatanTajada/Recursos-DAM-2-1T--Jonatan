package pruebasExamenesPrimerTrimestre;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SimularCMDConDirCargarArchivos {

	private JFrame ventana;
	private JTextArea txtArea;
	private JScrollPane scrollPane;
	private JButton btnSalir;

	public SimularCMDConDirCargarArchivos() {

		ventana = new JFrame("Simulador CMD con 'dir'");
		ventana.setSize(650, 550);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout());

		// Panel central
		JPanel panelCenter = new JPanel();
		ventana.add(panelCenter, BorderLayout.CENTER);

		txtArea = new JTextArea(20, 50);
		txtArea.setEditable(false);
		txtArea.setBackground(Color.BLACK);
		txtArea.setForeground(Color.WHITE);
		txtArea.setFont(new Font("Consolas", Font.PLAIN, 14));

		scrollPane = new JScrollPane(txtArea);
		panelCenter.add(scrollPane);

		JPanel panelSur = new JPanel();
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(e -> System.exit(0));
		panelSur.add(btnSalir);
		ventana.add(panelSur, BorderLayout.SOUTH);

		ventana.setVisible(true);

		ejecutarDir();
	}

	private void ejecutarDir() {

		try {
			ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "dir");
			pb.redirectErrorStream(true);
			Process proceso = pb.start();

			// leer la salida
			BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream(), "CP850"));
			String linea;

			while ((linea = br.readLine()) != null) {
				txtArea.append(linea + "\n");
			}

			br.close();
			proceso.waitFor();

		} catch (Exception e) {
			txtArea.append("Error al ejecutar el comando 'dir': " + e.getMessage() + "\n");
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new SimularCMDConDirCargarArchivos());
	}
}
