.section .data
br_max = 10
br: .fill br_max,1,0
error: .long 0
.section .text
.globl main

main:
 movl $3750000001, %eax      #broj
 leal br, %edi              #adresa
 movl $10, %ebx             #baza
 
 movl $br_max, %ecx
 
petlja:
 decl %ecx
 cmpl $1, %ecx
 jl greska
 
 movl $0, %edx
 divl %ebx
 addb $'0', %dl
 movb %dl, (%edi)
 incl %edi
 
 cmpl $0, %eax
 jne petlja
 
 movl $0, (%edi)
 decl %edi
 leal br, %esi
 
obrni:
 cmpl %edi, %esi
 jae kraj
 movb (%edi), %ah
 movb (%esi), %al
 movb %ah, (%esi)
 movb %al, (%edi)
 incl %esi
 decl %edi
 jmp obrni
 
greska:
 incb error

kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
