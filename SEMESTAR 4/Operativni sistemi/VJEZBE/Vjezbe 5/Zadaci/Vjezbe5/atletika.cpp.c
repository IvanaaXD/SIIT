/*
	Modelovati koriscenje zaletista na atletskom mitingu.
	Isto zaletiste koriste dve discipline: skok u dalj i bacanje koplja.
	Zaletiste ne mogu istovremeno da koriste dva takmicara.
	Discipline se naizmenicno smenjuju na zaletistu (jedan skakac u dalj, pa jedan bacac koplja i tako redom).

	Skok u dalj za jednog takmicara traje 1 sekundu. Bacanje koplja 2 sekunde.
	Metodu skaci poziva skakac u dalj. Metoda vraca duzinu u metrima koliko je takmicar skocio
	(izmedju 0 i 9 metara moze skociti) i koliko je ukupno trebalo vremena da zavrsi skok
	(koliko je zajedno trajalo cekanje i skakanje).
	Metodu baciKoplje poziva bacac koplja. Metoda vraca duzinu u metrima koliko je takmicar bacio koplje
	(izmedju 0 i 100 metara moze baciti) i koliko je ukupno trebalo vremena da zavrsi bacanje koplja
	(koliko je zajedno trajalo cekanje i bacanje koplja).
*/

#include <iostream>
#include <mutex>
#include <thread>
#include <condition_variable>
#include <chrono>
#include <random>

using namespace std;
using namespace chrono;

const int SKAKACI = 5;
const int BACACI = 4;

struct povratna_vrednost {
    int duzina;
    duration<double, milli> trajanje;
};

class AtletskaStaza {
    mutex m;
    condition_variable skakaci;
    condition_variable bacaci;
    int posljednja_disciplina;
    int br_skakaca;
    int br_bacaca;
    enum discpilina {SKOK=0, BACANJE};

    public:
		AtletskaStaza(int bs, int bb) {
            trenutna_disciplina = -1;
            br_bacaca = bb;
            br_skakaca = bs;
		}

		povratna_vrednost skaci() {
    `
            auto poceo = steady_clock::now();

            unique_lock<mutex> l(m);
            while (posljednja_disciplina=disciplina[0]) {
                skakaci.wait(l);
            }

            posljednja_disciplina=disciplina[0];
            br_skakaca--;
            l.unlock();

            this_thread::sleep_for(chrono::seconds(disciplina[0]+1))

            auto zavrsio = steady_clock::now();

            if (br_bacaca > 0) {
                bacaci.notify_one();
            } else {
                skakaci.notify_all();
            }

            return povratna_vrednost(rand()%10, zavrsio-poceo);
		}

		povratna_vrednost baciKoplje() {

            auto poceo = steady_clock::now();

            unique_lock<mutex> l(m);
            while (posljednja_disciplina=disciplina[1]) {
                bacaci.wait(l);
            }

            posljednja_disciplina=disciplina[1];
            br_bacaca--;
            l.unlock();

            this_thread::sleep_for(chrono::seconds(disciplina[1]+1))

            auto zavrsio = steady_clock::now();

            if (br_skakaca> 0) {
                skakaci.notify_one();
            } else {
                bacaci.notify_all();
            }

            return povratna_vrednost(rand()%100, zavrsio-poceo);
		}
};

mutex terminal;

void atletika(AtletskaStaza& a, int disc) {
    {
        unique_lock<mutex> l(terminal);
        if (disc == 0) {
            cout << "Skakac " << this_thread.get_id() << " ceka da skoci" << endl;
        } else if (disc == 1) {
            cout << "Bacac " << this_thread.get_id() << " ceka da baci" << endl;
        }
    }

    povratna_vrednost p;
    if (disc == 0) {
        p = a.skaci();
    } else if (disc == 1) {
        p = a.baciKoplje();
    }

    {
        unique_lock<mutex> l(terminal);
        if (disc == 0) {
            cout << "Skakac " << this_thread.get_id() << " je skocio" << p.duzina << " metara i trebalo mu je " << p.trajanje << " sekundi"  <<  endl;
        } else if (disc == 1) {
            cout << "Bacac " << this_thread.get_id() << " je bacio" << p.duzina << " metara i trebalo mu je " << p.trajanje << " sekundi"  <<  endl;
        }
    }
}

int main() {

    AtletskaStaza(SKAKACI, BACACI);
    n = BACACI + SKAKACI;
    thread t[n];

    for (int i = 0; i < n; i++) {
        t[i] = thread();
    }

    for (int i = 0; i < n; i++) {
        t[i].join();
    }

    return 0;
}
