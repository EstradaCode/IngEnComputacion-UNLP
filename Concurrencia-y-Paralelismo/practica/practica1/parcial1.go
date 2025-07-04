package main

import (
	"fmt"
	"sync"
)

const (
	P = 20 // Personas
	E = 10 // Entradas
)

var (
	mu              sync.Mutex
	fila            []int
	entradaPersonas = make([]int, P) // -1: no atendido, 0: sin entrada, >0: nro entrada
	totalEntradas   = E

	condFila        = sync.NewCond(&mu)
	esperaCondicion = make([]*sync.Cond, P)
)

func persona(id int, wg *sync.WaitGroup) {
	defer wg.Done()

	mu.Lock()
	fila = append(fila, id)
	condFila.Signal()
	mu.Unlock()

	mu.Lock()
	for entradaPersonas[id] == -1 {
		esperaCondicion[id].Wait()
	}
	val := entradaPersonas[id]
	mu.Unlock()

	if val > 0 {
		fmt.Printf("Persona %2d consiguió entrada número %2d \n", id, val)
	} else {
		fmt.Printf("Persona %2d no consiguió entrada :( \n", id)
	}
}

func boleteria(id int) {
	for {
		mu.Lock()

		for len(fila) == 0 {
			condFila.Wait()
		}

		idPersona := fila[0]
		fila = fila[1:]

		if totalEntradas > 0 {
			entradaPersonas[idPersona] = totalEntradas
			totalEntradas--
		} else {
			entradaPersonas[idPersona] = 0
		}

		esperaCondicion[idPersona].Signal()
		mu.Unlock()
	}
}

func main() {
	var wg sync.WaitGroup

	for i := 0; i < P; i++ {
		entradaPersonas[i] = -1
		esperaCondicion[i] = sync.NewCond(&mu)
	}

	for i := 0; i < 5; i++ {
		go boleteria(i)
	}

	for i := 0; i < P; i++ {
		wg.Add(1)
		go persona(i, &wg)
	}

	wg.Wait()
}
