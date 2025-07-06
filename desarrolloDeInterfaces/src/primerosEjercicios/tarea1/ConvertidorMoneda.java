package primerosEjercicios.tarea1;

import javax.swing.*;
import java.awt.*;

//ejercicio de la pagina 26. 2/3. Tema 2 InterfazGraficaJava
//Sin windowBuilder
public class ConvertidorMoneda extends JFrame {

  
	private static final long serialVersionUID = -8349550199556295933L;
	private JTextField txtCantidad;
    private JLabel lblResultado;

    public ConvertidorMoneda() {
    	
        // Configuración de la ventana
        setTitle("Convertidor de Moneda €/$");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear el panel central
        JPanel panelCentral = new JPanel(new FlowLayout());

        // Crear componentes
        JLabel lblCantidad = new JLabel("Cantidad:");
        txtCantidad = new JTextField(10);

        String[] opcionesConversion = {"€ a $", "$ a €"};
        JComboBox<String> comboConversion = new JComboBox<>(opcionesConversion);

        JButton btnConvertir = new JButton("Convertir");
        lblResultado = new JLabel("Resultado: ");

        // Añadir ActionListener al botón
        btnConvertir.addActionListener(e -> realizarConversion(comboConversion.getSelectedItem().toString()));

        // Añadir componentes al panel central
        panelCentral.add(lblCantidad);
        panelCentral.add(txtCantidad);
        panelCentral.add(comboConversion);
        panelCentral.add(btnConvertir);

        // Crear el panel para el resultado
        JPanel panelResultado = new JPanel(new FlowLayout());
        panelResultado.add(lblResultado);

        // Añadir los paneles al BorderLayout
        add(panelCentral, BorderLayout.CENTER);
        add(panelResultado, BorderLayout.SOUTH);
    }

    // Método para realizar la conversión
    private void realizarConversion(String tipoConversion) {
        try {
            double cantidad = Double.parseDouble(txtCantidad.getText());
            double resultado;

            if (tipoConversion.equals("€ a $")) {
                resultado = cantidad * 0.91; 
            } else {
                resultado = cantidad / 0.91; 
            }

            lblResultado.setText("Resultado: " + String.format("%.2f", resultado));
        } catch (NumberFormatException ex) {
            lblResultado.setText("Introduce un valor numérico válido");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConvertidorMoneda ventana = new ConvertidorMoneda();
            ventana.setVisible(true);
        });
    }
}
