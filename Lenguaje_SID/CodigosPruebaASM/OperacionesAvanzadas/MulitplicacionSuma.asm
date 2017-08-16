%include 'functions.asm'

SECTION .text
global _start

_start:


	mov		ecx, 3
	mov 	eax, 3

	add  	eax, ecx

	mov 	ecx, eax
	mov 	ebx, 14

	mul		ebx

;-------------------------Imprime resultado
	call 		iprintLF

;-------------------------Cierra el programa, importante
call quit






