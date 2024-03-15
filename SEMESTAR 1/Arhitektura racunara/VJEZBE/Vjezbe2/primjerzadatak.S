#program sabiranje brojeva u dvostrukoj preciznosti
#Ivana Radovanovic, SV 23/2022
.section .data
a: .quad 152
b: .quad 15
greska: .byte 0
.section .text
.globl main

main:
 movb $0, greska
 movl a, %eax
 addl b, %eax                    #sabiranje nizih 
 movl a+4, %ebx
 adcl b+4, %ebx                  #sabiranje visih plus prenos sabiranja nizih
 jnc kraj                        #skoci na kraj ako nema prenosa sabiranja visih
 incb greska                     #increase with 1, ako ima prenosa sabiranja visih greska

kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
