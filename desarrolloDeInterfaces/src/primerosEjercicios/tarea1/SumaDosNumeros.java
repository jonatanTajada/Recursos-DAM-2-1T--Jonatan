package primerosEjercicios.tarea1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

//ejercicio de la pagina 26. 1/3. Tema 2 InterfazGraficaJava
// Sin windowBuilder

public class SumaDosNumeros extends JFrame {

	
	private static final long serialVersionUID = 4345498252042321764L;
	private JTextField txtNum1;
    private JTextField txtNum2;
    private JLabel lblResultado;

    public SumaDosNumeros() {
        // Configuración de la ventana
        setTitle("Suma de dos enteros");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear el panel para los campos de texto y el botón
        JPanel panelCentral = new JPanel(new FlowLayout());
        
        JLabel lblNum1 = new JLabel("Número 1:");
        txtNum1 = new JTextField(10);
        
        JLabel lblNum2 = new JLabel("Número 2:");
        txtNum2 = new JTextField(10);
        
        JButton btnSumar = new JButton("Sumar");
        JButton btnCerrar = new JButton("Cerrar aplicacion");
        
        // Crear el panel para el resultado
        lblResultado = new JLabel("Resultado: ");
        
        JPanel panelResultado = new JPanel(new FlowLayout());
        panelResultado.add(lblResultado);

        // Añadir componentes al panel central
        panelCentral.add(lblNum1);
        panelCentral.add(txtNum1);
        panelCentral.add(lblNum2);
        panelCentral.add(txtNum2);
        panelCentral.add(btnSumar);
        panelCentral.add(btnCerrar);

        // Añadir ActionListener al botón
        btnSumar.addActionListener(e -> calcularSuma());
        btnCerrar.addActionListener(e-> dispose());

        // Añadir los paneles al BorderLayout
        add(new JLabel("Introduce números enteros para calcular la suma:"), BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelResultado, BorderLayout.SOUTH);
    }

    // Método para calcular la suma y actualizar JLabel
    public void calcularSuma() {
        try {
            // Obtener y validar los números
            int num1 = Integer.parseInt(txtNum1.getText());
            int num2 = Integer.parseInt(txtNum2.getText());

            int suma = num1 + num2;

            // Mostrar resultado
            lblResultado.setText("Resultado: " + suma);
        } catch (NumberFormatException ex) {
            lblResultado.setText("Introduce valores numéricos válidos");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SumaDosNumeros ventana = new SumaDosNumeros();
            ventana.setVisible(true);
        });
    }
}
