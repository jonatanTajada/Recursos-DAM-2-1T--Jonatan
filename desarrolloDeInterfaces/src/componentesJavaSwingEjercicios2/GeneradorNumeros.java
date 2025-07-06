package componentesJavaSwingEjercicios2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class GeneradorNumeros extends JFrame implements ActionListener {

   
	private static final long serialVersionUID = 3962698082204987571L;
	private JSpinner spinnerNumero1;
    private JSpinner spinnerNumero2;
    private JTextField campoNumeroGenerado;
    private JButton botonGenerar;
    
    public static void main(String[] args) {
        // Ejecutar la aplicación
        SwingUtilities.invokeLater(() -> new GeneradorNumeros());
    }
    
    
    public GeneradorNumeros() {
    	
        // Configurar la ventana
        setTitle("Generador de números");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout(10, 10)); 

        // Crear el panel superior con los dos spinners
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new GridLayout(2, 2, 5, 5)); 

        panelSuperior.add(new JLabel("Número 1:"));
        spinnerNumero1 = new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
        panelSuperior.add(spinnerNumero1);

        panelSuperior.add(new JLabel("Número 2:"));
        spinnerNumero2 = new JSpinner(new SpinnerNumberModel(10, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
        panelSuperior.add(spinnerNumero2);

        // Añadir el panel superior 
        add(panelSuperior, BorderLayout.NORTH);

        // Crear el campo de texto 
        campoNumeroGenerado = new JTextField(10);
        campoNumeroGenerado.setEditable(false);
        add(campoNumeroGenerado, BorderLayout.CENTER);

        // Crear el boton Generar
        botonGenerar = new JButton("Generar");
        botonGenerar.addActionListener(this);
        add(botonGenerar, BorderLayout.SOUTH); 

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
  
        int numero1 = (int) spinnerNumero1.getValue();
        int numero2 = (int) spinnerNumero2.getValue();

        int min = Math.min(numero1, numero2);
        int max = Math.max(numero1, numero2);

        // Generar numero aleatorio entre los dos valores
        Random random = new Random();
        int numeroGenerado = random.nextInt((max - min) + 1) + min;

        // Mostrar el numero generado en el JTextField
        campoNumeroGenerado.setText(String.valueOf(numeroGenerado));
    }

}
