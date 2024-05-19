/* Definisati klasu parking koja modeluje parking prostor kapaciteta N mesta.
 * Kapacitet parkinga proslediti kao argument konstruktoru, pri instanciranju
 * deljene promenljive.
 *
 * Klasa parking ima operacije:
 *
 *     void parking::udji();
 *     void parking::izadji();
 *
 * Automobili koji dolaze na parking su predstavljeni nitima. Za ulazak na
 * parking, automobil poziva metodu udji(). Za izlazak sa parkinga, automobil
 * poziva metodu izadji(). Automobil se na parkingu zadrzava 3 sekunde.
 *
 * Pri ulasku, ukoliko su sva parking mesta zauzeta, automobil mora da saceka da
 * se neko parking mesto oslobodi.
 */

 /*
 #include <thread>
 #include <iostream>
 #include <mutex>
 #include <condition_variable>

 using namespace std;

 const int N = 5;
 const int CARS = 7;

class parking {
    int br_mjesta;
    condition_variable c;
    mutex evidencija;

  public:
	parking(int N): br_mjesta(N) {};
	void udji();
	void izadji();
};

void parking::udji() {
    unique_lock<mutex> l(evidencija);
    while (!br_mjesta) {
        c.wait(l);
    };
    br_mjesta--;
};

void parking::izadji() {
    unique_lock<mutex> l(evidencija);
    br_mjesta++;
    l.unlock();
    c.notify_one();
};

mutex m;

void automobil(parking& p) {
   p.udji();
   { unique_lock<mutex> l(m);
      cout << "Automobil " << this_thread::get_id() << " usao na parking." << endl;
   }
   this_thread::sleep_for(chrono::seconds(3));
   p.izadji();
   { unique_lock<mutex> l(m);
      cout << "Automobil " << this_thread::get_id() << " izasao sa parkinga." << endl;
   }
}

int main() {

    parking p(N);
    thread t[CARS];

    for(int i = 0; i < CARS; i++) {
        t[i] = thread(automobil, ref(p));
    };

    for(int i = 0; i < CARS; i++) {
        t[i].join();
    };
};
*/
