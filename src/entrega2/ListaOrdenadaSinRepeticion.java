package entrega2;

import java.util.Comparator;

public class ListaOrdenadaSinRepeticion<E> extends ListaOrdenada<E> {
	
    public ListaOrdenadaSinRepeticion(Comparator<E> comparator) {
    	super(comparator);
    };
    public static <E> ListaOrdenadaSinRepeticion<E> of(Comparator<E> comparator) {
		return new ListaOrdenadaSinRepeticion<E>(comparator);
	}
    @Override
    public void add(E e) {
		Integer newIndex = this.indexOrder(e);
		if (!this.elements().contains(e)) {
			this.lista.add(newIndex, e);
		}
	}
}