ORG 1000H
TABLAA db 1,2,5,4,7
TABLAB db 2,3,4,5,7

ORG 3000H
CHANGE: CMP al, [bx]
JS THEN
jmp fin
THEN: MOV [bx],al
fin:ret
;CONSULTAR SOBRE LOS DATOS DENTRO DE LOS REGISTROS AL SALIR DE LA SUBRUTINA Y COMO DEVOLVER UN VALOR


ORG 2000H
mov al, TABLAB
mov ah, offset TABLAB- OFFSET TABLAA
mov bx, OFFSET TABLAA
WHILE: CMP BX, OFFSET TABLAB
JZ FINAL
DOES: call CHANGE
INC BX
ADD BL, AH
MOV AL, [BX]
SUB BL,AH
JMP WHILE
FINAL: HLT
END