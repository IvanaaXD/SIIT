#program poređenja brojeva u dvostrukoj preciznosti
#Ivana Radovanovic, SV 23/2022
.section .data
a: .quad 152
b: .quad 15
greska: .byte 0
.section .text
.globl main

main:
 movb $0, greska
 
 movl a+4, %ebx
 cmpl b+4, %ebx  
 jl manje
 jg vece
 je jednako
 
jednako:
 movl a, %ecx
 cmpl b, %ecx
 jl manje
 jg vece
 je nula    

manje:                          #drugi manji od prvog
 movl $-1, %eax
 jmp kraj             
             
nula:                           #jednaki su
 movl $0, %eax  
 jmp kraj
 
vece:                           #drugi veci od prvog
 movl $1, %eax
 jmp kraj
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
