#program
#Ivana Radovanovic, SV 23/2022
.section .data
.section .text
.globl main

main:

 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
