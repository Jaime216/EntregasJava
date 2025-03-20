package entrega2;

public class ColaPrioridad<E, P extends Comparable<P>> extends Cola<PriorityElement<E, P>> {
    public static <E, P extends Comparable<P>> ColaPrioridad<E, P> ofPriority();
    @Override
    public void add(PriorityElement<E, P> element);
    public void add(E value, P priority);
    public List<E> valuesAsList();
    public void decreasePriority(E value, P newPriority);
    public E removeValue();
    public void addAllValues(List<E> values, P priority);
}
