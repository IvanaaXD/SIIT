#program
#Ivana Radovanovic, SV 23/2022

.section .text
.globl fibonaci

fibonaci:
 pushl %ebp
 movl %esp, %ebp
 subl $4, %esp       
 movl $0, -4(%ebp)
 
 movl $1, %eax   #f1
 movl %eax, %ecx
 
fib_petlja:
    cmpl $2, 8(%ebp)
    jbe fib_kraj
    addl %eax, %ecx
    jc fib_greska
    xchgl %eax, %ecx
    decl 8(%ebp)
    jmp fib_petlja
fib_greska:
    incl -4(%ebp)
fib_kraj:
    movl 12(%ebp), %ecx #adresa rezultata
    movl %eax, (%ecx)   #upis rezultata
    movl -4(%ebp), %eax #greška je povratna vrednost
    movl %ebp, %esp
    popl %ebp
    ret
