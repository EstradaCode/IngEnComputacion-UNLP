package tp5.ej3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tp5.ej1.*;
public class Map {
	private Graph<String> ciudades ;

	public Map(Graph<String> ciudades) {
		super();
		this.ciudades = ciudades;
	}
	
	/* RETORNA LA LISTA DE CIUDADES QUE SE DEBEN ATRAVESAR PARA IR DE C1 A C2
	 * SI NO SE PUEDE LLEGAR, LISTA NULL
	 */
	public List<String> devolverCamino(String ciudad1,String ciudad2){
			List<String> camino = new ArrayList<String>();
			if(!this.ciudades.isEmpty()) {
				Vertex<String> origin = this.ciudades.search(ciudad1);
				Vertex<String> destination = this.ciudades.search(ciudad2);
				if( (origin != null) && (destination != null) ) {
					buscarCamino(origin, new boolean [this.ciudades.getSize()], camino, destination);
				}
			}
			return camino;	
		}
			private boolean buscarCamino(Vertex<String> origin, boolean[] marca,List<String> camino, Vertex<String> destination) {
				boolean exito= false;
				marca[origin.getPosition()] = true; // recorrido el vertice
				camino.add(origin.getData());
				if( origin == destination) {
					return true;
				}
				else {
					List<Edge<String>> adyacentes = this.ciudades.getEdges(origin); //adyacentes
					Iterator<Edge<String>> it = adyacentes.iterator();
					int j;
					while((it.hasNext())&& (!exito)) {
						 Vertex<String>aux = it.next().getTarget();
						j=aux.getPosition();
						 if(!marca[j]) {
							exito = buscarCamino(aux,marca,camino,destination);
						}
					}
				}
				if(!exito) {
					camino.remove(camino.size()-1); // como no llego a nada, se elimina de la lista recursivaente
				}
				return exito;
			}
			
				public List<String> devolverCaminoExceptuando(String ciudad1,String ciudad2,String ciudad3){
					List<String> camino = new ArrayList<String>();
					if(!this.ciudades.isEmpty()) {
						Vertex<String> origin = this.ciudades.search(ciudad1);
						Vertex<String> destination = this.ciudades.search(ciudad2);
						Vertex<String> evitar =	this.ciudades.search(ciudad3);
						if( (origin != null) && (destination != null) && (evitar!=null) ) {
							if(origin != evitar) {
								buscarCaminoExcepto(origin, new boolean [this.ciudades.getSize()], camino, destination,evitar);
							}
						}
					}
					return camino;
					
					
				}
					private boolean buscarCaminoExcepto(Vertex<String> origin, boolean[] marca,List<String> camino, Vertex<String> destination,Vertex<String> evitar) {
						boolean exito= false;
						marca[origin.getPosition()] = true; // recorrido el vertice
						camino.add(origin.getData());
						if(( origin == destination) && (destination!=evitar)) {
							return true;
						}
						else {
							List<Edge<String>> adyacentes = this.ciudades.getEdges(origin); //adyacentes
							Iterator<Edge<String>> it = adyacentes.iterator();
							int j;
							while((it.hasNext())&& (!exito)) {
								 Vertex<String>aux = it.next().getTarget();
								 j=aux.getPosition();
								 if((!marca[j]) && (aux != evitar)) {
									exito = buscarCaminoExcepto(aux,marca,camino,destination,evitar);
								}
							}
						}
						if(!exito) {
							camino.remove(camino.size()-1); // como no llego a nada, se elimina de la lista recursivaente
						}
						return exito;
					}
					
					public List<String> CaminoMasCorto(String ciudad1,String ciudad2){
						List<String> camino = new ArrayList<String>();
						List<String> act = new ArrayList<String>();
						if(!this.ciudades.isEmpty()) {
							Vertex<String> origin = this.ciudades.search(ciudad1);
							Vertex<String> destination = this.ciudades.search(ciudad2);
							if( (origin != null) && (destination != null) ) {
								CaminoCorto(origin, new boolean [this.ciudades.getSize()], camino,act, destination,0,99999);
							}
						}
						return camino;	
					}
					private int CaminoCorto(Vertex<String> origin, boolean[]marca, List<String> camino,List<String> act, Vertex<String> destination, int total, int min) {
						marca[origin.getPosition()]= true;
						act.add(origin.getData());
						//System.out.println("actual:" + act);
						if((origin == destination)&&(total< min)) {
							camino.removeAll(camino);
							camino.addAll(act);
							//System.out.println("camino min : " + camino);
							min = total;
						}else {
							List<Edge<String>> adyacentes = this.ciudades.getEdges(origin);
							Iterator<Edge<String>> it = adyacentes.iterator();
							int j ;
							while(it.hasNext() && total < min) { // sirve el corte de control para que sepa cuando volver y no recorrer al pepe
								Vertex<String> aux = it.next().getTarget();
								j= aux.getPosition();
								if(!marca[j]) {
									min = CaminoCorto(aux,marca,camino,act,destination,total+1,min);
								}	
							}
						}
						marca[origin.getPosition()]= false; // lo desmarco
						act.remove(act.size()-1);
						return min;
					}
					// re hacer despues con dijkstra y floyd
				}

