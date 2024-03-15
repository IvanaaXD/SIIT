#peogram sabira prvih n prirodnih brojeva
#autor: Ivana Radovanovic, SV 23/2022
.section .data
.section .text
.globl main

main:
 movl $5, %eax
 movl $5, %ebx
 
petlja:
 subl $1, %eax
 je kraj
 addl %eax, %ebx
 jmp petlja

kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
