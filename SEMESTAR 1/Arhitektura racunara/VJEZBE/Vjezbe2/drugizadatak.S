#program mnozi dva broja
#Ivana Radovanovic, SV 23/2022
.section .data
a: .quad 14
b: .quad 5
.section .text
.globl main

main:
 movl a, %eax
 movl b, %ebx
 movl $0, %ecx
 
 cmpl %eax, %ebx           #poredi
 jb zamjena                #ako je a vece od b
 jmp mnozenje
 
zamjena:
 xchgl %eax, %ebx 
 jmp mnozenje               #ako je b vece od a

mnozenje:
 addl %ebx, %ecx
 subl $1, %eax
 je kraj
 jmp mnozenje
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
