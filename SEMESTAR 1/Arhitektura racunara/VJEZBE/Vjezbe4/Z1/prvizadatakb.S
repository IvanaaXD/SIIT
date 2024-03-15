#program ispisuje ime i prezime b)
#Ivana Radovanovic, SV 23/2022
.section .data
 poruka1: .ascii "Unesite ime i prezime: \0"
 poruka1_len = . - poruka1
 poruka2: .ascii "Vi ste: \0"
 poruka2_len = . - poruka2
 iip_max = 50
 iip: .fill 50, 1, 42
 iip_len = . - iip
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
 leal iip, %ecx
 movl $iip_max, %edx
 int $0x80
 
 movl $4, %eax
 movl $1, %ebx
 leal poruka2, %ecx
 movl $poruka2_len, %edx
 int $0x80
 
ispitivanje: 
 cmpl iip, %esi
 ja ispis 
 movb iip
 
uppercase:
 subb $28, %eax
 movb %eax, iip2
 jmp ispitivanje
 
ispis: 
 movl $4, %eax
 movl $1, %ebx
 leal iip, %ecx
 movl $iip_len, %edx
 int $0x80
 
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
