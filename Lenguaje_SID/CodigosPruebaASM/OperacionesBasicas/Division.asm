%include 'functions.asm'
SECTION .text
global _start

_start:
	mov 	eax, 90
	mov 	ebx, 9
	div		ebx
	call 		iprintLF

	call 		quit
