package tp2.ejerciciosParciales;
import tp2.ejercicio1.*;
public class ParcialArboles {
	BinaryTree <Integer> root;

	public ParcialArboles(BinaryTree<Integer> root) {
		super();
		this.root = root;
	}
	/* CORRESPONDE AL EJERCICIO 7 DE LA PRACTICA 2
	 * 
	 */
	public boolean isLeftTree(int num) {
		BinaryTree<Integer> ab = getRootNode(root, num);
		if(ab == null) return false;
		int leafsL = compareLeafs(ab.getLeftChild());
		int leafsR = compareLeafs(ab.getRightChild());
		return leafsL > leafsR;
	}
	private BinaryTree <Integer> getRootNode( BinaryTree<Integer> ab, int num ) {
		BinaryTree <Integer> data = null;
		if(ab!=null && !ab.isEmpty()) {
			if(ab.getData() == num) {
				return ab;
			}else {	
				if(ab.hasLeftChild()) {
					data = getRootNode(ab.getLeftChild(),num);
					if(data != null) return data;
					
				}
				if(ab.hasRightChild()) {
					data = getRootNode(ab.getRightChild(),num);
					if(data != null) return data;
				}
			}
		} return data;
	}
	private int compareLeafs(BinaryTree<Integer> ab) {
		int sumLeafs=0;
		if(ab!= null && !ab.isEmpty()) {
			if(ab.hasLeftChild() ^ ab.hasRightChild()){
				sumLeafs++;
			}
			if(ab.hasLeftChild()) {
				sumLeafs += compareLeafs(ab.getLeftChild());
			}
			if(ab.hasRightChild()) {
				sumLeafs+= compareLeafs(ab.getRightChild());
			}
		}
		return sumLeafs;
	}
	/* FIN DEL EJERCICIO 7 PRACTICA 2 
	 * 
	 */
	/*
	 * ejercicio 8
	 */
	public boolean esPrefijo( BinaryTree<Integer> arbol1, BinaryTree<Integer> arbol2) {
		// es prefijo si el arbol1 y sus elementos coincide con la parte inicial del arbol2
		boolean cumple= false; // para retornar un valor 
		if(arbol1 == null) {
			cumple = true; // caso 1, el arbol es null debido a que vino vacio o que de desplazó mas alla de una hoja
		}
		if((arbol1!=null && !arbol1.isEmpty()) && (arbol2!=null && !arbol2.isEmpty())){
			if(!arbol1.getData().equals(arbol2.getData())) { // si el dato no es igual devuelve un false
				return false;
			}
			cumple= esPrefijo(arbol1.getLeftChild(),arbol2.getLeftChild()) && // si o si devuelve algo, sea porque llegó hasta el final de arbol1 por un lado o no coincide un nodo con otro
			esPrefijo(arbol1.getRightChild(),arbol2.getRightChild()); // recorro los hijos con esta condicion
		}
		return cumple; // asegura la devolución obligatoria del dato
	} // consultar la implementacion y estructuras de recorridos
	/* FIN DEL EJERCICIO 8 PRACTICA 2 
	 * 
	 */
	
	// ejercicio 9 p2
	public BinaryTree<Datos> sumAndDif(BinaryTree<Integer>arbol){
		if( arbol == null) {
			return null;
		}
		BinaryTree<Datos> root = sumDifHelper(arbol, 0,0);
		//dif(arbol,root,0); antes lo hacia por partes, sumdifHelper realiza todo.
		return root;
	}
	
	private BinaryTree<Datos> sumDifHelper(BinaryTree<Integer> arbol, int currentsum, int datoPadre){
		BinaryTree<Datos> aux = null;
		if(arbol != null && !arbol.isEmpty()) {
			aux = new BinaryTree<Datos>();
			Datos d= new Datos();
			currentsum += arbol.getData();
			d.setSuma(currentsum);
			d.setDif(arbol.getData() - datoPadre);
			aux.setData(d);
			
			if(arbol.isLeaf()) {
				return aux;
			}
			
			if(arbol.hasLeftChild()) {
				aux.addLeftChild(sumDifHelper(arbol.getLeftChild(), currentsum, arbol.getData()));
			}
			if(arbol.hasRightChild()) {
				aux.addRightChild(sumDifHelper(arbol.getRightChild(), currentsum, arbol.getData()));
			}
		}
		return aux; 
	}
	public void preorden (BinaryTree<Datos> a) {
		if(a!=null && !a.isEmpty()) {
			System.out.println(a.getData().getSuma() + "|" + a.getData().getDif());
			if(a.hasLeftChild()) {
				preorden(a.getLeftChild());
			}
			if(a.hasRightChild()) {
				preorden(a.getRightChild());
			}
		}
	}
	
}
