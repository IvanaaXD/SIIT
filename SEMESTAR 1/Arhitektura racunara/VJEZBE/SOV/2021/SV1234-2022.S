#autor: Imenko Prezimić INDEX SV1234-2022
.section .data
prompt1: .ascii "Unseite logicki izraz: \0"
prompt1_len = . - prompt1
prompt2: .ascii "Rezultat: \0"
prompt2_len = . - prompt2
err1: .ascii "Greska kod operanda.\n\0"
err1_len = . - err1
err2: .ascii "Greska kod operacije.\n\0"
err2_len = . - err2
newline: .ascii "\n\0"
newline_len = . - newline

greska: .long 0
izraz_max = 100
izraz: .fill izraz_max,1,42
znak: .ascii " \0" 
.section .text
.globl main

main:
 movl $4, %eax
 movl $1, %ebx
 leal prompt1, %ecx
 movl $prompt1_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal izraz, %ecx
 movl $izraz_max, %edx
 int $0x80

 movl $izraz, %esi
 movl $znak, %edi            #pamti znak operacije
 
 





















kraj:
 movl $1, %eax
 movl greska, %ebx
 int $0x80
