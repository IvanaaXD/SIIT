.section .text
.globl inter_to_oct_str

inter_to_oct_str:
 pushl %ebp
 movl %esp, %ebp
 subl $4, %esp
 subl $4, %esp

 pushl %ebx
 pushl %edi
 pushl %esi 

 movl 8(%ebp), %eax           #broj
 movl 12(%ebp), %edi          #adresa
 movl $8, %ebx                #baza
 movl $0, -4(%ebp)            #greska
 movl $0, -8(%ebp)            #brojac

 cmpl $0, %eax
 jge petlja
 
 incl -8(%ebp)
 decl 16(%ebp)
 negl %eax

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
 
 cmpl $1, -8(%ebp)
 jne plus
 
 movb $'-', (%edi)
 incl %edi
 movb $0, (%edi)
 decl %edi
 movl 12(%ebp), %esi
 jmp obrni
 
plus:
 movb $0, (%edi)
 decl %edi
 movl 12(%ebp), %esi
 
obrni:
 cmpl %edi, %esi
 jae kraj
 movb (%edi), %ah
 movb (%esi), %al
 movb %ah, (%esi)
 movb %al, (%edi)
 incl %esi
 decl %edi
 jmp obrni

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
