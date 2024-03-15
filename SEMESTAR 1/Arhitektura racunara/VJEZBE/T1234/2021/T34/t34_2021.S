.section .text
.globl BitSwitch
BitSwitch:
 pushl %ebp
 movl %esp, %ebp
 subl $4, %esp
 movl $0, -4(%ebp)

 pushl %esi
 pushl %edi
 pushl %ebx

 movl 12(%ebp), %eax        #broj elemenata niza
 movl 8(%ebp), %esi         #adresa niza neoznacenih

 movl $1, %ebx              #00001
 movl $2, %edx              #00010

 movl $-1, %ecx             #brojac 32 bita
 movl $0, %edi              #indeks niza

petlja:
 cmpl %edi, %eax
 je end
 incl %ecx
 cmpl $31, %ecx
 je sledeci
 testl %ebx, (%esi)
 jne invertuj
 shll %ebx
 shll %edx
 jmp petlja

invertuj:
 incl -4(%ebp)
 xorl %edx, (%esi)
 shll %ebx
 shll %edx
 jmp petlja

sledeci:
 incl %edi
 addl $4, %esi
 movl $1, %ebx
 movl $2, %edx
 movl $-1, %ecx
 jmp petlja

end:
 movl -4(%ebp), %eax
 popl %ebx
 popl %edi
 popl %esi
 movl %ebp, %esp
 popl %ebp
 ret
