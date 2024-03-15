.section .text
.globl oct_str_to_inter

oct_str_to_inter:
 pushl %ebp
 movl %esp, %ebp
 subl $4, %esp
 
 pushl %ebx
 pushl %edi
 pushl %esi
 
 movl 8(%ebp), %edi                    #adresa stringa koji pretvaramo
 movl $0, %esi                         #greska
 movl $8, -4(%ebp)                     #baza
 
 movl $0, %eax
 movl $0, %ecx
 
 cmpl $0, (%edi)
 je greska1
 
petlja:
 movb (%edi), %cl
 cmpb $0, %cl
 je kraj
 
 subb $'0', %cl
 js greska1
 
 cmpl -4(%ebp), %ecx
 jae greska1
 
 mull -4(%ebp)
 jc greska2
 
 addl %ecx, %eax
 jc greska2
 
 incl %edi
 jmp petlja
 
greska2:
 incl %esi
 
greska1:
 incl %esi
 
kraj:
 movl 12(%ebp), %ecx
 movl %esi, (%ecx)
 popl %esi
 popl %edi
 popl %ebx
 movl %ebp, %esp
 popl %ebp
 ret
