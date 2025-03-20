package entrega2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class AgregadoLineal<E> {
	ArrayList<E> lista = new ArrayList<E>();
	
    public int size() {
    	return lista.size();
	}
    public boolean isEmpty() {
    	if (lista.size() == 0) {
    		return true;
    	}
		return false;
	}
    public List<E> elements() {
		return lista;
	}
    
    public abstract void add(E e);
    
    public void addAll(List<E> list) {
    	for(E e: list) {
    		this.add(e);
    	}
	}
    public E remove() {
		if (this.isEmpty()) {
			 throw new NoSuchElementException("No se puede eliminar de un agregado vacío.");
		}
		return this.lista.remove(0);					
	}
    public List<E> removeAll() {
    	List<E> listaEliminados = new LinkedList<E>();
    	Integer size = this.size();
		for (Integer i = 0; i < size; i++) {
			E eliminado = this.remove();
			listaEliminados.add(eliminado);
		}
		return listaEliminados;
	}
}
