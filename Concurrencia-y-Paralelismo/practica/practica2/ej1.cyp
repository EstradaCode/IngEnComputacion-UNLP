Un sistema operativo mantiene 5 instancias de un recurso almacenadas en una cola, cuando un proceso necesita usar una instancia del recurso la saca de la cola, la usa y cuando termina de usarla la vuelve a depositar.

sem mutex=1;
sem recursos=5;
Cola c;
Process consumidorRecurso[id:0..N-1]{

	recurso R; // variable donde va a estar utilizada

	P(recursos) // verifico que haya recurso, sino espero exc muy -> además, en caso de haberlo, debe ser solo uno el que elija el recurso 5 por ejemplo.
	P(mutex) // verifico que solo un hilo pase por esta ejecucion
	R=pop(C);
	V(mutex) //es critico que solamente un proceso acceda al rec comp.
	// ahora que tengo el recurso
	// USO EL RECURSO FUERA DE LA SC

	P(mutex); // el mutex como valor binario me brinda seguridad independientemente de la seccion, siempre que entre ellas utilicen el mismo mutex
	push(C,R); // devuelvo el recurso, debo asegurarme de que nadie esté utilizando dicho recurso.
	V(mutex);
	V(recursos) // libero el recurso utilizado para otro.

}
