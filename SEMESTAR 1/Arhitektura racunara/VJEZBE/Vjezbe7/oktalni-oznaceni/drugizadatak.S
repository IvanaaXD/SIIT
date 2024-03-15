#program
#Ivana Radovanovic, SV 23/2022
.section .text
.globl inter_to_oct_str

inter_to_oct_str:
 pushl %ebp
 movl %esp, %ebp
 subl $4, %esp
 subl $4, %esp
 
 pushl %edi
 pushl %esi
 pushl %ebx
 
 movl 8(%ebp), %eax       #broj
 movl 12(%ebp), %edi      #adresa stringa
 movl $8, %ecx            #baza
 movl $0, -4(%ebp)        #greska
 
 movl $0, -8(%ebp)        #brojac
 
 movl 16(%ebp), %ebx
 
 cmpl $0, %eax
 jge petlja
 
minus:
 incl -8(%ebp)
 decl 16(%ebp)
 negl %eax
 
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
 jnz petlja
 
 cmpl $1, -8(%ebp)
 jne obrnii
 movl $'-', (%edi)
 incl %edi
 movl $0, (%edi)
 decl %edi
 movl 12(%ebp), %esi
 jmp obrni
 
obrnii:
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
 decl %edi
 incl %esi
 jmp obrni
 
greska:
 incl -4(%ebp)
 
kraj:
 movl -4(%ebp), %eax
 popl %ebx
 popl %esi
 popl %edi
 movl %ebp, %esp
 popl %ebp
 ret
