ORG 1000H
CONT EQU 10H
COMP EQU 11H
EOI EQU 20H
IMR EQU 21H
INT0 EQU 24H; F10
INT1 EQU 25H; TIMER
INT2 EQU 26H; HANDSHAKE

PA EQU 30H; LLAVES
PB EQU 31H; LEDS
CA EQU 32H
CB EQU 33H
; PA, CA LLAVE
; PB, BC LUCES
; IN AL, 40H LEER DATO QUE ESTA EN MEM E/S
; OUT 40H, AL ESCRIBIR EN MEMORIA E/S EL DATO DE AL 
HAND_DATA EQU 40H
HAND_STATUS EQU 41H

ESTADOLLAVE DB ?
FLAG DB 0
ORG 40
DW TIME_OUT

ORG 3000H
TIME_OUT: OUT PB, AL; AL TIENE 0
          MOV FLAG,1
I_STOP: MOV AL, 20H
        OUT EOI, AL
        IRET


LEDSX12: PUSH BX; REUTILIZACION
         MOV BX, SP
         PUSH AX; REUTILIZACION
         ADD BX, 4
         MOV BX, [BX]
         MOV AL, BYTE PTR[BX]; AL TIENE DATO DE OFFSET
  IF:    AND AL, 10000000B; SI DA 0 SIGNIFICA QUE EN AL EL BIT 7 ESTABA APAGADO
         JZ FIN
  THEN:  ; CONFIGURO PIC
          CLI; CONFIGURO PIC Y PIO 

          MOV AL, 0FDH
          OUT IMR, AL; HABILITO INT TIMER
          
          MOV AL, 10
          OUT INT1, AL; LE DOY UN ID PARA EL VEC DE INTERRUPCION
          
          ; LE DARÉ VALORES AL TIMER
          MOV AL, 12
          OUT COMP, AL
          
          
          MOV AL, 0FFH; TODOS LOS BITS PRENDIDOS
          OUT PB, AL  
          
          MOV AL, 0
          OUT CONT, AL
          
          STI
    LAZO: CMP FLAG,1
          JNZ LAZO
    FIN: POP AX
         POP BX
         RET
ORG 2000H
; CONFIGURO PIO 
MOV AL, 0FFH
OUT CA, AL; LLAVES RECIBEN ENTRADA

MOV AL, 0
OUT CB, AL; LEDS SON SALIDAS
;
IN AL, PA; LE DOY A AL LAS 
MOV ESTADOLLAVE, AL
MOV BX, OFFSET ESTADOLLAVE
PUSH BX
CALL LEDSX12
POP BX 
INT 0
END
