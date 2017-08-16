%include 'functions.asm'
SECTION .text
global _start

_start:
	mov 	eax, 90

	mov 	ecx, eax

	mov 	eax, ecx
	mov 	ebx, 9
	div		ebx
	call 		iprintLF

	call 		quit
