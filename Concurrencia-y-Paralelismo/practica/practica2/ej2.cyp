2. A una cerealera van T camiones a descargarse trigo y M camiones a descargar maíz. Sólo hay 
lugar para que 7 camiones a la vez descarguen pero no pueden ser más de 5 del mismo tipo 
de cereal. Nota: sólo se pueden usar procesos que representen a los camiones
-------------------------------------------------------------------------
sem trigo=5
sem maiz=7
sem camiones=7

process CamionTrigo[id:0..T-1]{
	P(camiones); // consume el recurso de camion, marcandolo, en caso de estar ocupado, debe esperar a que algun otro camion lo libere
	P(trigo) // idem pero de trigo
	// Deposito trigo
	V(trigo) // libero mi recurso actual
	V(camiones) // libero el camion actual

}
process CamionMaiz[id:0..M-1]{
	P(camiones);
	P(maiz);
	// Deposito maiz
	V(maiz);
	V(camiones);

}
