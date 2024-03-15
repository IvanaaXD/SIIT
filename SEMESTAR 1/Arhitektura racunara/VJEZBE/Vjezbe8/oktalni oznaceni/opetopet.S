.section .text
.globl oct_str_to_inter

oct_str_to_inter:
 pushl %ebp
 movl %esp, %ebp
 subl $8, %esp
 
 pushl %ebx
 pushl %edi
 pushl %esi

 movl 8(%ebp), %edi                   #adresa stringa koji pretvaramo
 movl $0, %esi                        #greska
 movl $8, -4(%ebp)                    #baza
 movl $1, -8(%ebp)           
 
 movl $0, %ecx
 movl $0, %eax    
 
 cmpl $0, (%edi)
 je greska1
 
 cmpb $'+', (%edi)
 je plus
 cmpb $'-', (%edi)  
 jne petlja
 
 negl -8(%ebp)
 
plus:
 incl %edi
 
petlja:
 movb (%edi), %cl
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

 incl %edi
 jmp petlja

minus:
 cmpl $0, %eax
 jl labela
 imull -8(%ebp)
 jmp kraj

labela:
 movl 8(%ebp), %edi
 cmpb $'-', (%edi)
 jne greska2
 imull -8(%ebp)
 jmp kraj

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
