package Kevin_Ruoxi_Liu;



public interface Heap<V extends Comparable<V>> {

	  public void add(V value);

	  public V[] toArray(V[] array);

	  public V remove();

	  public void fromArray(V[] array);

	  public V[] getSortedContents(V[] array);

	}

