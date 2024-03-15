# Palindrom (mala slova, bez razmaka), indirektno adresiranje
# Imenko Prezimić ra1234

.section .data
str_max = 50
str: .fill str_max,1,42
poruka: .ascii "Unesite string: \0"
poruka_len = . - poruka
nijep: .ascii "String nije palindrom.\n"
nijep_len = . - nijep
jestep: .ascii "String je palindrom.\n"
jestep_len = . - jestep

.section .text
.globl main
main:
    movl $4, %eax
    movl $1, %ebx
    movl $poruka, %ecx
    movl $poruka_len, %edx
    int $0x80

    movl $3, %eax
    movl $0, %ebx
    movl $str, %ecx
    movl $str_max, %edx
    int $0x80       # eax nakon poziva sadrži broj unetih znakova

    addl $str-2, %eax    # pokazivač na kraj
                            # oduzima se 2 jer:
                            #  - indeksi idu od nule, a ne od 1
                            #  - poslednji znak je "\n"
    movl $str, %ebx      #pokazivač na početak
provera:
    movb (%eax), %cl
    cmpb %cl, (%ebx)
    jne nije
    decl %eax
    incl %ebx
    cmpl %ebx, %eax
    jg provera

    # ako su se pokazivači susreli, string jeste palindrom
    movl $4, %eax
    movl $1, %ebx
    movl $jestep, %ecx
    movl $jestep_len, %edx
    int $0x80
    movl $0, %ebx   #izlazni kod 0
    jmp kraj

nije:
    movl $4, %eax
    movl $1, %ebx
    movl $nijep, %ecx
    movl $nijep_len, %edx
    int $0x80
    movl $1, %ebx   #izlazni kod 1
kraj:
    movl $1, %eax
    int $0x80

