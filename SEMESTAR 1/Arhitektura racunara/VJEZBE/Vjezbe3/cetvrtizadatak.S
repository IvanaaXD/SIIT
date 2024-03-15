#program sortira niz
#Ivana Radovanovic, SV 23/2022
.section .data
niz: .long 2,5,14,8,3,-24,11,6,-9,150
c = 10
.section .text
.globl main

main:
 movl $niz, %ebx
 movl $c, %esi

vanjska_petlja:
 decl %esi
 jz kraj
 movl $c, %edi
 
unutrasnja_petlja:
 decl %edi
 jz vanjska_petlja
 
 movl (%ebx, %edi, 4), %eax
 movl -4(%ebx, %edi, 4), %edx
 cmpl %edx, %eax
 jnl unutrasnja_petlja
 
zamjena:
 movl %eax, -4(%ebx, %edi, 4)
 movl %edx, (%ebx, %edi, 4)
 jmp unutrasnja_petlja
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
