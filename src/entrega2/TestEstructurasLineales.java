package entrega2;

import java.util.Comparator;
import java.util.List;

public class TestEstructurasLineales {
	
	public static void testListaOrdenada() {
		System.out.println("----- Prueba de ListaOrdenada -----");
		Comparator<Integer> comparator = (a, b) -> a - b;
		ListaOrdenada<Integer> listaOrdenada = ListaOrdenada.of(comparator);
		System.out.println("Añadiendo elementos: 5, 2, 8, 1, 3");
		listaOrdenada.add(5);
		listaOrdenada.add(2);
		listaOrdenada.add(8);
		listaOrdenada.add(1);
		listaOrdenada.add(3);
		System.out.println("Elementos en la lista: " + listaOrdenada.elements());
		System.out.println("Tamano de la lista: " + listaOrdenada.size());
		System.out.println("Eliminando el primer elemento: 1");
		listaOrdenada.remove();
		System.out.println("Elementos después de eliminar: " + listaOrdenada.elements());
		System.out.println("Añadiendo elementos en lote: 4, 6, 7");
		listaOrdenada.addAll(List.of(4, 6, 7));
		System.out.println("Elementos después de añadir lote: " + listaOrdenada.elements());
		System.out.println("Eliminando todos los elementos: " + listaOrdenada.removeAll());
		System.out.println("Elementos después de eliminar todos: " + listaOrdenada.elements());
		System.out.println("¿Está vacía la lista? " + listaOrdenada.isEmpty());
		
		System.out.println("\nPrueba con strings:\n");
		//Elementos ordenados: [apple, banana, cherry, date]
		System.out.println("Añadiendo elementos: apple, banana, cherry, date");
        Comparator<String> comparatorString = (a, b) -> a.compareTo(b);	
        ListaOrdenada<String> listaOrdenadaString = ListaOrdenada.of(comparatorString);
        listaOrdenadaString.add("apple");
        listaOrdenadaString.add("banana");
        listaOrdenadaString.add("cherry");
        listaOrdenadaString.add("date");
        System.out.println("Elementos en la lista: " + listaOrdenadaString.elements()+"\n\n");
		
	}
	
	public static void testListaOrdenadaSinRepeticion() {
		System.out.println("----- Prueba de ListaOrdenadaSinRepeticion -----");
		Comparator<Integer> comparator = (a, b) -> a - b;
		ListaOrdenadaSinRepeticion<Integer> listaOrdenadaSinRepeticion = ListaOrdenadaSinRepeticion.of(comparator);
		System.out.println("Añadiendo elementos: 5, 2, 8, 1, 3, 5, 2");
        listaOrdenadaSinRepeticion.add(5);
        listaOrdenadaSinRepeticion.add(2);
        listaOrdenadaSinRepeticion.add(8);
        listaOrdenadaSinRepeticion.add(1);
        listaOrdenadaSinRepeticion.add(3);
        listaOrdenadaSinRepeticion.add(5);
        listaOrdenadaSinRepeticion.add(2);
		System.out.println("Elementos en la lista: " + listaOrdenadaSinRepeticion.elements());
		System.out.println("Tamano de la lista: " + listaOrdenadaSinRepeticion.size());
		System.out.println("Eliminando el primer elemento: 1");
		listaOrdenadaSinRepeticion.remove();
		System.out.println("Elementos después de eliminar: " + listaOrdenadaSinRepeticion.elements());
		System.out.println("Añadiendo elementos en lote: 4, 6, 7, 4");
		listaOrdenadaSinRepeticion.addAll(List.of(4, 6, 7));
		System.out.println("Elementos después de añadir lote: " + listaOrdenadaSinRepeticion.elements());
		System.out.println("Se espera que el 4 aparezca solo una vez");
		
        System.out.println("\n\n");
		
	}
	
