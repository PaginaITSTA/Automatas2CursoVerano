%include 'functions.asm'
SECTION .data

a	db 	10h
b	db 	5h
c	db 	0h
d	db 	'resultado2'
e	db 	'true'
f	db 	'null'
impresion15      db      'C Es Mayor ', 0Ah, 0h
impresion16      db      'Solo una impresion ', 0Ah, 0h

SECTION .text
global  _start
 
_start:
 
	mov 	eax, 3
	mov 	ebx, 14

	mul 	ebx
	call 	iprintLF

	mov 	ebx, 3
	mov 	ecx, eax
	mov 	eax, ecx

	add 	eax, ebx
	call 	iprintLF

	mov 	eax, 2
	mov 	ebx, 3

	mul 	ebx
	call 	iprintLF

	mov 	ebx, c
;	mov 	eax, eax

	add 	eax, ebx
	call 	iprintLF

	mov 	eax, 10
	mov 	ebx, b

	mul 	ebx
	call 	iprintLF

	mov 	ebx, a
	mov 	ecx, eax
	mov 	eax, ecx

	add 	eax, ebx
	call 	iprintLF



	mov 	eax, impresion15
	call	 sprint


	mov 	eax, impresion16
	call	 sprint



	call 	quit
