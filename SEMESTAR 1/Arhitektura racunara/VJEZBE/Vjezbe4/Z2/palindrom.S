#program
#Ivana Radovanovic, SV 23/2022
.section .data
poruka: .ascii "Unesite string:\0"
poruka_len = . - poruka
nijep:  .ascii "String nije palindrom.\n\0"
nijep_len = . - nijep
jestep: .ascii "String je palindrom.\n\0"
jestep_len = . - jestep
unos:   .fill 50,1,42
unos_max = 50
string: .fill 50,1,42
brojac: .long 0
palindrom_b: .long 0
.section .text
.globl main

main:
 movl $4, %eax
 movl $1, %ebx
 leal poruka, %ecx
 movl $poruka_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal unos, %ecx
 movl $unos_max, %edx
 int $0x80
 
 movl %eax, brojac
 movl $0, %esi
 movl $0, %edi
 
petlja:
 movb unos(,%esi,1), %bl
 cmpb $'\n', %bl
 je pal

 cmpb $32, %bl
 jne ispituj_dalje
 incl %esi
 jmp petlja
 
ispituj_dalje:
 cmpb $65, %bl
 jae veliko_slovo
 addb %bl, string(,%edi,1)
 incl %edi
 incl %esi
 jmp petlja
 
veliko_slovo:
 cmpb $90, %bl
 jbe zamjena
 addb %bl, string(,%edi,1)
 incl %edi
 incl %esi
 jmp petlja
 
zamjena:
 addb $32, %bl
 addb %bl, string(,%edi,1)
 incl %edi
 incl %esi
 jmp petlja 
 
pal:
 movl $0, %esi
 decl %edi
 
palindrom:
 cmpl %edi, %esi
 jae jeste_palindrom
 movb unos(,%esi,1), %bl
 movb unos(,%edi,1), %bh
 cmpb %bh, %bl
 jne nije_palindrom
 incl %esi
 decl %edi
 jmp palindrom
 
nije_palindrom:
 movl $4, %eax
 movl $1, %ebx
 leal nijep, %ecx
 movl $nijep_len, %edx
 int $0x80
 movl $1, %ebx
 jmp kraj
 
jeste_palindrom:
 movl $4, %eax
 movl $1, %ebx
 leal jestep, %ecx
 movl $jestep_len, %edx
 int $0x80
 movl $0, %ebx
 jmp kraj

kraj:
 movl $1, %eax
 int $0x80

