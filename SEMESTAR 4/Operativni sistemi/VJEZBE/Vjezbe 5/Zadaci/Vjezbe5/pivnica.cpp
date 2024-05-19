// Napisati konkurentni program koji modeluje pivnicu.
// U pivnici postoji ogranicen broj mesta (konstanta STOLICA_U_PIVNICI).

// Pivopija poziva operaciju udji() kada zeli da udje u pivnicu.
// Pivopija ulazi u pivnicu ako u pivnici ima mesta, inace ceka da se
// pojavi slobodno mesto.

// Kada udje u pivnicu, pivopija narucuje pivo pozivanjem operacije naruci().
// Tocenje velikog piva traje 2 sekunde, a malog 1 sekundu.
// Tokom tocenja barmen je zauzet i ne moze da opsluzuje druge pivopije.

// Kada dobije pivo pivopija provede jos neko vreme u pivnici pijuci ga,
// a zatim napusti pivnicu pozivom operacije izadji().

// Stvoriti 10 pivopija (5 piju malo, a 5 veliko pivo)

// Komentari su obavezni.


#include<iostream>
#include <mutex>
#include <condition_variable>
#include <thread>

using namespace std;

const int STOLICA_U_PIVNICI = 7;
const int N = 10;

class Pivnica {
    int br_stolica;
    condition_variable c;
    condition_variable b;
    mutex k;
    mutex m;

public:
    enum Pivo { VELIKO, MALO };

    Pivnica(int stolica) : br_stolica(stolica) {};

    void udji() {
        unique_lock<mutex> l(m);
        if (br_stolica == 0) {
            c.wait(l);
        }

        br_stolica--;
    };

    void izadji() {
        unique_lock<mutex> l(m);
        br_stolica--;
        c.notify_all();
    };

    void naruci(Pivo velicina_piva) {
        unique_lock<mutex> l(k);

        l.unlock();

        if (velicina_piva = VELIKO) {
            this_thread::sleep_for(chrono::seconds(2));
        } else {
            this_thread::sleep_for(chrono::seconds(1));
        }

        l.lock();
    };
};

void pivopija(Pivnica& pivnica, Pivnica::Pivo velicina_piva) {
    static mutex term_m;
    {
        unique_lock<mutex> l(term_m);
        cout  << this_thread::get_id() << " Pokusavam da udjem u pivnicu..." << endl;
    }
    pivnica.udji();
    {
        unique_lock<mutex> l(term_m);
        cout  << this_thread::get_id() << " Usao u pivnicu. Narucujem";
        if(velicina_piva == Pivnica::VELIKO)
            cout << " VELIKO ";
        else
            cout << " MALO ";
        cout << "pivo." << endl;
    }
    pivnica.naruci(velicina_piva);
    // 3 sekunde pivopija pije pivo, a zatim napusta pivnicu.
    this_thread::sleep_for(chrono::seconds(3));
    {
        unique_lock<mutex> l(term_m);
        cout  << this_thread::get_id() << " Popio. Odoh..." << endl;
    }
    pivnica.izadji();
};

/*
int main() {

    Pivnica p(STOLICA_U_PIVNICI);
    thread t[N];

    for (int i = 0; i < N; i++) {
        t[i] = thread(pivopija, ref(p), (Pivnica::Pivo)(i % 2));
    }

    for (int i = 0; i < N; i++) {
        t[i].join();
    }

    return 0;
}
*/
