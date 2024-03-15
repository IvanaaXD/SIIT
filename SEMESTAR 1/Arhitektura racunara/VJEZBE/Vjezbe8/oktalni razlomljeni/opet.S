.section .text
.globl oct_str_float_to_inter

oct_str_float_to_inter:
 pushl %ebp
 movl %esp, %ebp
 subl $8, %esp

 pushl %ebx
 pushl %edi
 
 movl 8(%ebp), %ebx              #broj/string
 movl $0, %edi                   #greska
 movl $8, -4(%ebp)               #baza
 movl $0, %ecx
 movl $100000000, -8(%ebp)       #skaliranje
 
 cmpb $0, (%ebx)
 je greska
 
null:
 cmpb $0, 1(%ebx)
 je petlja
 incl %ebx
 jmp null
 
petlja:
 movl $0, %eax
 
 movb (%ebx), %al
 cmpb $'.', %al
 je kraj
 
 subb $'0', %al
 js greska
 
 cmpl -4(%ebp), %eax
 jae greska
 
 mull -8(%ebp)
 addl %ecx, %eax	 
 divl -4(%ebp)	
 movl %eax, %ecx
 	 
 decl %ebx
 jmp petlja 
 
greska:
 incl %edi
 
kraj:
 movl 12(%ebp), %ebx   
 movl %edi, (%ebx)
 movl %ecx, %eax
 popl %edi
 popl %ebx
 movl %ebp, %esp
 popl %ebp
 ret 
