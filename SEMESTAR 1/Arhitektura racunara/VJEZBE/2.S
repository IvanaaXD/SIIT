#program
#Ivana Radovanovic, SV 23/2022
.section .data
NELEM = 10
niz: .long 1,2,3,4,5,6,7,8,9,10
min: .long 0
max: .long 0
.section .text
.globl main

main:
 movl $niz, %esi
 movl $0, %eax

minimalni:
 addl (%esi), %eax
 cmpl %eax, min
 jl maksimalni
 addl $4, %esi
 
  

 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
