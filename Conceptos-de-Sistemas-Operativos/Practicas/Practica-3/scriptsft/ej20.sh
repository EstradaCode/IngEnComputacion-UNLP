#!/bin/bash
#
# pila estructura lifo
#
arreglo=()
indice=0
function push{
elemento="$1"
arreglo[$indice]=$elemento
echo "agregado elemento $1 a la pila"
(( indice++ ))
}
function pop{
	unset arreglo[$indice]
	(( indice-- ))
	arreglo=(${arreglo[@]})
	echo "desapilado ultimo elemento ingresado"
}
function length{
	echo "${#arreglo[@]}"
}
function print{
	echo "${arreglo[@]}"
}

select opcion in "push" "pop" "length" "print" "salir" do
	case $opcion in 
		push) echo "ingrese elemento a añadir"
			read elem
			push $elem;;
		pop) pop;;
		length) length;;
		print) print;;
		salir) echo "saliendo del programa"
			break;;
		*) echo" opcion no valida! pruebe de nuevo";;
	esac
done
