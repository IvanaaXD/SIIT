#program sortira niz
#Ivana Radovanovic, SV 23/2022
.section .data
string1: .ascii "Trebinje je lijep grad\0"
strlen = . - string1
string2: .ascii "\0"
n: .long 0
.section .text
.globl main

main:
 movl $0, %esi
 movl $0, %edi	
 movl $0, %ebx
 
poredi: 
 movb string1(,%esi,1), %al
 cmpb $0, %al
 jne provjera
 je rasporedjivanje

provjera:
 cmpb $32, %al
 jne dodjela
 incl %ebx
 incl %esi
 jmp poredi
 
dodjela:
 cmpb $65, %al
 jae provjera1
 incl %esi
 jmp poredi
 
provjera1:
 cmpb $90, %al
 jbe zamjena
 jmp provjera2
 
provjera2:
 cmpb $97, %al
 jae provjera3
 incl %esi
 jmp poredi
 
provjera3:
 cmpb $122, %al
 jbe premjesti
 incl %esi
 jmp poredi
 
premjesti:
 movb string1(,%esi,1), %al
 addb %al, string2(,%edi,1)
 incl %esi
 incl %edi
 jmp poredi
  
zamjena:
 movb string1(,%esi,1), %al
 addb $32, %al
 addb %al, string2(,%edi,1)
 incl %edi
 incl %esi
 jmp poredi
 
rasporedjivanje:
 movl $strlen, %eax
 subl $1, %eax
 subl %ebx, %eax
 
 movl %eax, %esi
 movl %eax, %edi
 
spoljasnja_petlja: 
 decl %esi
 jz kraj
 
unutrasnja_petlja:
 decl %edi
 jz spoljasnja_petlja
 
 movb string2(,%edi,1), %bl
 decl %edi
 movb string2(,%edi,1), %cl
 cmpb %bl, %cl
 jb promjena
 incl %edi
 jmp unutrasnja_petlja
 
promjena:
 xchgb %bl, %cl
 movb %bl, string2(,%edi,1)
 incl %edi
 movb %cl, string2(,%edi,1)
 jmp unutrasnja_petlja
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
