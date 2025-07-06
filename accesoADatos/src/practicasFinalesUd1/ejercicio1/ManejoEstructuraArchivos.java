package practicasFinalesUd1.ejercicio1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// Ejercicio 1

public class ManejoEstructuraArchivos {

	public static void main(String[] args) {
		
		try {
			// Crear la estructura de directorios
			crearDirectorios();

			// En directorio "ejercicios": crear fichero pizzas.xml y leer autores de
			// libros.xml
			crearFicheroPizzas();
			leerAutoresLibros();

			// En directorio "alumnos": crear ficheros de alumnos
			crearFicherosAlumnos();

			// En directorio "profesores": crear fichero listado de profesores
			crearFicheroProfesores();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------
	// Método para crear la estructura de directorios
	public static void crearDirectorios() throws IOException {
		Files.createDirectories(Paths.get("ejercicios"));
		Files.createDirectories(Paths.get("alumnos"));
		Files.createDirectories(Paths.get("profesores"));
		System.out.println("Ruta de trabajo actual: " + new File(".").getAbsolutePath());

		System.out.println("Estructura de directorios creada.");
	}

	// Método para crear el fichero pizzas.xml con sus ingredientes
	public static void crearFicheroPizzas() throws Exception {
		// Crear el documento XML para las pizzas
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();

		// Elemento raíz "pizzas"
		Element rootElement = doc.createElement("pizzas");
		doc.appendChild(rootElement);

		// Crear pizza 1
		Element pizza1 = doc.createElement("pizza");
		rootElement.appendChild(pizza1);
		pizza1.setAttribute("nombre", "Margarita");

		Element ingredientes1 = doc.createElement("ingredientes");
		pizza1.appendChild(ingredientes1);
		ingredientes1.appendChild(doc.createElement("ingrediente")).appendChild(doc.createTextNode("Tomate"));
		ingredientes1.appendChild(doc.createElement("ingrediente")).appendChild(doc.createTextNode("Queso"));
		ingredientes1.appendChild(doc.createElement("ingrediente")).appendChild(doc.createTextNode("Albahaca"));

		// Crear pizza 2
		Element pizza2 = doc.createElement("pizza");
		rootElement.appendChild(pizza2);
		pizza2.setAttribute("nombre", "Pepperoni");

		Element ingredientes2 = doc.createElement("ingredientes");
		pizza2.appendChild(ingredientes2);
		ingredientes2.appendChild(doc.createElement("ingrediente")).appendChild(doc.createTextNode("Tomate"));
		ingredientes2.appendChild(doc.createElement("ingrediente")).appendChild(doc.createTextNode("Queso"));
		ingredientes2.appendChild(doc.createElement("ingrediente")).appendChild(doc.createTextNode("Pepperoni"));

		// Guardar el documento XML en un fichero
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("ejercicios/pizzas.xml"));
		transformer.transform(source, result);

		System.out.println("Fichero pizzas.xml creado con éxito.");
	}

	// Método para leer el fichero libros.xml y mostrar únicamente los autores
	public static void leerAutoresLibros() {
		File librosFile = new File("ejercicios/libros.xml");

		if (librosFile.exists()) {
			try {
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(librosFile);
				doc.getDocumentElement().normalize();

				NodeList listaAutores = doc.getElementsByTagName("autor");

				System.out.println("Autores en libros.xml:");
				for (int i = 0; i < listaAutores.getLength(); i++) {
					Node autor = listaAutores.item(i);
					System.out.println(autor.getTextContent());
				}
			} catch (ParserConfigurationException e) {
				System.err.println("Error de configuración del parser XML: " + e.getMessage());
			} catch (SAXException e) {
				System.err.println("Error al analizar el fichero XML: " + e.getMessage());
			} catch (IOException e) {
				System.err.println("Error de entrada/salida al leer el fichero XML: " + e.getMessage());
			}
		} else {
			System.out.println("El fichero libros.xml no existe.");
		}
	}

	// Método para crear y guardar objetos de alumnos en ficheros
	public static void crearFicherosAlumnos() throws IOException {
		guardarAlumno(new Alumno("Jonatan", 36), "alumnos/alumno1.txt");
		guardarAlumno(new Alumno("Andrew", 16), "alumnos/alumno2.txt");
		guardarAlumno(new Alumno("Janire", 36), "alumnos/alumno3.txt");

		// Leer los alumnos guardados
		leerAlumno("alumnos/alumno1.txt");
		leerAlumno("alumnos/alumno2.txt");
		leerAlumno("alumnos/alumno3.txt");
	}

	// Método para guardar alumno en un fichero
	public static void guardarAlumno(Alumno alumno, String ruta) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
			oos.writeObject(alumno);
		}
		System.out.println("Alumno guardado en: " + ruta);
	}

	// Método para leer un alumno desde un fichero
	public static void leerAlumno(String ruta) throws IOException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
			Alumno alumno = (Alumno) ois.readObject();
			System.out.println("Alumno leído: " + alumno);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Método para crear un fichero de texto con el listado de profesores
	public static void crearFicheroProfesores() throws IOException {
		String[] profesores = { "Profesor Sonia", "Profesor Blanca", "Profesor Izaskun" };
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("profesores/listadoProfesores.txt"))) {
			for (String profesor : profesores) {
				bw.write(profesor);
				bw.newLine();
			}
		}
		System.out.println("Fichero listadoProfesores.txt creado con éxito.");
	}
}

//---------------------------------------------------------------------------------------------------------------------------
// Clase Alumno
class Alumno implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private int edad;

	public Alumno(String nombre, int edad) {
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
		return "Alumno [nombre=" + nombre + ", edad=" + edad + "]";
	}

}
