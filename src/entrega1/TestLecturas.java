package entrega1;

import java.util.List;
import java.util.Set;

public class TestLecturas {
    public static void main(String[] args) {
        String nombreFichero;
        String separador;
        String palabra;
        String cadena;

        System.out.println("################################################");
        System.out.println("TEST DE LA FUNCIÓN 6:");
        nombreFichero = "resources/lin_quijote.txt";
        separador = "\\s+"; 
        palabra = "quijote";
        System.out.println("El número de veces que aparece la palabra '" + palabra + "' en el fichero es: " 
                           + Lecturas.contar_palabra(nombreFichero, separador, palabra));

        System.out.println("################################################");
        System.out.println("TEST DE LA FUNCIÓN 7:");
        nombreFichero = "resources/lin_quijote.txt";
        cadena = "quijote";
        List<String> lineas = Lecturas.lineas_con_palabra(nombreFichero, cadena);
        System.out.println("Las líneas en las que aparece la palabra '" + cadena + "' son: " + lineas);

        System.out.println("################################################");
        System.out.println("TEST DE LA FUNCIÓN 8:");
        nombreFichero = "resources/archivo_palabras.txt";
        Set<String> palabras = Lecturas.palabras_unicas(nombreFichero);
        System.out.println("Las palabras únicas en el archivo son: " + palabras);

        System.out.println("################################################");
        System.out.println("TEST DE LA FUNCIÓN 9:");
        nombreFichero = "resources/palabras_random.csv";
        separador = ",";
        System.out.println("La longitud media de las líneas del archivo es: " 
                           + Lecturas.longitud_promedio_lineas(nombreFichero, separador));

        nombreFichero = "resources/vacio.csv";
        System.out.println("La longitud media de las líneas del archivo es: " 
                           + Lecturas.longitud_promedio_lineas(nombreFichero, separador));
    }
}
