# autor: Imenko Prezimić INDEX ra1234-2016
# fajlovi: zad.S

# Ako vaše rešenje sadrži dodatne fajlove, stavite njihove nazive u liniju fajlovi
# Na primer, ako imate i potprogram u pp.S, iza fajlovi treba da stoji: zad.S pp.S
.section .data
unos: .ascii "Unesite string: \0"
unos_len = . - unos
string_max = 30
string: .fill string_max, 1, 42
string_len: .long 0
string_int: .fill string_max, 1, 42
pgreska: .ascii "Doslo je do greske u izvrsavanju programa!\n\0"
suma: .ascii "Suma elemenata niza je: \0"
suma_len = . - suma
zbir: .long 0
broj_oktalnih: .ascii "\nBroj oktalnih elemenata u nizu: \0"
broj_oktalnih_len = . - broj_oktalnih
br_okt: .long 0
broj_decimalnih: .ascii "\nBroj decimalnih elemenata u nizu: \0"
broj_decimalnih_len = . - broj_decimalnih
br_dec: .long 0
novi_red: .ascii "\n\0"
novi_red_len = . - novi_red

greska: .long 0
broj: .fill 30,1,0
int_broj: .fill 30,1,0
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
 leal string, %ecx
 movl $string_max, %edx
 int $0x80

 subl $1, %eax
 movl %eax, string_len
 
 movl $0, %eax
 movl $string, %edi
 
petlja:
 cmpb $'\n', (%edi)
 je ispis
 cmpb $' ', (%edi)
 jne provjera_za_broj
 
 incl %edi
 jmp petlja
 
provjera_za_broj:
 movl $broj, %esi
 cmpb $'0', (%edi)
 jb greska1
 je oktalni
 
dekadni:
 cmpb $' ', (%edi)
 je dekadni_u_int
 cmpb $'\n', (%edi)
 je dekadni_u_int
 
 cmpb $'9', (%edi)
 ja greska1
 
 movb (%edi), %dl 
 movb %dl, (%esi)
 
 incl %edi
 incl %esi
 jmp dekadni
 
oktalni:
 cmpb $' ', 1(%edi)
 je dekadni
 incl %edi
 
oktalnii:
 cmpb $' ', (%edi)
 je oktalni_u_int
 cmpb $'\n', (%edi)
 je oktalni_u_int
 
 cmpb $'7', (%edi)
 ja greska1
 
 movb (%edi), %dl
 movb %dl, (%esi)
 incl %esi
 incl %edi
 jmp oktalnii
 
dekadni_u_int:
 movb $0, (%esi)
 
 pushl $greska                 
 pushl $broj                   #adresa stringa koji se pretvara
 pushl $int_broj               #adresa broja
 call znakovni_dekadni_u_int
 addl $12, %esp
 
 cmpb $2, greska
 je greska2
 
 movl broj, %edx
 addl %edx, suma
 jc greska3
  
 incl br_dec
 jmp petlja
 
oktalni_u_int:
 movb $0, (%esi)
 
 pushl $greska                 
 pushl $broj                   #adresa stringa koji se pretvara
 pushl $int_broj               #adresa broja
 call znakovni_oktalni_u_int
 addl $12, %esp
 
 cmpb $2, greska
 je greska2
 
 movl broj, %edx
 addl %edx, suma
 jc greska3
  
 incl br_okt
 jmp petlja
 
ispis: 
 movl $4, %eax
 movl $1, %ebx
 leal broj_oktalnih, %ecx
 movl $broj_oktalnih_len, %edx
 int $0x80
 
 pushl $br_okt
 pushl $broj
 call broj_u_int

 movl $4, %eax
 movl $1, %ebx
 leal broj, %ecx
 movl $2, %edx
 int $0x80
 
 movl $4, %eax
 movl $1, %ebx
 leal novi_red, %ecx
 movl $novi_red_len, %edx
 int $0x80

 movl $4, %eax
 movl $1, %ebx
 leal broj_decimalnih, %ecx
 movl $broj_decimalnih_len, %edx
 int $0x80

 pushl $br_dec
 pushl $broj
 call broj_u_int

 movl $4, %eax
 movl $1, %ebx
 leal broj, %ecx
 movl $2, %edx
 int $0x80
 
 movl $4, %eax
 movl $1, %ebx
 leal novi_red, %ecx
 movl $novi_red_len, %edx
 int $0x80

 movl $4, %eax
 movl $1, %ebx
 leal suma, %ecx
 movl $suma_len, %edx
 int $0x80

 pushl $zbir
 pushl $broj
 call broj_u_int

 movl $4, %eax
 movl $1, %ebx
 leal broj, %ecx
 movl $3, %edx
 int $0x80 
 
 jmp kraj

