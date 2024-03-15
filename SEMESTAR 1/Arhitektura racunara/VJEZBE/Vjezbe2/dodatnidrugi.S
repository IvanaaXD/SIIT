#program ispisuje rijec
#Ivana Radovanovic, SV 23/2022
.section .data
rijec: .long 1351864166
.section .text
.globl main

main:
 movb rijec, %ah
 movb rijec+2, %al
 movb rijec+4, %bh
 movb rijec+6, %bl
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
