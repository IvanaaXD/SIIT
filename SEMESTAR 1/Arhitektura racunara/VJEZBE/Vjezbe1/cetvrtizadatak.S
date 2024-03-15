#program racuna faktorijel broja n
#Ivana Radovanovic, SV 23/2022
.section .data
.section .text
.globl main

prvi: .long 5         #promjenljiva 5

main:
 movl prvi, %eax
 movl prvi, %ebx
 movl $0, %ecx
 subl $1, %eax
 
mnozenje:
 addl %eax, %ecx
 subl $1, %ebx
 je faktorijel
 jmp mnozenje
 
faktorijel:
 subl $1, %eax
 je kraj
 movl %ecx, %ebx      #vraca se rezultat iz ecx u ebx
 movl $0, %ecx
 jmp mnozenje
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
