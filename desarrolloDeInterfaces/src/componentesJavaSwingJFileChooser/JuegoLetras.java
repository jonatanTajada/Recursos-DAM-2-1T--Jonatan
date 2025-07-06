package componentesJavaSwingJFileChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Random;

public class JuegoLetras extends JFrame {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5714706675366730854L;
	
	private static final int NUM_LETRAS = 5; 
    private JLabel labelLetra, labelCronometro, labelAciertos;
    private JButton botonIniciarReiniciar;
    private Timer timer;
    private int aciertos = 0;
    private int letrasMostradas = 0;
    private long tiempoInicio;
    private String letraActual;
    private boolean juegoEnCurso = false;
    
    // Variable para conexiOn BBDD
    private Connection conexion;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JuegoLetras juego = new JuegoLetras();
            juego.setVisible(true);
        });
    }

//--------------------------------------------------------------------------------------    
    public JuegoLetras() {
        configurarVentana();
        configurarComponentes();
        configurarEventos();
        configurarConexionBD();
    }

    private void configurarVentana() {
        setTitle("Juego de Letras");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void configurarComponentes() {
        // Etiquetas
        labelLetra = new JLabel("", SwingConstants.CENTER);
        labelLetra.setFont(new Font("Arial", Font.BOLD, 100));
        labelCronometro = new JLabel("Tiempo: 0.00", SwingConstants.CENTER);
        labelAciertos = new JLabel("Aciertos: 0", SwingConstants.CENTER);
        
        // Botón para iniciar/reiniciar
        botonIniciarReiniciar = new JButton("Iniciar");
        
        // Añadir componentes al panel
        add(labelLetra, BorderLayout.CENTER);
        add(labelCronometro, BorderLayout.NORTH);
        add(labelAciertos, BorderLayout.SOUTH);
        add(botonIniciarReiniciar, BorderLayout.EAST);
    }

    private void configurarEventos() {
        // Listener para iniciar/reiniciar el juego
        botonIniciarReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!juegoEnCurso) {
                    iniciarJuego();
                } else {
                    reiniciarJuego();
                }
            }
        });

        // Listener para las teclas pulsadas
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (juegoEnCurso) {
                    verificarLetra(e.getKeyChar());
                }
            }
        });

        // Timer para actualizar el cronómetro
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCronometro();
            }
        });
    }

    private void iniciarJuego() {
        juegoEnCurso = true;
        letrasMostradas = 0;
        aciertos = 0;
        tiempoInicio = System.currentTimeMillis();
        mostrarSiguienteLetra();
        timer.start();
        botonIniciarReiniciar.setText("Reiniciar");
        requestFocus();
    }

    private void reiniciarJuego() {
        timer.stop();
        labelLetra.setText("");
        labelCronometro.setText("Tiempo: 0.00");
        labelAciertos.setText("Aciertos: 0");
        juegoEnCurso = false;
        botonIniciarReiniciar.setText("Iniciar");
    }

    private void verificarLetra(char letraTecleada) {
        if (letraActual.charAt(0) == letraTecleada) {
            aciertos++;
            labelAciertos.setText("Aciertos: " + aciertos);
        }
        letrasMostradas++;
        if (letrasMostradas < NUM_LETRAS) {
            mostrarSiguienteLetra();
        } else {
            finalizarJuego();
        }
    }

    private void mostrarSiguienteLetra() {
        Random rand = new Random();
        letraActual = String.valueOf((char) (rand.nextInt(26) + 'A'));
        labelLetra.setText(letraActual);
    }
    
    /*
     private void mostrarSiguienteLetra() {
    Random rand = new Random();
    
    // Operador ternario para elegir mayuscula o minuscula
    letraActual = rand.nextBoolean() ? String.valueOf((char) (rand.nextInt(26) + 'A')) 
                                     : String.valueOf((char) (rand.nextInt(26) + 'a'));
    
    // Mostrar la letra en la etiqueta
    labelLetra.setText(letraActual);
}
*/

    private void actualizarCronometro() {
        long tiempoActual = System.currentTimeMillis();
        double tiempoTranscurrido = (tiempoActual - tiempoInicio) / 1000.0;
        labelCronometro.setText(String.format("Tiempo: %.2f", tiempoTranscurrido));
    }

    private void finalizarJuego() {
        timer.stop();
        double tiempoFinal = (System.currentTimeMillis() - tiempoInicio) / 1000.0;
        String nombre = JOptionPane.showInputDialog(this, "Introduce tu nombre:");
        if (nombre != null && !nombre.isEmpty()) {
            guardarPuntuacion(nombre, aciertos, tiempoFinal);
            mostrarTopPuntuaciones();
        }
        reiniciarJuego();
    }

    private void configurarConexionBD() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/juego_letras", "root", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void guardarPuntuacion(String nombre, int aciertos, double tiempo) {
        try {
            String sql = "INSERT INTO puntuaciones (nombre, aciertos, tiempo) VALUES (?, ?, ?)";
            
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, aciertos);
            ps.setDouble(3, tiempo);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void mostrarTopPuntuaciones() {
    	
        try {
            String sql = "SELECT nombre, aciertos, tiempo FROM puntuaciones ORDER BY aciertos DESC, tiempo ASC LIMIT 10";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            StringBuilder top = new StringBuilder("Top 10 Puntuaciones:\n");
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int aciertos = rs.getInt("aciertos");
                double tiempo = rs.getDouble("tiempo");
                top.append(nombre).append(" - Aciertos: ").append(aciertos).append(", Tiempo: ").append(tiempo).append("s\n");
            }
            JOptionPane.showMessageDialog(this, top.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
}
