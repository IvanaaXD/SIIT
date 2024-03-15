# Potprogram za konverziju iz označenog oktalnog stringa u vrednost
# Imenko Prezimić ra1234

# unsigned int oct_str_to_inter(char* str, unsigned int* greska)
.section .text
.globl oct_str_to_inter
oct_str_to_inter:
    pushl %ebp
    movl %esp, %ebp
    subl $4, %esp
    pushl %ebx
    pushl %edi
    movl $8, -4(%ebp)   #promenljiva za bazu; nije neophodno cuvati u promenljivoj
    movl $0, %edi       #edi ce cuvati gresku
    xorl %eax, %eax
    xorl %ecx, %ecx
    movl 8(%ebp), %ebx
    cmpb $0, (%ebx)     #provera praznog stringa
    je o2i_greska1
o2i_cifra: 		 #glavna petlja
    movb (%ebx), %cl 	 #karakter u registru cl
    andb %cl, %cl	 
    jz o2i_kraj	 #provera kraja stringa (zajedno sa prethodnom linijom; andb ce dati nulu samo ako je u cl-u karakter \0)
    subb $'0', %cl	 #oduzimanje ascii koda nule radi pretvaranja ascii koda cifre u njenu numericku vrednost
    js o2i_greska1	 #provera da li je u cl karakter koji je pre '0' u ascii tabeli; ako jeste, prethodno oduzimanje dace oznacen broj
    cmpl -4(%ebp), %ecx #zajedno sa linijom ispod proverava da li je cl u opsegu '0'-'7'
    jae o2i_greska1
    mull -4(%ebp)	 #mnozenje rezultata osnovom
    jc o2i_greska2	 #provera prekoracenje
    addl %ecx, %eax	 #dodavanje trenutne cifre na rezultat
    jc o2i_greska2	 #provera prekoracenja
    incl %ebx
    jmp o2i_cifra
o2i_greska2:
    incl %edi
o2i_greska1:
    incl %edi
o2i_kraj:
    movl 12(%ebp), %ebx
    movl %edi, (%ebx) 	 #smestanje greske u njenu promenljivu
    popl %edi
    popl %ebx
    movl %ebp, %esp
    popl %ebp
    ret

