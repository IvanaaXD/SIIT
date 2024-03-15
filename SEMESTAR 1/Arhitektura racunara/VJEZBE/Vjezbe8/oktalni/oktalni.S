.section .text
.globl oct_str_to_inter

oct_str_to_inter:
 pushl %ebp
 movl %esp, %ebp
 subl $4, %esp
 
 pushl %ebx
 pushl %edi
 pushl %esi
 
 movl $8, -4(%ebp)          #baza
 movl $0, %edi              #greska
 movl 8(%ebp), %ebx         #string
 
 movl $0, %ecx
 movl $0, %eax
 
 cmpl $0, (%ebx)
 je greska1
 
petlja:
 movb (%ebx), %cl
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
 
 incl %ebx
 jmp petlja
 
greska2:
 incl %edi
 
greska1:
 incl %edi
 
kraj:
 movl 12(%ebp), %ebx
 movl %edi, (%ebx)
 popl %esi
 popl %edi
 popl %ebx
 movl %ebp, %esp
 popl %ebp
 ret
