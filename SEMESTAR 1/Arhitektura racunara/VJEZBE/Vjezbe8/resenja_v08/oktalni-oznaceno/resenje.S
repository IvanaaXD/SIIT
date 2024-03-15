# Potprogram za konverziju iz označenog oktalnog stringa u vrednost
# Imenko Prezimić ra1234

# int oct_str_to_inter(char* str, unsigned int* greska)
.section .text
.globl oct_str_to_inter
oct_str_to_inter:
    pushl %ebp
    movl %esp, %ebp
    subl $8, %esp
    pushl %ebx
    pushl %edi
    movl $8, -4(%ebp)   #baza
    movl $1, -8(%ebp)   #negacija
    movl $0, %edi       #greška
    xorl %eax, %eax
    xorl %ecx, %ecx
    movl 8(%ebp), %ebx
    cmpb $0, (%ebx)     #prazan string?
    je o2i_greska1
    cmpb $'+', (%ebx)   #predznak?
    je o2i_znak1	 #ako je plus, skoci na labelu koja ce preskociti ovaj karakter
    cmpb $'-', (%ebx)   #predznak?
    jne o2i_cifra       #ako nije minus, a nije bio ni plus, znaci da nema predznaka i u pitanju je pozitivan broj
    negl -8(%ebp)	 #ako je predznak minus, pretvori 1 u -1 kao vrednost kojom se rezultat mnozi na kraju
o2i_znak1:
    incl %ebx           #preskoči predznak
o2i_cifra:		 #ista petlja kao u zadatku sa neoznacenim brojevima
    movb (%ebx), %cl
    andb %cl, %cl
    jz o2i_znak2
    subb $'0', %cl
    js o2i_greska1
    cmpl -4(%ebp), %ecx
    jae o2i_greska1
    mull -4(%ebp)
    jc o2i_greska2
    addl %ecx, %eax
    jc o2i_greska2
    incl %ebx
    jmp o2i_cifra
o2i_znak2:
    andl %eax, %eax
    js o2i_jedannajvisibit  #ako je neoznačeni broj veći od 0x7fffffff, treba ispitati predznak unosa
    imull -8(%ebp)	     #ako je prvi bit okej, mnozi sa 1 ili -1 (sto je odredjeno u liniji 25)
    jmp o2i_kraj

o2i_jedannajvisibit:
    movl 8(%ebp), %ebx
    cmpb $'-', (%ebx)	     #ako je najvisi bit jedinica, ispitaj znak
    jne o2i_greska2	     #ako nije minus, znaci da je greska
    imull -8(%ebp)	     #ako jeste minus, nnozi sa -1
    jmp o2i_kraj
o2i_greska2:
    incl %edi
o2i_greska1:
    incl %edi
o2i_kraj:
    movl 12(%ebp), %ebx
    movl %edi, (%ebx)
    popl %edi
    popl %ebx
    movl %ebp, %esp
    popl %ebp
    ret

# potprogram ne radi dobro za jednu vrednost. koju?
