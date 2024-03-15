#program
#Ivana Radovanovic, SV 23/2022
.section .data
unos: .ascii "Unesite string: \0"
unos_len = . - unos
ispis: .ascii "Promenjen string je: \0"
ispis_len = . - ispis
a_max = 20
a: .fill a_max,1,42
n: .long 0
.section .text
.globl main

main:

 movl $4, %eax
 movl $1, %ebx
 leal unos, %ecx
 movl $unos_len, %edx
 int $0x80

 movl $3, %eax
 movl $0, %ebx
 leal a, %ecx
 movl $a_max, %edx
 int $0x80
 
 movl %eax, n
 
 cmpl $1, %eax
 je ispisivanje
 
 cmpl $2, %eax
 je prepiss
 
 movl $0, %edx
 movl $2, %ebx
 divl %ebx
 
 cmpl $1, %edx
 jne neparni_broj
 je parni_broj
 
parni_broj:
 movl n, %esi
 decl %esi
 decl %esi
 movl %esi, %edi
 subl $1, %edi
 
petlja1:
 movb a(,%esi,1), %al
 movb a(,%edi,1), %bl
 
 xchgb %al,%bl
 
 movb  %al, a(,%esi,1)
 movb %bl, a(,%edi,1)
 
 subl $2, %esi
 jc ispisivanje
 movl %esi, %edi
 subl $1, %edi
 jmp petlja1
 
neparni_broj:
 movl $0, %esi
 movl $1, %edi
 movl n, %ecx
 decl %ecx
 decl %ecx

petlja2:
 cmpl %ecx, %esi
 ja ispisivanje
 je prepis

 movb a(,%esi,1), %al
 movb a(,%edi,1), %bl
 
 xchgb %al,%bl
 
 movb  %al, a(,%esi,1)
 movb %bl, a(,%edi,1)
 
 addl $2, %esi
 cmpl %ecx, %esi
 ja ispisivanje
 addl $2, %edi
 cmpl %ecx, %edi
 je prepis
 jmp petlja2
 
prepiss:
 movl $4, %eax
 movl $1, %ebx
 leal ispis, %ecx
 movl $ispis_len, %edx
 int $0x80

 movl $4, %eax
 movl $1, %ebx
 leal a, %ecx
 movl $1, %edx
 int $0x80
 jmp kraj
 
prepis:
 movb a(,%edi,1), %al

ispisivanje:
 movl $4, %eax
 movl $1, %ebx
 leal ispis, %ecx
 movl $ispis_len, %edx
 int $0x80

 movl $4, %eax
 movl $1, %ebx
 leal a, %ecx
 movl n, %edx
 int $0x80
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
