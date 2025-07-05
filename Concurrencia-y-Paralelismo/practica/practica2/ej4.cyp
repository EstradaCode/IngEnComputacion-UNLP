# N personas deben fotocopiar un documento cada una.
#
#a - supongamos hay una unica fotocopiadora para n personas, la deben usar de a una persona a la vez, sin orden. Ftotocopiar() hace uso de la fotocopiadora. Solo con procesos de personas.
#
sem mutex=1; // reservado para el recurso compartido
procedure Persona[id=0..N-1]{
	P(mutex); // la que encuentre el mutex disponible entra y fotocopia
	Fotocopiar();
	V(mutex); // liberan mutex


}
i
#b - modifica para que se deba respetar el orden de llegada
sem mutex=1;
sem mutex_impresora=1;
bool imprimiendo= false;
sem turno[N]=([N] 0)
Cola c;
int siguiente=-1;
procedure Persona[id=0..N-1]{
	P(mutex);
	if(c.empty() && !imprimiendo){
		imprimiendo= true;
		siguiente= id; // cola vacia, voy yo
		V(turno[siguiente]);
	else c.enqueue(id); // me encolo, espero mi turno
	V(mutex);
	
	P(turno[id]);
	P(mutex_impresora);
	Fotocopiar();
	V(mutex_impresora);
	imprimiendo=false;


	P(mutex)
	if(!c.empty()){
		siguiente= c.dequeue();
		V(turno[siguiente]);
	}
	V(mutex)

}

#c - lo mismo pero cambio el proceso de encolado por uno que reciba la edad tambien
#d - se debe respetar el orden numerado
#
sem mutex=1;
int siguiente=0;
sem turno[N]= ([N] 0)
process Persona[id=0..N-1]{
	if( id==0){
		V(turno[id]);
	}
	P(turno[id])
	Fotocopiar()
	if(id+1 < N){ 
		V(turno[id+1]);
	}

}
#e - Empleado indiica a cada persona cuando usar la fotocopiadora.
#
sem mutex=1;
sem mutex_impresora=1;
sem turno[N]=([N] 0)
Cola c;
int siguiente=-1;
procedure Persona[id=0..N-1]{
	P(mutex);
	c.enqueue(id); // me encolo, espero mi turno
	V(mutex);
	
	P(turno[id]);
	P(mutex_impresora);
	Fotocopiar();
	V(mutex_impresora);


}
process Empleado{
   while(true){
   	P(mutex);
	if(!c.empty()){
	siguiente= c.dequeue();
	V(turno[siguiente])
	}
	V(mutex)
	}
  }
}
sem mutex=1; 
sem mutex_impresora=([10]1);
sem impresoras=10;
sem turno[N]=([N] 0)
Cola c;

procedure Persona[id=0..N-1]{
	P(mutex);
	c.enqueue(id); // me encolo
	V(mutex);
	
	P(turno[id]);   // el empleado me habilita
	P(impresora) // sem contador para saber que impresora me dieron
	P(mutex_impresora[impresora]);     // accedo a la impresora
	Fotocopiar();
	V(mutex_impresora);
	V(impresora)
	V(mutex);               // aviso que terminé
}

process Empleado{
   while(true){
	P(mutex);
	if(!c.empty()){
		int siguiente = c.dequeue();
		V(turno[siguiente]);
	}
	V(mutex);
   }
}
 #hacer el ultimo, muy completo
