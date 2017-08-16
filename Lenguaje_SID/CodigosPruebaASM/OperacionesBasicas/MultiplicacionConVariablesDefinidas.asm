%include 'functions.asm'

SECTION .text
global _start

_start:

;-------------------------Resive el valor 1
	mov 	eax, 3
;-------------------------Resive el valor 2
	mov 	ebx, 2
;-------------------------Hace multiplicacion
	mul		ebx

;-------------------------Imprime resultado
	call 		iprintLF

;-------------------------Cierra el programa, importante
call quit






