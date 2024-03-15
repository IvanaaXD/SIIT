#program racuna faktorijel broja n
#Ivana Radovanovic, SV 23/2022
.section .data
n: .quad 5
.section .text
.globl main

main:
 movl n, %eax
 movl $0, %ecx
 subl $1, %eax
 
mnozenje:
 addl %eax, %ecx
 subl $1, n
 je faktorijel
 jmp mnozenje
 
faktorijel:
 subl $1, %eax
 je kraj
 movl %ecx, n      #vraca se rezultat iz ecx u n
 movl $0, %ecx
 jmp mnozenje
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
