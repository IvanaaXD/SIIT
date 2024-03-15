.section .text
.globl inter_to_oct_str_float

inter_to_oct_str_float:
 pushl %ebp
 movl %esp, %ebp
 subl $8, %esp
 
 pushl %ebx
 pushl %edi
 pushl %esi

 movl 8(%ebp), %eax           #broj
 movl 12(%ebp), %edi          #adresa
 movl $8, %ebx                #baza
 movl $100000000, %ecx        #konstanta skaliranja
 movl $0, -4(%ebp)            #greska
 
 cmpl $0, %eax
 jns nula
 
 movl $'-', (%edi)
 incl %edi
 decl 16(%ebp)
 negl %eax
 
nula:
 cmpl $4, 16(%ebp)
 jl greska

 movb $'0', (%edi)
 movb $'.', 1(%edi)
 addl $2, %edi
 decl 16(%ebp)

petlja:
 decl 16(%ebp)
 cmpl $1, 16(%ebp)
 je krajj
 
 mull %ebx
 divl %ecx
 addb $'0', %al
 movb %al, (%edi)
 incl %edi

 movl %edx, %eax
 cmpl $0, %eax
 jne petlja
 
krajj:
 movb $0, (%edi)
 jmp kraj

greska:
 incb -4(%ebp)

kraj:
 movl -4(%ebp), %eax
 popl %esi
 popl %edi
 popl %ebx
 movl %ebp, %esp
 popl %ebp
 ret
