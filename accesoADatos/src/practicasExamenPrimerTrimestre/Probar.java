package practicasExamenPrimerTrimestre;

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

public class Probar {

    public static void main(String[] args) {

        try {
            // Ruta del archivo XML
            File archivo = new File("libros.xml");

            // Cargar y parsear el archivo XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.getDocumentElement().normalize();

            // -------------------- 1. Incrementar el precio en un 10% --------------------
            NodeList listaLibros = doc.getElementsByTagName("libro");
            for (int i = 0; i < listaLibros.getLength(); i++) {
                Element libro = (Element) listaLibros.item(i);

                // Obtener y modificar el precio
                String precioStr = libro.getElementsByTagName("precio").item(0).getTextContent();
                double precio = Double.parseDouble(precioStr);
                double nuevoPrecio = precio * 1.10; // Incrementar un 10%
                libro.getElementsByTagName("precio").item(0).setTextContent(String.format("%.2f", nuevoPrecio));
            }

            System.out.println("Precios incrementados en un 10%.");

            // -------------------- 2. Cambiar el autor del libro con id="3" --------------------
            for (int i = 0; i < listaLibros.getLength(); i++) {
                Element libro = (Element) listaLibros.item(i);

                // Verificar el atributo id
                if (libro.getAttribute("id").equals("3")) {
                    libro.getElementsByTagName("autor").item(0).setTextContent("Ana Fernández");
                    System.out.println("Autor del libro con id=3 actualizado a Ana Fernández.");
                    break;
                }
            }

            // -------------------- 3. Guardar los cambios en el archivo XML --------------------
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(archivo);

            transformer.transform(source, result);

            System.out.println("Cambios guardados en el archivo XML.");

            // -------------------- 4. Mostrar el archivo actualizado en consola --------------------
            NodeList librosActualizados = doc.getElementsByTagName("libro");
            System.out.println("\nArchivo XML actualizado:");
            for (int i = 0; i < librosActualizados.getLength(); i++) {
                Element libro = (Element) librosActualizados.item(i);
                String id = libro.getAttribute("id");
                String titulo = libro.getElementsByTagName("titulo").item(0).getTextContent();
                String autor = libro.getElementsByTagName("autor").item(0).getTextContent();
                String precio = libro.getElementsByTagName("precio").item(0).getTextContent();

                System.out.println("Id: " + id);
                System.out.println("Titulo: " + titulo);
                System.out.println("Autor: " + autor);
                System.out.println("Precio: " + precio);
                System.out.println("--------------------------------------------");
            }

        } catch (Exception e) {
            System.err.println("Error al modificar el archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
