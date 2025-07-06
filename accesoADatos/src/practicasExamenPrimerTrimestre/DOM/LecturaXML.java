package practicasExamenPrimerTrimestre.DOM;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LecturaXML {
	
	
	public static void main(String[] args) {
		
		
		try {
			 //Cargar el archivo XML
			File archivoXML = new File("D:\\PROGRAMACION\\DAM ORDUNA 2024_2025\\SegundoAnio\\workspaceEclipseSegundoAnioOrduna\\accesoADatos\\libros.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(archivoXML);

			 //Normalizar el documento XML
			doc.getDocumentElement().normalize();

			 //Obtener todos los elementos <libro>
			NodeList listaNodos = doc.getElementsByTagName("libro");

			System.out.println("- Títulos de los libros:");
			 
			//Recorrer la lista de <libro> y extraer los títulos
			for (int i = 0; i < listaNodos.getLength(); i++) {
				
				Element libro =  (Element) listaNodos.item(i);
				
				String id = libro.getAttribute("id");
				String titulo = libro.getElementsByTagName("titulo").item(0).getTextContent();
				String autor =  libro.getElementsByTagName("autor").item(0).getTextContent();
				String precio =  libro.getElementsByTagName("precio").item(0).getTextContent();
				
				System.out.println("Id:: " + id);
				System.out.println("Titulo: " + titulo);
				System.out.println("Autor: " + autor);
				System.out.println("Precio: " + precio);
				
				System.out.println("---------------------------------------------");
				
			}
		} catch (Exception e) {
			System.err.println("Error en la lectura: " + e.getMessage());
		    e.printStackTrace();
		}
	}
		
		

}
