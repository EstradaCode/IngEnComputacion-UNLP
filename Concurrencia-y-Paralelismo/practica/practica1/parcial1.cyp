/*
resolver con AWAIT <> <await B;S> el siguiente problema. Para un partido de futbol se vender E entradas de forma online, y hay P personas (P>E) que intentan comprar una entrada formando una unica fila. Para la venta existen 5 boleterías virtuales que van atendiendo los pedidos de las personas de acuerdo con el orden de llegada. Cuando una boletería atiende a una persona, si aún quedan entradas disponibles le envia el numero de entrada vendida, sino le indica que no hay más entradas. NOTA: MAXIMIZAR LA CONCURRENCIA (NO BUSY WAITING)*/

int total_entradas=E;// recurso compartido para las boleterias
Cola fila; // recurso compartido para las personas
entradas_personas[P] = ([entradas_personas] -1) 
int personas_atendidas=0,
process Persona[id=0..P-1]{
	<fila.enqueue(id)>
	<await(entrada_personas[id] != -1);>
	if(entrada_personas[id] >0)
		imprimir("mi entrada es la numero: " + entrada_personas[id]);
	else
		imprimir("no conseguí entrada :(");
}

process Boleteria[0..4]{
id_actual=-1;
while(true)// 
	<await (!fila.empty()); id_actual= fila.dequeue()>
	<if(total_entradas > 0);entrada_personas[id_actual]= total_entradas; total_entradas--;
	else entrada_personas[id_actual]=0>
}
