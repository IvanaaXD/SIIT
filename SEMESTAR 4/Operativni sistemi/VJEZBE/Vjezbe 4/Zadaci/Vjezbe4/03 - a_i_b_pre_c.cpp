/* Napraviti konkurentni program sa tri niti.
 * Nit a ispisuje: "Ja sam nit a."
 * Nit b ispisuje: "Ja sam nit b."
 * Nit c ispisuje: "Ja sam nit c."
 * Obezbediti da se niti a i b, uvek izvrse pre niti c.
 */

 /*
 #include <mutex>
 #include <iostream>
 #include <thread>
 #include <condition_variable>

 using namespace std;

 class koordinator {
    int br_niti;
    int zavrseno_niti;
    mutex m;
    condition_variable c;

public:

    koordinator(int x) : br_niti(x), zavrseno_niti(0) {};

    void zavrsio() {
        unique_lock<mutex> l(m);
        zavrseno_niti++;
        if (zavrseno_niti == br_niti) {
            c.notify_all();
        };
    };

    void cekam() {
        unique_lock<mutex> l(m);
        while (zavrseno_niti != br_niti){
            c.wait(l);
        };
    };
 };

 mutex mm;

 void nit_a(koordinator &k) {
    unique_lock<mutex> lock(mm);
    cout << "Ja sam nit a" << endl;
    k.zavrsio();
 };

 void nit_b(koordinator &k) {
    unique_lock<mutex> lock(mm);
    cout << "Ja sam nit b" << endl;
    k.zavrsio();
 };

 void nit_c(koordinator &k) {
    k.cekam();
    unique_lock<mutex> lock(mm);
    cout << "Ja sam nit c" << endl;
 };

 int main() {

    koordinator k{2};

    thread t1(nit_a, ref(k));
    thread t2(nit_b, ref(k));
    thread t3(nit_c, ref(k));

    t1.join();
    t2.join();
    t3.join();

    return 0;
 };
 */
