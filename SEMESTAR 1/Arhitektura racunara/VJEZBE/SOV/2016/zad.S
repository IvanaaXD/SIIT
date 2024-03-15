# autor: Imenko Prezimić INDEX ra1234-2016
# fajlovi: zad.S

# Ako vaše rešenje sadrži dodatne fajlove, stavite njihove nazive u liniju fajlovi
# Na primer, ako imate i potprogram u pp.S, iza fajlovi treba da stoji: zad.S pp.S

.macro ispis adresa duzina
    movl $4, %eax
    movl $1, %ebx
    leal \adresa, %ecx
    movl \duzina, %edx
    int $0x80
.endm

.macro upis adresa duzina
    movl $3, %eax
    movl $0, %ebx
    leal \adresa, %ecx
    movl \duzina, %edx
    int $0x80
.endm

.macro brojustr brojp, stringp, brojlen
    pushl \stringp
    pushl \brojp
    call bus
    addl $8, %esp

    pushl $brojstr
    call brojel
    addl $8, %esp

    movl %eax, \brojlen
.endm

.section .data

punos: 		.ascii "Unesite string: \0"
len1 = . - punos
pgreska: 	.ascii "Doslo je do greske u izvrsavanju programa!\n\0"
len2 = . - pgreska
psuma: 		.ascii "Suma elemenata niza je: \0"
len3 = . - psuma
pbroj_oktalnih: .ascii "\nBroj oktalnih elemenata u nizu: \0"
len4 = . - pbroj_oktalnih
pbroj_decimalnih: .ascii "\nBroj decimalnih elemenata u nizu: \0"
len5 = . - pbroj_decimalnih
pnovi_red: 	.ascii "\n\0"
len6 = . - pnovi_red

string: .fill 30,1,0

greska: .byte 0
broj: .long 0
brojstr: .fill 30,1,0
suma: .long 0
brojokt: .long 0
brojdec: .long 0

.section .text
.globl main

main:
    ispis punos, $len1
    upis string, $30

    movl $string, %esi    #adresa teksta

petlja:
    cmpb $'\n', (%esi)
    je krajpetlje

    cmpb $' ', (%esi)
    jne proverabroj

    incl %esi
    jmp petlja

proverabroj:
    movl $brojstr, %edi   #adresa stringa za jedan broj
    cmpb $'0', (%esi)
    jb greska1
    je oktalni 

dekadni:
    cmpb $' ', (%esi)
    je konvertujdec
    cmpb $'\n', (%esi)
    je konvertujdec

    cmpb $'9', (%esi)
    ja greska1

    movb (%esi), %dl
    movb %dl, (%edi)

    incl %edi
    incl %esi
    jmp dekadni

konvertujdec:
    movb $0, (%edi)

    pushl $greska
    pushl $broj
    pushl $brojstr
    call sub10
    addl $12, %esp

    cmpb $2, greska
    je greska2

    movl broj, %edx
    addl %edx, suma
    jc greska3

    incl brojdec

    jmp petlja

oktalni:
    cmpb $' ', 1(%esi)
    je dekadni

    incl %esi

oktalni2:
    cmpb $' ', (%esi)
    je konvertujoct
    cmpb $'\n', (%esi)
    je konvertujoct

    cmpb $'7', (%esi)
    ja greska1

    movb (%esi), %dl
    movb %dl, (%edi)

    incl %edi
    incl %esi
    jmp oktalni2

konvertujoct:
    movb $0, (%edi)

    pushl $greska
    pushl $broj
    pushl $brojstr
    call sub8
    addl $12, %esp

    cmpb $2, greska
    je greska2

    movl broj, %edx
    addl %edx, suma
    jc greska3

    incl brojokt

    jmp petlja

krajpetlje:
    ispis psuma, $len3
    brojustr suma, $brojstr, broj
    ispis brojstr, broj

    ispis pbroj_oktalnih, $len4
    brojustr brojokt, $brojstr, broj
    ispis brojstr, broj

    ispis pbroj_decimalnih, $len5
    brojustr brojdec, $brojstr, broj
    ispis brojstr, broj

    jmp kraj

