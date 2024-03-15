#program pretvara dekadne cijele brojeve u znakovni oblik
#Ivana Radovanovic, SV 23/2022
.section .data
br_max = 10
br: .fill br_max,1,0
.section .text
.globl main

main:
 movl $375000000, %eax            #broj
 leal br, %edi
 movl $10, %ebx                   #baza
 
petlja:
 movl $0, %edx
 divl %ebx
 addb $'0', %dl
 movb %dl, (%edi)
 incl %edi
 
 cmpl $0, %eax
 jne petlja
 
 movb $0, (%edi)                  #NULL
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
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
