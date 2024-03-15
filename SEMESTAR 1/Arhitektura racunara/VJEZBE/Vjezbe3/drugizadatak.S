#program
#Ivana Radovanovic, SV 23/2022
.section .data
niz: .long 10,8,6,4,5,3,7,2,9,1
c = 10
min: .long 0
max: .long 3
.section .text
.globl main

main:
    movl $niz, %esi     #inicijalna vrednost
    movl (%esi) ,%ecx
    movl niz ,%edx      #može i ovako
    
petlja:
    cmpl (%esi), %ecx   #min
    jle dalje
    movl (%esi), %ecx
    
dalje:
    cmpl (%esi), %edx   #max
    jge dalje2
    movl (%esi), %edx
    
dalje2:
    addl $4, %esi
    cmpl $niz+C*4, %esi
    jl petlja
    
kraj:
    movl %ecx, min
    movl %edx, max
    movl $1, %eax
    movl $0, %ebx
    int $0x80
