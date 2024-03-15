#program oduzima dva broja dvostruke preciznosti
#Ivana Radovanovic, SV 23/2022
.section .data
a: .quad 3578347657365476587
b: .quad 54365476486687
greska: .byte 0
.section .text
.globl main

main:
 movl a, %eax
 movl a+4, %ebx
 
 subl b+4, %ebx
 sbbl b, %eax
 jc prenos
 
prenos:
 incb greska
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
