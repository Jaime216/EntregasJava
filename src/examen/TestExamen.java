package examen;

import java.util.Arrays;
import java.util.List;

public class TestExamen {
	public static void testProductoImpares() {
		// Prueba con varios valores
	    System.out.println("ProductoImpares(3) = " + Examen.ProductoImpares(3)); // Esperado: 15
	    System.out.println("ProductoImpares(5) = " + Examen.ProductoImpares(5)); // Esperado: 945
	    System.out.println("ProductoImpares(1) = " + Examen.ProductoImpares(1)); // Esperado: 1
	    
	    // Prueba con valores no válidos
	    try {
	    	Examen.ProductoImpares(0); // Debería lanzar excepción
	    } catch (IllegalArgumentException e) {
	        System.out.println("Correcto: Excepción lanzada para n = 0");
	    }
	
	    try {
	    	Examen.ProductoImpares(-5); // Debería lanzar excepción
	    } catch (IllegalArgumentException e) {
	        System.out.println("Correcto: Excepción lanzada para n = -5");
	    }
	}
	public static void testCombinatorioSinMultiplosDeTres() {
		// Caso 1: Combinatorio normal sin múltiplos de 3
        System.out.println("Test 1: " + Examen.combinatorioSinMultiplosDeTres(5, 2));
        // Caso 2: n = 6, k = 2 (verifica que no se multiplica por 3)
        System.out.println("Test 2: " + Examen.combinatorioSinMultiplosDeTres(6, 2));
        // Caso 3: n = 7, k = 3 (verifica que no se multiplica por múltiplos de 3)
        System.out.println("Test 3: " + Examen.combinatorioSinMultiplosDeTres(7, 3));
        // Caso 4: n = 10, k = 3
        System.out.println("Test 4: " + Examen.combinatorioSinMultiplosDeTres(10, 3));
        // Caso 5: Caso donde n < k (debe lanzar excepción)
        try {
            System.out.println("Test 5: " + Examen.combinatorioSinMultiplosDeTres(3, 5));
        } catch (IllegalArgumentException e) {
            System.out.println("Test 5: Excepción lanzada como se esperaba");
        }
        // Caso 6: Combinatorio con 1 (n = k = 1)
        System.out.println("Test 6: " + Examen.combinatorioSinMultiplosDeTres(1, 1));
        // Caso 7: n y k igual a 3 (verifica sin múltiplos de 3)
        System.out.println("Test 7: " + Examen.combinatorioSinMultiplosDeTres(3, 3));
        }
	
	public static void testFiltrarLineasConsecutivas() {
		String filename= "resources/file";
        // Palabras clave que estamos buscando en las líneas consecutivas
        List<String> palabrasClave = Arrays.asList("hello", "world", "java", "file");

        // Llamar a la función filtrarLineasConsecutivas
        List<String> lineasFiltradas = Examen.filtrarLineasConsecutivas(filename, palabrasClave);

        // Comprobar si las líneas filtradas son correctas
        System.out.println("Líneas filtradas:");
        for (String linea : lineasFiltradas) {
            System.out.println(linea);
        }
    }

	public static void testSumaGeometricaAlternada() {
        // Test 1: Caso normal con k = 5, a1 = 2, r = 3
        System.out.println("Test 1: " + Examen.sumaGeometricaAlternada(5, 2, 3)); // Esperado: 122

        // Test 2: Caso con k = 4, a1 = 1, r = 2
        System.out.println("Test 2: " + Examen.sumaGeometricaAlternada(4, 1, 2)); // Esperado: 5 (1 - 2 + 4 - 8)

        // Test 3: Caso con k = 6, a1 = 3, r = 2
        System.out.println("Test 3: " + Examen.sumaGeometricaAlternada(6, 3, 2)); // Esperado: 21 (3 - 6 + 12 - 24 + 48 - 96)

        // Test 4: Caso con k = 3, a1 = 5, r = 1
        System.out.println("Test 4: " + Examen.sumaGeometricaAlternada(3, 5, 1)); // Esperado: 15 (5 - 5 + 5)

        // Test 5: Caso con k = 2, a1 = 10, r = 0.5
        System.out.println("Test 5: " + Examen.sumaGeometricaAlternada(2, 10, 0.5)); // Esperado: 5 (10 - 5)

        // Test 6: Caso con k = 1 (solo el primer término)
        System.out.println("Test 6: " + Examen.sumaGeometricaAlternada(1, 4, 3)); // Esperado: 4

        // Test 7: Caso con r = 1, que no cambia los términos
        System.out.println("Test 7: " + Examen.sumaGeometricaAlternada(4, 3, 1)); // Esperado: 3 (3 - 3 + 3 - 3)

        // Test 8: Caso con valores pequeños, k = 2, a1 = 1, r = 0.5
        System.out.println("Test 8: " + Examen.sumaGeometricaAlternada(2, 1, 0.5)); // Esperado: 0.5 (1 - 0.5)
    }

	public static void main(String[] args) {
		testProductoImpares();
		testCombinatorioSinMultiplosDeTres();
		testFiltrarLineasConsecutivas();
		testSumaGeometricaAlternada();
	}
}
