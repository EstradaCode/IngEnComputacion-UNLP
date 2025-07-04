package main

import (
	"fmt"
	"sync"
)

func ej2() {
	const M = 10
	array := []int{1, 2, 3, 2, 4, 2, 5, 2, 6, 2}
	N := 2

	var Total int
	var mu sync.Mutex
	var wg sync.WaitGroup

	for i := 0; i < M; i++ {
		wg.Add(1)
		go func(i int) {
			defer wg.Done()
			if array[i] == N {
				mu.Lock()
				Total++
				mu.Unlock()
			}
		}(i)
	}

	wg.Wait()
	fmt.Printf("El número %d aparece %d veces en el arreglo.\n", N, Total)
}
