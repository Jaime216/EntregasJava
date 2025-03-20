package entrega2;

import java.util.NoSuchElementException;

public class Pila<E> extends AgregadoLineal<E> {
    @Override
    public void add(E e) {
    	this.lista.add( e);
    };
    @Override
    public E remove() {
    	if (this.isEmpty()) {
			 throw new NoSuchElementException("No se puede eliminar de un agregado vacío.");
		}
		return this.lista.remove(this.size() - 1);
    }
    public E top() {
		if (this.isEmpty()) {
			return null;
		}
		return this.lista.get(this.size() - 1);
    };
}