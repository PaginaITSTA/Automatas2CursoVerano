%include 'functions.asm'
SECTION .text
global _start

_start:
	mov		ebx, 2
	mov 	eax, 2
	add  	eax, ebx

	mov		ebx, eax
	mov 	eax, 2
	add  	eax, ebx

	call 		iprintLF

	call 		quit
