#program
#Ivana Radovanovic, SV 23/2022
.section .data
a: .quad 20
b: .quad 4
k: .quad 0
o: .quad 0
greska: .byte 0
.section .text
.globl main

main:
 movl a, %eax
 movl a+4, %ebx
 cmpl $0, b                 #da li je dijeljenje 0
 jne dijeljenje
 cmpl $0, b+4
 je nula             

dijeli:
 cmpl b+4, %ebx             #a ? b
 ja dijeljenje
 jb kraj
 cmpl b, %eax
 jb kraj

dijeljenje:
 subl b, %eax
 sbbl b+4, %ebx
 addl $1, k
 adcl $0, k+4
 jmp dijeli
 
nula:
 incl greska
 
kraj:
 movl %eax, o
 movl %ebx, o+4
 movl $1, %eax
 movl $0, %ebx
 int $0x80
