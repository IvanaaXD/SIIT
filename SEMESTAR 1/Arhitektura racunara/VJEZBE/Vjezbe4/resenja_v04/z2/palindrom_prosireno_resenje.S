# Palindrom, kombinovano adresiranje,
# zanemaruje sve znake koji nisu slova, radi upcase pre provere
# (bez direktne izmene stringa, samo za znakove koji se trenutno porede)
# Program postavlja promenljivu "palindrom" na 1 ukoliko
# se u stringu "string" nalazi palindrom
# Poželjno je program prepraviti da radi sa ulazom i izlazom
# Imenko Prezimić ra1234

.section .data
str: .ascii "AnA!!,   voLI   ,  MiloVA    na#?\0"
palindrom:  .byte 0 #logička promenljiva (1 - jeste palindrom)

.section .text
.globl main
main:
    movb $0, palindrom
    movl $str,%eax
    movl $0, %esi   #indeks prvog
    movl $0, %edi   #indeks poslednjeg
null:   #nalaženje kraja stringa
    cmpb $0, 1(%eax,%edi,1)
    je provera1a
    incl %edi
    jmp null

provera1a: #upcase (pokazivač na početak)
    movb (%eax,%esi,1), %bl
    cmpb $'a', %bl
    jb provera1b
    cmpb $'z', %bl
    ja provera1b
    addb $'A'-'a', %bl  #dodaje se razlika malih i velikih slova
provera1b: #preskakanje svega što nije slovo (pokazivač na početak)
    cmpb $'A', %bl
    jl nije_slovo1
    cmpb $'Z', %bl
    jg nije_slovo1
    jmp provera2a
nije_slovo1:
    incl %esi
    jmp provera1a

provera2a: #upcase (pokazivač na kraj)
    movb (%eax,%edi,1), %bh
    cmpb $'a', %bh
    jb provera2b
    cmpb $'z', %bh
    ja provera2b
    addb $'A'-'a', %bh
provera2b: #preskakanje svega što nije slovo (pokazivač na kraj)
    cmpb $'A', %bh
    jl nije_slovo2
    cmpb $'Z', %bh
    jg nije_slovo2
    jmp provera3
nije_slovo2:
    decl %edi
    jmp provera2a

provera3: #da li su isti znaci
    cmpb %bl, %bh
    jne kraj    #ako nisu, nije palindrom
    incl %esi
    decl %edi
    cmpl %esi,%edi
    jae provera1a
    movb $1, palindrom  #jeste palindrom
kraj:
    movl $1, %eax
    movl $0, %ebx
    int $0x80

