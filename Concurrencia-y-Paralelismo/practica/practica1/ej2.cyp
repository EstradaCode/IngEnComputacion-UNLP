int N;                // valor a buscar
int array[M];         // arreglo de M elementos
int Total := 0;       // contador global

Process Contar[i: 0 .. M-1] { // cada uno de los procesos pregunta por una casilla del vector especifica, dado el id que poseen:
  if array[i] == N then
    <Total := Total + 1;>
}
