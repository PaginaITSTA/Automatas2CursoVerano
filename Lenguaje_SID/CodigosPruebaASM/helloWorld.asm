%include 'funciones.asm'
SECTION .data
msgHelloWorld db	 'Hola mundo en ASMS', 0h

SECTION .text

global _start

_start:

hello:
mov eax, msgHelloWorld
call	sprintLF

call	salir
