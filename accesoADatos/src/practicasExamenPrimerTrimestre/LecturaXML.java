package practicasExamenPrimerTrimestre;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LecturaXML {

	
	
	public static void main(String[] args) {
		
		try {
			
			File archivoXML = new File("D:\\PROGRAMACION\\DAM ORDUNA 2024_2025\\SegundoAnio\\workspaceEclipseSegundoAnioOrduna\\accesoADatos\\libros.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(archivoXML);
			doc.normalize();
			
			
			NodeList listaNodos = doc.getElementsByTagName("libro");
			
			
			for (int i = 0; i < listaNodos.getLength(); i++) {
				
				Element libro = (Element) listaNodos.item(i);
			
				String id = libro.getAttribute("id");
				String titulo = libro.getElementsByTagName("titulo").item(0).getTextContent();
				String autor = libro.getElementsByTagName("autor").item(0).getTextContent();
				String precio = libro.getElementsByTagName("precio").item(0).getTextContent();
				String categoria = libro.getAttribute("categoria");
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	
	
}
