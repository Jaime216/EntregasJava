package entrega2;

import java.util.Comparator;

public class ListaOrdenada<E> extends AgregadoLineal<E> {
	private Comparator<E> comparator;
	
    public ListaOrdenada(Comparator<E> comparator) {
    	this.comparator = comparator;	
    };
    public static <E> ListaOrdenada<E> of(Comparator<E> comparator) {
		return new ListaOrdenada<E>(comparator);
	}
    protected int indexOrder(E e) {
        for (int i = 0; i < elements().size(); i++) {
            if (comparator.compare(e, elements().get(i)) < 0) {
                return i;
            }
        }
        return elements().size();
	}
    @Override
    public void add(E e) {
    	Integer newIndex = indexOrder(e);
    	this.lista.add(newIndex, e);
	}
}

