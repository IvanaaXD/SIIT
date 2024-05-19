/* Napraviti konkurentni program koji modeluje klasu brojača.
 * Interfejs klase sadrži sledeće metode:
 *
 * class brojac {
 * public:
 *     void inc();
 *     void dec();
 *     friend ostream& operator<<(ostream& , brojac& );
 * };
 *
 * Metode inc i dec povećavaju, odnosno smanjuju vrednost brojača za 1. Operator
 * << služi za ispis brojača na ekran. Klasa mora biti thread-safe (da garantuje
 * ispravan rad i ako se objektu klase pristupa iz razlicitih niti).
 *
 * Kreirati jednu instancu date klase kojoj pristupaju 2 niti.
 *
 * Jedna nit poziva metodu uvećavanja brojača 1000000 puta, a druga metodu
 * smanjivanja brojača 1000000 puta.
 *
 * Na kraju programa ispisati konačnu vrednost brojača.
 */
/*
 #include <iostream>
 #include <mutex>
 #include <thread>

 using namespace std;

 mutex m;
 const int ITERATIONS = 1000000;


 class brojac {
    int broj;
    mutex m;

    public:
        brojac() : broj(0) {};

        void inc() {
            unique_lock<mutex> l(m);
            ++broj;
        };

        void dec() {
            unique_lock<mutex> l(m);
            --broj;
        };

        friend ostream &operator<<(ostream &os, brojac &b) { // preklopljen operator za ispis objekta klase brojac. Ispisuje se atribut "broj" iz objekta
            unique_lock<mutex> l(b.m);                         // pre pristupa broju, zakljucava se propusnica da bi se sprecilo stetno preplitanje
            os << b.broj << endl;
            return os;
          }
 };

 brojac b;

 void incc() {
    for (int i = 0; i < ITERATIONS; i++) {
        b.inc();
    }
 };

 void decc() {
    for (int i = 0; i < ITERATIONS; i++) {
        b.dec();
    }
 };

 int main() {

    thread t1(incc);
    thread t2(decc);

    t1.join();
    t2.join();

    cout << b;
    return 0;
 };
*/
