#include <Arduino_FreeRTOS.h>
#include <Arduino.h>
#include <semphr.h>

// Secuencia combinada: A (1,3,2) + B (2,2,3,1) + C (3,3,3,1,2) — 12 pasos, se repite indefinidamente
const uint8_t secuencia[] = {1, 3, 2,  2, 2, 3, 1,  3, 3, 3, 1, 2};
const uint8_t LARGO_SECUENCIA = sizeof(secuencia) / sizeof(secuencia[0]);
volatile uint8_t indice = 0;

SemaphoreHandle_t semTarea1;
SemaphoreHandle_t semTarea2;
SemaphoreHandle_t semTarea3;

void avanzar()
{
    indice = (indice + 1) % LARGO_SECUENCIA;
    switch (secuencia[indice])
    {
        case 1: xSemaphoreGive(semTarea1); break;
        case 2: xSemaphoreGive(semTarea2); break;
        case 3: xSemaphoreGive(semTarea3); break;
    }
}

void Tarea1(void *pvParameters)
{
    for (;;)
    {
        xSemaphoreTake(semTarea1, portMAX_DELAY);
        Serial.println("Tarea 1");
        avanzar();
    }
}

void Tarea2(void *pvParameters)
{
    for (;;)
    {
        xSemaphoreTake(semTarea2, portMAX_DELAY);
        Serial.println("Tarea 2");
        avanzar();
    }
}

void Tarea3(void *pvParameters)
{
    for (;;)
    {
        xSemaphoreTake(semTarea3, portMAX_DELAY);
        Serial.println("Tarea 3");
        avanzar();
    }
}

void setup()
{
    Serial.begin(9600);

    semTarea1 = xSemaphoreCreateBinary();
    semTarea2 = xSemaphoreCreateBinary();
    semTarea3 = xSemaphoreCreateBinary();

    xTaskCreate(Tarea1, "T1", 128, NULL, 1, NULL);
    xTaskCreate(Tarea2, "T2", 128, NULL, 1, NULL);
    xTaskCreate(Tarea3, "T3", 128, NULL, 1, NULL);

    // Arranca la secuencia en el índice 0 (Tarea 1), sin pasar por avanzar()
    xSemaphoreGive(semTarea1);
}

void loop() {}