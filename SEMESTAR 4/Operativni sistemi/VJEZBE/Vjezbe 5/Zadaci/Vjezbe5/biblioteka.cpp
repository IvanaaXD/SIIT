/*
	Modelovati iznajmljivanje jedne knjige u biblioteci. Biblioteka poseduje N primeraka ove knjige.
	Parametar N biblioteka dobija pri inicijalizaciji.
	Clan iznajmljuje primerak pozivom metode iznajmi().
	Ukoliko su svi primerci trenutno na citanju, clan biblioteke mora da saceka da neki primerak bude vracen u biblioteku.
	Primerak moze da bude kod clana na citanju neki slucajan vremenski period, koji nije duzi od 4 sekunde.
	Nakon citanja, clan vraca primerak u biblioteku pozivom metode vrati().
*/

#include <thread>
#include <iostream>
#include <cstdlib>
#include <mutex>
#include <condition_variable>

using namespace std;
using namespace chrono;

const int N = 5;
const int MEM = 7;

int rand_sync() {
    static mutex mx;
    unique_lock<mutex> l(mx);
    return rand();
}

class Biblioteka {
    int n;
    mutex m;
    condition_variable c;
    int busy;
    public:
        Biblioteka(int br) : n(br) {};

        void iznajmi() {
            unique_lock<mutex> l(m);

            while (busy == n) {
                c.wait(l);
            }

            busy++;
            c.notify_all();
        };

        void vrati() {

            unique_lock<mutex> l(m);
            busy--;
            c.notify_all();
        };
};

mutex term_mx;

void clan(Biblioteka& b, int brClanskeKarte) {
    {
        unique_lock<mutex> l(term_mx);
        cout << "Clan " << brClanskeKarte << " zeli da iznajmi knjigu." << endl;
    }
    b.iznajmi();
    {
        unique_lock<mutex> l(term_mx);
        cout << "Clan " << brClanskeKarte << " iznajmio knjigu." << endl;
    }
    this_thread::sleep_for(seconds(rand_sync()%4 + 1));
    b.vrati();
    unique_lock<mutex> l(term_mx);
    cout << "Clan " << brClanskeKarte << " vratio knjigu." << endl;
}

/*
int main () {

    Biblioteka b(N);
    thread t[MEM];

    for (int i = 0; i < MEM; i++) {
        t[i] = thread(clan, ref(b), i);
    }

    for (int i = 0; i < MEM; i++) {
        t[i].join();
    }

    return 0;
}
*/
