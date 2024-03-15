#program racuna n-ti stepen broja b
#Ivana Radovanovic, SV 23/2022
.section .data
b: .quad 120
n: .long 4
rezultat: .quad 0
brojac: .quad 0
greska: .byte 0
.section .text
.globl main

main:
 movl b, %eax
 movl b+4, %ebx
 
stepen:
 subl $1, n
 cmpl $0, n
 je kraj
 
 movl %eax, brojac
 movl %ebx, brojac+4
 
petlja:
 addl %ebx, rezultat+4
 adcl %eax, rezultat
 jc prenos
 subl $1, brojac+4
 sbbl $0, brojac
 cmpl $0, brojac
 je poredjenje
 
poredjenje:
 cmpl $0, brojac+4
 je stepen
 jne petlja
  
prenos:
 incb greska 
 
kraj:
 movl rezultat+4, %ecx
 movl rezultat, %edx
 movl $1, %eax
 movl $0, %ebx
 int $0x80
