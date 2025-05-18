package entrega6.test;

import java.time.LocalDate;
import java.util.Map;
import entrega6.preguntas.*;

public class TestPreguntasBancos {

    

    public static void testValorTotalPrestamosFuncional_valoresValidos() {
        System.out.println("Test funcional valores válidos:");
        try {
            Map<String, Double> resultado = PreguntasBancos.valorTotalPrestamosFuncional(
                100, 20000.0, 30000.0, LocalDate.of(2000, 10, 10)
            );
            if (resultado == null) {
                System.out.println("  ERROR: Resultado es null");
            } else if (resultado.values().stream().anyMatch(v -> v == 0.0)) {
                System.out.println("  ERROR: Hay valores con 0.0");
            } else {
                System.out.println("  OK: Resultado correcto con " + resultado.size() + " entradas");
            }
        } catch (Exception ex) {
            System.out.println("  ERROR: Excepción inesperada: " + ex.getMessage());
        }
    }

    public static void testValorTotalPrestamosImperativo_valoresValidos() {
        System.out.println("Test imperativo valores válidos:");
        try {
            Map<String, Double> resultado = PreguntasBancos.valorTotalPrestamosImperativo(
                100, 20000.0, 30000.0, LocalDate.of(2000, 10, 10)
            );
            if (resultado == null) {
                System.out.println("  ERROR: Resultado es null");
            } else if (resultado.values().stream().anyMatch(v -> v == 0.0)) {
                System.out.println("  ERROR: Hay valores con 0.0");
            } else {
                System.out.println("  OK: Resultado correcto con " + resultado.size() + " entradas");
            }
        } catch (Exception ex) {
            System.out.println("  ERROR: Excepción inesperada: " + ex.getMessage());
        }
    }

    public static void testValorTotalPrestamosFuncional_edadMenor18() {
        System.out.println("Test funcional edad menor que 18:");
        try {
            PreguntasBancos.valorTotalPrestamosFuncional(17, 1000.0, 2000.0, LocalDate.now());
            System.out.println("  ERROR: No lanzó excepción para edad < 18");
        } catch (IllegalArgumentException ex) {
            if ("La edad debe ser mayor a 18.".equals(ex.getMessage())) {
                System.out.println("  OK: Lanzó excepción correcta");
            } else {
                System.out.println("  ERROR: Mensaje de excepción incorrecto: " + ex.getMessage());
            }
        } catch (Exception ex) {
            System.out.println("  ERROR: Excepción inesperada: " + ex.getMessage());
        }
    }

    public static void testValorTotalPrestamosImperativo_edadMenor18() {
        System.out.println("Test imperativo edad menor que 18:");
        try {
            PreguntasBancos.valorTotalPrestamosImperativo(17, 1000.0, 2000.0, LocalDate.now());
            System.out.println("  ERROR: No lanzó excepción para edad < 18");
        } catch (IllegalArgumentException ex) {
            if ("La edad debe ser mayor a 18.".equals(ex.getMessage())) {
                System.out.println("  OK: Lanzó excepción correcta");
            } else {
                System.out.println("  ERROR: Mensaje de excepción incorrecto: " + ex.getMessage());
            }
        } catch (Exception ex) {
            System.out.println("  ERROR: Excepción inesperada: " + ex.getMessage());
        }
    }

    public static void testValorTotalPrestamosImperativo_valoresInvalidos() {
        System.out.println("Test imperativo valores inválidos:");

        probarValoresInvalidos(-1.0, 5.0);
        probarValoresInvalidos(1.0, -5.0);
        probarValoresInvalidos(10.0, 5.0);
    }

    private static void probarValoresInvalidos(Double a, Double b) {
        try {
        	PreguntasBancos.valorTotalPrestamosImperativo(20, a, b, LocalDate.now());
            System.out.println("  ERROR: No lanzó excepción para valores a=" + a + " b=" + b);
        } catch (IllegalArgumentException ex) {
            if ("Los valores a y b deben ser positivos, siendo a menor que b.".equals(ex.getMessage())) {
                System.out.println("  OK: Lanzó excepción correcta para a=" + a + " b=" + b);
            } else {
                System.out.println("  ERROR: Mensaje excepción incorrecto para a=" + a + " b=" + b + ": " + ex.getMessage());
            }
        } catch (Exception ex) {
            System.out.println("  ERROR: Excepción inesperada para a=" + a + " b=" + b + ": " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        testValorTotalPrestamosFuncional_valoresValidos();
        testValorTotalPrestamosImperativo_valoresValidos();
        testValorTotalPrestamosFuncional_edadMenor18();
        testValorTotalPrestamosImperativo_edadMenor18();
        testValorTotalPrestamosImperativo_valoresInvalidos();
    }
}
