package entrega6.preguntas;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.biblioteca.*;

public class PreguntasBiblioteca {
/*Implemente el método masVecesPrestado que devuelva el libro que se ha prestado más
veces y el número de veces que se ha prestado.*/

	private static Biblioteca bib = Biblioteca.of("name","21100","@.");
	
	public static Map<Libro, Integer> masVecesPrestadoImperativo() {
		int masVeces = -1;
		Map<Libro,Integer> topLibro = new HashMap<>();
		for(Libro libro:bib.libros().todos()) {
			int contador = 0;
			for(Prestamo prestamo:bib.prestamos().todos()) {
				if(libro.isbn().equals(prestamo.isbn())) contador++;
			}

			topLibro.put(libro, contador);
			if(masVeces <= contador) {
				masVeces = contador;
			}
		}int max = masVeces;
		topLibro.entrySet().removeIf(entry ->{
			if(entry.getValue() == max) return false;
			 return true;
		});
		return topLibro;
	}
	
	public static Map<Libro, Integer> masVecesPrestadoFuncional(){
		Map<Libro,Integer> mapa = bib.libros().todos().stream()
				.collect(Collectors.toMap(
					x->x,
					libro -> (int) bib.prestamos().todos().stream()
					.filter(x->x.isbn().equals(libro.isbn())).count()));
		int max = mapa.values().stream().mapToInt(Integer::intValue).max().orElse(0);
		
		mapa.entrySet().removeIf(entry -> {
			if( entry.getValue() == max) {
				return false;
			}else{
				return true;
			}
			});
		
		return mapa;
	}
	
	public static Map<String, Set<String>> librosPorAutorFuncional(Libros libros, List<String> nombres) {
	    return libros.todos().stream()
	        .filter(libro -> nombres == null || nombres.contains(libro.autor()))
	        .collect(Collectors.groupingBy(
	            Libro::autor,
	            Collectors.mapping(Libro::titulo, Collectors.toSet())
	        ));
	}
	
	public static Map<String, Set<String>> librosPorAutorImperativo(Libros libros, List<String> nombres) {
	    Map<String, Set<String>> resultado = new HashMap<>();
	    
	    for (Libro libro : libros.todos()) {
	        // Filtrar por autores si la lista nombres no es nula
	        if (nombres == null || nombres.contains(libro.autor())) {
	            String autor = libro.autor();
	            String titulo = libro.titulo();
	            
	            // Si el autor no está en el mapa, inicializamos el conjunto
	            if (!resultado.containsKey(autor)) {
	                resultado.put(autor, new HashSet<>());
	            }
	            
	            // Añadimos el título al conjunto del autor
	            resultado.get(autor).add(titulo);
	        }
	    }
	    
	    return resultado;
	}

	
	public static void main(String args[]) {
		System.out.println(masVecesPrestadoImperativo());
		System.out.println(masVecesPrestadoFuncional());
		
		System.out.println(librosPorAutorImperativo(bib.libros(),List.of("Salomé Oliver") ));

		System.out.println(librosPorAutorImperativo(bib.libros(), null ));
	}
}
