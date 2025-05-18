package entrega6.test;

import java.util.List;
import java.util.Map;

import entrega6.preguntas.PreguntasBiblioteca;
import us.lsi.biblioteca.Biblioteca;
import us.lsi.biblioteca.Libro;

public class TestPreguntasBiblioteca {

    private static Biblioteca bib = Biblioteca.of("name", "21100", "@.");

    private static void testMasVecesPrestado() {
        System.out.println("Test de masVecesPrestadoImperativo:");
        Map<Libro, Integer> resultadoImp = PreguntasBiblioteca.masVecesPrestadoImperativo();
        System.out.println(resultadoImp);

        System.out.println("\nTest de masVecesPrestadoFuncional:");
        Map<Libro, Integer> resultadoFunc = PreguntasBiblioteca.masVecesPrestadoFuncional();
        System.out.println(resultadoFunc);

        if (resultadoImp.isEmpty()) {
            System.out.println("ERROR: masVecesPrestadoImperativo devolvió un mapa vacío.");
        } else {
            System.out.println("masVecesPrestadoImperativo pasó la prueba (no vacío).");
        }

        if (resultadoFunc.isEmpty()) {
            System.out.println("ERROR: masVecesPrestadoFuncional devolvió un mapa vacío.");
        } else {
            System.out.println("masVecesPrestadoFuncional pasó la prueba (no vacío).");
        }

        boolean iguales = resultadoImp.equals(resultadoFunc);
        if (iguales) {
            System.out.println("Ambos métodos devuelven el mismo resultado. Test PASADO.");
        } else {
            System.out.println("Diferencias en resultados. Test FALLADO.");
        }
    }

    private static void testLibrosPorAutor() {
        System.out.println("\nTest 1: buscar autor que existe (Salomé Oliver):");
        System.out.println(PreguntasBiblioteca.librosPorAutorImperativo(bib.libros(), List.of("Salomé Oliver")));

        System.out.println("\nTest 2: buscar autor que no existe (Lord Voldemort):");
        System.out.println(PreguntasBiblioteca.librosPorAutorImperativo(bib.libros(), List.of("Lord Voldemort")));

        System.out.println("\nTest 3: buscar con parámetro autor = null:");
        System.out.println(PreguntasBiblioteca.librosPorAutorImperativo(bib.libros(), null));
    }

    public static void main(String[] args) {
        testMasVecesPrestado();
        testLibrosPorAutor();
    }
}