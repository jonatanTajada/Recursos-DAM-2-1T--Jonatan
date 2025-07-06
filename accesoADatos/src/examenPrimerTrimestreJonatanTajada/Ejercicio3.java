package examenPrimerTrimestreJonatanTajada;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Ejercicio que se encargar de leer un xml
 *
 */

public class Ejercicio3 {

	public static void leerSuperheroesDesdeXML(String rutaFichero) {

		try {

			// cargar archivo xml, crear intancias y normalizar documento
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(rutaFichero));
			document.getDocumentElement().normalize();

			// Obtener una lista de todos los elementos: superheroe
			NodeList listaSuperheroes = document.getElementsByTagName("superheroe");

			System.out.println("Lista de superheroes:");
			System.out.println("---------------------------------------");

			// Iterar sobre cada <superheroe> en la lista
			for (int i = 0; i < listaSuperheroes.getLength(); i++) {
				Node nodo = listaSuperheroes.item(i);

				if (nodo.getNodeType() == Node.ELEMENT_NODE) { // Verificar que sea de tipo elemento
					Element elemento = (Element) nodo;

					// Obtener el valor del elemento: nombre
					String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();

					System.out.println("- " + nombre);
				}
			}
		} catch (Exception e) {
			System.err.println("Ocurrio un error al leer el archivo XML: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// Llamar al metodo para leer el archivo XML
		leerSuperheroesDesdeXML("/C:\\Users\\Usuario\\Desktop\\Orduna20242025\\accesoADatos\\src\\examenPrimerTrimestreJonatanTajada\\superheroes.xml");
	}
}
