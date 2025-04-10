package bloque5.examen;

import java.util.Comparator;
import java.util.List;

import entrega2.ListaOrdenada;

public class ClientesPorAntiguedad extends ListaOrdenada<Cliente>{

	public ClientesPorAntiguedad(Comparator<Cliente> comparator) {
		super(comparator);
		// TODO Auto-generated constructor stub
	}
/*ClientesPorAntiguedad extiende ListaOrdenada<Cliente> e
implementa una lista ordenada de clientes, usando un comparador que los ordene por
antigüedad descendente (lo más antiguos primero). Añada el siguiente método e
impleméntelo de forma imperativa:
a. public List<Cliente> topClientes(int n) → devuelve una lista con los n clientes con
más antigüedad del sistema*/
	public List<Cliente> topClientes(int n) {
		List<Cliente> clientes = this.elements()
;		int n1 = this.size();
	    for (int i = 0; i < n1 - 1; i++) {
	        for (int j = 0; j < n1 - i - 1; j++) {
	            if (clientes.get(j).getAntiguedad() < clientes.get(j + 1).getAntiguedad()) {
	                Cliente temp = clientes.get(j);
	                clientes.set(j, clientes.get(j + 1));
	                clientes.set(j + 1, temp);
	            }
	        }
	    }
	    if (n > clientes.size()) {
	        return clientes;
	    }else {
	    	return clientes.subList(0, n);
	    }
	}
	
	public static void main(String[] args) {
		Cliente ana = Cliente.of("Ana", 5);
		Cliente juan = Cliente.of("Juan", 2);
		Cliente luis = Cliente.of("Luis", 7);

		ClientesPorAntiguedad cpa = new ClientesPorAntiguedad((c1, c2) -> c2.getAntiguedad() - c1.getAntiguedad());
		cpa.add(ana);
		cpa.add(juan);
		cpa.add(luis);
		System.out.println("Clientes por antigüedad:");
		for (Cliente cliente : cpa.elements() ) {
			System.out.println(cliente.getNombre() + " - " + cliente.getAntiguedad());
		}
		
		System.out.println("Top 2 clientes:");
		List<Cliente> topClientes = cpa.topClientes(2);
		for (Cliente cliente : topClientes) {
			System.out.println(cliente.getNombre() + " - " + cliente.getAntiguedad());
		}
	}
}