greska1:
    ispis pgreska, $len2
    movl $1, greska
    jmp kraj
greska2:
    ispis pgreska, $len2
    jmp kraj
greska3:
    ispis pgreska, $len2
    movl $3, greska
kraj:
    movl $1, %eax
    movl greska, %ebx
    int $0x80

# unsigned brojel(char* broj)
brojel:
    pushl %ebp
    movl %esp, %ebp

    movl 8(%ebp), %ecx   #adresa stringa broja
    movl $1, %eax      #brojac (povratna vrednost funkcije)

petlja41:
    testb $0xFF, (%ecx)
    jz krajp4

    incl %eax
    incl %ecx
    jmp petlja41

krajp4:
    movl %ebp, %esp
    popl %ebp
    ret


# unsigned sub8(char* str, unsigned* broj, unsigned *greska)
sub8:
    pushl %ebp
    movl %esp, %ebp

    pushl %eax
    pushl %edx
    pushl %esi
    pushl %edi

    xorl %eax, %eax     #broj
    movl 8(%ebp), %esi  #adresa stringa
    movl $8, %edi       #osnova

    xorl %edx, %edx     #prazni se %edx

petlja11:
    testb $0xFF, (%esi)  #kraj stringa
    jz krajpetlje11

    mull %edi
    jc greska11

    movb (%esi), %dl
    subb $'0', %dl      #znak u broj

    addb %dl, %al

    incl %esi
    jmp petlja11

krajpetlje11:
    movl 12(%ebp), %edx
    movl %eax, (%edx)
    jmp krajp1

greska11:
    movl 16(%ebp), %edx
    movl $2, (%edx)

krajp1:
    popl %edi
    popl %esi
    popl %edx
    popl %eax
    movl %ebp, %esp
    popl %ebp
    ret

# unsigned sub10(char* str, unsigned* broj, unsigned *greska)
sub10:
    pushl %ebp
    movl %esp, %ebp

    pushl %eax
    pushl %edx
    pushl %esi
    pushl %edi

    xorl %eax, %eax     #broj
    movl 8(%ebp), %esi  #adresa stringa
    movl $10, %edi       #osnova

    xorl %edx, %edx     #prazni se %edx

petlja31:
    testb $0xFF, (%esi)  #kraj stringa
    jz krajpetlje31

    mull %edi
    jc greska31

    movb (%esi), %dl 
    subb $'0', %dl      #znak u broj

    addb %dl, %al

    incl %esi
    jmp petlja31

krajpetlje31:
    movl 12(%ebp), %edx
    movl %eax, (%edx)
    jmp krajp3

greska31:
    movl 16(%ebp), %edx
    movl $2, (%edx)

krajp3:
    popl %edi
    popl %esi
    popl %edx
    popl %eax
    movl %ebp, %esp
    popl %ebp
    ret

# unsigned bus(unsigned broj, char* str)
bus:
    pushl %ebp
    movl %esp, %ebp

    pushl %eax
    pushl %edx
    pushl %esi
    pushl %edi

    movl 12(%ebp), %esi   #adresa stringa
    movl  8(%ebp), %eax   #broj
    movl $10, %edi        #osnova

    testl $0xFFFFFFFF, %eax
    jnz petlja21

    movb $'0', (%esi)
    movb $0,  1(%esi)
    jmp krajp2

petlja21:
    testl $0xFFFFFFFF, %eax
    jz krajpetlje21

    xorl %edx, %edx
    divl %edi

    addl $'0', %edx      #broj u znak
    movb %dl, (%esi)

    incl %esi
    jmp petlja21

krajpetlje21:
    movb $0, (%esi)       #'\0' na kraj stringa

    decl %esi             #adresa kraja stringa
    movl 12(%ebp), %edi   #adresa pocetka stringa

obrni:
    cmpl %esi, %edi
    jae krajp2

    #zamena znakova
    movb (%esi), %dl
    movb (%edi), %dh 
    movb %dl, (%edi)
    movb %dh, (%esi)

    incl %edi
    decl %esi
    jmp obrni

krajp2:
    popl %edi
    popl %esi
    popl %edx
    popl %eax
    movl %ebp, %esp
    popl %ebp
    ret