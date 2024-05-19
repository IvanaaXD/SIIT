/*
Modelovati napadacku akciju fudbalskog kluba Barselona.
Tim sadrzi 11 igraca. Ponasanje igraca predstavljeno je datom funkcijom igrac.
Igraci medjusobno dodaju loptu, koja je predstavljena datom klasom Lopta.

Igrac mora da saceka da dobije loptu. Kada dobije loptu, igrac drzi loptu slucajan
vremenski period izmedju 1 i 3 sekunde i predaje je bilo kojem drugom igracu.
Pri predaji, lopta moze da ide unapred
(prema protivnickom golu) ili unazad. 2/3 predaja lopti ide ka napred.

Nakon 12 predaja lopti unapred, Barselona postize gol, cime se program zavrsava.
Ako pri predaji lopte igrac postigne gol, metoda dodaj_loptu vraca broj dresa igraca koji je postigao gol.
Nakon postignut gola, za sve ostale igrace metoda dodaj_loptu treba da vrati vrednost 0.
Ako gol nije postignut, metoda vraca vrednost "-1".

Barselona nikad ne gubi loptu.
*/

#include <thread>
#include <iostream>
#include <cstdlib>
#include <mutex>
#include <condition_variable>

using namespace std;
using namespace chrono;

const int N = 11;

class Lopta {
    mutex m;
    int potrebno_za_gol;
    int broj_dodavanja;
    bool lopta_slobodna;
    condition_variable red_za_loptu;
    bool pao_gol;

    public:

        Lopta(int n) : potrebno_za_gol(n) {};

        int dodaj_loptu(int broj_dresa) {
            unique_lock<mutex> l(m);

            while (!lopta_slobodna && !pao_gol) {
                red_za_loptu.wait(l);
            }

            if (pao_gol) {
                return 0;
            }

            lopta_slobodna = false;

            l.unlock();
            this_thread::sleep_for(seconds(rand() % 3 + 1));
            l.lock();

            bool naprijed = rand() % 3 == 2 ? false : true;

            if (naprijed) {
                broj_dodavanja++;
            }

            lopta_slobodna = true;

            if (broj_dodavanja = potrebno_za_gol) {
                pao_gol = true;
                red_za_loptu.notify_all();
                return broj_dresa;
            } else {
                red_za_loptu.notify_one();
                return -1;
            }


        };
};

mutex term_m;
void igrac(Lopta &lopta, int broj_dresa) {
    {
        unique_lock<mutex> l(term_m);
        cout << "Igrac sa brojem " << broj_dresa << " je izasao na teren." << endl;
    }
    int strelac = -1;
    do {
        strelac = lopta.dodaj_loptu(broj_dresa);
        cout << "Igrac " << broj_dresa << " dodao loptu." << endl;
        this_thread::sleep_for(seconds(1));
        if (strelac > 0) {
            unique_lock<mutex> l(term_m);
            cout << "GOOOOL!!! Pogodak je postigao igrac sa brojem " << strelac << "!" << endl;
        }
    } while (strelac == -1);
}

/*
int main() {

    Lopta l(12);
    thread t[N];

    for (int i = 0; i < N; i++) {
        t[i] = thread(igrac, ref(l), i);
    }

    for (int i = 0; i < N; i++) {
        t[i].join();
    }

    return 0;
}
*/
