%include 'functions.asm'

SECTION .text
global _start

_start:

valor1: 
;-------------------------------Aqui solo se asigna un valor
	mov ecx, 3
	mov ebx, 0
	mov eax, 3
	int 80h

;-------------------------------Moviendo el valor con las funciones de functions
	mov eax, 3 
	call atoi
	push eax

;-------------------------------Aqui solo se asigna un valor
	mov ecx, 2
	mov ebx, 0
	mov eax, 3
	int 80h

;-------------------------------Moviendo el valor con las funciones de functions
	mov eax, 2
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






