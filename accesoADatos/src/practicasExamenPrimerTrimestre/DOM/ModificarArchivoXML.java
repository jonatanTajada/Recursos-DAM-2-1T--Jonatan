package practicasExamenPrimerTrimestre.DOM;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.io.File;

public class ModificarArchivoXML {
	
	
	public static void main(String[] args) {
		
		
		try {
			// Cargar el archivo XML
			File archivoXML = new File("libros.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(archivoXML);

			doc.getDocumentElement().normalize();

			NodeList listaLibros = doc.getElementsByTagName("libro");

			
			for (int i = 0; i < listaLibros.getLength(); i++) {
				
				String idABuscar = "101";
				String precioNuevo = "150.99";
				
				Element libro = (Element) listaLibros.item(i);
				String id = libro.getAttribute("id");

				if (id.equals(idABuscar)) {
					
					libro.getElementsByTagName("precio").item(0).setTextContent(precioNuevo);
					System.out.println("Precio del libro con Id " + idABuscar + " actualizado a: " + precioNuevo + "€");
					break;
				}
			}

			// Guardar los cambios en el archivo XML
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("D:\\PROGRAMACION\\DAM ORDUNA 2024_2025\\SegundoAnio\\workspaceEclipseSegundoAnioOrduna\\accesoADatos"));
			transformer.transform(source, result);
 
			System.out.println("Archivo XML actualizado y guardado exitosamente.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
