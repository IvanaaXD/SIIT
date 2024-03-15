#program računa n-ti član Fibonačijevof niza
#Ivana Radovanovic, SV 23/2022
.section .data
a: .long 5
.section .text
.globl main

main:
 movl $1, %eax
 movl $1, %ebx
 movl a, %esi
 movl $2, %ecx
 
n:
 cmpl %esi, %ecx
 jae kraj
 movl %eax, %edx
 addl %ebx, %edx
 movl %ebx, %eax
 movl %edx, %ebx
 addl $1, %ecx
 jmp n
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
