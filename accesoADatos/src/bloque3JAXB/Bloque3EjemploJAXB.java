package bloque3JAXB;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Bloque3EjemploJAXB {

	//para leer un solo libro
	
	public static void main(String[] args) {
		
		String ruta = "D:\\PROGRAMACION\\DAM ORDUNA 2024_2025\\SegundoAnio\\workspaceEclipseSegundoAnioOrduna\\accesoADatos\\libros2.xml";
        try {
            // Acceder al archivo XML libro.xml
            File file = new File(ruta);  

            // Crear el contexto de JAXB
            JAXBContext context = JAXBContext.newInstance(Libro.class);

            // Crear el unmarshaller
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Deserializar (unmarshal) el archivo XML en un objeto Java
            Libro libro = (Libro) unmarshaller.unmarshal(file);

            // Mostrar el objeto deserializado
            System.out.println(libro);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

