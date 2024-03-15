.section .data
br_max = 10
broj = 5,325462
br: .fill br_max, 1, 0
error: .long 0
.section .text
.globl main

main:
 movl $broj, %eax         #broj     
 leal br, %edi               #adresa
 movl $10, %ebx              #baza
 movl $100000000, %ecx       #konstanta skaliranja
 
 movl $0, %edx
 movl $br_max, %esi
 
 cmpl $0, %eax
 jns nula
 
 movl $'-', (%edi)
 incl %edi
 decl %esi
 negl %eax
 
nula:
 cmpl $4, %esi
 jl greska
 
 movb $'0', (%edi)
 movb $'.', 1(%edi)
 incl %edi
 decl %esi

petlja:
 decl %esi
 cmpl $1, %esi
 je krajj

 mull %ebx
 divl %ecx
 addb $'0', %al
 movb %al, (%edi)
 incl %edi

 movl %edx, %eax
 cmpl $0, %eax
 jne petlja

krajj:
 movb $0, (%edi)
 jmp kraj

greska:
 incb error

kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
