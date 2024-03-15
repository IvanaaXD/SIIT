.section .data
br_max = 10
br: .fill br_max,1,0
error: .long 0
negativni: .long 0
.section .text
.globl main

main:
 movl $-75, %eax               #broj
 leal br, %edi                 #adresa
 movl $10, %ebx
 
 cmpl $0, %eax
 jge petlja
 
minus:
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
 
 movl negativni, %esi
 cmpl $1, %esi
 jne obrnii
 
 movl $'-', (%edi)
 incl %edi
 movl $0, (%edi)
 decl %edi
 leal br, %esi
 jmp obrni
 
obrnii:
 movl $0, (%edi)
 decl %edi
 leal br, %esi

obrni:
 cmpl %edi, %esi
 jae kraj
 movb (%edi), %ah
 movb (%esi), %al
 movb %al, (%edi)
 movb %ah, (%esi)
 incl %esi
 decl %edi
 jmp obrni

greska:
 incb error

kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
