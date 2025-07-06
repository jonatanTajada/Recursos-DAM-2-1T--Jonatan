package componentesJavaSwingJFileChooser;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class QuicksortVisualizer extends JFrame {
	
	private static final long serialVersionUID = 8931729534624881415L;
	
	private int[] alturasRectangulos = new int[40];

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			QuicksortVisualizer visualizador = new QuicksortVisualizer();
			visualizador.setVisible(true);
		});
	}
	
//-----------------------------------------------------------------------------------------------------------------------------------	
	
	public QuicksortVisualizer() {
		
		setTitle("Visualización de Quicksort");
		setSize(1200, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// Llenar el array con alturas aleatorias
		Random random = new Random();
		for (int i = 0; i < alturasRectangulos.length; i++) {
			alturasRectangulos[i] = random.nextInt(800) + 20; // Alturas entre 20 y 800 píxeles
		}

		// Añadir el panel donde se dibujarán los rectángulos
		add(new VisualizacionPanel(alturasRectangulos), BorderLayout.CENTER);

		JButton btnOrdenar = new JButton("Ordenar");
		btnOrdenar.addActionListener(e -> ordenarRectangulos());
		add(btnOrdenar, BorderLayout.SOUTH);
	}

	private void ordenarRectangulos() {
		new Thread(() -> quicksort(alturasRectangulos, 0, alturasRectangulos.length - 1)).start();
	}

	private void quicksort(int[] array, int bajo, int alto) {
		if (bajo < alto) {
			int indiceParticion = particion(array, bajo, alto);
			quicksort(array, bajo, indiceParticion - 1);
			quicksort(array, indiceParticion + 1, alto);
		}
	}

	private int particion(int[] array, int bajo, int alto) {
		int pivot = array[alto];
		int i = (bajo - 1);
		for (int j = bajo; j < alto; j++) {
			if (array[j] < pivot) {
				i++;
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				actualizarPanel(); // Actualizar visualización después de cada intercambio
			}
		}

		int temp = array[i + 1];
		array[i + 1] = array[alto];
		array[alto] = temp;
		actualizarPanel(); 
		return i + 1;
	}

	private void actualizarPanel() {
		try {
			Thread.sleep(100); // 100 ms para visualizar el cambio
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		repaint();
	}

	

//--------------- Clase interna para el panel de visualización---------------------------------------------------------------------------------------------------------------
	private class VisualizacionPanel extends JPanel {
		
		private int[] alturas;

		public VisualizacionPanel(int[] alturas) {
			this.alturas = alturas;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			int anchoRectangulo = (getWidth() - (alturas.length - 1) * 5) / alturas.length; // Ancho de cada rectangulo con separacion: 5
																							
			for (int i = 0; i < alturas.length; i++) {
				int x = i * (anchoRectangulo + 5);
				int y = getHeight() - alturas[i];
				g.fillRect(x, y, anchoRectangulo, alturas[i]);
			}
		}
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------	

}



