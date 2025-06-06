#include <stdio.h>
#include <stdint.h>

int main() {
    int16_t q7_8;
    printf("Ingrese n�mero Q(7,8) en hexadecimal (ej: 80C0): 0x");
    scanf("%hx", (unsigned short*)&q7_8);

    // Parte entera: hacer shift aritm�tico (preserva el signo)
    int16_t parte_entera = q7_8 >> 8;

    // Parte fraccionaria cruda (los bits bajos, sin signo)
    uint8_t parte_frac_bits = q7_8 & 0xFF;

    // Si el n�mero es negativo y tiene fracci�n, hay que corregir
    int negativo = (q7_8 < 0);
    int parte_frac;

    if (negativo) {
        // Si es negativo, la parte fraccional est� en complemento a dos tambi�n.
        // Para obtener el valor decimal correcto, hay que hacer:
        // - ajustar la parte entera (ya es -N-1)
        // - invertir la fracci�n (10000 - fracci�n)
        parte_frac = ((256 - parte_frac_bits) * 10000) / 256;

        // Si la fracci�n es exactamente 0, no se invierte
        if (parte_frac_bits == 0) {
            parte_frac = 0;
        } else {
            parte_entera += 1;  // porque estaba redondeada hacia abajo
        }
    } else {
        parte_frac = (parte_frac_bits * 10000) / 256;
    }

    printf("N�mero decimal: %d.%04d\n", parte_entera, parte_frac);
    return 0;
}
