 package ficheros;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ManejoFicherosBloque3 {

	public static void main(String[] args) {

		try {
			

			String ruta = "libros.xml";

			// Cargar el documento XML
			File inputFile = new File(ruta);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			// 1. Mostrar los diferentes ID de cada libro
			mostrarIDs(doc);

			// 2. Mostrar lista de autores y títulos
			mostrarAutoresYTitulos(doc);

			// 3. Mostrar los títulos y precios, ordenados de más económico a más caro
			mostrarTitulosYPrecios(doc);

			// 4. Mostrar libros por su género
			mostrarLibrosPorGenero(doc);

			// 5. Traducir todas las etiquetas y guardar el XML traducido
			traducirYGuardarXML(doc, "libros_traducidos.xml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//-------------------------------------------------------------------------------------------------
	// 1. Metodo para mostrar los diferentes ID de cada libro
	private static void mostrarIDs(Document doc) {
		System.out.println("=== IDs de los libros ===");
		NodeList bookList = doc.getElementsByTagName("Book");
		for (int i = 0; i < bookList.getLength(); i++) {
			Element book = (Element) bookList.item(i);
			String id = book.getAttribute("id");
			System.out.println("ID del libro: " + id);
		}
	}

	// 2. Método para mostrar autores y títulos de sus libros
	private static void mostrarAutoresYTitulos(Document doc) {
		System.out.println("\n=== Autores y títulos de los libros ===");
		NodeList bookList = doc.getElementsByTagName("Book");
		for (int i = 0; i < bookList.getLength(); i++) {
			Element book = (Element) bookList.item(i);
			String author = book.getElementsByTagName("Author").item(0).getTextContent();
			String title = book.getElementsByTagName("Title").item(0).getTextContent();
			System.out.println("Autor: " + author + " | Título: " + title);
		}
	}

	// 3. Metodo para mostrar titulos y precios, ordenados de mas barato a mas caro
	private static void mostrarTitulosYPrecios(Document doc) {

		System.out.println("\n=== Títulos y precios ordenados ===");
		NodeList bookList = doc.getElementsByTagName("Book");
		List<Element> books = new ArrayList<>();
		for (int i = 0; i < bookList.getLength(); i++) {
			books.add((Element) bookList.item(i));
		}
		// Ordenar por precio
		books.sort((b1, b2) -> {
			double price1 = Double.parseDouble(b1.getElementsByTagName("Price").item(0).getTextContent());
			double price2 = Double.parseDouble(b2.getElementsByTagName("Price").item(0).getTextContent());
			return Double.compare(price1, price2);
		});
		// Mostrar los titulos y precios
		for (Element book : books) {
			String title = book.getElementsByTagName("Title").item(0).getTextContent();
			String price = book.getElementsByTagName("Price").item(0).getTextContent();
			System.out.println("Título: " + title + " | Precio: " + price);
		}
	}

	// 4. Metodo para mostrar libros por genero
	private static void mostrarLibrosPorGenero(Document doc) {

		System.out.println("\n=== Libros por genero ===");
		NodeList bookList = doc.getElementsByTagName("Book");
		Map<String, List<String>> genreMap = new HashMap<>();
		for (int i = 0; i < bookList.getLength(); i++) {
			Element book = (Element) bookList.item(i);
			String genre = book.getElementsByTagName("Genre").item(0).getTextContent();
			String title = book.getElementsByTagName("Title").item(0).getTextContent();
			genreMap.putIfAbsent(genre, new ArrayList<>());
			genreMap.get(genre).add(title);
		}
		// Mostrar libros por genero
		for (String genre : genreMap.keySet()) {
			System.out.println("Género: " + genre);
			for (String title : genreMap.get(genre)) {
				System.out.println(" - " + title);
			}
		}

	}

	// 5. Metodo para traducir las etiquetas y guardar el nuevo XML
	private static void traducirYGuardarXML(Document doc, String fileName) {
		try {
			NodeList catalogList = doc.getElementsByTagName("Catalog");
			if (catalogList.getLength() > 0) {
				Element catalog = (Element) catalogList.item(0);
				doc.renameNode(catalog, null, "Catalogo");

				NodeList books = catalog.getElementsByTagName("Book");
				for (int i = 0; i < books.getLength(); i++) {
					Element book = (Element) books.item(i);
					doc.renameNode(book, null, "Libro");
					renombrarElemento(doc, book, "Title", "Titulo");
					renombrarElemento(doc, book, "Genre", "Genero");
					renombrarElemento(doc, book, "Price", "Precio");
					renombrarElemento(doc, book, "Publish_date", "FechaPublicacion");
					renombrarElemento(doc, book, "Description", "Descripción");
					renombrarElemento(doc, book, "Author", "Autor");
				}

				// Guardar el archivo XML traducido
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(fileName));
				transformer.transform(source, result);

				System.out.println("\nXML traducido y guardado como " + fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Metodo auxiliar para renombrar etiquetas
	private static void renombrarElemento(Document doc, Element parent, String oldTag, String newTag) {
		NodeList elements = parent.getElementsByTagName(oldTag);
		if (elements.getLength() > 0) {
			Element oldElement = (Element) elements.item(0);
			Element newElement = doc.createElement(newTag);
			newElement.setTextContent(oldElement.getTextContent());
			parent.replaceChild(newElement, oldElement);
		}
	}
}
