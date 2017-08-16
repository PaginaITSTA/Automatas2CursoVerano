%include 'funciones.asm'

SECTION .data

 msgSal      db      'Hola mundo', 0Ah, 0h ;
 
SECTION .text
global  _start
 
_start:
 
    mov     eax, msgSal
    call    sprint

    call    salir
