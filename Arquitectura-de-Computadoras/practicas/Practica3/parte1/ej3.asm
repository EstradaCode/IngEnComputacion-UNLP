org 1000h
EOI EQU 20H
IMR EQU 21H
INT0 EQU 24H
N_F10 EQU 15
ORG 60; 15*4
IP_F10 DW RUT_F10 ; DEBE SER COLOCADA DENTRO DEL VECTOR DE INTS

ORG 3000H
RUT_F10: PUSH AX
INC DX
MOV AL, EOI
OUT EOI, AL
POP AX
IRET

ORG 2000H
CLI; DESHABILITA INTERRUPCIONES MIENTRAS SE CONF PIC
MOV AL, 0FEH; AL _> 11111110
OUT IMR, AL ; CARGO LA CONFIGURACION DE LOS PUERTOS A IMR
MOV AL, N_F10 ; LE CARGO EL DATO AL INT 0
OUT INT0, AL; UNA VEZ COLOCADO EL NUMERO AL INT0, CADA VEZ QUE SURGA LA INTERRUPCION, IRA A LA DIRECCION 15*4
MOV DX, 0
STI; HABILITADAS
LAZO:JMP LAZO ; BUCLE INFINITO EN EL QUE SE QUEDA ESPERANDO
END
; CADA VEZ QUE SE OPRIME F10 SE LLEVA A RUT_F10 
