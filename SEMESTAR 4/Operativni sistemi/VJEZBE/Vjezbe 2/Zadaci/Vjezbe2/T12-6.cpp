/* Napraviti konkurentni program za ispis identifikatora niti sa odredjenim
 * rednim brojem. Na pocetku programa potrebno je pitati korisnika da unese
 * redni broj niti. Program pokrece 10 niti. Svaka nit pri izvrsavanju ispisuje
 * "Pozdrav iz niti X", pri cemu je X redni broj niti. Main funkcija treba da
 * ispise identifikator niti ciji je redni broj korisnik uneo.
 */

 /*
#include <thread>
#include <iostream>

using namespace std;

const int k = 10;

void foo(int i, int x) {
    cout << "Pozdrav iz niti " << i << endl;
}

int main() {
    int n = 0;

    cout << "Enter a number of thread you want (between 1 and 10): ";
    cin >> n;

    thread t[k];
    for (int i = 0; i < k; i++) {
        t[i] = thread(foo, i, n);
    }

    cout << "Za redni broj niti: " << n << "identifikator je: " << t[n].get_id() << endl;

    for (int i = 0; i < k; i++) {
        t[i].join();
    }

    return 0;
}
*/
