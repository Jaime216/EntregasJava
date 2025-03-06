package entrega1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Lecturas {
	/* Dado un archivo de texto de nombre fichero, cuyos términos están separados mediante el separador sep,
	implemente una función que, dados el nombre del fichero, el separador y una palabra cad, cuente
	cuántas veces aparece dicha palabra dentro del fichero */
    public static int contar_palabra(String nombreFichero, String separador, String palabra) {
        int contador = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en palabras usando el separador
                String[] palabras = linea.split(separador);
                for (String p : palabras) {
                    if (p.toLowerCase().equals(palabra)) {
                        contador++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return contador;
    }
    
    // Función para buscar líneas que contengan una cadena específica
    public static List<String> lineas_con_palabra(String nombreFichero, String cadena) {
        List<String> lineasEncontradas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.toLowerCase().contains(cadena.toLowerCase())) {  // Si la línea contiene la cadena, se añade a la lista
                    lineasEncontradas.add(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return lineasEncontradas;
    }
    
    // Función para encontrar todas las palabras únicas en un archivo
    public static Set<String> palabras_unicas(String nombreFichero) {
        Set<String> palabrasUnicas = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Convertir a minúsculas y dividir por espacios
            	for (String palabra : linea.toLowerCase().split("\\s+")) {
                    palabrasUnicas.add(palabra.trim());
                    }
                }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return palabrasUnicas;
    }

    /*Dado un fichero csv de nombre fichero, cuyos términos están separados mediante el separador sep,
    implemente una función que devuelva la longitud media de las líneas de dicho fichero*/
	public static double longitud_promedio_lineas(String nombreFichero, String separador) {
		double longitudMedia = 0;
		int numLineas = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				// Dividir la línea en palabras usando el separador
				String[] palabras = linea.split(separador);
				longitudMedia += palabras.length;
				numLineas++;
			}
		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
		}
		return longitudMedia / numLineas;
	}
    
    
    // Método main para probar la función
    public static void main(String[] args) {
        String nombreFichero = "resources/lin_quijote.txt"; // Ruta del archivo
        String separador = "\\s+"; // Separador: espacio en blanco
        String palabra = "quijote";

        int ocurrencias = contar_palabra(nombreFichero, separador, palabra);
        System.out.println("El número de veces que aparece la palabra '" + palabra + "' en el fichero es: " + ocurrencias);
        
        nombreFichero = "resources/lin_quijote.txt"; // Ruta del archivo
        String cadena = "quijote";

        List<String> lineas = lineas_con_palabra(nombreFichero, cadena);
        System.out.println("Las líneas en las que aparece la palabra '" + cadena + "' son: " + lineas);
        
        nombreFichero = "resources/archivo_palabras.txt"; // Ruta del archivo
        Set<String> palabras = palabras_unicas(nombreFichero);
        System.out.println("Las palabras únicas en el archivo son: " + palabras);
        
        nombreFichero = "resources/palabras_random.csv"; // Ruta del archivo
        separador = ","; // Separador: espacio en blanco
        System.out.println("La longitud media de las líneas del archivo es: " + longitud_promedio_lineas(nombreFichero, separador));
    }
}
