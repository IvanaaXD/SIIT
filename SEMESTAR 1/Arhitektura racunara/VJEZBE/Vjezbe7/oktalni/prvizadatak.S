#program
#Ivana Radovanovic, SV 23/2022
.section .text
.globl inter_to_oct_str

inter_to_oct_str:
 pushl %ebp
 movl %esp, %ebp
 subl $4, %esp
 
 pushl %edi
 pushl %esi
 pushl %ebx
 
 movl 8(%ebp), %eax    #broj
 movl 12(%ebp), %edi   #adresa stringa
 movl $0, -4(%ebp)     #greska
 movl $8, %ecx         #baza
 
petlja:
 decl 16(%ebp)
 cmpl $1, 16(%ebp)
 jl greska

 movl $0, %edx
 divl %ecx
 addb $'0', %dl
 movb %dl, (%edi)
 incl %edi
 
 cmpl $0, %eax
 jne petlja
 
 movl $0, (%edi)
 decl %edi
 movl 12(%ebp), %esi
 
obrni:
 cmpl %edi, %esi
 jae kraj
 movb (%esi), %ah
 movb (%edi), %al
 movb %al, (%esi)
 movb %ah, (%edi)
 incl %esi
 decl %edi  
 jmp obrni
 
greska:
 incb -4(%ebp)

kraj:
 movl -4(%ebp), %eax
 popl %ebx
 popl %esi
 popl %edi
 movl %ebp, %esp
 popl %ebp
 ret
