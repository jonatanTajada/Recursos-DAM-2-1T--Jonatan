package com.miempresa.tienda.sistema_gestion_tienda.vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.miempresa.tienda.sistema_gestion_tienda.estilos.EstiloUI;

/**
 * Clase base para crear ventanas con diseño uniforme.
 */
public class VentanaBase extends JFrame {

	/**
	 * Constructor para inicializar una ventana base.
	 * 
	 * @param titulo Título de la ventana.
	 */
	public VentanaBase(String titulo) {
		// Configuración básica
		setTitle(titulo);
		setSize(700, 700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// Panel superior con logo y nombre de empresa
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BorderLayout());
		panelSuperior.setBackground(EstiloUI.COLOR_PRIMARIO);
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel lblNombreEmpresa = new JLabel("Logistica Basque");
		lblNombreEmpresa.setFont(EstiloUI.FUENTE_TITULO);
		lblNombreEmpresa.setForeground(Color.WHITE);
		lblNombreEmpresa.setHorizontalAlignment(SwingConstants.LEFT);

		// Logo de la empresa
		ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
		JLabel lblLogo = new JLabel(icono);
		lblLogo.setHorizontalAlignment(SwingConstants.RIGHT);

		panelSuperior.add(lblNombreEmpresa, BorderLayout.WEST);
		panelSuperior.add(lblLogo, BorderLayout.EAST);

		// Agregar el panel superior a la ventana
		add(panelSuperior, BorderLayout.NORTH);
	}
}
