package tp3.ej1;

import java.util.List;
import java.util.LinkedList;


public class GeneralTree<T> {

	private T data;
	private List<GeneralTree<T>> children = new LinkedList<GeneralTree<T>>();

	public GeneralTree(T data) {
		this.data = data;
	}

	public GeneralTree(T data, List<GeneralTree<T>> children) {
		this(data);
		this.children = children;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setChildren(List<GeneralTree<T>> children) {
		if (children != null)
			this.children = children;
	}

	public List<GeneralTree<T>> getChildren() {
		return this.children;
	}

	public void addChild(GeneralTree<T> child) {
		this.getChildren().add(child); // añade un hijo a la lista de hijos
	}

	public boolean isLeaf() {
		return !this.hasChildren(); // si no tiene hijos es hoja
	}

	public boolean hasChildren() {
		return children != null && !children.isEmpty();
	}

	public boolean isEmpty() {
		return this.data == null && !hasChildren();
	}

	public void removeChild(GeneralTree<T> child) {
		if (this.hasChildren()) { // si tiene hijos
			List<GeneralTree<T>> children = this.getChildren(); // obtengo la lista de hijos
			if (children.contains(child)) // si lo tiene lo borro
				children.remove(child);
		}
	}

	

}
