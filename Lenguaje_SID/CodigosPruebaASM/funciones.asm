;-----------------------------
;int slen(String mensaje)
;Tamaño de la cadena calcula función
slen:
push ebx
mov ebx , eax
;-----------------------------


nextChar:
cmp  byte[eax], 0
jz finished
inc eax
jmp nextChar

finished:
sub eax, ebx
pop ebx
ret

;-----------------------------
;void string (string mensaje)
;Cadena imprime función 
sprint:
push edx
push ecx
push ebx
push eax
call slen

mov edx, eax
pop eax

mov ecx, eax 
mov ebx, 1
mov eax, 4
int 80h

pop ebx
pop ecx
pop edx
ret

;-----------------------------
;void Salida()
;Exit program and restore resources
salir:
mov ebx, 0
mov eax, 1
int 80h
ret

