package entrega6.preguntas;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.aeropuerto.*;

public class PreguntasAeropuertos {
	private static EspacioAereo es= EspacioAereo.of();
	
	 public static void validarFechas(LocalDateTime a, LocalDateTime b) {
	        if (a == null || b == null) {
	            throw new IllegalArgumentException("Las fechas no pueden ser nulas.");
	        }

	        if (!a.isBefore(b)) {
	            throw new IllegalArgumentException("La fecha 'a' debe ser anterior a la fecha 'b'.");
	        }

	        Duration duracion = Duration.between(a, b);
	        if (duracion.toHours() <= 24) {
	            throw new IllegalArgumentException("La diferencia entre las fechas debe ser mayor a un día.");
	        }
	    }
	
		/*Implementar el método ciudadAeropuertoMayorFacturacion que obtenga el nombre de
		la ciudad donde está ubicado el aeropuerto con la mayor facturación entre dos fechas
		dadas a y b. Se entiende por facturación la suma de los precios de los billetes de un
		conjunto de vuelos. Las fechas a y b, ambas de tipo fecha y hora, deben estar una antes
		que la otra y tener más de un día de diferencia, en otro caso se disparará una excepción.*/
	public static Map<String, Double> ciudadAeropuertoMayorFacturaciónFuncional(LocalDateTime a, LocalDateTime b) {
		validarFechas(a,b);
		Map<String,Double> mapa = es.vuelos().todos().stream()
				.filter(x-> es.ocupacionesVuelos().todas().stream()
						.anyMatch(y -> a.isBefore(y.fecha()) && b.isAfter(y.fecha()) && x.codigo().equals(y.codigoVuelo()))
						)
				.collect(Collectors.groupingBy(
				Vuelo::codigoAerolinea,
				Collectors.summingDouble(x->x.precio())
				));
		double max = mapa.values().stream().mapToDouble(Double::doubleValue).max().orElse(0);
		mapa.entrySet().removeIf(entry -> {
			if(entry.getValue() == max) return false;
			return true;
		});
		
		return mapa;
	}
	
	public static Map<String, Double> ciudadAeropuertoMayorFacturaciónImperativa(LocalDateTime a, LocalDateTime b) {
		validarFechas(a, b);

	    Map<String, Double> mapa = new HashMap<>();
	    
	    for (Vuelo vuelo : es.vuelos().todos()) {
	        boolean tieneOcupacionEnRango = false;

	        for (OcupacionVuelo ocup : es.ocupacionesVuelos().todas()) {
	            if (ocup.codigoVuelo().equals(vuelo.codigo())) {
	                LocalDateTime fecha = ocup.fecha();
	                if (!fecha.isBefore(a) && !fecha.isAfter(b)) {
	                    tieneOcupacionEnRango = true;
	                    break;
	                }
	            }
	        }

	        if (tieneOcupacionEnRango) {
	            String codigoAerolinea = vuelo.codigoAerolinea();
	            double precio = vuelo.precio();
	            mapa.put(codigoAerolinea, mapa.getOrDefault(codigoAerolinea, 0.0) + precio);
	        }
	    }

	    // Calcular máximo
	    double max = 0.0;
	    for (double value : mapa.values()) {
	        if (value > max) {
	            max = value;
	        }
	    }
	    double max2 = max;
	    // Eliminar entradas que no tienen el valor máximo
	    mapa.entrySet().removeIf(entry -> entry.getValue() != max2);

	    return mapa;
	}

	
	public static void main (String args[]) {
		LocalDateTime fecha1 = LocalDateTime.of(2020, 1, 1, 10, 0);
        LocalDateTime fecha2 = LocalDateTime.of(2025, 5, 17, 12, 0);

		
		System.out.println(ciudadAeropuertoMayorFacturaciónFuncional(fecha1,fecha2));
		System.out.println(ciudadAeropuertoMayorFacturaciónImperativa(fecha1,fecha2));
	}
}
