# Sortirani ispis niza stringova, koristeći algoritam u kome se zamenjuju susedni elementi
# Imenko Prezimić ra1234

.section .data
str_len = 20    # dužina jednog stringa
str_num = 10    # broj stringova
strings:   .fill str_len*str_num,1,42   # prostor za sve stringove
currentn:  .long 0 # indeks tekućeg stringa
currents:  .long 0 # adresa tekućeg stringa
str_lens:  .fill str_num,4,0   # dužine za svaki string
str_unos:  .ascii "Unesite string:\0"
str_unos_len = . - str_unos
str_ispis: .ascii "\nSortirani redosled:\n\0"
str_ispis_len = . - str_ispis
zamena:    .byte 0  # da li je bila zamena prilikom sortiranja

.section .text
.globl main
main:
    # unos stringova
    movl $0, currentn
    movl $strings, %eax
    movl %eax, currents
petlja1:
    movl $4, %eax       # ispis poruke
    movl $1, %ebx
    movl $str_unos, %ecx
    movl $str_unos_len, %edx
    int $0x80
    movl $3, %eax       # unos stringa
    movl $0, %ebx
    movl currents, %ecx
    movl $str_len, %edx
    int $0x80
    movl currentn, %esi
    movl %eax,str_lens(,%esi,4) # čuvanje dužine stringa
    addl $str_len, currents     # adresa sledećeg stringa
    incl currentn
    cmpl $str_num, currentn
    jl petlja1

sortiranje: # spoljašnja petlja koja prolazi od početka niza stringova
    movl $strings, %eax
    movl %eax, currents
    movl $0, currentn
    movb $0, zamena     # na početku, nije bilo zamena

sort_unutrasnja:
    movl currents, %esi
poredi:                     # poređenje dva susedna stringa
    movb (%esi), %al        # znak tekućeg stringa
    movb str_len(%esi), %ah # znak sledećeg stringa
    cmpb $10, %al   # ako smo stigli do kraja prvog stringa, on je "manji" od drugog
    je sledeci
    cmpb $10, %ah   # ako smo stigli do kraja drugog stringa, on je "manji" od prvog
    je drugi_manji
    cmpb %al, %ah
    ja sledeci      # ako je prvi string manji, ne treba ništa da se radi
    jb drugi_manji
    incl %esi
    jmp poredi
drugi_manji:        # ako je drugi manji, treba ih zameniti
    movb $1, zamena
    movl currentn, %esi     # zamena dužina dva stringa
    movl str_lens(,%esi,4), %eax
    movl str_lens+4(,%esi,4), %ebx
    movl %ebx,str_lens(,%esi,4)
    movl %eax,str_lens+4(,%esi,4)

    movl currents, %esi     # zamena znakova dva stringa
    movl $str_len, %edi     # zamenjujemo svih $str_len znakova pošto stringovi mogu biti različite dužine
zameni:
    decl %edi
    js sledeci
    movb (%esi,%edi,1), %al         # znak tekućeg stringa
    movb str_len(%esi,%edi,1), %ah  # znak sledećeg stringa
    movb %ah, (%esi,%edi,1)         # zamena dva znaka
    movb %al, str_len(%esi,%edi,1)
    jmp zameni
sledeci:
    addl $str_len, currents     # prelazak na sledeći par stringova
    incl currentn
    cmpl $str_num-1, currentn   # indeks ne ide do kraja, zbog provere po parovima
    jl sort_unutrasnja
    cmpb $0, zamena     # ako je bila zamena, prođi ponovo kroz niz stringova
    jne sortiranje

    #ispis stringova
    movl $4, %eax       # ispis poruke
    movl $1, %ebx
    movl $str_ispis, %ecx
    movl $str_ispis_len, %edx
    int $0x80
    movl $0, currentn
    movl $strings, currents
petlja2:
    movl $4, %eax       # ispis stringa
    movl $1, %ebx
    movl currents, %ecx    # adresa tekućeg stringa
    movl currentn, %edx    # dužina tekućeg stringa
    movl str_lens(,%edx,4), %edx
    int $0x80   # zgodno je što je \n deo stringa, pa će ispis biti u posebnim redovima
    addl $str_len, currents
    incl currentn
    cmpl $str_num, currentn
    jl petlja2
kraj:
    movl $1, %eax
    movl $0, %ebx
    int $0x80

