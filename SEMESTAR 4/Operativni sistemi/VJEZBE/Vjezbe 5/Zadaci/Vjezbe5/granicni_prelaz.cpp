/*
Modelovati granicni prelaz sa 4 rampe za propustanje (gledano samo u jednom pravcu) pri cemu su tri rampe za automobile,
a jedna za kamione.

Od toga je jedna automobilska rampa za automobile iz Evropske unije a ostale 2 za automobile iz ostalih zemalja.
Automobili ulaze na rampe (ako postoji slobodna odgovarajuceg tipa a u suprotnom cekaju).

Obicni automobili cekaju na jednoj od 2 rampe za obicne automobile, sa tim sto cekaju na rampi na kojoj je red cekanja kraci.
Zadrzavaju se na rampama onoliko sekundi koliko ima putnika (maksimalno 5).

Na rampi za kamione kamioni se zadrzavaju onoliko sekundi koliko imaju robe u sebi.

Komentari su obavezni!
*/

/*
#include <iostream>
#include <thread>
#include <set>
#include <string>
#include <mutex>
#include <condition_variable>

using namespace std;

int rand_sync() {
    static mutex mx;
    unique_lock<mutex> l(mx);
    return rand();
}

enum tip_vozila { EU_AUTOMOBIL=0, OBICAN_AUTOMOBIL, KAMION };


class Prelaz {
    mutex m;
    condition_variable obicni_cv[2];
    condition_variable kamioni_cv;
    condition_variable eu_cv;
    bool slobodne_rampe[4]; // 0, 1 - obicni, 2 - eu, 3 -kamioni
    int br_auta[2]; // 0- prva rampa, 1 - druga rampa

public:
    Prelaz() {
        for (int i = 0; i < 4; i++) {
            slobodne_rampe[i] = true;
        }
        for (int i = 0; i < 2; i++) {
            br_auta[i] = 0;
        }
    }

    string predji_prelaz(tip_vozila tip, int kolicina_robe_ili_putnika) {
        unique_lock<mutex> l(m);

        if (tip==EU_AUTOMOBIL) {

            while (slobodne_rampe[2]==false) {
                eu_cv.wait(l);
            }

            slobodne_rampe[2]=false;
            l.unlock();
            this_thread::sleep_for(chrono::seconds(kolicina_robe_ili_putnika));
            l.lock();
            slobodne_rampe[2]=true;
            eu_cv.notify_one();

            return "EU PRELAZ";
        }

        else if (tip==KAMION) {

            while (slobodne_rampe[3]==false) {
                kamioni_cv.wait(l);
            }

            slobodne_rampe[3]=false;
            l.unlock();
            this_thread::sleep_for(chrono::seconds(kolicina_robe_ili_putnika));
            l.lock();
            slobodne_rampe[3]=true;
            kamioni_cv.notify_one();

            return "KAMION PRELAZ";
        }

        else {
            int prelaz = 1;
            if (br_auta[0] < br_auta[1]) {
                prelaz = 0;
            }

            br_auta[prelaz]++;

            while(slobodne_rampe[prelaz]==false) {
                obicni_cv[prelaz].wait(l);
            }

            slobodne_rampe[prelaz]=false;
            l.unlock();
            this_thread::sleep_for(chrono::seconds(kolicina_robe_ili_putnika));
            l.lock();
            slobodne_rampe[prelaz]=true;
            br_auta[prelaz]--;
            obicni_cv[prelaz].notify_one();

            return "OBICNI PRELAZ";
        }

    }
};

const int VOZILA = 10;

mutex terminal;

void prelazi(Prelaz& p, int tipVozila, int roba) {

    {
        unique_lock<mutex> l(terminal);
        if (tipVozila==0) {
            cout << "Vozilo eu automobil koji prevozi " << roba << " putnika hoce da predje granicu." << endl;
        } else if (tipVozila==1) {
            cout << "Vozilo obicni automobil koji prevozi " << roba << " putnika hoce da predje granicu." << endl;
        } else {
            cout << "Vozilo kamion koji prevozi " << roba << " tona hoce da predje granicu." << endl;
        }
    }

    if (tipVozila==0) {
        p.predji_prelaz(EU_AUTOMOBIL, roba);
    } else if (tipVozila==1) {
        p.predji_prelaz(OBICAN_AUTOMOBIL, roba);
    } else {
        p.predji_prelaz(KAMION, roba);
    }

    unique_lock<mutex> l(terminal);
    if (tipVozila==0) {
        cout << "Vozilo eu automobil koji prevozi " << roba << " putnika preslo granicu." << endl;
    } else if (tipVozila==1) {
        cout << "Vozilo obicni automobil koji prevozi " << roba << " putnika preslo granicu." << endl;
    } else {
        cout << "Vozilo kamion koji prevozi " << roba << " tona preslo granicu." << endl;
    }
}


int main() {

    Prelaz p;
    thread t[VOZILA];

    for (int i = 0; i < VOZILA; i++) {
        t[i] = thread(prelazi, ref(p), (tip_vozila) (i%3), rand_sync()%2);
    }
    for (int i = 0; i < VOZILA; i++) {
        t[i].join();
    }

    return 0;
}
*/
