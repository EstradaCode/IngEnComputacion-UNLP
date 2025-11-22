chan Solicitudes(int);
chan liberar(int,int);
chan Siguiente[5](int);
chan respuesta[n](texto);

proceso Persona [id:0..n-1]{
	texto res; 
		send Solicitudes(id)
		receive Respuesta[id](res)


}
proceso coordinador{
vcontador[5]=([5] 0)
int idP,idC;
while(true){
	do (not empty(Solicitudes)) -> {receive Solicitudes(idP)
			idC= min(vcontador); // devuelve el id de la caja minima.
			send Siguiente[idC](idP);
			vcontador[idC]++;}
	[] (not empty(Liberar)) -> {receive Liberar(idC,idP); vcontador[idC]--; send Respuesta[idP]("Exito")
}

}

proceso Caja[id:0..4]{
	int idP;
	while true{
		receive Siguiente[id](idP);
		if(idp<>-1){ AtenderPersona(idP);send Liberar(id,idp)}

	}

}
