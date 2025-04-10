package bloque5.examen;

public final class Compra {
/*Diseñe e implemente el tipo inmutable Compra, que representa una compra individual realizada por
un cliente. Cada compra debe tener asociado un cliente, una descripción que resume el contenido de la
compra y un importe que indica el valor total en euros. Se necesitará un constructor canónico, un método
de factoría of y métodos de consulta para las propiedades básicas y la representación como cadena que
sigue este ejemplo: Compra [Nombre de cliente= Juan, descripción= Taza con foto y poster mini, importe=
60.50 €].*/
	private Cliente cliente;
	private String descripcion;
	private double importe;
	
	public Compra(Cliente cliente, String descripcion, double importe) {
		this.cliente = cliente;
		this.descripcion = descripcion;
		this.importe = importe;
	}
	
	public static Compra of(Cliente cliente, String descripcion, double importe) {
		return new Compra(cliente, descripcion, importe);
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public double getImporte() {
		return this.importe;
	}
	
	@Override
	public String toString() {
		return "Compra [Nombre de cliente= " + cliente.getNombre() + ", descripción= " + descripcion + ", importe= "
				+ importe + " €]";
	}
}
