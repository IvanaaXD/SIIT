// Napraviti konkurentni program koji modeluje kreditno poslovanje banke.
// Banka odobrava kredite u dinarima i u evrima.

// Klijent trazi kredit pozivanjem operacije uzmi_kredit(),
// kojoj prosledjuje svotu koju zeli da pozajmi od banke i valutu u kojoj zeli da pozajmi.
// Klijent neko vreme koristi pozajmljena sredstva, pa ih vrati banci
// pozivanjem operacije vrati_kredit().

// Banka inicijalno poseduje odredjene svote dinara i evra
// na dva razlicita racuna, koje pozajmljuje.
// Banka odobrava kredite dok ima sredstava.
// Kada vise nema sredstava, banka ceka da klijenti vrate
// pretodno odobrene kredite pre nego sto odobri sledeci kredit.
// Banka odobrava kredite u proizvoljnom redosledu.

// Banka tezi tome da klijent ciji je zahtev moguce ispunitini
// (postoje sredstva) ne ceka na kredit.

// Komentari su obavezni

// Obrisati main i implementaciju banke (ostaviti enum).

//#define _GLIBCXX_USE_NANOSLEEP
/*
#include <iostream>
#include <thread>
#include <mutex>
#include <condition_variable>

using namespace std;

class Banka
{
    int posjeduje_evra;
    int posjeduje_dinara;
    mutex m;
    condition_variable cv[2]; // 0 - dinari, 1 - evri

public:
    enum valute
    {
        DINAR = 0,
        EVRO
    };
    Banka(int posjeduje_evra, int posjeduje_dinara)
        : posjeduje_evra(posjeduje_evra), posjeduje_dinara(posjeduje_dinara) {}

    int uzmi_kredit(int svota, valute valuta) {
        unique_lock<mutex> l(m);

        if (valuta==DINAR) {
            while (svota > posjeduje_dinara || posjeduje_dinara==0) {
                cv[0].wait(l);
            }

            posjeduje_dinara = posjeduje_dinara - svota;
            l.unlock();
            cv[0].notify_all();

            return posjeduje_dinara;
        } else {
            while (svota > posjeduje_evra || posjeduje_evra==0) {
                cv[1].wait(l);
            }

            posjeduje_evra = posjeduje_evra - svota;
            l.unlock();
            cv[1].notify_all();

            return posjeduje_evra;
        }
    };

    int vrati_kredit(int svota, valute valuta) {
        unique_lock<mutex> l(m);

        if (valuta==DINAR) {
            posjeduje_dinara = posjeduje_dinara + svota;
            cv[0].notify_all();

            return posjeduje_dinara;
        } else {
            posjeduje_evra = posjeduje_evra + svota;
            cv[1].notify_all();

            return posjeduje_evra;
        }
    };
};

string string_valuta(Banka::valute v) {
    if (v == Banka::DINAR)
        return " din ";
    else
        return " eur ";
}

mutex terminal;

void klijent(Banka& b, int klijent, int svota, Banka::valute v) {
    {
        unique_lock<mutex> l(terminal);
        cout << "Klijent " << klijent << " zeli da pozajmi " << svota << string_valuta(v) << endl;
    }

    int posjeduje = b.uzmi_kredit(svota, v);

    {
        unique_lock<mutex> l(terminal);
        cout << "Klijent " << klijent << " pozajmio " << svota << string_valuta(v) << " u banci je ostalo " << posjeduje << endl;
    }

    this_thread::sleep_for(chrono::seconds(1));
    posjeduje = b.vrati_kredit(svota, v);

    unique_lock<mutex> l(terminal);
    cout << "Klijent " << klijent << " vratio " << svota << string_valuta(v) << " u banci ima " << posjeduje  << endl;
}

const int DSVOTA = 30;
const int ESVOTA = 20;
const int KLIJENATA = 18;

int main()
{
    Banka b(ESVOTA, DSVOTA);
    thread t[KLIJENATA];

    for (int i = 0; i < KLIJENATA; i ++) {
        int svota = 0;
        if (i % 2 == 0) {
            svota = DSVOTA / 3;
        } else {
            svota = ESVOTA / 2;
        }
        t[i] = thread(klijent, ref(b), i, svota, (Banka::valute)(i % 2));
    }

    for (int i = 0; i < KLIJENATA; i ++) {
        t[i].join();
    }

    return 0;
}
*/
