/* Napraviti konkurentni program koji sadrzi main funkciju i 2 niti. Obe niti
 * pristupaju istoj celobrojnoj promenljivoj brojac, koja inicijalno ima
 * vrednost 0.
 * Prva nit 1000000 puta uvecava vrednost brojaca.
 * Druga nit isti broj puta smanjuje vrednost brojaca.
 *
 * Ukoliko je program ispravno napisan, na kraju programa vrednost brojaca mora
 * biti 0.
 */
/*
#include <iostream>
#include <mutex>
#include <thread>

using namespace std;

const int ITERATIONS = 1000000;

int brojac = 0;

mutex m;

 void incc() {
    for (int i = 0; i < ITERATIONS; i++){
        m.lock();
        ++brojac;
        m.unlock();
    };
 };

 void decc() {
    for (int i = 0; i < ITERATIONS; i++){
        m.lock();
        --brojac;
        m.unlock();
    };
 };

 int main() {

    thread t1(incc);
    thread t2(decc);

    t1.join();
    t2.join();

    cout << brojac << endl;

    return 0;
 };
*/
