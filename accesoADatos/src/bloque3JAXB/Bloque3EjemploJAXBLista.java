package bloque3JAXB;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

//leer mas de un libro, una coleccion de libros

public class Bloque3EjemploJAXBLista {

	public static void main(String[] args) {
		
		try {
			String ruta = "D:\\PROGRAMACION\\DAM ORDUNA 2024_2025\\SegundoAnio\\workspaceEclipseSegundoAnioOrduna\\accesoADatos\\libros3.xml";
			File file = new File(ruta);

			// Crear el contexto de JAXB para la clase LibrosWrapper
			JAXBContext context = JAXBContext.newInstance(LibrosWrapper.class);

			// Crear el unmarshaller
			Unmarshaller unmarshaller = context.createUnmarshaller();

			// Deserializar (unmarshal) el archivo XML en una lista de libros
			LibrosWrapper librosWrapper = (LibrosWrapper) unmarshaller.unmarshal(file);
			List<Libro> libros = librosWrapper.getLibros();

			// Mostrar la lista de libros
			//libros.forEach(System.out::println);
			//libros.forEach(libro -> System.out.println(libro));
			for (Libro libro : libros) {
			    System.out.println(libro);
			}



		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
