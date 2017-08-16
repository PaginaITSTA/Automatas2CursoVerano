%include 'functions.asm'
SECTION .data

	msgEntrada db ': ', 0h

SECTION .bss
sinput: resb 1

SECTION .text
global _start

_start:

valor1: 
;-------------------------------Aqui solo se esta resiviendo el primer valor
	mov eax, msgEntrada
	call sprint
	mov edx, 255
	mov ecx, sinput
	mov ebx, 0
	mov eax, 3
	int 80h

;-------------------------------Moviendo el valor con las funciones de functions
	mov eax, sinput 
	call atoi
	push eax

;-------------------------------Aqui solo se esta resiviendo el segundo valor
	mov eax, msgEntrada
	call sprint 
	mov edx, 255
	mov ecx, sinput
	mov ebx, 0
	mov eax, 3
	int 80h

;-------------------------------Moviendo el valor con las funciones de functions
	mov eax, sinput
	call atoi

;-------------------------------Multiplicacion
	pop ebx
	mul ebx

;-------------------------------Recuerda el valor que multiplico
	push eax

;-------------------------------ImpresionResultado
	pop eax
	call iprintLF

;-------------------------------Salir
call quit






