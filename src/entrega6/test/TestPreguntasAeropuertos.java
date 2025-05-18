package entrega6.test;

import java.time.LocalDateTime;
import java.util.Map;

import entrega6.preguntas.PreguntasAeropuertos;

public class TestPreguntasAeropuertos {
	private static void testComparacion(LocalDateTime a, LocalDateTime b) {
        try {
            Map<String, Double> resultadoFuncional = PreguntasAeropuertos.ciudadAeropuertoMayorFacturaciónFuncional(a, b);
            Map<String, Double> resultadoImperativo = PreguntasAeropuertos.ciudadAeropuertoMayorFacturaciónImperativa(a, b);

            System.out.println("Test fechas: " + a + " - " + b);
            System.out.println("Resultado Funcional: " + resultadoFuncional);
            System.out.println("Resultado Imperativo: " + resultadoImperativo);

            boolean iguales = resultadoFuncional.equals(resultadoImperativo);

            if (iguales) {
                System.out.println("TEST OK: Ambos resultados coinciden.\n");
            } else {
                System.out.println("TEST FALLIDO: Resultados diferentes.\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("TEST EXCEPCIÓN: " + e.getMessage() + "\n");
        }
    }

    public static void runAllTests() {
        // Test 1: rango amplio
        testComparacion(LocalDateTime.of(2020, 1, 1, 0, 0),
                        LocalDateTime.of(2025, 5, 17, 23, 59));

        // Test 2: rango pequeño válido (más de 24h)
        testComparacion(LocalDateTime.of(2024, 1, 1, 10, 0),
                        LocalDateTime.of(2024, 1, 3, 12, 0));

        // Test 3: rango con fechas límite
        testComparacion(LocalDateTime.of(2020, 1, 1, 0, 0),
                        LocalDateTime.of(2020, 1, 2, 0, 1));

        // Test 4: rango inválido (menos de 24h) - debe lanzar excepción
        testComparacion(LocalDateTime.of(2024, 1, 1, 0, 0),
                        LocalDateTime.of(2024, 1, 1, 10, 0));
    }

    public static void main(String[] args) {
        runAllTests();
    }
}
