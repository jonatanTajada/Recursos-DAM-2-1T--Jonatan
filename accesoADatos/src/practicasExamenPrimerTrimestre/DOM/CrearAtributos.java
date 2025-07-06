package practicasExamenPrimerTrimestre.DOM;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CrearAtributos {

	public static void main(String[] args) {

		try {
			// Ruta del archivo XML
			File archivoXML = new File("libros.xml");

			// Cargar y parsear el archivo XML
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(archivoXML);
			doc.getDocumentElement().normalize();

			// Obtener todos los nodos <libro>
			NodeList listaLibros = doc.getElementsByTagName("libro");

			// Añadir el atributo "categoria" según el precio
			for (int i = 0; i < listaLibros.getLength(); i++) {
				Element libro = (Element) listaLibros.item(i);

				// Obtener el precio
				String precioStr = libro.getElementsByTagName("precio").item(0).getTextContent();
				//double precio = Double.parseDouble(precioStr);
				double precio = Double.parseDouble(precioStr.replace(",", "."));

				// Determinar la categoría y añadir el atributo
				if (precio <= 50) {
					libro.setAttribute("categoria", "básico");
				} else {
					libro.setAttribute("categoria", "avanzado");
				}
			}

			// Guardar los cambios en el archivo XML
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(archivoXML);

			transformer.transform(source, result);

			System.out.println("Atributo 'categoria' añadido a todos los libros.");

			// Mostrar el archivo XML actualizado
			System.out.println("\nArchivo XML actualizado:");
			for (int i = 0; i < listaLibros.getLength(); i++) {
				Element libro = (Element) listaLibros.item(i);
				String id = libro.getAttribute("id");
				String titulo = libro.getElementsByTagName("titulo").item(0).getTextContent();
				String autor = libro.getElementsByTagName("autor").item(0).getTextContent();
				String precio = libro.getElementsByTagName("precio").item(0).getTextContent();
				String categoria = libro.getAttribute("categoria");

				System.out.println("Id: " + id);
				System.out.println("Titulo: " + titulo);
				System.out.println("Autor: " + autor);
				System.out.println("Precio: " + precio);
				System.out.println("Categoria: " + categoria);
				System.out.println("--------------------------------------------");
			}

		} catch (Exception e) {
			System.err.println("Error al procesar el archivo XML: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
