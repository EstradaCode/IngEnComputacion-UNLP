A----

ORG 1000h
EOI EQU 20H
IMR EQU 21H
INT0 EQU 24H
INT1 EQU 25H

CONT EQU 10H
COMP EQU 11H
ORG 40
DIR_F10 DW 3000H
ORG 44
DW CUENTA_REG
ORG 1200H
STRING DB "INGRESE UN VALOR ENTRE 1 Y 9"
SALTO DB 10
FIN DB ?
NUM DB ?
FLAG DB ?


ORG 3000H
SET_WF10: MOV AL, 0FDH
OUT IMR, AL

MOV AL, 11
OUT INT1, AL

MOV AL, 1
OUT COMP, AL

MOV AL, 0 
OUT CONT, AL

ISTOP: MOV AL, 20H
OUT EOI,AL
IRET

CUENTA_REG: MOV AL, 1
            INT 7
            DEC BYTE PTR[BX]
            DEC DL
            JS ISTOP
            MOV AL, 0
            OUT CONT, AL
            JMP ISTOP
ORG 2000H
MOV AX,0
CLI
MOV BX, OFFSET STRING
MOV AL, OFFSET FIN - OFFSET STRING
 INT 7
 MOV BX, OFFSET NUM
 INT 6
 MOV DL, [BX]
 SUB DL, '0' 
MOV AL, 0FEH ; DEFINO IMR para int0 y int 1
OUT IMR, AL

MOV AL, 11
OUT INT1, AL; DEFINO VALOR DENTRO DE PIC

DEC AL; 10 
OUT INT0, AL; CORRESPONDE A LAS LLAMADAS DEL 
STI
LAZO: CMP DL,0
JNS LAZO
INT 0
END
