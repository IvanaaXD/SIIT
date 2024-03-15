#program racuna nzd dva broja
#Ivana Radovanovic, SV 23/2022
.section .data
a: .long 12
b: .long 36
.section .text
.globl main

main:
 movl a, %eax
 movl b, %ebx
 
poredjenje:
 cmpl %eax, %ebx
 jl manji
 ja veci
 je kraj
 
manji:
 subl %ebx, %eax
 jmp poredjenje
 
veci: 
 subl %eax, %ebx
 jmp poredjenje
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
