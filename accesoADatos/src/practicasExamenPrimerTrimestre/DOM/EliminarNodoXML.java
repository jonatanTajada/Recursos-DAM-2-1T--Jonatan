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

public class EliminarNodoXML {

	public static void main(String[] args) {
		
		
		try {
			
			File archivo = new File("C:\\Users\\Usuario\\Desktop\\Orduna20242025\\accesoADatos\\src\\practicasExamenPrimerTrimestre\\libros.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(archivo);
			doc.getDocumentElement().normalize();

			NodeList listaNodos = doc.getElementsByTagName("libro");

			String idAEliminar = "103";
			boolean encontrado = false;

			for (int i = 0; i < listaNodos.getLength(); i++) {
				
				Element libro = (Element) listaNodos.item(i);
				String id = libro.getAttribute("id");

				if (id.equals(idAEliminar)) {
					
					libro.getParentNode().removeChild(libro);
					System.out.println("Libro con id: " + idAEliminar + " eliminado correctamente!");
					encontrado = true;
					break;
				}
			}

			if (!encontrado) {
				System.out.println("El id " + idAEliminar + " no se encuentra en la lista de libros.");
			}

			// Guardar cambios en el XML
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(archivo);
			transformer.transform(source, result);

			System.out.println("Archivo XML actualizado y guardado exitosamente.");

		} catch (Exception e) {
			System.err.println("Ocurrió algún tipo de error, no se pudo eliminar el libro: " + e.getMessage());
		}
	}
	
}
