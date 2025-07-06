package practicasExamenPrimerTrimestre.DOM;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LecturaXML2 {

	
	public static void main(String[] args) {
		
		
		try {
			
			File archivo = new File("C:\\Users\\Usuario\\Desktop\\Orduna20242025\\accesoADatos\\src\\practicasExamenPrimerTrimestre\\libros.xml");
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(archivo);
			doc.getDocumentElement().normalize();
			
			
			NodeList listaNodos =  doc.getElementsByTagName("libro");
			
			System.out.println("\t***Informacion de la lista de libros***");
			System.out.println("----------------------------------------------");
			
			for (int i = 0; i < listaNodos.getLength(); i++) {
				
				Element libro =  (Element) listaNodos.item(i);
				
				String titulo = libro.getElementsByTagName("titulo").item(0).getTextContent();
				String autor = libro.getElementsByTagName("autor").item(0).getTextContent();
				String precio = libro.getElementsByTagName("precio").item(0).getTextContent();
				
				System.out.println("Título: " + titulo);
                System.out.println("Autor: " + autor);
                System.out.println("Precio: " + precio);
                System.out.println("---------------------------");
			}
			
		} catch (Exception e) {
			System.err.println("Ocurrio un error en la lectura del xml: " + e.getMessage());
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
