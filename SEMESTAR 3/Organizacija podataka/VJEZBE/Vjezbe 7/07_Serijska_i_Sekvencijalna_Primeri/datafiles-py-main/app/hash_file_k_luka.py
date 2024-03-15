from binary_file import BinaryFile


class HashFileK(BinaryFile):
    def __init__(self, k, b, filename, record, blocking_factor, empty_key=-1):
        BinaryFile.__init__(self, filename, record, blocking_factor, empty_key)
        self.k = k
        self.b = b

    def hash(self, id):
        # jednostavna hash funkcija, po modulu maksimalnog broja blokova
        return id % self.b

    def init_file(self):
        # inicijalizuje fajl sa b blokova koji su sacinjeni od praznih slogova
        with open(self.filename, 'wb') as f:
            for _ in range(self.b):
                empty_block = [self.get_empty_rec()] * self.blocking_factor
                self.write_block(f, empty_block)

    def insert(self, rec):
        # gde prvobitno proveravamo da li ima mesta
        initial_block_loc = self.hash(rec['id'])

        with open(self.filename, 'rb+') as f:
            # pozicioniranje i citanje inicijalnog bloka
            f.seek(self.block_size * initial_block_loc)
            initial_block = self.read_block(f)

            i = 0
            while i < self.blocking_factor:
                # prolazimo kroz svaki slog inicijalnog bloka
                record = initial_block[i]

                if record['status'] == 0:
                    # ako je status = 0, to je prazan slog (moze se proveravati i sa id == self.empty_key),
                    # mozemo odmah prepisati preko njega
                    initial_block[i] = rec
                    f.seek(self.block_size * initial_block_loc)
                    self.write_block(f, initial_block)
                    return
                if record['id'] == rec['id']:
                    # ako je pronadjen slog sa istim id-jem u inicijalnom bloku
                    if record['status'] == 2:
                        # a taj slog koji se vec nalazi u bloku je logicki obrisan,
                        # slobodno prepisati preko njega
                        initial_block[i] = rec
                        f.seek(self.block_size * initial_block_loc)
                        self.write_block(f, initial_block)
                        return
                    else:
                        # a taj slog koji se vec nalazi u bloku nije logicki obrisan,
                        # ispisujemo gresku da slog vec postoji
                        print("Error: record already exists")
                        return
                i += 1

        # u ovom trenutku smo prosli kroz sve slogove inicijalnog bloka i nismo pronasli ni jedan prazan
        # preko kog moze da se prepise, a ni jedan logicki obrisan sa istim id-jem preko kog takodje
        # moze da se prepise (sto takodje znaci da je blok pun)
        # pa moramo da umecemo slog na drugo mesto, tj. krece proces pomeranja za k mesta u desno dok ne
        # nadjemo prvo prazno mesto

        self.__insert_overflow(rec)

    def find_by_id(self, id):
        # gde prvobitno trazimo slog
        initial_block_loc = self.hash(id)

        with open(self.filename, 'rb') as f:
            # otvaramo fajl, pozicioniramo se i citamo blok
            f.seek(self.block_size * initial_block_loc)
            initial_block = self.read_block(f)

            for i, record in enumerate(initial_block):
                # prolazimo kroz svaki slog inicijalno ucitanog bloka
                if record['status'] == 0:
                    # ako je pronadjen prazan slog (moze se proveravati i sa id == self.empty_key)
                    # mozemo biti sigurni da trazeni slog ne postoji dalje u fajlu
                    # jer da postoji, on bi popunio ovo prazno mesto
                    return None
                if record['id'] == id and record['status'] == 1:
                    # ako je pronadjen u inicijalnom bloku, vracamo poziciju tog bloka i gde se slog
                    # nalazi unutar tog bloka, samo ako nije logicki obrisan (tj. samo ako status == 1)
                    return initial_block_loc, i

        # u ovom trenutku smo prosli kroz sve slogove inicijalnog bloka i nismo naisli ni na jedan
        # prazan (sto bi prekinulo funkciju), a nismo naisli ni na trazeni slog (sto bi prekinulo funkciju),
        # pa mozemo zakljuciti da se trazeni slog ne nalazi u ovom bloku ali da i dalje postoji mogucnost da se
        # negde drugde nalazi
        # krece proces prolazenja kroz ostale blokove pomeranjem k blokova u desno dok ne pronadjemo (ili ne
        # pronadjemo slog)

        self.__find_by_id_overflow(id)

    def logical_delete_by_id(self, id):
        # pronadjemo gde se nalazi slog sa datim id-jem
        found = self.find_by_id(id)

        if not found:
            # ako taj slog zapravo ne postoji, nista se ne radi
            return None

        block_loc = found[0]
        in_block_loc = found[1]

        with open(self.filename, 'rb+') as f:
            # pozicioniramo se i procitamo pronadjeni blok
            f.seek(block_loc * self.block_size)
            block = self.read_block(f)

            # stavimo pronadjenom slogu status na 2 sto predstavlja status logicko obrisanog sloga
            block[in_block_loc]['status'] = 2

            # prepisemo blok
            f.seek(block_loc * self.block_size)
            self.write_block(f, block)

    def true_delete_by_id(self, id):
        # pronalazimo gde se nalazi slog sa ovim id-jem
        found = self.find_by_id(id)

        if not found:
            # ako nije pronadjen, nema brisanja
            return None

        original_block_loc = found[0]
        in_block_loc = found[1]

        with open(self.filename, 'rb+') as f:
            # otvaramo fajl, pozicioniramo se na inicijalni blok i citamo ga
            f.seek(original_block_loc * self.block_size)
            original_block = self.read_block(f)

            # return_rec predstavlja promenjivu koja drzi slog koji cemo kasnije vratiti iz funkcije
            return_rec = original_block[in_block_loc]

            # pomeri sve slogove ovog bloka u levo za jedan, oslobadjajuci poslednje mesto
            for i in range(in_block_loc, self.blocking_factor - 1):
                original_block[i] = original_block[i + 1]

            # what the fuck?
            # (overflow true delete funkcija vraca slog ciji se hes id-ja podudara sa lokacijom maticnog baketa
            # (tj. taj slog je trebao biti u maticnom baketu) iz sledeceg bloka u lancu i to radi i za svaki blok lanca,
            # rekurzivno; lanac je serija blokova koji sadze bar jedan slog koji je trebao ici u ovaj baket;
            # iterativna funkcija moguca ali nisam pronasao adekvatan nacin da je lako implementiram)
            original_block[self.blocking_factor - 1] = self.__true_delete_by_id_overflow(f, original_block_loc, id)

            # vrati se na lokaciju originalnog bloka i prepisi ga
            f.seek(original_block_loc * self.block_size)
            self.write_block(f, original_block)

        # vrati obrisan slog iz funkcije da korisnik moze dalje da manipulise sa njim
        return return_rec

    def __insert_overflow(self, rec):
        # gde inicijalno moze da se umetne, koristi kao pocetni korak
        initial_block_loc = self.hash(rec['id'])
        # gde sledece moze da se umetne, idemo k blokova u desno, ali sa modulom b (brojem blokova u fajlu),
        # jer kraj fajla ne mora nuzno znaciti i kraj slobodnih blokova za upis
        # primer:
        # k = 3
        # |3.|7.|0.|4.|8.|1.|5.|9.|2.|6.|10.|
        # u ovom primeru inicijalni blok je blok 0., ali pomeranjem u desno sa modulom smo omogucili
        # da su svi blokovi maksimalno iskorisceni
        current_block_loc = (initial_block_loc + self.k) % self.b

        with open(self.filename, 'rb+') as f:
            # otvaramo fajl
            while current_block_loc != self.hash(rec['id']):
                # pozicioniramo se na trenutni blok i citamo ga
                f.seek(current_block_loc * self.block_size)
                current_block = self.read_block(f)

                for i, record in enumerate(current_block):
                    # prolazimo kroz sve slogove u trenutnom bloku
                    if record['status'] == 0:
                        # ako naidjemo na prazan slog (moze da se proveri i sa id == self.empty_key), odma
                        # zapisujemo dat slog na ovo mesto jer je to prvo slobodno
                        current_block[i] = rec
                        f.seek(self.block_size * current_block_loc)
                        self.write_block(f, current_block)
                        return
                    if record['id'] == rec['id']:
                        # ako su isti id-jevi
                        if record['status'] == 2:
                            # i slog vec u bloku je logicki obrisan, prepisi
                            current_block[i] = rec
                            f.seek(self.block_size * current_block_loc)
                            self.write_block(f, current_block)
                            return
                        else:
                            # i slog vec u bloku nije logicki obrisan, ispisi gresku
                            print("Error: record already exists")
                            return

                # pomeri se za k blokova u desno sa modulom b (ukupan broj blokova u fajlu)
                current_block_loc = (current_block_loc + self.k) % self.b

            # ako smo prosli kroz sve blokove (nismo upisali, tj. svi su popunjeni) i opet stigli do maticnog,
            # to znaci da za ovaj slog nema mesta jer je lanac blokova koji se dobija na osnovu njegovog id-ja
            # skroz popunjen, ispisi gresku
            print("Record can't be inserted")

    def __find_by_id_overflow(self, id):
        # gde je prvo mogao biti slog, treba nam kao pocetna tacka
        initial_block_loc = self.hash(id)
        # gde sledece moze biti, idemo k blokova u desno, ali sa modulom b (brojem blokova u fajlu),
        # jer kraj fajla ne mora nuzno znaciti i kraj slobodnih blokova za upis
        # primer:
        # k = 3
        # |3.|7.|0.|4.|8.|1.|5.|9.|2.|6.|10.|
        # u ovom primeru inicijalni blok je blok 0., ali pomeranjem u desno sa modulom smo omogucili
        # da su svi blokovi maksimalno iskorisceni
        current_block_loc = (initial_block_loc + self.k) % self.b

        with open(self.filename, 'rb') as f:
            # otvaramo fajl
            while current_block_loc != self.hash(id):
                # dok god ne dodjemo opet do inicijalnog bloka, postoji sansa da se trazeni slog
                # nalazi u nekom bloku

                # pozicioniramo se na trenutni blok
                f.seek(current_block_loc * self.block_size)
                current_block = self.read_block(f)

                for i, record in enumerate(current_block):
                    # prolazimo kroz sve slogove trenutnog bloka
                    if record['status'] == 0:
                        # ako pronadjemo prazan, to je kraj iz istih razloga
                        # kao kod inicijalnog bloka
                        return None
                    if record['id'] == id and record['status'] == 1:
                        # ako pronadjemo blok sa istim id-jem, vracamo u kom bloku se nalazi i gde u tom bloku,
                        # samo ako nije logicki obrisan (tj. samo ako status == 1)
                        return current_block_loc, i

                # pomeranje za k blokova u desno, sa modulom b (ukupnim brojem blokova u fajlu)
                current_block_loc = (current_block_loc + self.k) % self.b

            # ako smo izasli iz petlje, to znaci da smo proverili svaki moguci blok i nismo pronasli dati slog
            return None

    def __true_delete_by_id_overflow(self, f, current_block_loc, id):
        current_block_loc = (current_block_loc + self.k) % self.b

        # jak edge case:
        # ako smo se vratili na maticni blok, znaci da smo oslobodili bar jedno mesto u svakom bloku lanca
        # do sada, pa mozemo da vratimo prazan slog jer postoji jedno prazno mesto
        if current_block_loc == self.hash(id):
            return self.get_empty_rec()

        # pozicioniramo se na trenutan blok i procitamo ga
        f.seek(current_block_loc * self.block_size)
        current_block = self.read_block(f)

        # trazimo da li postoji neki slog u ovom bloku ciji se hes id-ja podudara sa maticnim blokom
        # jer te slogove treba da vratimo jedno mesto u nazad u lancu
        next_removed_record = None
        i = 0
        for i, rec in enumerate(current_block):
            if self.hash(rec['id'] == self.hash(id)):
                # prvi slog ciji se hes id-ja podudara sa lokacijom maticnog baketa, je onaj koji cemo staviti
                # za jedno mesto u nazad u lancu
                next_removed_record = rec
                break

        # ako nismo pronasli ni jedan slog ciji se hes id-ja podudara sa lokacijom maticnog baketa,
        # znaci da je prethodni blok bio poslednji blok u lancu i mozemo vratiti prazan slog
        # koji ce popuniti poslednje mesto u prethodnom bloku
        if i == self.blocking_factor - 1:
            return self.get_empty_rec()

        # kada je pronadjen slog ciji se hes id-ja podudara sa lokacijom maticnog baketa, on je zapamcen
        # i od njega do kraja trenutnog bloka se svi slogovi pomeraju za jedno mesto u levo da se oslobodi prostor
        for j in range(i, self.blocking_factor - 1):
            current_block[j] = current_block[j + 1]

        # slog koji treba da popuni prazno mesto ovog bloka se dobija rekurzivnim pozivom ove funkcije
        # jednostavno zvuci jer se ova funkcija brine o tome da li vraca prazan ili je pronasla slog
        # ciji se hes id-ja podudara sa lokacijom maticnog baketa, takodje se lako brine o upisu u fajl
        # jer je lokacija trenutnog bloka zapamcena
        next_insertion_record = self.__true_delete_by_id_overflow(f, current_block_loc, id)

        # upis u trenutni blok na slobodno (poslednje) mesto
        current_block[self.blocking_factor - 1] = next_insertion_record
        f.seek(current_block_loc * self.block_size)
        self.write_block(f, current_block)

        # vracamo slog koji je obrisan tj. slog ciji se hes id-ja podudara sa lokacijom maticnog baketa,
        # ako smo stigli do ovde
        return next_removed_record
