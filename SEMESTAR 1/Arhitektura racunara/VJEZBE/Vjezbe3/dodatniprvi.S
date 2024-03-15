#program odredjuje aritmeticku sredinu niza
#Ivana Radovanovic, SV 23/2022
.section .data
niz: .long 4, 62, 5, 24, 93, 15, 45
brojac: .long 7
.section .text
.globl main

main:
 movl $0, %esi
 movl $0, %eax
 
petlja: 
 addl niz(, %esi, 4), %eax
 incl %esi
 subl $1, brojac
 cmpl $0, brojac
 je dijeljenje
 jmp petlja
 
dijeljenje:
 movl $0, %edx
 movl $7, %ecx
 divl %ecx
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
