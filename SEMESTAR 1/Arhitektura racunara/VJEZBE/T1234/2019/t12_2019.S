#program
#Ivana Radovanovic, SV 23/2022
.section .data
poruka: .ascii "Unesite string: \0" 
poruka_len = . - poruka
string_max = 50
string: .fill string_max, 1, 42
n: .long 0
.section .text
.globl main

main:
 movl $4, %eax
 movl $1, %ebx
 leal poruka, %ecx
 movl $poruka_len, %edx
 int $0x80

 movl $3, %eax
 movl $0, %ebx
 leal string, %ecx
 movl $string_max, %edx
 int $0x80
 
 subl $1, %eax
 movl %eax, n
 
 movl $0, %esi
 
petlja:
 movb string(,%esi,1), %bl
 
 cmpb $65, %bl
 jae provjera1
 incl %esi
 cmpl %esi, %eax
 je ispis
 jmp petlja
 
provjera1:
 cmpb $77, %bl
 jbe zamjena1
 ja provjera11
 
provjera11:
 cmpb $90, %bl
 jbe zamjena2
 ja provjera2
 
provjera2:
 cmpb $97, %bl
 jae provjera22
 incl %esi
 cmpl %esi, %eax
 je ispis
 jmp petlja
 
provjera22:
 cmpb $109, %bl
 jbe zamjena1
 ja provjera222
 
provjera222:
 cmpb $122, %bl
 jbe zamjena2
 incl %esi
 cmpl %esi, %eax
 je ispis
 jmp petlja
 
zamjena1:
 addb $13, %bl
 movb %bl, string(,%esi,1)
 incl %esi
 cmpl %esi, %eax
 je ispis
 jmp petlja

zamjena2:
 subb $13, %bl
 movb %bl, string(,%esi,1)
 incl %esi
 cmpl %esi, %eax
 je ispis
 jmp petlja

ispis:
 movl $4, %eax
 movl $1, %ebx
 leal string, %ecx
 movl n, %edx
 int $0x80
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
