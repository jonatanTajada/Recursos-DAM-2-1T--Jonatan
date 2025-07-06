package practicasFinalesUd1.ejercicio2;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

// Ejercicio 10: Convertir el fichero binario alumnos a XML usando DOM

public class ConvertirBinarioAxml10 {
	
	public static void main(String[] args) throws Exception {
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		Element rootElement = doc.createElement("alumnos");
		doc.appendChild(rootElement);

		try (RandomAccessFile raf = new RandomAccessFile("alumnos.dat", "r")) {
			while (raf.getFilePointer() < raf.length()) {
				String apellido = raf.readUTF();
				int edad = raf.readInt();
				double nota = raf.readDouble();

				Element alumno = doc.createElement("alumno");
				rootElement.appendChild(alumno);

				Element eApellido = doc.createElement("apellido");
				eApellido.appendChild(doc.createTextNode(apellido));
				alumno.appendChild(eApellido);

				Element eEdad = doc.createElement("edad");
				eEdad.appendChild(doc.createTextNode(String.valueOf(edad)));
				alumno.appendChild(eEdad);

				Element eNota = doc.createElement("nota");
				eNota.appendChild(doc.createTextNode(String.valueOf(nota)));
				alumno.appendChild(eNota);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Escribir el XML en un archivo
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("alumnos.xml"));
		transformer.transform(source, result);

		System.out.println("Fichero alumnos.xml creado con éxito.");
	}
}
