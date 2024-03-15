# Program za sabiranje dva broja u proizvoljnoj bazi
# Imenko Prezimić ra1234

# Napisati asemblerski program koji, koristeći potprograme za konverziju
# za bilo koju bazu 2‑36, vrši sabiranje dva neoznačena cela broja data u
# znakovnom obliku i rezultat vraća u znakovnom obliku. Sve konverzije
# raditi odmah nakon unosa, a u slučaju greške prekinuti dalji rad
# programa i ispisati odgovarajuću poruku. Primer korišćenja:
#       Unesite bazu broja:16
#       Unesite broj:Fa
#       Unesite bazu broja:8
#       Unesite broj:123
#       Unesite bazu rezultata:2
#       Rezultat:101001101
# Za ostale primere pokrenuti testiraj.sh.
# Dodatni primeri se mogu napraviti pomoću kalkulatora (Accessories/Calculator)
.section .data
ulaz1:      .ascii "Unesite bazu broja:\0"
ulaz1_len = . - ulaz1
ulaz2:      .ascii "Unesite broj:\0"
ulaz2_len = . - ulaz2
ulaz3:      .ascii "Unesite bazu rezultata:\0"
ulaz3_len = . - ulaz3
izlaz1:     .ascii "Rezultat:\0"
izlaz1_len = . - izlaz1
greska1:    .ascii "Greska: pogresan znak!\0"
greska1_len = . - greska1
greska2:    .ascii "Greska: prekoracenje!\0"
greska2_len = . - greska2

unos_broj: .fill 50,1,42
unos_baza: .fill 10,1,42
rezultat: .fill 50,1,42
rezultat_len: .long 0

baza: .long 0
broj: .long 0
zbir: .long 0
greska: .long 0
.section .text
.globl main

#potprogram za konverziju stringa u interni oblik
string_u_int:
 pushl %ebp
 movl %esp, %ebp
 subl $4, %esp
 
 movl $0, -4(%ebp)              #greska
 
 pushl %ebx
 pushl %edi
 pushl %esi

 movl 8(%ebp), %ecx             #baza
 movl 12(%ebp), %edi            #adresa stringa
 
 movl $0, %eax                  #rezultat
 movl $0, %ebx
 movl $0, %edx 
 
convert_n:
    movb (%edi), %bl
    cmpb $10, %bl
    je end_convert_to_number
    cmpb $'9', %bl
    jbe check_number
    cmpl $10, %ecx
    ja check_lower_letter
    jmp set_err_1
check_number:
    cmpb $'0', %bl
    jae is_number
    jmp set_err_1
check_lower_letter:
    cmpb $'z', %bl
    jbe check_lower_letter_2
    jmp set_err_1
check_lower_letter_2:
    cmpb $'a', %bl
    jae is_lower_letter
    cmpb $'Z', %bl
    jbe check_upper_letter
    jmp set_err_1
check_upper_letter:
    cmpb $'A', %bl
    jae is_upper_letter
    jmp set_err_1

is_number:
    subb $'0', %bl
    cmpl %ecx, %ebx
    jae set_err_1
    mull %ecx
    jc set_err_2
    addl %ebx, %eax
    jc set_err_2
    incl %edi
    jmp convert_n
is_upper_letter:
    subb $'A', %bl
    addb $10, %bl
    cmpl %ecx, %ebx
    ja set_err_1
    mull %ecx
    jc set_err_2
    addl %ebx, %eax
    jc set_err_2
    incl %edi
    jmp convert_n
is_lower_letter:
    subb $'a', %bl
    addb $10, %bl
    cmpl %ecx, %ebx
    ja set_err_1
    mull %ecx
    jc set_err_2
    addl %ebx, %eax
    jc set_err_2
    incl %edi
    jmp convert_n
set_err_2:
    incl -4(%ebp)
set_err_1:
    incl -4(%ebp)
end_convert_to_number:
    movl 16(%ebp), %ebx
    movl -4(%ebp), %esi
    movl %esi, (%ebx)
    popl %edi
    popl %esi
    popl %ebx
    movl %ebp, %esp
    popl %ebp
    ret


int_u_string:
    pushl %ebp
    movl %esp, %ebp
    subl $4, %esp
    movl $0, -4(%ebp) # kod greske
    pushl %edi
    pushl %esi
    pushl %ebx
    movl 8(%ebp), %ecx # baza
    movl 12(%ebp), %eax # broj
    movl 16(%ebp), %edi # adresa rezultata
    xorl %ebx, %ebx
convert_s:
    cmpl $0, %eax
    je set_end
    xorl %edx, %edx
    divl %ecx
    cmpl %ecx, %edx
    ja set_err_1s
    cmpl $10, %edx
    ja is_letter_s
    jmp is_number_s
