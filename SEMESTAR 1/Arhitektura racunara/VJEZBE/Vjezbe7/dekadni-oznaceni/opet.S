.section .data
br_max = 10
br: .fill br_max,1,0
error: .long 0
negativni: .long 0
.section .text
.globl main

main:
 movl $-7841, %eax              #broj
 leal br, %edi                  #string
 movl $10, %ebx                 #baza            
 
 cmpl $0, %eax
 jge petlja
 
 incl negativni 
 decl %edi
 negl %eax

petlja:
 decl %edi
 cmpl $1, %edi
 jl greska

 movl $0, %edx
 divl %ebx
 addb $'0', %dl
 movb %dl, (%edi)
 incl %edi

 cmpl $0, %eax
 jne petlja

 movl negativni, %edx
 cmpl $1, %edx
 jne plus
 
 movb $'-', (%edi)
 incl %edi
 movl $0, (%edi)
 decl %edi
 leal br, %esi
 jmp obrni
 
plus:
 movl $0, (%edi)
 decl %edi
 leal br, %esi

obrni:
 cmpl %edi, %esi
 jae kraj
 movb (%esi), %ah
 movb (%edi), %al
 movb %al, (%esi)
 movb %ah, (%edi)
 incl %esi
 decl %edi
 jmp obrni

greska:
 incl error

kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
