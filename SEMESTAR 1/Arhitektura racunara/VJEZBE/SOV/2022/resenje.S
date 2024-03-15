# autor: Imenko Prezimić INDEX ra1234-2016
# fajlovi: resenje.S

.macro ispis adresa, duzina
	movl $4, %eax
	movl $1, %ebx
	leal \adresa, %ecx
	movl \duzina, %edx
	int $0x80
.endm
.macro upis adresa, duzina
	movl $3, %eax
	movl $0, %ebx
	leal \adresa, %ecx
	movl $\duzina, %edx
	int $0x80
.endm

.section .data
	NELEM = 31

	punos: .ascii "Unesite string: \0"
	punos_len = . - punos
	pgreska: .ascii "Doslo je do greske u izvrsavanju programa!\n\0"
	pgreska_len = . - pgreska
	psuma: .ascii "Suma elemenata niza je: \0"
	psuma_len = . - psuma
	pbroj_oktalnih: .ascii "\nBroj oktalnih elemenata u nizu: \0"
	pbroj_oktalnih_len = . - pbroj_oktalnih
	pbroj_decimalnih: .ascii "\nBroj decimalnih elemenata u nizu: \0"
	pbroj_decimalnih_len = . - pbroj_decimalnih
	pnovi_red: .ascii "\n\0"
	pnovi_red_len = . - pnovi_red

	greska: .long 0
	suma: .long 0
	br_oktalnih: .long 0
	br_decimalnih: .long 0
	str: .fill NELEM
	broj: .fill NELEM
	suma_str: .fill NELEM
	br_oktalnih_str: .fill NELEM
	br_decimalnih_str: .fill NELEM

.section .text
.globl main
# konverzija iz stringa u interni oblik
# void str2int(char *str, unsigned osnova, unsigned *greska)
str2int:
	pushl %ebp
	movl %esp, %ebp
	pushl %esi
	pushl %ebx
	movl 8(%ebp), %esi 	# adresa stringa
	movl 12(%ebp), %ecx	# baza
	xorl %eax, %eax			# rezultat
	xorl %ebx, %ebx
str2int_sledeci:
	movb (%esi), %bl
	cmpb $0, %bl
	je str2int_kraj
	mull %ecx
	andl %edx, %edx
	jnz str2int_greska
	subb $'0', %bl
	addl %ebx, %eax
	jc str2int_greska
	incl %esi
	jmp str2int_sledeci
str2int_greska:
	movl 16(%ebp), %esi
	movl $2, (%esi)
str2int_kraj:
	popl %ebx
	popl %esi
	movl %ebp, %esp
	popl %ebp
	ret

# konverzija iz internog oblika u string
# int int2str(char *str, unsigned osnova, unsigned broj)
# povratna vrednost je broj karaktera u rezultujucem stringu
int2str:
	pushl %ebp
	movl %esp, %ebp
	pushl %esi
	pushl %edi
	movl 8(%ebp), %esi		# pokazivac na string
	movl 12(%ebp), %ecx		# osnova brojnog sistema
	movl 16(%ebp), %eax		# broj koji se konvertuje
	movl %esi, %edi				# edi pokazuje na pocetak stringa
int2str_sledeci:
	xorl %edx, %edx
	divl %ecx
	addb $'0', %dl
	movb %dl, (%esi)
	incl %esi
	incb %bh
	andl %eax, %eax
	jnz int2str_sledeci
int2str_obrni:
	decl %esi
	cmpl %edi, %esi
	jbe int2str_kraj
	movb (%esi), %al
	movb (%edi), %ah
	movb %ah, (%esi)
	movb %al, (%edi)
	incl %edi
	jmp int2str_obrni
int2str_kraj:
	movl 8(%ebp), %esi
	xorl %eax, %eax
	movb %bh, %al			# vraca broj karaktera u stringu
	popl %edi
	popl %esi
	movl %ebp, %esp
	popl %ebp
	ret

main:
	ispis punos, $punos_len
	upis str, NELEM
	decl %eax								# da se ne racuna \n
	leal str, %esi
	leal broj, %edi
nadji_broj:
	andl %eax, %eax					# kraj stringa?
	jz kraj_broja
	movb (%esi), %bl				# trenutni karakter
	incl %esi
	decl %eax
	cmpb $' ', %bl
	jz kraj_broja
	cmpb $'0', %bl         # da li je karakter u dozvoljenom opsegu
	jb greska1
	cmpb $'9', %bl
	ja greska1
	movb %bl, (%edi)
	incl %edi
	jmp nadji_broj
kraj_broja:
	movb $0, (%edi)					# 0 na kraj stringa koji predstavlja broj
	cmpb $'1', broj
	jae decimalni
	cmpb $0, broj+1
	je decimalni
	incl br_oktalnih
	movl $8, %ecx
	jmp konvertuj
decimalni:
	incl br_decimalnih
	movl $10, %ecx
	jmp konvertuj
konvertuj:
	pushl %eax							# brojac karaktera na stek, eax se koristi za povratnu vrednost
	pushl $greska
	pushl %ecx							# izmeniti tako da se osnova menja
	pushl $broj
	call str2int						# povratna vrednost konvertovani broj
	addl $12, %esp
	cmpl $0, greska
	jne ispis_poruke_greske
	addl %eax, suma
	jnc sledeci
	movl $3, greska
	jmp ispis_poruke_greske
sledeci:
	popl %eax
	leal broj, %edi					# reset pokazivaca na broj
	andl %eax, %eax
	jne nadji_broj

	# ispis sume
	pushl suma
	pushl $10
	pushl $suma_str
	call int2str
	addl $12, %esp
	movl %eax, %esi					# pamti se broj karaktera u stringu
	ispis psuma, $psuma_len
	ispis suma_str, %esi

	# ispis broja oktalnih
	pushl br_oktalnih
	pushl $10
	pushl $br_oktalnih_str
	call int2str
	addl $12, %esp
	movl %eax, %esi
	ispis pbroj_oktalnih, $pbroj_oktalnih_len
	ispis br_oktalnih_str, %esi

	# ispis broja decimalnih
	pushl br_decimalnih
	pushl $10
	pushl $br_decimalnih_str
	call int2str
	addl $12, %esp
	movl %eax, %esi
	ispis pbroj_decimalnih, $pbroj_decimalnih_len
	ispis br_decimalnih_str, %esi
	jmp kraj
greska1:
  	movl $1, greska
	ispis_poruke_greske:
	ispis pgreska, $pgreska_len
kraj:
	# ispis novog reda
	ispis pnovi_red, $pnovi_red_len
	movl $1, %eax
	movl greska, %ebx
	int $0x80
