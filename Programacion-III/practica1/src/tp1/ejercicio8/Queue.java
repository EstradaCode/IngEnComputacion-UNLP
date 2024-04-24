package tp1.ejercicio8;
import java.util.List;
import java.util.ArrayList;

public class Queue <E> extends Sequence {
	private List <E> l;
	public Queue() {
		// TODO Auto-generated constructor stub
		this.l = new ArrayList<E>();
	}
	public Queue(List <E> dato) {
		this.l = new ArrayList<E>();
		for(E e : dato) {
			this.enqueue(e);
		}
	}
	@Override
	public int size() {
		return l.size();
	}
	@Override
	public boolean isEmpty() {
		return l.isEmpty();
	}
	protected List<E> getQueue(){
		return l;
	}
	public void enqueue(E dato) {
		l.addLast(dato); // estructura fifo first in first out;
	}
	public E dequeue() {
		return l.removeFirst();
	}
	public E head() {
		return l.getFirst();
	}
	@Override
	public String toString() {
		String ret = "Queue [";
		for(E e : l) {
			ret+= " " + e +"  ,";
		}
		ret+="]";
		return ret;
	}
	
}
