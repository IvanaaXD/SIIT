#program mnozenje dva broja preko sabiranja dvostruka preciznost
#Ivana Radovanovic, SV 23/2022
.section .data
a: .quad 17
b: .quad 25
r: .quad 0
greska: .byte 0
.section .text
.globl main

main:
 movl a, %eax
 movl a+4, %ebx
 
poredi:
 cmpl $0, a
 jne mnozi
 cmpl $0, a+4
 je kraj

mnozi:
 addl %eax, r
 adcl %ebx, r+4
 jc prenos
 subl $1, b
 sbbl $0, b+4
 jmp poredi
 
prenos:
 incb greska

kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
