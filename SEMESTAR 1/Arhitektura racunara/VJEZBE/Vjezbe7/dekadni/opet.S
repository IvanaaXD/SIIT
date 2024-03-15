#program pretvara dekadne cijele brojeve u znakovni oblik
#Ivana Radovanovic, SV 23/2022
.section .data
br_max = 10
br: .fill br_max,1,0
greska: .byte 0
.section .text
.globl main

main:
 movl $3756, %eax          #broj
 leal br, %edi             #adresa
 movl $10, %ecx            #baza
 movl $br_max, %esi
 
petlja:
 decl %esi
 cmpl $1, %esi
 jl error
 
 movl $0, %edx
 divl %ecx
 addb $'0', %dl
 movb %dl, (%edi)
 incl %edi
 
 cmpl $0, %eax
 jne petlja

 movl $0, (%edi)
 incl %edi
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

error:
 incb greska

kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
