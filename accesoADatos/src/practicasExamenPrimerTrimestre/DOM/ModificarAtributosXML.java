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

public class ModificarAtributosXML {
	
	public static void main(String[] args) {
		
		try {
			
			File archivo = new File("C:\\Users\\Usuario\\Desktop\\Orduna20242025\\accesoADatos\\src\\practicasExamenPrimerTrimestre\\libros.xml");
			boolean encontrado = false;
			String idABuscar ="101";
			String idNuevo = "201";
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(archivo);
			doc.getDocumentElement().normalize();

			NodeList listaNodos = doc.getElementsByTagName("libro");
			
			for (int i = 0; i < listaNodos.getLength(); i++) {
				
				Element libro = (Element) listaNodos.item(i);
				String idActual = libro.getAttribute("id");
				
				if (idABuscar.equals(idActual)) {
					
					libro.setAttribute("id", idNuevo);
					System.out.println("Id: " + idActual + " - actualizado con id: " + idNuevo);
					encontrado = true;
					break;
				}
			}
			
			if (!encontrado) {
				System.err.println("No se encontro el id: " + idABuscar);
			}
			
			// Guardar los cambios en el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(archivo);
            transformer.transform(source, result);

            System.out.println("Archivo XML actualizado y guardado exitosamente.");

		} catch (Exception e) {
			
			System.err.println("Ocurrio algun error en la modificacion del atributo id: " + e.getMessage());
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
