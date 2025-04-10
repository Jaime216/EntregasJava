package bloque5.examen;

import java.util.List;
import java.util.stream.Collectors;

import entrega2.Pila;

public class HistorialCompras extends Pila<Compra> {
	/*HistorialCompras extiende Pila<Compra> y modela el historial de
compra de todos los clientes. Las compras se apilan en orden cronológico inverso: la
última compra realizada, estará en la cima. Añada los siguientes métodos e
impleméntelos de forma funcional:
a. public double totalGastadoPor(Cliente cliente) → calcula el importe total gastado
por un cliente específico.
b. public List<Compra> comprasMayoresA(double cantidad) → devuelve la lista de
compras cuyo importe supera la cantidad indicada.*/
	public double totalGastadoPorCliente(Cliente cliente) {
		return this.elements()
				.stream()
				.filter(x->x.getCliente().equals(cliente))
				.mapToDouble(x->x.getImporte())
				.sum();
	}
	public List<Compra> comprasMayoresA(double cantidad){
		return this.elements()
				.stream()
				.filter(x->x.getImporte()>cantidad)
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
		
		HistorialCompras historial = new HistorialCompras();
		historial.add(c1);
		historial.add(c2);
		historial.add(c3);
		historial.add(c4);
		
		System.out.println("Pila de compras: ");
		System.out.println(historial.elements());
		
		System.out.println(historial.totalGastadoPorCliente(ana)); // 40.5
		System.out.println(historial.totalGastadoPorCliente(juan)); // 60.0
		System.out.println(historial.totalGastadoPorCliente(luis)); // 80.0
		
		System.out.println("Compras mayores a 30 euros: ");
		System.out.println(historial.comprasMayoresA(30)); // [Compra@4b1210ee, Compra@5b6f64c2]
	}
}
