.section .text
.globl inter_to_roman
dec:    .long 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
rom1:   .ascii "MCDCCXLXXIVII"
rom2:   .ascii " M D C L X V "

#int inter_to_roman(unsigned int broj, char *str, int duzina);
inter_to_roman:
    pushl %ebp
    movl %esp, %ebp
    pushl %ebx

    xorl %ecx, %ecx         # indeks za nizove
    movl 12(%ebp), %edx     # pokazivač stringa
tor_check:
    movl dec(,%ecx,4),%eax
    cmpl %eax, 8(%ebp)
    jb tor_next
    subl %eax, 8(%ebp)
    movb rom1(,%ecx,1), %bl
    movb %bl, (%edx)
    incl %edx
    decl 16(%ebp)
    jz tor_error    # ako nema mesta za null
    movb rom2(,%ecx,1), %bl
    cmpb $' ', %bl
    je tor_check
    movb %bl, (%edx)
    incl %edx
    decl 16(%ebp)
    jz tor_error    # ako nema mesta za null
    jmp tor_check
tor_error:
    movl $1, %eax
    jmp tor_end
tor_next:
    incl %ecx
    cmpl $13, %ecx
    jl tor_check

    movl $0, (%edx)
    xorl %eax, %eax
tor_end:
    popl %ebx
    movl %ebp, %esp
    popl %ebp
    ret

