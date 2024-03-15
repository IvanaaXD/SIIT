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

 movl 8(%ebp), %eax               #broj koji se pretvara
 movl 12(%ebp), %esi              #adresa stringa
 movl $0, -4(%ebp)                #greska
 movl $8, %ebx                    #baza
 movl $0, -8(%ebp)                #brojac
 
 cmpl $0, %eax
 jge petlja 
 incl -8(%ebp)
 negl %eax
 decl 16(%ebp)
 
petlja:
 decl 16(%ebp)
 cmpl $1, 16(%ebp)
 jl greska
 
 movl $0, %edx
 divl %ebx
 addb $'0', %dl
 movb %dl, (%esi)
 incl %esi
 
 cmpl $0, %eax
 jne petlja
 
 cmpl $1, -8(%ebp)
 jne postavi
 
 movb $'-', (%esi)
 incl %esi
 
postavi:
 movb $0, (%esi)
 decl %esi
 movl 12(%ebp), %edi 
 
obrni:
 cmpl %esi, %edi
 jae kraj
 
 movb (%esi), %al
 movb (%edi), %ah
 movb %ah, (%esi)
 movb %al, (%edi)
 incl %edi
 decl %esi
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
