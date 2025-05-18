package entrega6.preguntas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import us.lsi.centro.*;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;



 public class PreguntasCentro{
	static Centro centro = Centro.of("centro/alumnos.txt",
			"centro/profesores.txt",
			"centro/asignaturas.txt",
			"centro/matriculas.txt",
			"centro/asignaciones.txt");
	public static int promedioProfesoresFuncional(String dni) {
	    // Buscar el alumno con el dni proporcionado
	    Alumno a = centro.alumnos().todos().stream()
	        .filter(x -> x.dni().equals(dni))
	        .findFirst()
	        .orElse(null);

	    if (a == null) throw new IllegalArgumentException("Alumno no encontrado: " + dni);

	    // Filtrar las matrículas de ese alumno
	    List<Matricula> matriculas = centro.matriculas().todas().stream()
	        .filter(x -> x.dni().equals(dni))
	        .collect(Collectors.toList());

	    // Buscar asignaciones que coincidan con cada matrícula
	    List<Asignacion> asignaciones = matriculas.stream()
	    	    .flatMap(m -> centro.asignaciones().todas().stream()
	    	        .filter(b -> b.ida().equals(m.ida()) && b.idg().equals(m.idg())))
	    	    .collect(Collectors.toList());


	    // Calcular la suma de edades de los profesores asociados
	    int suma = asignaciones.stream()
	    	    .map(b -> centro.profesores().todos().stream()
	    	        .filter(p -> p.dni().equals(b.dni()))
	    	        .findFirst()
	    	        .orElse(null))
	    	    .filter(p -> p != null)
	    	    .mapToInt(Profesor::edad)
	    	    .sum();

	    if (asignaciones.isEmpty()) return 0;
	    return suma / asignaciones.size();
	}
	
	
	public static int promedioProfesoresImperativo(String dni) {
	    // 1. Buscar al alumno con ese dni
	    Alumno alumno = null;
	    for (Alumno a : centro.alumnos().todos()) {
	        if (a.dni().equals(dni)) {
	            alumno = a;
	            break;
	        }
	    }

	    if (alumno == null) {
	        throw new IllegalArgumentException("Alumno no encontrado");
	    }

	    // 2. Obtener las matrículas del alumno
	    List<Matricula> matriculas = new ArrayList<>();
	    for (Matricula m : centro.matriculas().todas()) {
	        if (m.dni().equals(dni)) {
	            matriculas.add(m);
	        }
	    }

	    // 3. Buscar las asignaciones correspondientes a esas matrículas
	    List<Asignacion> asignaciones = new ArrayList<>();
	    for (Matricula m : matriculas) {
	        for (Asignacion a : centro.asignaciones().todas()) {
	            if (a.ida().equals(m.ida()) && a.idg().equals(m.idg())) {
	                asignaciones.add(a);
	            }
	        }
	    }

	    // 4. Buscar los profesores que dan esas asignaciones y calcular edad total
	    int sumaEdades = 0;
	    int contador = 0;

	    for (Asignacion asignacion : asignaciones) {
	        for (Profesor p : centro.profesores().todos()) {
	            if (p.dni().equals(asignacion.dni())) {
	                sumaEdades += p.edad();
	                contador++;
	                break;
	            }
	        }
	    }

	    if (contador == 0) {
	        return 0; // evitar división por cero
	    }

	    return sumaEdades / contador;
	}

	public static Integer grupoMayorDiversidadEdadFuncional() {
	    return centro.matriculas().todas().stream()
	        .collect(Collectors.groupingBy(
	            Matricula::idg, // Agrupamos solo por idg (grupo)
	            Collectors.mapping(
	                m -> centro.alumnos().todos().stream()
	                        .filter(a -> a.dni().equals(m.dni()))
	                        .findFirst().orElse(null),
	                Collectors.toList()
	            )
	        ))
	        .entrySet().stream()
	        .filter(e -> e.getValue().size() >= 2)
	        .max(Comparator.comparing(
	            e -> e.getValue().stream().mapToInt(Alumno::edad).max().orElse(0)
	                 - e.getValue().stream().mapToInt(Alumno::edad).min().orElse(0)
	        ))
	        .map(Map.Entry::getKey)
	        .orElse(null); // Retorna el idg del grupo con mayor diversidad de edad
	}


	public static int grupoMayorDiversidadEdadImperativo() {
	    int grupoConMayorDiversidad = 0;
	    int mayorDiferencia = -1;

	    Set<Grupo> g=  centro.grupos().todos();
	    for (Grupo grupo : g) {
	        // Obtenemos todos los DNIs de alumnos en este grupo
	        Set<String> dnisAlumnosGrupo = new HashSet<>();
	        for (Matricula m : centro.matriculas().todas()) {
	            if (m.idg().equals(grupo.idg())) {
	                dnisAlumnosGrupo.add(m.dni());
	            }
	        }

	        if (dnisAlumnosGrupo.isEmpty()) continue;

	        List<Integer> edades = new ArrayList<>();

	        for (String dni : dnisAlumnosGrupo) {
	            for (Alumno alumno : centro.alumnos().todos()) {
	                if (alumno.dni().equals(dni)) {
	                    edades.add(alumno.edad());
	                }
	            }
	        }

	        if (!edades.isEmpty()) {
	            int min = Collections.min(edades);
	            int max = Collections.max(edades);
	            int diferencia = max - min;

	            if (diferencia > mayorDiferencia) {
	                mayorDiferencia = diferencia;
	                grupoConMayorDiversidad = grupo.idg();
	            }
	        }
	    }

	    return grupoConMayorDiversidad;
	}

	public static Alumno alumnoMasMatriculasFuncional() {
	    List<Entry<String, Long>> l =centro.matriculas().todas().stream()
	    	    .collect(Collectors.groupingBy(Matricula::dni, Collectors.counting()))
	    	    .entrySet().stream().sorted(Map.Entry.comparingByValue()).sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());

	    return centro.alumnos().todos().stream().filter(x-> x.dni().equals(l.get(0).getKey())).collect(Collectors.toList()).get(0);
	}

	public static Alumno alumnoMasMatriculasImperativo() {
	    // Mapa de dni → número de matrículas
	    Map<String, Integer> contador = new HashMap<>();
	    
	    for (Matricula m : centro.matriculas().todas()) {
	        String dni = m.dni();
	        contador.put(dni, contador.getOrDefault(dni, 0) + 1);
	    }

	    // Encontramos el dni con mayor número de matrículas
	    String dniMax = null;
	    int maxMatriculas = -1;

	    for (Map.Entry<String, Integer> entry : contador.entrySet()) {
	        if (entry.getValue() > maxMatriculas) {
	            maxMatriculas = entry.getValue();
	            dniMax = entry.getKey();
	        }
	    }

	    // Buscamos el alumno con ese dni
	    for (Alumno a : centro.alumnos().todos()) {
	        if (a.dni().equals(dniMax)) {
	            return a;
	        }
	    }

	    return null; // En caso de que no se encuentre (no debería ocurrir si los datos están bien)
	}

	public static List<int[]> parsearRangos(String rangos) {
	    if (rangos == null || rangos.trim().isEmpty()) {
	        throw new IllegalArgumentException("La cadena de rangos no puede estar vacía.");
	    }

	    String[] partes = rangos.split(",");
	    List<int[]> listaRangos = new ArrayList<>();

	    for (String parte : partes) {
	        String[] numeros = parte.trim().split("-");
	        if (numeros.length != 2) {
	            throw new IllegalArgumentException("Formato incorrecto en: " + parte);
	        }
	        try {
	            int inicio = Integer.parseInt(numeros[0].trim());
	            int fin = Integer.parseInt(numeros[1].trim());
	            if (inicio > fin) {
	                throw new IllegalArgumentException("Rango mal formado: " + parte);
	            }
	            listaRangos.add(new int[]{inicio, fin});
	        } catch (NumberFormatException e) {
	            throw new IllegalArgumentException("No es un número válido: " + parte);
	        }
	    }

	    // Verificar solapamientos
	    listaRangos.sort(Comparator.comparingInt(r -> r[0]));
	    for (int i = 1; i < listaRangos.size(); i++) {
	        if (listaRangos.get(i - 1)[1] >= listaRangos.get(i)[0]) {
	            throw new IllegalArgumentException("Los rangos se solapan.");
	        }
	    }

	    return listaRangos;
	}


	public static Map<String, List<Alumno>> rangosEdadPorAlumnoImperativo(String cadenaRangos) {
	    List<int[]> rangos = parsearRangos(cadenaRangos);
	    Map<String, List<Alumno>> resultado = new LinkedHashMap<>();

	    for (int[] rango : rangos) {
	        String clave = rango[0] + " - " + rango[1];
	        List<Alumno> lista = new ArrayList<>();
	        for (Alumno a : centro.alumnos().todos()) {
	            if (a.edad() >= rango[0] && a.edad() <= rango[1]) {
	                lista.add(a);
	            }
	        }
	        resultado.put(clave, lista);
	    }

	    return resultado;
	}

	public static Map<String, List<Alumno>> rangosEdadPorAlumnoFuncional(String cadenaRangos) {
	    List<int[]> rangos = parsearRangos(cadenaRangos);

	    return rangos.stream()
	        .collect(Collectors.toMap(
	            r -> r[0] + " - " + r[1],
	            r -> centro.alumnos().todos().stream()
	                    .filter(a -> a.edad() >= r[0] && a.edad() <= r[1])
	                    .collect(Collectors.toList()),
	            (a, b) -> a,
	            LinkedHashMap::new
	        ));
	}

	public static String nombreProfesorMasGruposFuncional(int min, int max) {
	    if (min >= max) {
	        throw new IllegalArgumentException("La edad mínima debe ser menor que la edad máxima");
	    }

	    return centro.profesores().todos().stream()
	        .filter(p -> p.edad() >= min && p.edad() <= max)
	        .map(p -> Map.entry(p.nombre(),
	                centro.asignaciones().todas().stream()
	                    .filter(a -> a.dni().equals(p.dni()))
	                    .count()))
	        .max(Map.Entry.comparingByValue())
	        .map(Map.Entry::getKey)
	        .orElse(null);
	}

	
	public static String nombreProfesorMasGruposImperativo(int min, int max) {
	    if (min >= max) {
	        throw new IllegalArgumentException("La edad mínima debe ser menor que la edad máxima");
	    }

	    String nombreMax = null;
	    int maxGrupos = -1;

	    for (Profesor profesor : centro.profesores().todos()) {
	        if (profesor.edad() >= min && profesor.edad() <= max) {
	            int c = 0;
	            for(Asignacion a:centro.asignaciones().todas()) {
	            	if(a.dni().equals(profesor.dni())) c+=1;
	            }
	            if (c > maxGrupos) {
	                maxGrupos =  c;
	                nombreMax = profesor.nombre();
	            }
	        }
	    }

	    return nombreMax;
	}

	public static List<String> nombresAlumnosMayorNotaImperativo(Integer n, Integer a) {
	    if (n == null || a == null) {
	        throw new IllegalArgumentException("n y a no pueden ser null");
	    }
	    if (n < 1 || n > 10) {
	        throw new IllegalArgumentException("n debe estar entre 1 y 10");
	    }

	    List<Alumno> listaFiltrada = new ArrayList<>();
	    LocalDate fechaLimite = LocalDate.of(a + 1, 1, 1); // Nacidos después del año 'a'

	    for (Alumno alumno : centro.alumnos().todos()) {  // supongo tienes lista alumnos
	        if (alumno.fechaDeNacimiento().toLocalDate().isAfter(fechaLimite.minusDays(1))) {
	            listaFiltrada.add(alumno);
	        }
	    }

	    // Ordenar descendente por nota
	    listaFiltrada.sort((al1, al2) -> al2.nota().compareTo(al1.nota()));

	    List<String> resultado = new ArrayList<>();
	    for (int i = 0; i < Math.min(n, listaFiltrada.size()); i++) {
	        resultado.add(listaFiltrada.get(i).nombre());
	    }

	    return resultado;
	}

	public static List<String> nombresAlumnosMayorNotaFuncional(Integer n, Integer a) {
	    if (n == null || a == null) {
	        throw new IllegalArgumentException("n y a no pueden ser null");
	    }
	    if (n < 1 || n > 10) {
	        throw new IllegalArgumentException("n debe estar entre 1 y 10");
	    }

	    LocalDate fechaLimite = LocalDate.of(a + 1, 1, 1);

	    return centro.alumnos().todos().stream()
	        .filter(al -> al.fechaDeNacimiento().toLocalDate().isAfter(fechaLimite.minusDays(1)))
	        .sorted(Comparator.comparing(Alumno::nota).reversed())
	        .limit(n)
	        .map(Alumno::nombre)
	        .collect(Collectors.toList());
	}

	
	public static void main(String args[]) {
		//System.out.println(promedioProfesoresFuncional("86283165E"));
		//System.out.println(promedioProfesoresImperativo("86283165E"));
		
		//System.out.println(grupoMayorDiversidadEdadFuncional());
		//System.out.println(grupoMayorDiversidadEdadImperativo());
		
		System.out.println(alumnoMasMatriculasFuncional());
		System.out.println(alumnoMasMatriculasImperativo());
		
		String input = "18 - 20, 21 - 25, 26 - 30";
		Map<String, List<Alumno>> grupos1 = rangosEdadPorAlumnoFuncional(input);
		grupos1.forEach((rango, alumnos) -> {
		    System.out.println(rango + ": " + alumnos);
		});
		Map<String, List<Alumno>> grupos2 = rangosEdadPorAlumnoImperativo(input);
		grupos2.forEach((rango, alumnos) -> {
		    System.out.println(rango + ": " + alumnos);
		});

		System.out.println(nombreProfesorMasGruposImperativo(20,50));
		System.out.println(nombreProfesorMasGruposFuncional(20,50));
		
		System.out.println(nombresAlumnosMayorNotaImperativo(8,2000));
		System.out.println(nombresAlumnosMayorNotaFuncional(8,2000));
	}
	
	
}
