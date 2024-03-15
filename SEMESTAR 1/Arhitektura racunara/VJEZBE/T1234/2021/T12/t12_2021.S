#program
#Ivana Radovanovic, SV 23/2022
.section .data
poruka1: .ascii "Unesite string: \0"
poruka1_len = . - poruka1
unos_max = 50
unos: .fill unos_max, 1, 42
minn: .byte 0
maxx: .byte 0
n: .long 0
.section .text
.globl main

main:
 movl $4, %eax
 movl $1, %ebx
 leal poruka1, %ecx
 movl $poruka1_len, %edx
 int $0x80

 movl $3, %eax
 movl $0, %ebx
 leal unos, %ecx
 movl $unos_max, %edx
 int $0x80
 
 subl $1, %eax
 movl $0, %ecx
 movl $0, %esi
 
 movb $48, maxx
 movb $57, minn
 
petlja:
 cmpl %eax, %esi
 je sabiranje
 jne provjera1
 
provjera1:
 movb unos(,%esi,1), %bh
 cmpb $48, %bh
 jae provjera2
 incl %esi
 incl %ecx
 cmpl %ecx, %eax
 je nula
 jmp petlja
 
provjera2:
 cmpb $57, %bh
 jbe minmax
 incl %esi
 incl %ecx
 cmpl %ecx, %eax
 je nula
 jmp petlja
 
minmax:
 cmpb minn, %bh
 jb minimalni
 cmpb maxx, %bh
 ja maksimalni
 incl %esi
 jmp petlja
 
minimalni:
 movb %bh, minn
 incl %esi
 jmp petlja 
 
maksimalni:
 movb %bh, maxx
 incl %esi
 jmp petlja
 
sabiranje:
 movl $0, %ebx
 movl minn, %ebx
 addl maxx, %ebx
 jmp kraj
 
nula:
 movl $0, %ebx

kraj:
 movl $1, %eax
 int $0x80
