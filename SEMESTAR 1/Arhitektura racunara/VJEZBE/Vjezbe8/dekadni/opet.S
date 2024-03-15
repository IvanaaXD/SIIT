.section .data
br: .ascii "25"
error: .byte 0
.section .text
.globl main

main:
 leal br, %esi             #broj
 movb $0, error            #greska
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
 incb error

kraj:
 movl $1, %eax
 movl $0, %evx
 int $0x80
