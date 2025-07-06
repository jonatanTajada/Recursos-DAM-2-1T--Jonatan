package practicasFinalesUd1.ejercicio2;

// Ejercicio 11: Visualizar el fichero XML alumnos usando DOM

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

import java.io.File;

public class LeerXmlDom11 {
	
	public static void main(String[] args) {
		
		try {
			File inputFile = new File("alumnos.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("alumno");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out
							.println("Apellido: " + eElement.getElementsByTagName("apellido").item(0).getTextContent());
					System.out.println("Edad: " + eElement.getElementsByTagName("edad").item(0).getTextContent());
					System.out.println("Nota: " + eElement.getElementsByTagName("nota").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
