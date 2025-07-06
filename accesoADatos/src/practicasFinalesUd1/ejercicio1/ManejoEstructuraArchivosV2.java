package practicasFinalesUd1.ejercicio1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class ManejoEstructuraArchivosV2 {

	public static void main(String[] args) {
		crearMenu();
	}

//-----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	// Método para crear el menu principal
	public static void crearMenu() {
		boolean salir = false;

		while (!salir) {
			String[] opciones = { "Crear estructura de directorios", "Crear fichero pizzas.xml",
					"Leer autores de libros.xml", "Crear y guardar ficheros de alumnos", "Crear fichero de profesores",
					"Salir" };

			int opcion = mostrarMenu(opciones);

			switch (opcion) {
			case 0 -> ejecutarOpcion(() -> crearDirectorios(), "Error al crear directorios");

			case 1 -> ejecutarOpcion(() -> {
				try {
					crearFicheroPizzas();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, "Error al crear fichero pizzas.xml");

			case 2 -> ejecutarOpcion(() -> leerAutoresLibros(), "Error al leer autores de libros.xml");

			case 3 -> ejecutarOpcion(() -> crearFicherosAlumnos(), "Error al crear ficheros de alumnos");

			case 4 -> ejecutarOpcion(() -> crearFicheroProfesores(), "Error al crear fichero de profesores");

			case 5 -> salir = true;

			default -> mostrarMensaje("Opción no válida. Intente de nuevo.", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	// Método para crear la estructura de directorios
	public static void crearDirectorios() throws IOException {
		crearDirectorio("ejercicios");
		crearDirectorio("alumnos");
		crearDirectorio("profesores");
		mostrarMensaje("Estructura de directorios creada.", JOptionPane.INFORMATION_MESSAGE);
	}
	

	// Método para crear el fichero pizzas.xml con sus ingredientes
	public static void crearFicheroPizzas() throws Exception {
		Document doc = crearDocumentoXML();
		Element root = agregarElemento(doc, "pizzas");

		crearPizza(doc, root, "Margarita", "Tomate", "Queso", "Albahaca");
		crearPizza(doc, root, "Pepperoni", "Tomate", "Queso", "Pepperoni");

		guardarXML(doc, "ejercicios/pizzas.xml");
		mostrarMensaje("Fichero pizzas.xml creado con éxito.", JOptionPane.INFORMATION_MESSAGE);
	}

	// Método para leer el fichero libros.xml y mostrar autores o crearlo si no
	// existe y preguntar si desea leer el fichero
	public static void leerAutoresLibros() throws IOException {
		File librosFile = new File("ejercicios/libros.xml");

		if (librosFile.exists()) {
			leerPizzas(librosFile);
		} else {
			if (confirmarAccion("El fichero libros.xml no existe. ¿Deseas crearlo?")) {
				String ruta = obtenerRutaConJFileChooser();
				if (!ruta.isEmpty()) {
					crearXMLPorDefecto(ruta);

					// Preguntar si desea abrir y leer el archivo después de crearlo
					int abrirRespuesta = JOptionPane.showConfirmDialog(null,
							"El archivo libros.xml ha sido creado. ¿Deseas abrirlo y leer su contenido?",
							"Archivo creado", JOptionPane.YES_NO_OPTION);

					if (abrirRespuesta == JOptionPane.YES_OPTION) {
						// Leer el archivo creado
						leerPizzas(new File(ruta + "/libros.xml"));
					}
				}
			}
		}
	}

	// Método para crear y guardar objetos de alumnos en ficheros
	public static void crearFicherosAlumnos() throws IOException {
		guardarAlumno(new AlumnoV2("Jonatan", 36), "alumnos/alumno1.txt", 1);
		guardarAlumno(new AlumnoV2("Andrew", 16), "alumnos/alumno2.txt", 2);
		guardarAlumno(new AlumnoV2("Janire", 36), "alumnos/alumno3.txt", 3);

		leerAlumno("alumnos/alumno1.txt");
		leerAlumno("alumnos/alumno2.txt");
		leerAlumno("alumnos/alumno3.txt");
	}

	// Método para crear un fichero de texto con el listado de profesores
	public static void crearFicheroProfesores() throws IOException {
		String[] profesores = { "Profesor Sonia", "Profesor Blanca", "Profesor Izaskun" };
		escribirTexto("profesores/listadoProfesores.txt", profesores);
		mostrarMensaje("Fichero listadoProfesores.txt creado con éxito.", JOptionPane.INFORMATION_MESSAGE);
	}

	// ------------ Métodos Auxiliares ------------

	// Mostrar menú principal
	private static int mostrarMenu(String[] opciones) {
		return JOptionPane.showOptionDialog(null, "Selecciona una opción:", "Menú", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
	}

	// Ejecutar opción y manejar excepciones
	private static void ejecutarOpcion(IOOperation operacion, String mensajeError) {
		try {
			operacion.ejecutar();
		} catch (IOException e) {
			mostrarMensaje(mensajeError + ": " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}

	// Crear directorio
	private static void crearDirectorio(String ruta) throws IOException {
		Files.createDirectories(Paths.get(ruta));
	}

	// Mostrar mensajes con JOptionPane
	private static void mostrarMensaje(String mensaje, int tipo) {
		JOptionPane.showMessageDialog(null, mensaje, "Información", tipo);
	}

	// Leer autores de libros.xml
	private static void leerPizzas(File librosFile) {
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(librosFile);
			doc.getDocumentElement().normalize();
			NodeList listaPizzas = doc.getElementsByTagName("pizza");
			StringBuilder pizzas = new StringBuilder("Pizzas en libros.xml:\n");

			if (listaPizzas.getLength() > 0) {
				for (int i = 0; i < listaPizzas.getLength(); i++) {
					Node pizza = listaPizzas.item(i);
					Element pizzaElement = (Element) pizza;
					String nombrePizza = pizzaElement.getAttribute("nombre");
					pizzas.append("Pizza: ").append(nombrePizza).append("\nIngredientes:\n");

					NodeList ingredientes = pizzaElement.getElementsByTagName("ingrediente");
					for (int j = 0; j < ingredientes.getLength(); j++) {
						pizzas.append("- ").append(ingredientes.item(j).getTextContent()).append("\n");
					}
					pizzas.append("\n");
				}
				mostrarMensaje(pizzas.toString(), JOptionPane.INFORMATION_MESSAGE);
			} else {
				mostrarMensaje("No se encontraron pizzas en el archivo libros.xml.", JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (Exception e) {
			mostrarMensaje("Error al leer pizzas: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}

	// Confirmar acción del usuario
	private static boolean confirmarAccion(String mensaje) {
		return JOptionPane.showConfirmDialog(null, mensaje, "Confirmación",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	}

	// Obtener ruta de archivo usando JFileChooser
	private static String obtenerRutaConJFileChooser() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Solo directorios
		int seleccion = fileChooser.showOpenDialog(null);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().getAbsolutePath();
		}
		return "";
	}

	// Crear archivo XML por defecto con etiquetas de pizzas e ingredientes
	private static void crearXMLPorDefecto(String ruta) {
		try {
			Document doc = crearDocumentoXML();
			Element root = agregarElemento(doc, "pizzas");

			// Crear pizzas con ingredientes
			crearPizza(doc, root, "Margarita", "Tomate", "Queso", "Albahaca");
			crearPizza(doc, root, "Pepperoni", "Tomate", "Queso", "Pepperoni");

			guardarXML(doc, ruta + "/libros.xml");
			mostrarMensaje("Fichero libros.xml (con pizzas) creado con éxito en " + ruta,
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			mostrarMensaje("Error al crear el archivo libros.xml: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}

	// Crear documento XML
	private static Document crearDocumentoXML() throws ParserConfigurationException {
		return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
	}

	// Agregar elemento a documento XML
	private static Element agregarElemento(Document doc, String nombreElemento) {
		Element element = doc.createElement(nombreElemento);
		doc.appendChild(element);
		return element;
	}

	// Crear una pizza en el XML
	private static void crearPizza(Document doc, Element root, String nombrePizza, String... ingredientes) {
		Element pizza = doc.createElement("pizza");
		pizza.setAttribute("nombre", nombrePizza);
		Element ingredientesElement = doc.createElement("ingredientes");
		for (String ingrediente : ingredientes) {
			ingredientesElement.appendChild(doc.createElement("ingrediente"))
					.appendChild(doc.createTextNode(ingrediente));
		}
		pizza.appendChild(ingredientesElement);
		root.appendChild(pizza);
	}

	// Guardar documento XML
	private static void guardarXML(Document doc, String ruta) throws TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(new DOMSource(doc), new StreamResult(new File(ruta)));
	}

	// Guardar alumno
	private static void guardarAlumno(AlumnoV2 alumno, String ruta, int i) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
			oos.writeObject(alumno);
		}
		mostrarMensaje("Alumno " + i + ": " + alumno, JOptionPane.INFORMATION_MESSAGE);
	}

	// Leer alumno
	private static void leerAlumno(String ruta) throws IOException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
			AlumnoV2 alumno = (AlumnoV2) ois.readObject();
			mostrarMensaje("Alumno leído: " + alumno, JOptionPane.INFORMATION_MESSAGE);
		} catch (ClassNotFoundException e) {
			mostrarMensaje("Error al leer el fichero de alumno: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}

	// Escribir texto en archivo
	private static void escribirTexto(String ruta, String[] lineas) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
			for (String linea : lineas) {
				bw.write(linea);
				bw.newLine();
			}
		}
	}

	// Interfaz funcional para manejo de excepciones
	@FunctionalInterface
	interface IOOperation {
		void ejecutar() throws IOException;
	}

	// -------------------------------------------------------------------------------------------------------
	// Clase AlumnoV2
	public static class AlumnoV2 implements Serializable {

		private static final long serialVersionUID = 1L;
		private String nombre;
		private int edad;

		public AlumnoV2(String nombre, int edad) {
			this.nombre = nombre;
			this.edad = edad;
		}

		public String getNombre() {
			return nombre;
		}

		public int getEdad() {
			return edad;
		}

		@Override
		public String toString() {
			return "AlumnoV2 [nombre=" + nombre + ", edad=" + edad + "]";
		}
	}
}