greska1:
 movl $4, %eax
 movl $1, %ebx
 leal greska1, %ecx
 movl $2, %edx
 int $0x80

 movl $1, %eax
 jmp kraj
 
greska2:
 movl $4, %eax
 movl $1, %ebx
 leal greska2, %ecx
 movl $2, %edx
 int $0x80
 
 movl $2, %eax
 jmp kraj
 
greska3:
 movl $4, %eax
 movl $1, %ebx
 leal greska3, %ecx
 movl $2, %edx
 int $0x80
 
 movl $3, %eax

kraj:
 movl $0, %ebx
 int $0x80
 
 
#znakovni oktalni u int(string, adresa broja)
znakovni_oktalni_u_int:
 pushl %ebp
 movl %esp, %ebp
 subl $4, %esp
 
 pushl %ebx
 pushl %ecx
 pushl %edx
 pushl %edi
 
 movl 12(%ebp), %ebx                #adresa stringa
 movl $8, -4(%ebp)                  #baza
 movl $0, %edi                      #greska
 
 movl $0, %ecx
 movl $0, %eax
 
petlja2:
 movb (%ebx), %cl
 cmpb $0, %cl
 je krajpetlje2
 
 subb $'0', %cl
 
 mull -4(%ebp)
 jc greska22
 
 addl %ecx, %eax
 
 incl %ebx
 jmp petlja
 
krajpetlje2:
 movl 8(%ebp), %ebx
 movl %eax, (%ebx)
 jmp krajp2
 
greska22:
 movl 8(%ebp), %ecx
 movl $2, (%ecx)
 
krajp2:
 popl %edi
 popl %edx
 popl %ecx
 popl %ebx
 movl %ebp, %esp
 popl %ebp
 ret
#znakovni decimalni u int(string, adresa broja)
znakovni_dekadni_u_int:
 pushl %ebp
 movl %esp, %ebp
 subl $4, %esp
 
 pushl %ebx
 pushl %ecx
 pushl %edx
 pushl %edi
 
 movl 12(%ebp), %ebx                #adresa stringa
 movl $10, -4(%ebp)                 #baza
 movl $0, %edi                      #greska
 
 movl $0, %ecx
 movl $0, %eax
 
petlja1:
 movb (%ebx), %cl
 cmpb $0, %cl
 je krajpetlje1
 
 subb $'0', %cl
 
 mull -4(%ebp)
 jc greska11
 
 addl %ecx, %eax
 
 incl %ebx
 jmp petlja
 
krajpetlje1:
 movl 8(%ebp), %ebx
 movl %eax, (%ebx)
 jmp krajp1
 
greska11:
 movl 8(%ebp), %ecx
 movl $2, (%ecx)
 
krajp1:
 popl %edi
 popl %edx
 popl %ecx
 popl %ebx
 movl %ebp, %esp
 popl %ebp
 ret
 
#interni decimalni u znakovni(adresa broja, string)
broj_u_int:
 pushl %ebp
 movl %esp, %ebp
 
 pushl %eax
 pushl %ebx
 pushl %ecx
 pushl %edx
 pushl %edi
 
 movl 8(%ebp), %eax               #adresa broja koji se pretvara
 movl 12(%ebp), %esi              #string
 
 movl $10, %ebx
  
 cmpl $0, %eax
 jne petlja3
 
 movb $'0', (%esi)
 incl %esi
 movb $0, (%esi)
 jmp krajp3
  
petlja3:
 cmpb $0, (%eax)
 je obrnii
 
 movl $0, %edx
 divl %ebx
 
 addb $'0', %dl
 movb %dl, (%esi)
 
 incl %esi
 jmp petlja3
 
obrnii:
 movb $0, (%esi)
 decl %esi
 movl 12(%ebp), %edi
 
obrni:
 cmpl %esi, %edi
 jae krajp3
 movb (%esi), %ah
 movb (%edi), %al
 movb %al, (%esi)
 movb %ah, (%edi)
 incl %edi
 decl %esi
 jmp obrni
 
krajp3:
 popl %edi
 popl %edx
 popl %ecx
 popl %ebx
 popl %eax
 movl %ebp, %esp
 popl %ebp
 ret
