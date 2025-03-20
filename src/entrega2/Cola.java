package entrega2;

public class Cola<E> extends AgregadoLineal<E> {
    public static <E> Cola<E> of(){
    	return new Cola<E>();
    };
    @Override
    public void add(E e) {
    	this.lista.add(e);
    };
}
