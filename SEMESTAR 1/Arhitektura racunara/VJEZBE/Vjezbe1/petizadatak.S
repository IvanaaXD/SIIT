#program dijeli dva broja
#Ivana Radovanovic, SV 23/2022
.section .data
.section .text
.globl main

main:
 movl $21, %eax
 movl $4, %ebx
 movl $0, %ecx
 
 cmpl %eax, %ebx           #poredi
 ja zamjena                #ako je b vece od a
 jmp dijeljenje
 
zamjena:
 xchgl %eax, %ebx 
 ja dijeljenje             #ako je a vece od b

dijeljenje:
 subl %ebx, %eax
 addl $1, %ecx
 cmpl %eax, %ebx
 ja krajj
 jmp dijeljenje
 
krajj:
 movl %eax,  %edx
 jmp kraj  

kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
