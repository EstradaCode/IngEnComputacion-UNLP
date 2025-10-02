ej5 
-------
// C corredores
// maquina (20 botellas)
// repositor 
// cuando llegan los c corredores al inicio, comienza la carrera. cuando uno termina la carrera, se dirige a la maquina, espera su turna (orden de llegada), saca una botella y se retira.
// si la maquina sin botella, el repositor debe cargar nuevamente la maquina con 20 botellas; (corredor) espera a que se haga la recarga; saca una botella y se retirna. se deben encolar los otros corredores mientras se reponen las botellas.
INT C=...;  // VOY A UTILIZAR UNA BARRERA
INT BOTELLAS=20;

// etapa de llegada
sem mutex_llego=1;

process corredores[id:0..C-1]{
	int i;
	P(mutex_llego)
	contador++;
	if(contador==C){ // significa que soy el ultimo y llegaron todos
		fori=1..C -> V(barrera) // esto les permite avanzar al resto
	}
	V(mutex_llego) // termino de "llegar"
	P(barrera) // para comenzar la carrera
	Correr();
	V(mutex);
}
