#program
#Ivana Radovanovic, SV 23/2022
.section .data
b: .quad 547657476
c: .quad 654654
a: .quad 0
k: .quad 0
r: .quad 0
greska: .byte 0
.section .text
.globl main

main:
 movl b, %eax
 movl b+4, %ebx
 
 addl %ebx, %ebx
 adcl %eax, %eax
 
 movl c, %ecx
 movl c+4, %edx
 
dijeli:
 cmpl $3, %edx             #a ? b
 jg dijeljenje
 cmpl $3, %ecx
 jb krajj

dijeljenje:
 subl $3, %ecx
 sbbl $3, %edx
 addl $1, k
 adcl $0, k+4
 jmp dijeli
 
nula:
 incl greska
 
krajj:
 addl k+4, %ebx
 adcl k, %eax
 jmp kraj
 
kraj:
 movl %ebx, a+4
 movl %eax, a
 movl $1, %eax
 movl $0, %ebx
 int $0x80
