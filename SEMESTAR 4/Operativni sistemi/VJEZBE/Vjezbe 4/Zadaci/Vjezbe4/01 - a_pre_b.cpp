/* Napraviti konkurentni program sa dve niti.
 * Nit a ispisuje: "Ja sam nit a."
 * Nit b ispisuje: "Ja sam nit b."
 * Obezbediti da se uvek izvrsi prvo nit a.
 */

 /*
 #include <thread>
 #include <iostream>
 #include <mutex>
 #include <condition_variable>

 using namespace std;

 class koordinator {
    enum redoslijed {PRVI, DRUGI};
    redoslijed na_redu_je;
    mutex m;
    condition_variable c;

public:

    koordinator() : na_redu_je(PRVI) {};

    void prvi_zavrsio() {
        unique_lock<mutex> l(m);
        c.notify_one();
        na_redu_je = DRUGI;
    };

    void drugi_ceka() {
        unique_lock<mutex> l(m);
        while (!(na_redu_je == DRUGI)) {
            c.wait(l);
        };
    };
 };

 koordinator k;

 void nit_a() {
    cout << "Ja sam nit a" << endl;
    k.prvi_zavrsio();
 };

 void nit_b() {
    k.drugi_ceka();
    cout << "Ja sam nit b" << endl;
};


 int main() {

    thread t1(nit_a);
    thread t2(nit_b);

    t1.join();
    t2.join();

    return 0;
 };
*/
