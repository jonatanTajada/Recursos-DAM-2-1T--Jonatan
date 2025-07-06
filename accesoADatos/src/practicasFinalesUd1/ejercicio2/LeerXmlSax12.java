package practicasFinalesUd1.ejercicio2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

// Ejercicio 12: Visualizar el fichero XML alumnos usando SAX

public class LeerXmlSax12 {

	public static void main(String[] args) {

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				boolean bApellido = false;
				boolean bEdad = false;
				boolean bNota = false;

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					if (qName.equalsIgnoreCase("apellido")) {
						bApellido = true;
					}
					if (qName.equalsIgnoreCase("edad")) {
						bEdad = true;
					}
					if (qName.equalsIgnoreCase("nota")) {
						bNota = true;
					}
				}

				public void characters(char[] ch, int start, int length) throws SAXException {
					if (bApellido) {
						System.out.println("Apellido: " + new String(ch, start, length));
						bApellido = false;
					}
					if (bEdad) {
						System.out.println("Edad: " + new String(ch, start, length));
						bEdad = false;
					}
					if (bNota) {
						System.out.println("Nota: " + new String(ch, start, length));
						bNota = false;
					}
				}
			};

			saxParser.parse(new File("alumnos.xml"), handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
