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

public class CrearYAnadirNodos {

	public static void main(String[] args) {

		try {
			File archivo = new File("D:\\PROGRAMACION\\DAM ORDUNA 2024_2025\\SegundoAnio\\workspaceEclipseSegundoAnioOrduna\\accesoADatos\\libros.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(archivo);
			doc.getDocumentElement().normalize();

			// crear nuevo elemento libro
			Element nuevoLibro = doc.createElement("libro");
			nuevoLibro.setAttribute("id", "103");

			Element titulo = doc.createElement("titulo");
			titulo.setTextContent("Java Spring Boot");
			nuevoLibro.appendChild(titulo);

			Element autor = doc.createElement("autor");
			autor.setTextContent("Jonatan Tajada");
			nuevoLibro.appendChild(autor);

			Element precio = doc.createElement("precio");
			precio.setTextContent("250.99");
			nuevoLibro.appendChild(precio);

			doc.getDocumentElement().appendChild(nuevoLibro);

			// GUARDAR LOS CAMBIOS EN EL XML
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(archivo);

			transformer.transform(source, result);

			System.out.println("Nuevo libro creado y añadido correctamente!");

		} catch (Exception e) {
			System.err.println("Ocurrio algun error al crear o añadir elemento nuevo al xml: " + e.getMessage());
			System.err.println(e.getLocalizedMessage());
		}

	}

}
