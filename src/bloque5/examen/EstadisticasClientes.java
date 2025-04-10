package bloque5.examen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EstadisticasClientes {
	public static Map<Cliente, List<Compra>> agruparComprasPorCliente(List<Compra> compras){
		return compras
				.stream()
				.collect(Collectors.groupingBy(Compra::getCliente));
	}
	public static Map<Cliente, List<Compra>> agruparComprasPorClienteImperativo(List<Compra> compras){
		Map<Cliente,List<Compra>> mapeo = new HashMap<>();
		for(Compra r:compras) {
			ArrayList<Compra> list = new ArrayList<Compra>();
			if(mapeo.containsKey(r.getCliente())) {
				continue;
			}else {
				for(Compra r2:compras) {
					if(r.getCliente().equals(r2.getCliente())) {

						list.add(r2);
					}
				}
				mapeo.put(r.getCliente(), list);
			}
			
		}
		return mapeo;
	}
	
	public static void main(String[] args) {
		Cliente ana = Cliente.of("Ana", 5);
		Cliente juan = Cliente.of("Juan", 2);
		Cliente luis = Cliente.of("Luis", 7);

		Compra c1 = Compra.of(ana, "Agenda personalizada", 25.5);
		Compra c2 = Compra.of(juan, "Camiseta estampada", 60.0);
		Compra c3 = Compra.of(ana, "Taza con foto", 15.0);
		Compra c4 = Compra.of(luis, "Poster gigante", 80.0);

		ArrayList<Compra> compras = new ArrayList<Compra>();
		compras.add(c1);
		compras.add(c2);
		compras.add(c3);
		compras.add(c4);

		// Llamar al método para agrupar las compras por cliente
		Map<Cliente, List<Compra>> resultadoAgrupado = agruparComprasPorCliente(compras);

		// Imprimir el resultado agrupado
		for (Map.Entry<Cliente, List<Compra>> entry : resultadoAgrupado.entrySet()) {
			System.out.println("Cliente: " + entry.getKey());
			for (Compra r : entry.getValue()) {
				System.out.println("\t" + r);
			}
		}
		System.out.println("-----------------------------------------------------");
		// Llamar al método para agrupar las compras por cliente de forma imperativa
		resultadoAgrupado = agruparComprasPorClienteImperativo(compras);
		for (Map.Entry<Cliente, List<Compra>> entry : resultadoAgrupado.entrySet()) {
			System.out.println("Cliente: " + entry.getKey());
			for (Compra r : entry.getValue()) {
				System.out.println("\t" + r);
			}
		}
	}
}
