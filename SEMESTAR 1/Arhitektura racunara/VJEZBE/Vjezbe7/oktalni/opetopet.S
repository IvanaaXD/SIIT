.section .text
.globl inter_to_oct_str

inter_to_oct_str:
 pushl %ebp
 movl %esp, %ebp
 subl $4, %esp
 
 pushl %ebx
 pushl %edi
 pushl %esi
 
 movl 8(%ebp), %eax               #broj kooji se pretvara
 movl 12(%ebp), %edi              #adresa stringa
 movl $8, %ebx                    #baza
 movl $0, -4(%ebp)                #greska
 
petlja:
 decl 16(%ebp)
 cmpl $1, 16(%ebp)
 jl greska
 
 movl $0, %edx
 divl %ebx
 addb $'0', %dl
 movb %dl, (%edi)
 incl %edi
 
 cmpl $0, %eax
 jne petlja
 
 movb $0, (%edi)
 decl %edi
 movl 12(%ebp), %esi
 
obrni:
 cmpl %edi, %esi
 jae kraj
 movb (%edi), %ah
 movb (%esi), %al
 movb %al, (%edi)
 movb %ah, (%esi)
 incl %esi
 decl %edi
 jmp obrni
 
greska:
 incl -4(%ebp)
 
kraj:
 movl -4(%ebp), %eax
 popl %esi
 popl %edi
 popl %ebx
 movl %ebp, %esp
 popl %ebp
 ret
