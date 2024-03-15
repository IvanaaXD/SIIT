#program
#Ivana Radovanovic, SV 23/2022
.section .data
str_unos:   .ascii "Unesite string:\0"
str_unos_len = . - str_unos
str_ispis:  .ascii "\nObrnuti redosled:\n\0"
str_ispis_len = . - str_ispis
string1: .fill 20,1,42
string1_len = . - string1
string2: .fill 20,1,42
string2_len = . - string2
string3: .fill 20,1,42
string3_len = . - string3
string4: .fill 20,1,42
string4_len = . - string4
string5: .fill 20,1,42
string5_len = . - string5
string6: .fill 20,1,42
string6_len = . - string6
string7: .fill 20,1,42
string7_len = . - string7
string8: .fill 20,1,42
string8_len = . - string8
string9: .fill 20,1,42
string9_len = . - string9
string10: .fill 20,1,42
string10_len = . - string10
duzina1: .long 0
duzina2: .long 0
duzina3: .long 0
duzina4: .long 0
duzina5: .long 0
duzina6: .long 0
duzina7: .long 0
duzina8: .long 0
duzina9: .long 0
duzina10: .long 0
.section .text
.globl main

main:
 movl $4, %eax
 movl $1, %ebx
 leal str_unos, %ecx
 movl $str_unos_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal string1, %ecx
 movl $string1_len, %edx
 int $0x80
 
 movl %eax, duzina1
 
 movl $4, %eax
 movl $1, %ebx
 leal str_unos, %ecx
 movl $str_unos_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal string2, %ecx
 movl $string2_len, %edx
 int $0x80
 
 movl %eax, duzina2
 
 movl $4, %eax
 movl $1, %ebx
 leal str_unos, %ecx
 movl $str_unos_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal string3, %ecx
 movl $string3_len, %edx
 int $0x80
 
 movl %eax, duzina3
 
 movl $4, %eax
 movl $1, %ebx
 leal str_unos, %ecx
 movl $str_unos_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal string4, %ecx
 movl $string4_len, %edx
 int $0x80
 
 movl %eax, duzina4
 
 movl $4, %eax
 movl $1, %ebx
 leal str_unos, %ecx
 movl $str_unos_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal string5, %ecx
 movl $string5_len, %edx
 int $0x80
 
 movl %eax, duzina5
 
 movl $4, %eax
 movl $1, %ebx
 leal str_unos, %ecx
 movl $str_unos_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal string6, %ecx
 movl $string6_len, %edx
 int $0x80
 
 movl %eax, duzina6
 
 movl $4, %eax
 movl $1, %ebx
 leal str_unos, %ecx
 movl $str_unos_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal string7, %ecx
 movl $string7_len, %edx
 int $0x80
 
 movl %eax, duzina7
 
 movl $4, %eax
 movl $1, %ebx
 leal str_unos, %ecx
 movl $str_unos_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal string8, %ecx
 movl $string8_len, %edx
 int $0x80
 
 movl %eax, duzina8
 
 movl $4, %eax
 movl $1, %ebx
 leal str_unos, %ecx
 movl $str_unos_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal string9, %ecx
 movl $string9_len, %edx
 int $0x80
 
 movl %eax, duzina9
 
 movl $4, %eax
 movl $1, %ebx
 leal str_unos, %ecx
 movl $str_unos_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal string10, %ecx
 movl $string10_len, %edx
 int $0x80
 
 movl %eax, duzina10
 
ispis:
 movl $4, %eax
 movl $1, %ebx
 leal str_ispis, %ecx
 movl $str_ispis_len, %edx
 int $0x80

 movl $4, %eax
 movl $1, %ebx
 leal string10, %ecx
 movl duzina10, %edx
 int $0x80

 movl $4, %eax
 movl $1, %ebx
 leal string9, %ecx
 movl duzina9, %edx
 int $0x80

 movl $4, %eax
 movl $1, %ebx
 leal string8, %ecx
 movl duzina8, %edx
 int $0x80
  
 movl $4, %eax
 movl $1, %ebx
 leal string7, %ecx
 movl duzina7, %edx
 int $0x80
  
 movl $4, %eax
 movl $1, %ebx
 leal string6, %ecx
 movl duzina6, %edx
 int $0x80  

 movl $4, %eax
 movl $1, %ebx
 leal string5, %ecx
 movl duzina5, %edx
 int $0x80

 movl $4, %eax
 movl $1, %ebx
 leal string4, %ecx
 movl duzina4, %edx
 int $0x80

 movl $4, %eax
 movl $1, %ebx
 leal string3, %ecx
 movl duzina3, %edx
 int $0x80  

 movl $4, %eax
 movl $1, %ebx
 leal string2, %ecx
 movl duzina2, %edx
 int $0x80
  
 movl $4, %eax
 movl $1, %ebx
 leal string1, %ecx
 movl duzina1, %edx
 int $0x80
  
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
