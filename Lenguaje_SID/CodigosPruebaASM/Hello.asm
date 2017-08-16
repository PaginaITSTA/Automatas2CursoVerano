%include 'funciones.asm'
SECTION .data

 msgSal      db      'Hola mundo en SID ', 0Ah, 0h ;
 
SECTION .text
global  _start
 
_start:
 
    mov     eax, msgSal
    call    sprint

    call    salir
