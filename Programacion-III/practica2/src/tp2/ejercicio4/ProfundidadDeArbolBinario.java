package tp2.ejercicio4;
import tp1.ejercicio8.Queue;
import tp2.ejercicio1.*;
public class ProfundidadDeArbolBinario {
	private BinaryTree<Integer> treeNode;

	public ProfundidadDeArbolBinario(BinaryTree<Integer> treeNode) {
		super();
		this.treeNode = treeNode;
	}

	public ProfundidadDeArbolBinario() {
		super();
	}
	public int sumaElementosProfundidad(int p) { // respeta los parametros por ser iterativo, no requiere cambios!
	int prof = 0, suma=0;
	BinaryTree<Integer> ab = null; // ref nula
 	Queue<BinaryTree<Integer>> cola = new Queue<BinaryTree<Integer>>(); // cola 
 	cola.enqueue(this.treeNode);
    cola.enqueue(null); // todo cumple el nivel 0
 	 while (!cola.isEmpty() && prof <= p) {
 		 ab = cola.dequeue();
 		 if (ab != null) {
 			 if(prof == p)
 			   suma+= ab.getData();
 			 if (ab.hasLeftChild())
 				 cola.enqueue(ab.getLeftChild());
 			 if (ab.hasRightChild())
 				 cola.enqueue(ab.getRightChild());
 		 } else if (!cola.isEmpty()) {
 			 cola.enqueue(null);
 			 prof++; // aca es donde cambia de nivel de profundidad
 		 }
 	 }
 	 return suma;
	}
}

