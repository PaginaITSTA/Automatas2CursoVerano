%include 'functions.asm'
SECTION .text
global _start

_start:
	mov		ecx, 2
	mov 	eax, 2
	add  	eax, ecx
	call 		iprintLF

	call 		quit
