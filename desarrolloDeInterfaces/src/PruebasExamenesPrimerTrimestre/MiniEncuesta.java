package PruebasExamenesPrimerTrimestre;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MiniEncuesta extends JFrame {

	private JLabel lblRadioBtn;
	private JRadioButton rbWindows;
	private JRadioButton rbLinux;
	private JRadioButton rbMac;

	private JLabel lblCheckBox;
	private JCheckBox checkBoxProgramacion;
	private JCheckBox checkBoxDisenoGrafico;
	private JCheckBox checkBoxAdministracion;

	private JSlider slider;
	private JLabel lblHoras;
	private JButton btnGenerar;

	public MiniEncuesta() {

		// Configurar frame
		setTitle("Mini Encuesta");
		setSize(350, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Panel Norte - Elige un Sistema Operativo
		JPanel panelNorte = new JPanel();
		panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS));
		lblRadioBtn = new JLabel("Elige un sistema operativo");
		rbWindows = new JRadioButton("Windows");
		rbLinux = new JRadioButton("Linux");
		rbMac = new JRadioButton("Mac");

		ButtonGroup grupoRadioButton = new ButtonGroup();
		grupoRadioButton.add(rbWindows);
		grupoRadioButton.add(rbLinux);
		grupoRadioButton.add(rbMac);

		panelNorte.add(lblRadioBtn);
		panelNorte.add(rbWindows);
		panelNorte.add(rbLinux);
		panelNorte.add(rbMac);

		add(panelNorte, BorderLayout.NORTH);

		// Panel Centro - Elige tu Especialidad
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		lblCheckBox = new JLabel("Elige tu especialidad");
		checkBoxProgramacion = new JCheckBox("Programación");
		checkBoxDisenoGrafico = new JCheckBox("Diseño gráfico");
		checkBoxAdministracion = new JCheckBox("Administración");

		panelCenter.add(lblCheckBox);
		panelCenter.add(checkBoxProgramacion);
		panelCenter.add(checkBoxDisenoGrafico);
		panelCenter.add(checkBoxAdministracion);

		add(panelCenter, BorderLayout.CENTER);

		// Panel Sur - Horas dedicadas en el ordenador
		JPanel panelSur = new JPanel();
		panelSur.setLayout(new BoxLayout(panelSur, BoxLayout.Y_AXIS));
		JLabel lblHorasTitulo = new JLabel("Horas que dedicas en el ordenador:");
		slider = new JSlider(0, 10, 0);
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		lblHoras = new JLabel("Horas: 0");
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				lblHoras.setText("Horas: " + slider.getValue());
			}
		});

		panelSur.add(lblHorasTitulo);
		panelSur.add(slider);
		panelSur.add(lblHoras);

		add(panelSur, BorderLayout.SOUTH);

		// Botón Generar
		btnGenerar = new JButton("Generar");
		
		btnGenerar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Obtener el sistema operativo seleccionado
				String sistemaOperativo = "";
				
				if (rbWindows.isSelected())
					sistemaOperativo = "Windows";
				else if (rbLinux.isSelected())
					sistemaOperativo = "Linux";
				else if (rbMac.isSelected())
					sistemaOperativo = "Mac";

				// Obtener especialidades seleccionadas
				StringBuilder especialidades = new StringBuilder();
				if (checkBoxProgramacion.isSelected())
					especialidades.append("Programación ");
				if (checkBoxDisenoGrafico.isSelected())
					especialidades.append("Diseño gráfico ");
				if (checkBoxAdministracion.isSelected())
					especialidades.append("Administración ");

				// Obtener valor del slider
				int horas = slider.getValue();

				// Mostrar los resultados en un JOptionPane
				JOptionPane.showMessageDialog(null,
						"Tu sistema operativo preferido es " + sistemaOperativo + ", tus especialidades son "
								+ especialidades.toString() + "y el número de horas dedicadas al ordenador son "
								+ horas);
			}
		});

		add(btnGenerar, BorderLayout.LINE_END);

		setVisible(true);
	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MiniEncuesta();
			}
		});
	}
}
