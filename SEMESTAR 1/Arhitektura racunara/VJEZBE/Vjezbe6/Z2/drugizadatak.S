#program
#Ivana Radovanovic, SV 23/2022
.section .text
.globl SetParity

SetParity:
 pushl %ebp
 movl %ebp, %esp
 
 pushl %ebx
 pushl %edi
 pushl %esi
 
 movl 8(%ebp), %ecx
 
 movl $0, %edi
 movl $1, %dx
 movl $0, %esi
 
petlja:
 incl %esi
 cmpl $16, %esi
 je bla
 testw %dx, (%ecx)
 jnz jedan
 shll %dx
 jmp petlja
 
jedan:
 incl %edi
 shll %dx
 jmp petlja

bla:
 testw $1, %edi
 jz zero
 movl $1, %eax
 
zero:
 movl $0, %eax
 
kraj:
 popl %esi
 popl %edi
 popl %ebx
 movl %esp, %ebp
 popl %ebp
 ret
