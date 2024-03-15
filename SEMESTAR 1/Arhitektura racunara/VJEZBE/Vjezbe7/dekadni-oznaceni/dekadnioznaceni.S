#program pretvara dekadne cijele oznacene brojeve u znakovni oblik
#Ivana Radovanovic, SV 23/2022
.section .data
br_max = 10
br: .fill br_max,1,0
pomoc: .long 0
error: .long 0
.section .text
.globl main

main:
 movl $-15, %eax                  #broj
 leal br, %edi
 movl $10, %ebx                   #baza

 movl %edi, %esi
 cmpl $0, %eax
 jge petlja
 
minus:
 incl pomoc
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
 
 movl pomoc, %ecx
 cmpl $1, %ecx
 jne obrnii
 movl $'-', (%edi)
 incl %edi 
 movb $0, (%edi)                  #NULL
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
 movb (%esi), %ah
 movb (%edi), %al
 movb %al, (%esi)
 movb %ah, (%edi)
 decl %edi
 incl %esi
 jmp obrni
 
greska:
 incb error
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
