#faktorijal 64-bitnog broja

.section .data
n:  .quad 10
rezultat:   .quad 0
pomocna:   .quad 0
starirez:   .quad 0
greska: .byte 0
.section .text
.globl main

main:
    movl n+4, %eax  #n==rezultat
    movl n, %ebx 
    movl %eax, rezultat+4
    movl %ebx, rezultat

provjera.fakt:
    movl rezultat+4, %eax  #pomocna za mnozenje/stara vrijednost rezultata(5+5+5..., a ne 5+5+10+20)==n
    movl rezultat, %ebx  
    movl %eax, starirez+4
    movl %ebx, starirez     #strrez=5

    subl $1, n      #n=4
    sbbl $0, n+4

    movl n+4, %eax  #pomocna za mnozenje/stara vrijednost rezultata(5+5+5..., a ne 5+5+10+20)==n
    movl n, %ebx  
    movl %eax, pomocna+4
    movl %ebx, pomocna 

    cmpl $0, n+4
    jne nastavak
    cmpl $1, n
    jbe kraj

nastavak:
    subl $1, pomocna
    sbbl $0, pomocna+4

    movl starirez+4, %eax   #da bih ga mogla sabrati sa rezultatom
    movl starirez, %ebx
    addl %ebx, rezultat
    adcl %eax, rezultat+4
    jc prekoracenje    
    
    cmpl $0, pomocna+4
    jb nastavak
    cmpl $1, pomocna
    ja nastavak
    jbe provjera.fakt
prekoracenje:
    incb greska
kraj:
    movl $1, %eax
    movl $0, %ebx
    int $0x80
