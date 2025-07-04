package main

import (
	"fmt"
	"sync"
	"time"
)

const (
	numRecursos = 5
	numProcesos = 10
)

// Cola simple circular para recursos
type Cola struct {
	recursos []int
	ini      int
	fin      int
	tamano   int
	cap      int
}

func NuevaCola(cap int) *Cola {
	return &Cola{
		recursos: make([]int, cap),
		ini:      0,
		fin:      0,
		tamano:   0,
		cap:      cap,
	}
}

func (c *Cola) EstaVacia() bool {
	return c.tamano == 0
}

func (c *Cola) EstaLlena() bool {
	return c.tamano == c.cap
}

func (c *Cola) Push(x int) bool {
	if c.EstaLlena() {
		return false
	}
	c.recursos[c.fin] = x
	c.fin = (c.fin + 1) % c.cap
	c.tamano++
	return true
}

func (c *Cola) Pop() (int, bool) {
	if c.EstaVacia() {
		return 0, false
	}
	val := c.recursos[c.ini]
	c.ini = (c.ini + 1) % c.cap
	c.tamano--
	return val, true
}

func usarRecurso(id int, cola *Cola, mu *sync.Mutex, cond *sync.Cond, wg *sync.WaitGroup) {
	defer wg.Done()

	mu.Lock()
	// Esperar hasta que haya un recurso disponible
	for cola.EstaVacia() {
		cond.Wait()
	}

	// Sacar recurso de la cola
	recurso, _ := cola.Pop()
	fmt.Printf("Proceso %d: obtuvo recurso %d\n", id, recurso)
	mu.Unlock()

	// Usar recurso (lo mando a dormir porque no sé que hacer acá jsdajdja)
	time.Sleep(time.Millisecond * 500)

	mu.Lock()
	// Devolver recurso a la cola
	cola.Push(recurso)
	fmt.Printf("Proceso %d: liberó recurso %d\n", id, recurso)
	cond.Signal() // Avisar que hay recurso disponible
	mu.Unlock()
}

func ej3() {
	cola := NuevaCola(numRecursos)
	for i := 1; i <= numRecursos; i++ {
		cola.Push(i)
	}

	var mu sync.Mutex
	cond := sync.NewCond(&mu)
	var wg sync.WaitGroup

	for i := 1; i <= numProcesos; i++ {
		wg.Add(1)
		go usarRecurso(i, cola, &mu, cond, &wg)
	}

	wg.Wait()
	fmt.Println("Todos los procesos terminaron")
}
