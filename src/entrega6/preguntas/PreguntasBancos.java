package entrega6.preguntas;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.bancos.*;
import us.lsi.ejemplos_b1_tipos.Persona;

public class PreguntasBancos {
	private static Banco banco = Banco.of();
	/*
	Implemente el método valorTotalPrestamos que obtenga para cada cliente del banco
	de edad inferior a e, de tipo entero, el valor total de sus préstamos que tengan un valor
	comprendido entre a y b, ambos de tipo Double, y concedidos con fecha posterior a f, de
	tipo LocalDate. Implemente las siguientes restricciones:
	• La edad e debe ser un entero superior a 18
	• Los valores a y b deben ser positivos
	• a debe ser menor que b.
	 **/
	public static Map<String, Double> valorTotalPrestamosFuncional(int e, Double a, Double b, LocalDate f) {
		Map<String, Double> resultado = banco.personas().todos().stream()
		        .filter(p -> p.edad() < e)
		        .collect(Collectors.toMap(
		            Persona::dni,
		            p -> banco.prestamos().todos().stream()
		                .filter(pr -> pr.dniCliente().equals(p.dni())
		                    && pr.cantidad() > a
		                    && pr.cantidad() < b
		                    && pr.fechaComienzo().isAfter(f))
		                .mapToDouble(Prestamo::cantidad)
		                .sum()
		        ));

		    // Filtrar entradas con suma 0.0 y mostrar las sumas que no sean cero
		    resultado.entrySet().removeIf(entry -> {
		        if (entry.getValue() == 0.0) {
		            return true;
		        } else {
		            return false;
		        }
		    });

		    return resultado;
	}
	
	public static Map<String, Double> valorTotalPrestamosImperativo(int e, Double a, Double b, LocalDate f) {
	    if (e < 18) {
	        throw new IllegalArgumentException("La edad debe ser mayor a 18.");
	    }
	    if (a < 0 || b < 0 || a >= b) {
	        throw new IllegalArgumentException("Los valores a y b deben ser positivos, siendo a menor que b.");
	    }

	    Map<String, Double> resultado = new HashMap<>();

	    // Recorremos todas las personas
	    for (Persona p : banco.personas().todos()) {
	        if (p.edad() < e) {
	            String dni = p.dni();
	            double sumaPrestamos = 0.0;
	            // Recorremos todos los préstamos para sumar los que cumplen condiciones
	            for (Prestamo pr : banco.prestamos().todos()) {
	                if (pr.dniCliente().equals(dni) &&
	                    pr.cantidad() > a &&
	                    pr.cantidad() < b &&
	                    pr.fechaComienzo().isAfter(f)) {

	                    sumaPrestamos += pr.cantidad();
	                }
	            }
	            if(sumaPrestamos == 0.) continue;
	            resultado.put(dni, sumaPrestamos);
	        }
	    }

	    return resultado;
	}
	
	
	
	public static void main(String args[]) {
		System.out.println(valorTotalPrestamosImperativo(100,20000.,30000. ,LocalDate.of(2000,10,10)));
		System.out.println(valorTotalPrestamosFuncional(100,20000.,30000. ,LocalDate.of(2000,10,10)));
	}
}