	public static void testCola() {
		System.out.println("----- Prueba de Cola -----");
		Cola<String> cola = Cola.of();
		System.out.println("Añadiendo elementos: 'primero', 'segundo', 'tercero'");
        cola.add("primero");
        cola.add("segundo");
        cola.add("tercero");
		System.out.println("Elementos en la cola: " + cola.elements());
		System.out.println("Tamano de la cola: " + cola.size());
		System.out.println("Desencolado: primero");
		cola.remove();
		System.out.println("Cola restante: " + cola.elements());

		System.out.println("Desencolado: segundo");
		cola.remove();
		System.out.println("Cola restante: " + cola.elements());

		System.out.println("Desencolado: tercero");
		cola.remove();
		System.out.println("Cola restante: " + cola.elements());
		
		System.out.println("¿Está vacía la cola? " + cola.isEmpty());
		try {
			cola.remove();
		}catch (Exception e) {
            System.out.println("Excepción correctamente lanzada al intentar desencolar de una cola vacía: " + e.getMessage());
		}
		System.out.println("\n\n");
	}

	public static void testPila() {
		System.out.println("----- Prueba de Pila -----");
		Pila<Double> pila = new Pila<Double>();
		System.out.println("Añadiendo elementos: 1.1, 2.2, 3.3");
        pila.add(1.1);
        pila.add(2.2);
        pila.add(3.3);
		System.out.println("Elementos en la pila: " + pila.elements());
		System.out.println("Tamano de la pila: " + pila.size());
		System.out.println("Elemento en el tope: " + pila.top());
		System.out.println("Desapilando: 3.3");
		pila.remove();
		System.out.println("Pila restante: " + pila.elements());
		System.out.println("Desapilando: 2.2");
		pila.remove();
		System.out.println("Pila restante: " + pila.elements());
		System.out.println("Desapilando: 1.1");
		pila.remove();
		System.out.println("Pila restante: " + pila.elements());
		System.out.println("¿Está vacía la pila? " + pila.isEmpty());
		try {
			pila.remove();
		} catch (Exception e) {
			System.out.println(
					"Excepción correctamente lanzada al intentar desapilar de una pila vacía: " + e.getMessage());
		}
		System.out.println("\n\n");
	}
	
	public static void testColaPrioridad() {
        System.out.println("----- Prueba de ColaPrioridad -----\n");

        ColaPrioridad<String, Integer> cola = ColaPrioridad.ofPriority();
        
        System.out.println("Añadiendo elementos con prioridad:\n");
        cola.add("Crítico", 1);
        System.out.println("'Crítico' con prioridad 1");
        cola.add("Normal", 3);
        System.out.println("'Normal' con prioridad 3");
        cola.add("Urgente", 2);
        System.out.println("'Urgente' con prioridad 2");
        cola.add("Bajo", 4);
        System.out.println("'Bajo' con prioridad 4\n");

        System.out.println("Elementos en la cola por prioridad: " + cola.valuesAsList());
        System.out.println("Elementos con sus prioridades: " + cola.elements());
        System.out.println("Tamaño de la cola: " + cola.size() + "\n");
        
        System.out.println("Cambiando la prioridad de 'Normal' de 3 a 1:\n");
        cola.decreasePriority("Normal", 1);
        System.out.println("Elementos con prioridad actualizada: " + cola.valuesAsList() + "\n");
        
        System.out.println("Desencolando elementos por prioridad:\n");
        while (!cola.isEmpty()) {
            System.out.println("Desencolado: " + cola.removeValue());
            System.out.println("Cola restante: " + cola.valuesAsList() + "\n");
        }
        
        System.out.println("¿Está vacía? " + cola.isEmpty());
        
        try {
            cola.removeValue();
        } catch (Exception e) {
            System.out.println("Excepción correctamente lanzada al intentar desencolar de una cola vacía: " + e.getMessage());
        }
        
        System.out.println("\nPrueba con addAll:\n");
        cola.addAllValues(List.of("Tarea A", "Tarea B", "Tarea C"), 2);
        System.out.println("Elementos añadidos en lote con prioridad 2: " + cola.valuesAsList());
        
        cola.add("Tarea Urgente", 1);
        System.out.println("Después de añadir 'Tarea Urgente' con prioridad 1: " + cola.valuesAsList());
        
        System.out.println("\n\n");
    }
	
	public static void main(String[] args) {
    	System.out.println("===== INICIANDO PRUEBAS DE ESTRUCTURAS LINEALES =====\n\n");
    	
    	testListaOrdenada();
    	
    	testListaOrdenadaSinRepeticion();
    	
    	testCola();
    	
    	testPila();
    	
    	testColaPrioridad();
    	
    	System.out.println("===== TODAS LAS PRUEBAS COMPLETADAS =====");
    }
}