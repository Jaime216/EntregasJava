package bloque5.examen;

public final class Cliente {
/*. Diseñe e implemente el tipo inmutable Cliente, que representará a una persona registrada en la tienda.
Cada cliente debe tener un nombre y una antigüedad, que indique el número entero de años que lleva
como cliente de la tienda. Se necesitará únicamente un constructor canónico, un método de factoría of y
métodos de consulta para las propiedades básicas. Dos clientes son iguales si tienen el mismo nombre y
su representación como cadena sigue este ejemplo: Cliente[nombre=Juan, antigüedad=5].*/
	private String nombre;
	private int antiguedad;
	
	public Cliente(String nombre, int antiguedad) {
		this.nombre = nombre;
		this.antiguedad=antiguedad;
	}
	
	public static Cliente of(String nombre,int antiguedad) {
		return new Cliente(nombre,antiguedad);
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getAntiguedad() {
		return this.antiguedad;
	}
	
	public Boolean equals(Cliente cliente) {
		if(cliente.getNombre() == this.nombre) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Cliente[nombre="+this.nombre+", antigüedad="+this.antiguedad+"]";
	}
}
