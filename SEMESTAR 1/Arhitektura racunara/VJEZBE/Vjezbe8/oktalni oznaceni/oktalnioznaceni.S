.section .text
.globl oct_str_to_inter

oct_str_to_inter:
 pushl %ebp
 movl %esp, %ebp
 subl $8, %esp
 
 pushl %ebx
 pushl %edi
 pushl %esi

 movl $0, %edi           #greska
 movl $0, %eax
 movl $0, %ecx
 movl $1, -8(%ebp)

 movl $8, -4(%ebp)       #baza
 movl 8(%ebp), %ebx      #string

 cmpb $0, (%ebx)
 je greska1

 cmpb $'+', (%ebx)
 je plus
 cmpb $'-', (%ebx)
 jne petlja
 negl -8(%ebp)

plus:
 incl %ebx

petlja:
 movb (%ebx), %cl
 cmpb $0, %cl
 je minus

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
 
minus:
 cmpl $0, %eax
 js labela
 imull -8(%ebp)
 jmp kraj
 
labela:
 movl 8(%ebp), %ebx
 cmpb $'-', (%ebx)	     
 jne greska2	     
 imull -8(%ebp)	     
 jmp kraj

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