is_letter_s:
    addl $'a', %edx
    movb %dl, (%edi)
    incl %edi
    jmp convert_s
is_number_s:
    addl $'0', %edx
    movb %dl, (%edi)
    incl %edi
    jmp convert_s
set_end:
    movb $10, (%edi)
    movl %edi, %eax
    subl 16(%ebp), %eax
    incl %eax
    decl %edi
    movl 16(%ebp), %ecx
rotate:
    cmpl %ecx, %edi
    jbe end_convert_to_string
    movb (%ecx), %bl
    movb (%edi), %bh
    movb %bl, (%edi)
    movb %bh, (%ecx)
    incl %ecx
    decl %edi
    jmp rotate
set_err_2s:
    incl -4(%ebp)
set_err_1s:
    incl -4(%ebp)
end_convert_to_string:
    movl 20(%ebp), %ebx
    movl -4(%ebp), %esi
    movl %esi, (%ebx)
    popl %edi
    popl %esi
    popl %ebx
    movl %ebp, %esp
    popl %ebp
    ret



main:
 movl $4, %eax
 movl $1, %ebx
 leal ulaz1, %ecx
 movl $ulaz1_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal unos_baza, %ecx
 movl $50, %edx
 int $0x80
 
 pushl $greska            #potprogram za konverziju stringa u interni oblik
 pushl $unos_baza
 pushl $10
 call string_u_int 
 addl $12, %esp
 
 cmpl $16, %eax
 ja gr2
 cmpl $1, greska
 je gr1
 cmpl $2, greska
 je gr1
 
 movl %eax, baza
 
 
 
 movl $4, %eax
 movl $1, %ebx
 leal ulaz2, %ecx
 movl $ulaz2_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal unos_broj, %ecx
 movl $10, %edx
 int $0x80
 
 pushl $greska            #potprogram za konverziju stringa u interni oblik
 pushl $unos_broj
 pushl baza
 call string_u_int 
 addl $12, %esp
 
 cmpl $1, greska
 je gr1
 cmpl $2, greska
 je gr1
 
 movl %eax, zbir
 
 
 
 movl $4, %eax
 movl $1, %ebx
 leal ulaz1, %ecx
 movl $ulaz1_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal unos_baza, %ecx
 movl $10, %edx
 int $0x80
 
 pushl $greska            #potprogram za konverziju stringa u interni oblik
 pushl $unos_baza
 pushl $10
 call string_u_int 
 addl $12, %esp
 
 cmpl $16, %eax
 ja gr2
 cmpl $1, greska
 je gr1
 cmpl $2, greska
 je gr1
 
 movl %eax, baza
 
 
 
 movl $4, %eax
 movl $1, %ebx
 leal ulaz2, %ecx
 movl $ulaz2_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal unos_broj, %ecx
 movl $10, %edx
 int $0x80
 
 pushl $greska            #potprogram za konverziju stringa u interni oblik
 pushl $unos_broj
 pushl baza
 call string_u_int 
 addl $12, %esp
 
 cmpl $1, greska
 je gr1
 cmpl $2, greska
 je gr1
 
 movl %eax, broj
 
 
 
 movl $4, %eax
 movl $1, %ebx
 leal ulaz3, %ecx
 movl $ulaz3_len, %edx
 int $0x80
 
 movl $3, %eax
 movl $0, %ebx
 leal baza, %ecx
 movl $10, %edx
 int $0x80
 
 pushl $greska            #potprogram za konverziju stringa u interni oblik
 pushl $unos_baza
 pushl $10
 call string_u_int 
 addl $12, %esp
 
 cmpl $16, %eax
 ja gr2
 cmpl $1, greska
 je gr1
 cmpl $2, greska
 je gr1
 
 movl %eax, baza
 
 movl broj, %eax
 addl %eax, zbir
 jc gr2
 
 
 
 movl $4, %eax
 movl $1, %ebx
 leal izlaz1, %ecx
 movl $izlaz1_len, %edx
 int $0x80

 pushl $greska            #potprogram za konverziju internog oblika u string
 pushl $rezultat
 pushl zbir
 pushl baza
 call int_u_string
 addl $16, %esp
 
 cmpl $1, greska
 je gr1
 cmpl $2, greska
 je gr2
 movl %eax, rezultat_len
 
 movl $4, %eax
 movl $1, %ebx
 leal rezultat, %ecx
 movl $rezultat_len, %edx
 int $0x80
 
 jmp kraj
 
gr1:
 movl $4, %eax
 movl $1, %ebx
 leal greska1, %ecx
 movl $greska1_len, %edx
 int $0x80
 
 jmp kraj
 
gr2:
 movl $4, %eax
 movl $1, %ebx
 leal greska2, %ecx
 movl $greska2_len, %edx
 int $0x80 
 
kraj:
 movl $1, %eax
 movl $0, %ebx
 int $0x80
