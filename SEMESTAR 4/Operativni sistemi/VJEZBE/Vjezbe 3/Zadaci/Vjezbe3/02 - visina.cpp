/* Napraviti konkurentni program sa 2 niti koje imaju isto telo niti. Svaka nit
 * najpre trazi od korisnika da unese svoju visinu. Nakon unosa, nit ispisuje
 * unetu vrednost na terminal.
 *
 * Sinhronizovati pristup terminalu kao deljenom resursu. Kada jedna nit stupi u
 * interakciju sa korisnikom, ne sme biti prekinuta dok se ne zavrsi kompletna
 * interakcija (i unos i ispis).
 */

 /*
#include <iostream>
#include <mutex>
#include <thread>

using namespace std;

mutex m;

void foo() {

    m.lock();
    int height;

    cout << "Unesite svoju visinu: " << endl;
    cin >> height;
    cout << "Vasa visina je: " << height << endl;

    m.unlock();
};

int main() {

    thread t1(foo);
    thread t2(foo);

    t1.join();
    t2.join();

    return 0;
};
*/
