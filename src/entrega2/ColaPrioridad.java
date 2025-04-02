package entrega2;

import java.util.LinkedList;
import java.util.List;

public class ColaPrioridad<E, P extends Comparable<P>> extends Cola<PriorityElement<E, P>> {
    public static <E, P extends Comparable<P>> ColaPrioridad<E, P> ofPriority() {
		return new ColaPrioridad<E,P>();
	}
    @Override
    public void add(PriorityElement<E, P> element) {
    	 if (this.isEmpty()) {
             this.lista.add(element);
         } else {
             int index = 0;
             while (index < this.lista.size() && element.priority().compareTo(this.lista.get(index).priority()) >= 0) {
                 index++;
             }
             this.lista.add(index, element);
         }
	}
    public void add(E value, P priority) {
    	PriorityElement<E, P> element = new PriorityElement<E, P>(value, priority);
		this.add(element);
    }
    public List<E> valuesAsList() {
		List<E> list = new LinkedList<E>();
		for (PriorityElement<E, P> element : this.lista) {
			list.add(element.value());
		}
		return list;
	}
    public void decreasePriority(E value, P newPriority) {
		for (PriorityElement<E, P> element : this.lista) {
			if (element.value() == value) {
				this.lista.remove(element);
				this.add(new PriorityElement<E, P>(value, newPriority));
				System.out.println("Prioridad disminuida");
				break;
			}
		}
	}
    public E removeValue() {
		return this.remove().value();
	}
    public void addAllValues(List<E> values, P priority) {
		for (E value : values) {
			this.add(new PriorityElement<E, P>(value, priority));
		}
	}
}
