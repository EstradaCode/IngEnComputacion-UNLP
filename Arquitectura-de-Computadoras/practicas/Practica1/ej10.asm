;SWAP DW

ORG 1000H
NUM1 DW 0FFFFH
NUM2 DW 0450H

ORG 3000H
SWAP_DW: PUSH BX; DE REUTILIZACION
MOV BX, SP 
ADD BX, 4 ; 
PUSH AX; DE REUTILIZACION
PUSH CX
MOV AX, [BX] ; le da el valor de LA DIR DE NUM2
ADD BX, 2
MOV BX, [BX]; LA DIR DE NUM1
CAMBIAR: MOV CX, [BX] ; NUM1
PUSH AX; DIR NUM2 GUARDADA
PUSH BX; DIRECCION NUM1 GUARDADA
MOV BX, AX; MOVERME A DIR NUM2
MOV AX, [BX]; SACO DATO NUM2
POP BX
MOV [BX], AX; PONE NUM 2 EN DIR NUM 1
POP AX
MOV BX, AX; DIR DE NUM2
MOV [BX], CX
FIN: POP CX
POP AX
POP BX 
RET

ORG 2000H
; LETRA POR VALOR 
; CADENA POR REF TODO PILA
MOV BX, OFFSET NUM1
PUSH BX
ADD BX, 2
PUSH BX
CALL SWAP_DW
POP BX
POP BX
HLT 
END

