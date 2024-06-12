; 2 subrutinas ingreso de un num entero de un dig (verficar)
;  muestra
.data
num: .word 0
cero: .asciiz "cero"
uno: .asciiz "uno"
dos: .asciiz "dos"
tres: .asciiz "tres"
cuatro: .asciiz "cuatro"
cinco: .asciiz "cinco"
seis: .asciiz "seis"
siete: .asciiz "siete"
ocho: .asciiz "ocho"
nueve: .asciiz "nueve"


; necesita recibir $s0 con control
; y devuelve $v0 el valor de data en $s1
;ingreso:
; $a0 tiene el elemento para la  
muestra: daddi $t0,$0, 4
daddi $t1,$0, $a0
dmul $t1,$t1,8 ; 2*8 ej 1*8 es el desplazamiento 
daddi $t1,$t1, cero
sd $t1, 0($s1) ; mando la direccion para data
sd $t0, 0($s0); mando orden a control
jr $ra