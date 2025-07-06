package componentesJavaSwingEjercicios;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ComprobacioTeclas extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComprobacioTeclas frame = new ComprobacioTeclas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ComprobacioTeclas() {
		this.setTitle("Comprobación de la tecla pulsada");
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		this.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("Has pulsado la tecla →");
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("Has pulsado la tecla ←");
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("Has pulsado la tecla ↓");
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("Has pulsado la tecla ↑");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
