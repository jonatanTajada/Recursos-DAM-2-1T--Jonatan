package PruebasExamenesPrimerTrimestre;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MostrarRutaFicheroMenu extends JFrame {

    private static final long serialVersionUID = -7580123364618357845L;
    
    private JLabel lblMensaje;
    private JTextField txtRuta;

    public MostrarRutaFicheroMenu() {
        // Configuración de la ventana
        setTitle("Mostrar ruta fichero");
        setSize(650, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear barra de menú
        crearMenu();

        // Panel norte
        JPanel panelNorte = new JPanel();
        add(panelNorte, BorderLayout.NORTH);

        lblMensaje = new JLabel("Pulsa en 'File > Abrir...' para seleccionar un archivo");
        panelNorte.add(lblMensaje, BorderLayout.CENTER);

        // Panel central
        JPanel panelCenter = new JPanel();
        add(panelCenter, BorderLayout.CENTER);

        txtRuta = new JTextField();
        txtRuta.setPreferredSize(new Dimension(600, 25));
        txtRuta.setEditable(false);
        panelCenter.add(txtRuta, BorderLayout.CENTER);

        setVisible(true);
    }

    private void crearMenu() {
        // Crear barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Crear menú "File"
        JMenu menuArchivo = new JMenu("File");

        // Crear item "Abrir"
        JMenuItem itemAbrir = new JMenuItem("Abrir...");
        itemAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFileChooser();
            }
        });

        // Crear item "Salir"
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Agregar items al menú "File"
        menuArchivo.add(itemAbrir);
        menuArchivo.add(itemSalir);

        // Agregar menú a la barra de menú
        menuBar.add(menuArchivo);

        // Establecer la barra de menú en el JFrame
        setJMenuBar(menuBar);
    }

    // Método para abrir JFileChooser
    private void abrirFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona un archivo .txt");

        FileNameExtensionFilter filtroTxt = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
        fileChooser.setFileFilter(filtroTxt);

        int seleccion = fileChooser.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            txtRuta.setText(archivoSeleccionado.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MostrarRutaFicheroMenu();
            }
        });
    }
}
