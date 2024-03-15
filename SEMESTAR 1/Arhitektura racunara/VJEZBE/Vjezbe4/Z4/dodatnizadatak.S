#program
#Ivana Radovanovic, SV 23/2022
.section .data
unos1: .ascii "Unesite prvi string: \0"
unos1_len = . - unos1
unos2: .ascii "Unesite drugi string: \0"
unos2_len = . - unos2
ispis: .ascii "Spojeno: \0"
ispis_len = . - ispis
string1: .fill 50,1,42
string1_len = 50
string2: .fill 50,1,42
string2_len = 50
stringovi: .fill 100,1,42
stringovi_len = 100
duzina1: .long 0
duzina2: .long 0
duzina: .long 0
.section .text
.globl main

main:
 movl $4, %eax
 movl $1, %ebx
 leal unos1, %ecx
 movl $unos1_len, %edx
 int $0x80

 movl $3, %eax
 movl $0, %ebx
 leal string1, %ecx
 movl $string1_len, %edx
 int $0x80
 
 subl $1, %eax
 movl %eax, duzina1

 movl $4, %eax
 movl $1, %ebx
 leal unos2, %ecx
 movl $unos2_len, %edx
 int $0x80

 movl $3, %eax
 movl $0, %ebx
 leal string2, %ecx
 movl $string2_len, %edx
 int $0x80

 subl $1, %eax
 movl %eax, duzina2
 
 movl duzina2, %eax
 addl duzina1, %eax
 movl %eax, duzina
 
 movl $0, %esi
 movl $0, %edi
 
prvi: 
 cmpl %esi, duzina1
 je dalje
 movb string1(,%esi,1), %bl
 movb %bl, stringovi(,%edi,1)
 incl %esi
 incl %edi
 jmp prvi
 
dalje:
 movl $0, %esi
 
drugi:
 cmpl %esi, duzina2
 je ispisivanje
 movb string2(,%esi,1), %bl
 movb %bl, stringovi(,%edi,1)
 incl %esi
 incl %edi
 jmp drugi
 
ispisivanje:
 movl $4, %eax
 movl $1, %ebx
 leal ispis, %ecx
 movl $ispis_len, %edx
 int $0x80
 
 movl $4, %eax
 movl $1, %ebx
 leal stringovi, %ecx
 movl duzina, %edx
 int $0x80

kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80 
