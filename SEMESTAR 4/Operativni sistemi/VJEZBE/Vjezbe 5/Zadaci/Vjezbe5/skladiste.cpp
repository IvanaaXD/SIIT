// Modelovati skladiste koje ima dve identicne rampe za istovar robe iz kamiona.

// Nosivost kamiona je maksimalno 7 tona.
// Kamioni nose obicnu ili zapaljivu robu.
// Kamioni sa zapaljivom robom imaju prednost pri istovaru.

// Kamion koji zeli da ostavi robu u skladistu poziva operaciju istovari().
// Kamion ceka ispred skadista dok jedna od rampi ne postane slobodna.
// Istovar traje onoliko sekundi koliko u kamionu ima tona robe.
// Operacija istovar() vraca pozivaocu informaciju o tome na kojoj rampi je
// kamion istovaren.

// Stvoriti 5 kamiona sa obicnom i 5 sa zapaljivom robom.

#include <mutex>
#include <condition_variable>
#include <iostream>
#include <thread>

using namespace std;

const int NOSIVOST = 7;
const int KAMIONI = 10;

class skladiste {
    int zapaljivih;
    mutex m;
    condition_variable o, z;
    bool rampa1;
    bool rampa2;

public:

   skladiste() :  zapaljivih(0) {
        rampa1 = true;
        rampa2 = false;
   };

   int istovari(int kolicina, bool zapaljivo) {

        unique_lock<mutex> l(m);

        if (zapaljivo) {
            zapaljivih++;
        };

        while (rampa1 == false && rampa2 == false) {
            if (zapaljivo) {
                z.wait(l);
            } else {
                o.wait(l);
            }
        };

        if (rampa1) {
            rampa1 = false;

            l.unlock();
            this_thread::sleep_for(chrono::seconds(kolicina));
            l.lock();

            rampa1 = true;

        } else {
            rampa2 = false;

            l.unlock();
            this_thread::sleep_for(chrono::seconds(kolicina));
            l.lock();

            rampa2 = true;

        }

        if (zapaljivo) {
            zapaljivih--;
        };

        if (zapaljivih) {
            z.notify_one();
        } else {
            o.notify_one();
        }

        if (rampa1) {
            return 0;
        } else {
            return 1;
        }




   };
};

void kamion(skladiste& s, int kolicina, bool zapaljivo) {
   static mutex term_m;
   {
      unique_lock<mutex> l(term_m);
      cout << "Kamion broj: " << this_thread::get_id() << " nosi "
           << kolicina << " tona ";
      if(zapaljivo) cout << "zapaljive robe" << endl;
      else          cout << "obicne robe" << endl;
   }
   int rampa = s.istovari(kolicina, zapaljivo);
   {
      unique_lock<mutex> l(term_m);
      cout << "Kamion broj: " << this_thread::get_id()
           << " istovaren na rampi " << rampa << " (nosio " << kolicina;
     if(zapaljivo) cout << "; zapaljivo)." << endl;
     else          cout << "; obicno)." << endl;
   }
}

/*
int main() {

    skladiste s{};
    thread t[KAMIONI];

    for (int i = 0; i < KAMIONI; i++) {
        t[i] = thread(kamion, ref(s), i * NOSIVOST % 6 + 1, i % 2);
    }

    for (int i = 0; i < KAMIONI; i++) {
        t[i].join();
    }

    return 0;
}*/
