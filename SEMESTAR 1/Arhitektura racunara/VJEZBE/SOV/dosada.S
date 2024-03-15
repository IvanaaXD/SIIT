# autor: Imenko Prezimić INDEX ra1234-2016
# fajlovi: zad.S

# Ako vaše rešenje sadrži dodatne fajlove, stavite njihove nazive u liniju fajlovi
# Na primer, ako imate i potprogram u pp.S, iza fajlovi treba da stoji: zad.S pp.S

.section .data
punos: 		.ascii "Unesite string: \0"
punos_len = . - punos  #duzina stringa punos
pgreska: 	.ascii "Doslo je do greske u izvrsavanju programa!\n\0"
pgreska_len = . - pgreska 
psuma: 		.ascii "Suma elemenata niza je: \0"
psuma_len = . - psuma
pbroj_oktalnih: .ascii "\nBroj oktalnih elemenata u nizu: \0"
pbroj_oktalnih_len = . - pbroj_oktalnih
pbroj_decimalnih: .ascii "\nBroj decimalnih elemenata u nizu: \0"
pbroj_decimalnih_len = . - pbroj_decimalnih
pnovi_red: 	.ascii "\n\0"
pnovi_red_len = . - pnovi_red

greska: .byte 0
suma: .long 0
string: .fill 30,1,0
broj_oktalnih: .byte 0
broj_decimalnih: .byte 0

broj_string: .fill 30, 1, 0
brojac_el: .long 0
broj: .long 0


.section .text
.global main

main:
	movl $4, %eax
	movl $1, %ebx
	leal punos, %ecx
	movl $punos_len, %edx
	int $0x80
	
	movl $3, %eax
	movl $0, %ebx
	leal string, %ecx
	movl $30, %edx
	int $0x80
	
	movl $string, %ecx
	
petlja:
	cmpb $'\n', (%ecx)
	je ispis
	
	cmpb $'0', (%ecx)
	jb greska1
	
	cmpb $' ', (%ecx)
	jne brojj
	
	incl %ecx
	jmp petlja
brojj:
	cmpb $'0', (%ecx)
	jne dekadni
	
	cmpb $' ', 1(%ecx)
	je dekadni
	cmpb $'\n', 1(%ecx)
	je dekadni
	
	cmpb $'7', (%ecx)
	ja greska1
	
	movl $broj_string, %eax
	incl %ecx

napravi_okt_string:
	cmpb $' ', (%ecx)
	je pozovi_potprogram_okt
	cmpb $'\n', (%ecx)
	je pozovi_potprogram_okt
	
	movb (%ecx), %bl
	movb %bl, (%eax)
	
	incl %eax
	incl %ecx
	jmp napravi_okt_string
	
pozovi_potprogram_okt:
	movl $0, (%eax)
	
	movl $greska, %ebx
	movl $broj_string, %edx
	movl $broj, %esi
	pushl %esi
	pushl %ebx
	pushl %edx
	call okt_u_broj
	addl $4, %esp
	
	cmpb $2, (%ebx)
	je greska2
	
	movl broj, %esi
	addl %esi, suma
	jc greska3
	
	incl broj_oktalnih
	incl %ecx
	jmp petlja

dekadni:
	cmpb $'9', (%ecx)
	ja greska1
	
	movl $broj_string, %eax

napravi_dec_string:
	cmpb $' ', (%ecx)
	je pozovi_potprogram_dec
	cmpb $'\n', (%ecx)
	je pozovi_potprogram_dec
	
	movb (%ecx), %bl
	movb %bl, (%eax)
	
	incl %eax
	incl %ecx
	jmp napravi_dec_string
	
pozovi_potprogram_dec:
	movl $0, (%eax)
	
	movl $greska, %ebx
	movl $broj_string, %edx
	movl $broj, %esi
	pushl %esi
	pushl %ebx
	pushl %edx
	call dec_u_broj
	addl $4, %esp
	
	cmpb $2, (%ebx)
	je greska2
	
	movl broj, %esi
	movl suma, %edi
	addl %esi, %edi
	jc greska3
	
	movl %edi, suma
	
	incl broj_decimalnih
	incl %ecx
	jmp petlja
	
