// Parking sa 3 ulaza.
// Na parking automobili ulaze sa ulaza 0, 1 i 2 po round-robin protokolu
// (jedan udje sa prvog, jedan sa drugog, jedan sa treceg i tako u krug).

// Automobilu se pri stvaranju prosledjuje vreme (u sekundama) koje se on
// zadrzave na parkingu.
// U programu uvek ima jednak broj automobila na svim ulazima!

#include <thread>
#include <iostream>
#include <cstdlib>
#include <mutex>
#include <condition_variable>

using namespace std;
using namespace chrono;

const int N =12;

class parking {
    mutex m;
    int slobodna_mjesta;
    int aktivan_ulaz;
    condition_variable ulaz1_cv;
    condition_variable ulaz2_cv;
    condition_variable ulaz3_cv;

public:

   parking() : slobodna_mjesta(1), aktivan_ulaz(0){};

   void udji(int ulaz) {

        unique_lock<mutex> l(m);

        while (slobodna_mjesta == 0 || aktivan_ulaz != ulaz) {
            if (ulaz == 0) {
                ulaz1_cv.wait(l);
            } else if (ulaz == 1) {
                ulaz2_cv.wait(l);
            } else {
                ulaz3_cv.wait(l);
            }
        }
        --slobodna_mjesta;
        aktivan_ulaz = (aktivan_ulaz+1)%3;
   };

   void izadji() {

        unique_lock<mutex> l(m);
        ++slobodna_mjesta;

        if (aktivan_ulaz == 0) {
            ulaz1_cv.notify_one();
        } else if (aktivan_ulaz == 1) {
            ulaz2_cv.notify_one();
        } else {
            ulaz3_cv.notify_one();
        }
   };
};

void automobil(parking& p, int ulaz, int ostajem_na_parkingu) {
    static mutex term_m;
    thread::id id = this_thread::get_id();
    {
        unique_lock<mutex> l(term_m);
        cout<<"Automobil "<<id<<" pokusava da udje na parking na ulaz "<< ulaz<<endl;
    }
    p.udji(ulaz);
    {
        unique_lock<mutex> l(term_m);
        cout<<"Automobil "<<id<<" usao na parking na ulaz "<< ulaz<<endl;
    }
    this_thread::sleep_for(chrono::seconds(ostajem_na_parkingu));
    p.izadji();
    unique_lock<mutex> l(term_m);
    cout<<"Automobil "<<id<<" izasao sa parkinga."<<endl;
}

/*
int main() {

    parking p;
    thread t[N];

    for(int i = 0; i < N; ++i) {
        t[i] = thread(automobil, ref(p), i%3, i%2);
    };

    for(int i = 0; i < N; ++i) {
        t[i].join();
    }

    return 0;
};
*/
