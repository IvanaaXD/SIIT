#program
#Ivana Radovanovic, SV 23/2022
.section .text
.globl maska

maska:
 pushl %ebp
 movl %ebp, %esp
 
 pushl %ebx
 pushl %edi
 pushl %esi
 
 movl 8(%ebp), %ecx
 movl 12(%ebp), %edx
 
 cmpl $1, %edx
 ja greska
 
 cmpl $32, %ecx
 ja greska
 
 cmpl $0, %edx
 je petlja1
 jne petlja2
 
petlja1:
 movl $0xfffffffe, %eax
 roll %cl, %eax
 jmp kraj
 
petlja2:
 movl $1, %eax
 roll %cl, %eax
 jmp kraj
 
greska:
 movl $0, %eax
 
kraj:
 popl %esi
 popl %edi
 popl %ebx
 movl %esp, %ebp
 popl %ebp
 ret

