#autor: Imenko Prezimić INDEX ra1234-2015

.section .data
ulaz_str: .ascii "Unesite string: \0"
ulaz_str_len= .-ulaz_str
unos_max = 50
unos: 	.fill unos_max,1,42
pronasao_cifru: .byte 0
.section .text
.globl main
main:

	movl $4, %eax
	movl $1, %ebx	
	leal ulaz_str, %ecx
	movl $ulaz_str_len, %edx
	int $0x80

	movl $3, %eax
	movl $0, %ebx	
	leal unos, %ecx
	movl $unos_max, %edx
	int $0x80
	movl %eax, %edi
	decl %edi

	xorl %esi, %esi 	# esi je indeksni registar
	xorl %ebx, %ebx		# visi deo ebx se ne koristi pa se postavlja na 0 
prolazak:
	cmpl %edi, %esi
	jae kraj_stringa
	cmpb $10, unos(,%esi,1)
	je kraj_stringa
	
	movb unos(,%esi,1), %al

	cmpb $'0', %al
	jb nije_cifra
	cmpb $'9', %al
	ja nije_cifra
	
	cmpb $0, pronasao_cifru
	jne inicjalizovani_min_max
	movb %al, %bl		# min
	movb %al, %bh		# max
	movb $1, pronasao_cifru

inicjalizovani_min_max:
	cmpb %bl, %al	
	jae proveri_max		# obrnuti skok za min
	movb %al, %bl		# novi min
proveri_max:
	cmpb %bh, %al
	jbe nije_cifra		# obrnuti skok za max
	movb %al, %bh		# novi max
nije_cifra:
	incl %esi
	jmp prolazak

kraj_stringa:
	cmpb $0, pronasao_cifru
	jne saberi_ekstreme 
	movl $0, %ebx
	jmp kraj
saberi_ekstreme:
	addb %bh, %bl
	xorb %bh, %bh
kraj:
    movl $1, %eax
    int $0x80

