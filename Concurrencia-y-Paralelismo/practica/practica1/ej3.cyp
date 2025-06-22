{3. Realice una solución concurrente de grano grueso (utilizando <> y/o <await B; S>) para el 
siguiente problema. Un sistema operativo mantiene 5 instancias de un recurso almacenadas en una cola, cuando un proceso necesita usar una instancia del recurso la saca de la cola, la usa y cuando termina de usarla la vuelve a depositar.} 

// elementos compartidos: cola + elementos 5
-> cuando la sacan, la cola se reduce imagino
por lo que se debe tener en cuenta eso
cola C;
int siguiente=-1;
process usarRecurso[id:0..N-1] // pensar en vacio, lleno y en usoo
{ 	recurso R;
	<await(!empty(C); R = Pop(C)>; // await me determina que si no encuentro algun recurso disponible en la cola, voy a tener que esperar a que uno finalice.
	// uso el recurso
	<Push(C,R)>;

}
