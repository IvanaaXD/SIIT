.section .text
.globl inter_to_oct_str_float

inter_to_oct_str_float:
 pushl %ebp
 movl %esp, %ebp
 subl $8, %esp
    
 pushl %ebx
 pushl %esi
 pushl %edi
 
 movl 8(%ebp), %eax      #broj
 movl 12(%ebp), %edi     #str
 movl $8, %ebx           #baza
 movl $0, -4(%ebp)       #greška
 movl $100000000, -8(%ebp)   #konstanta skaliranja
 
 cmpl $0, %eax
 jns nula
 
 movb $'-', (%edi)
 incl %edi
 decl 16(%ebp)
 negl %eax
 
nula:
 cmpl $4, 16(%ebp)       #greška je samo ako inicijalno nema
 jl greska               #dovoljno mesta za "0." i jednu cifru
 
 movb $'0', (%edi)
 movb $'.', 1(%edi)
 addl $2, %edi
 decl 16(%ebp)
 
cifra:
 decl 16(%ebp)           #provera preostalog mesta
 cmpl $1, 16(%ebp)       #neophodan je i 1 bajt za NULL
 je term
 
 mull %ebx               #množenje bazom
 divl -8(%ebp)           #deljenje konstantom skaliranja
 addb $'0', %al
 movb %al, (%edi)
 incl %edi
 
 movl %edx, %eax
 cmpl $0, %eax
 jne cifra
 
term:
 movb $0, (%edi)
 jmp kraj
 
greska:
 incb -4(%ebp)
 
kraj:
 movl -4(%ebp), %eax
 popl %edi
 popl %esi
 popl %ebx
 movl %ebp, %esp
 popl %ebp
 ret
