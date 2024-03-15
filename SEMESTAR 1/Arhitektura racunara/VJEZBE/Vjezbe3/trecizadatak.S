#program uklanja razmake u stringu
#Ivana Radovanovic, SV 23/2022
.section .data
string: .ascii "   tekst   \0"
.section .text
.globl main

main:
 movl $string, %eax
 
kraj_stringa:
 cmpb $0, (%eax)
 je razmak
 incl %eax
 jmp kraj_stringa
 
razmak:
 cmpb $' ', -1(%eax)
 jne izbaci1
 decl %eax
 jmp razmak
 
izbaci1:
 movb $0, (%eax)

pocetak_stringa:
 movl $string, %eax
 cmpb $' ', (%eax)
 je izbaci2
 jmp kraj
 
izbaci2:
 cmpb $0, (%eax)
 je pocetak_stringa
 movb 1(%eax), %bl
 movb %bl, (%eax)
 incl %eax
 jmp izbaci2
 
kraj:
 movl $1, %eax
 movl $0, %ebx 
 int $0x80
