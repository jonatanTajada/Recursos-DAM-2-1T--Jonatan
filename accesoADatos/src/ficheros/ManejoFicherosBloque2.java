package ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ManejoFicherosBloque2 {

    public static void main(String[] args) {

        Persona persona = new Persona("Jonatan", 36, "123456798A");
        guardarPersona(persona, "personas.txt");

        Persona persona2 = new Persona("Andrew", 16, "123336798B");
        guardarPersona(persona2, "personas.txt");

        Persona persona3 = new Persona("Janire", 36, "123458798C");
        guardarPersona(persona3, "personas.txt");

        // Buscar una persona por nombre
        Persona personaRecuperada = recuperarPersona("personas.txt", "Jonatan");

        // Modificar la persona si se encuentra
        if (personaRecuperada != null) {
            personaRecuperada.setEdad(37);
            modificarPersona(personaRecuperada, "personas.txt");
            System.out.println("Persona modificada: " + personaRecuperada);
        }

        // Crear un fichero de texto
        String contenidoTexto = "Esto es un texto de prueba, estamos creando nuestro primer fichero de texto https://codigonline.com";
        crearFicheroTexto("listaPersonas.txt", contenidoTexto);
    }

//-------------------------------------------------------------------------------------------------------
    // Metodo para guardar una persona en una lista y escribir toda la lista en el archivo
    public static void guardarPersona(Persona persona, String ruta) {
        List<Persona> personas = leerTodasLasPersonas(ruta);
        personas.add(persona);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(personas);
            System.out.println("-Datos de " + persona.getNombre() + " guardados con éxito:\n" + persona.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo para leer todas las personas del archivo
    @SuppressWarnings("unchecked")
    public static List<Persona> leerTodasLasPersonas(String ruta) {
        List<Persona> personas = new ArrayList<>();
        File file = new File(ruta);

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                personas = (List<Persona>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return personas;
    }

    // Metodo para recuperar una persona por nombre
    public static Persona recuperarPersona(String ruta, String nombreBuscado) {
        List<Persona> personas = leerTodasLasPersonas(ruta);

        for (Persona persona : personas) {
            if (persona.getNombre().equalsIgnoreCase(nombreBuscado)) {
                System.out.println("Persona con nombre: " + nombreBuscado + " encontrada en el archivo " + ruta + "\n");
                return persona;
            }
        }

        System.err.println("No se encontró ninguna persona con el nombre: " + nombreBuscado + "\n");
        return null;
    }

    // Metodo para modificar una persona y sobrescribir el archivo con la lista actualizada
    public static void modificarPersona(Persona personaModificada, String ruta) {
        List<Persona> personas = leerTodasLasPersonas(ruta);

        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getNombre().equalsIgnoreCase(personaModificada.getNombre())) {
                personas.set(i, personaModificada);
                break;
            }
        }

        // Guardar la lista actualizada en el archivo
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(personas);
            System.out.println("Datos de " + personaModificada.getNombre() + " modificados y guardados con éxito.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    // Metodo para crear un fichero de texto utilizando FileWriter
    public static void crearFicheroTexto(String ruta, String contenido) {
        try (FileWriter fw = new FileWriter(ruta)) {
            fw.write(contenido);
            System.out.println("Fichero de texto creado correctamente en " + ruta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
 // Metodo para crear un fichero de texto utilizando BufferedWriter con codificación UTF-8
//    public static void crearFicheroTexto(String ruta, String contenido) {
//        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta), StandardCharsets.UTF_8))) {
//            writer.write(contenido);
//            System.out.println("Fichero de texto creado correctamente en " + ruta);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    
    
}
