#autor
#int SaberiMnozi(int n, unsigned short *a, unsigned short *b, unsigned short *resenje);

.section .text
.global SaberiMnozi
SaberiMnozi:
	pushl %ebp
	movl %esp,%ebp
	subl $4,%esp
	subl $4,%esp
	pushl %edi
	pushl %esi
	pushl %ebx
	movl $0,-4(%ebp) #promenljiva za broj gresaka
	
	movl 8(%ebp),%edi #n(brojac)
	movl 12(%ebp),%ebx #prvi niz(a)
	movl 16(%ebp),%ecx #drugi niz(b)
	movl 20(%ebp),%esi #rezultat(c)
	
	movl %edi,-8(%ebp) #n(lokalna promenljiva)
	xorl %edi,%edi
	
	
petlja:
	cmpl %edi,-8(%ebp)
	je kraj
	testl $0x0001,%edi	#provera parno neparno
	jz parni
	jnz neparni
	
	parni:
		xorl %eax,%eax	#praznjenje eax i edx(za svaki slucaj)
		xorl %edx,%edx
		movw (%ebx),%ax
		mulw (%ecx)
		testl $0xffffffff,%edx #jedno od mogucih prekoracenja
		jnz greska
		testl $0xffff0000,%eax #drugo od mogucih prekoracenja
		jnz greska
		
		movw %ax,(%esi)
		jmp korekcija
	
	
	neparni:
			movw (%ebx),%ax
			movw (%ecx),%dx
			addw %dx,%ax
			jc greska				#provera prekoracenja
			testl $0xffff0000,%eax 	
			jnz greska
			
			movw %ax,(%esi)
			jmp korekcija
		
	
				
greska:
	movw $11111,(%esi)
	incl -4(%ebp)	#greska++

	
korekcija:
	addl $2,%ebx	#korekcija je uvek ista pa postoji samo jedna
	addl $2,%ecx
	addl $2,%esi
	incl %edi
	jmp petlja
		
				
kraj:
	movl -4(%ebp),%eax #povratna vrednost se gura u eax (promenljiva za gresku)
	popl %ebx
	popl %esi
	popl %edi
	movl %ebp,%esp
	popl %ebp
	ret

