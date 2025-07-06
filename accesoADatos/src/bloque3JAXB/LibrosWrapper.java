package bloque3JAXB;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// relacion con Bloque3EjemploJAXBLista y Libro, para poder guardar en una array los libros

@XmlRootElement(name = "libros")
public class LibrosWrapper {
	
    private List<Libro> libros;

    @XmlElement(name = "libro")
    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
