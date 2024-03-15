.section .data
br: .ascii "25"
greska: .byte 0
.section .text
.globl main

main:
 leal br, %esi             #adresa stringa
 movb $0, greska           #greska
 movl $0, %eax             #rezultat
 movl $0, %ebx
 movl $10, %ecx            #baza
 
petlja:
 movb (%esi), %bl
 cmpl $0, %bl
 je krajj

 subb $'0', %bl
 mull %ecx
 addl %ebx, %eax
 incl %esi
 jmp petlja

krajj:
 cmpl $br, %esi
 jne kraj

greska:
 incb greska

kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