ispis:
	movl $4, %eax
	movl $1, %ebx
	leal psuma, %ecx
	movl $psuma_len, %edx
	int $0x80
		
	movl $broj_string, %edx
	movl suma, %esi
	movl $brojac_el, %eax
	pushl %eax
	pushl %esi
	pushl %edx
	call broj_u_string
	addl $4, %esp
	
	movl $4, %eax
	movl $1, %ebx
	leal broj_string, %ecx
	movl $brojac_el, %edx
	int $0x80
	
	movl $4, %eax
	movl $1, %ebx
	leal pbroj_oktalnih, %ecx
	movl $pbroj_oktalnih_len, %edx
	int $0x80
		
	movl $broj_string, %edx
	movl broj_oktalnih, %esi
	movl $brojac_el, %eax
	pushl %eax
	pushl %esi
	pushl %edx
	call broj_u_string
	addl $4, %esp
	
	movl $4, %eax
	movl $1, %ebx
	leal broj_string, %ecx
	movl $brojac_el, %edx
	int $0x80

	movl $4, %eax
	movl $1, %ebx
	leal pbroj_decimalnih, %ecx
	movl $pbroj_decimalnih_len, %edx
	int $0x80
		
	movl $broj_string, %edx
	movl broj_decimalnih, %esi
	movl $brojac_el, %eax
	pushl %eax
	pushl %esi
	pushl %edx
	call broj_u_string
	addl $4, %esp
	
	movl $4, %eax
	movl $1, %ebx
	leal broj_string, %ecx
	movl $brojac_el, %edx
	int $0x80
	
	jmp kraj

greska1:
	movl $1, greska
	jmp kraj
greska2:
	movl $2, greska
	jmp kraj
greska3:
	movl $3, greska

kraj:
	movl $1, %eax
	movl greska, %ebx
	int $0x80
	
	
# okt_u_broj(char* str, unsigned* greska, unsigned* broj)
okt_u_broj:
	pushl %ebp
	movl %esp, %ebp
	
	pushl %eax
	pushl %edx
	
	pushl %edi
	movl 8(%ebp), %edi   # adresa stringa
	
	pushl %esi
	movl 12(%ebp), %esi  # adresa greske
	movl $0, (%esi)
	
	pushl %ebx
	xorl %eax, %eax      # broj
	xorl %ebx, %ebx
	
petlja11:
	cmpb $0, (%edi)
	je krajp1
	
	movb $8, %dl  #osnova
	
	mulb %dl
	jc prekoracenje
	
	movb (%edi), %bl
	subb $'0', %bl
	
	addb %bl, %al
	
	incl %edi
	jmp petlja11
	
prekoracenje:
	movl $2, (%esi)
krajp1:
	movl 16(%ebp), %edx
	movl %eax, (%edx)
	
	popl %ebx
	popl %esi
	popl %edi
	popl %edx
	popl %eax
	movl %ebp, %esp
	popl %ebp
	ret
	
# dec_u_broj(char* str, unsigned* greska, unsigned* broj)
dec_u_broj:
	pushl %ebp
	movl %esp, %ebp
	
	pushl %eax
	pushl %edx
	
	pushl %edi
	movl 8(%ebp), %edi   # adresa stringa
	
	pushl %esi
	movl 12(%ebp), %esi  # adresa greske
	movl $0, (%esi)
	
	pushl %ebx
	xorl %ebx, %ebx      # broj
	xorl %eax, %eax
	
petlja12:
	cmpb $0, (%edi)
	je krajp2
	
	movb $10, %dl  #osnova
	
	mulb %dl
	jc prekoracenje2
	
	movb (%edi), %bl
	subb $'0', %bl
	
	addb %bl, %al
	
	incl %edi
	jmp petlja12
	
prekoracenje2:
	movl $2, (%esi)
krajp2:
	movl 16(%ebp), %edx
	movl %eax, (%edx)
	
	popl %ebx
	popl %esi
	popl %edi
	popl %edx
	popl %eax
	movl %ebp, %esp
	popl %ebp
	ret
	
# broj_u_string(char* string, unsigned suma, unsigned* brojac)
broj_u_string:
	pushl %ebp
	movl %esp, %ebp
	subl $4, %esp
	
	pushl %ecx
	xorl %ecx, %ecx
	
	pushl %esi
	pushl %eax
	
	movl 8(%ebp), %esi   # string
	movl 12(%ebp), %eax  # suma
	
	movl $10, -4(%ebp)
	
	cmpl $0, %eax
	jne petlja13
	
	movl $'0', (%esi)
	incl %esi
	jmp krajpetlje13
	
petlja13:
	cmpl $0, %eax
	je krajpetlje13
	
	movl $0, %edx
	
	divl -4(%ebp)
	
	addb $'0', %dl
	movb %dl, (%esi)
	
	incl %esi
	incl %ecx # brojac cifara sume
	jmp petlja13
	
krajpetlje13:
	movl $0, (%esi)
	
	pushl %edi
	movl 8(%ebp), %edi  # pocetak stringa
	decl %esi           # kraj string

obrni:
	cmpl %esi, %edi
	jae krajp3
	
	movb (%esi), %dl
	movb (%edi), %dh
	movb %dl, (%edi)
	movb %dh, (%esi)
	
	incl %edi
	decl %esi
	jmp obrni
	
krajp3:
	movl 16(%ebp), %eax
	movl %ecx, (%eax)
	
	popl %ecx
	popl %edi
	popl %eax
	popl %esi
	movl %ebp, %esp
	popl %ebp
	ret
	
	
