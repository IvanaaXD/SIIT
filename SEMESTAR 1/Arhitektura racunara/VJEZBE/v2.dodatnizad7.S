#Napisati asemblerski program koji računa n-ti stepen broja b
.section .data
b:  .quad 2
n:  .long 3
brojac: .quad 0
rezultat:   .quad 0
greska: .byte 0
.section .text
.globl main

main:
    movl b+4, %eax  #visi dio
    movl b, %ebx    #nizi dio
pocetak:
    subl $1, n
    cmpl $0, n
    jbe kraj

    movl %eax, brojac+4
    movl %ebx, brojac
mnozenje:
    subl $1, brojac
    sbbl $0, brojac+4

    addl %ebx, rezultat
    adcl %eax, rezultat+4
    jc greskalabela

    cmpl $0, brojac+4
    ja mnozenje
    cmpl $0, brojac
    ja mnozenje
    jmp pocetak
greskalabela:
    incb greska
kraj:
    movl $1, %eax
    movl $0, %ebx
    int $0x80