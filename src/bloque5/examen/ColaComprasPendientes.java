package bloque5.examen;

import java.util.List;
import java.util.stream.Collectors;

import entrega2.Cola;

public class ColaComprasPendientes extends Cola<Compra>{
/*ColaComprasPendientes extiende Cola<Compra> y representa la
cola de compras aún no procesadas. Las compras se atiendan en orden de llegada (FIFO).
Incluya los siguientes métodos:
a. public Compra buscarCompraPorDescripcion(String desc) → busca la primera
compra en la cola cuya descripción contenga la cadena indicada. Utilice
implementación imperativa.
b. public List<Compra> filtrarPorClienteYProducto(Cliente cliente, String producto)
→ devuelve todas las compras pendientes de ese cliente que contengan en su
descripción el producto indicado. Utilice implementación funcional.
*/
	public Compra buscarCompraPorDescripcion(String desc) {
		for(Compra c: this.elements()) {
			if(c.getDescripcion().contains(desc)) {
				return c;
			}
		}
		throw new IllegalArgumentException("No se encontró ninguna compra con la descripción: " + desc);
	}
	
	public List<Compra> filtrarPorClienteYProducto(Cliente cliente, String producto){
		return this.elements()
				.stream()
				.filter(x-> x.getCliente().equals(cliente) && x.getDescripcion().equals(producto))
				.collect(Collectors.toList());
	}
	
	public static void main(String[] args) {
		Cliente ana = Cliente.of("Ana", 5);
		Cliente juan = Cliente.of("Juan", 2);
		Cliente luis = Cliente.of("Luis", 7);

		Compra c1 = Compra.of(ana, "Agenda personalizada", 25.5);
		Compra c2 = Compra.of(juan, "Camiseta estampada", 60.0);
		Compra c3 = Compra.of(ana, "Taza con foto", 15.0);
		Compra c4 = Compra.of(luis, "Poster gigante", 80.0);
		
		ColaComprasPendientes cola = new ColaComprasPendientes();
		cola.add(c1);
		cola.add(c2);
		cola.add(c3);
		cola.add(c4);
		System.out.println("Compras en la cola:");
		for (Compra compra : cola.elements()) {
			System.out.println(compra.getDescripcion() + " - " + compra.getCliente().getNombre());
		}
		System.out.println("Buscar compra por descripción:");
		try {
			Compra compraBuscada = cola.buscarCompraPorDescripcion("Camiseta");
			System.out.println("Compra encontrada: " + compraBuscada.getDescripcion() + " - "
					+ compraBuscada.getCliente().getNombre());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Filtrar compras por cliente y producto:");
		List<Compra> comprasFiltradas = cola.filtrarPorClienteYProducto(ana, "Taza con foto");
		for (Compra compra : comprasFiltradas) {
			System.out.println("Compra filtrada: " + compra.getDescripcion() + " - " + compra.getCliente().getNombre());
		}
	}
}
