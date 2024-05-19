/* Napraviti konkurentni program koji pita korisnika koliko niti da stvori, a
 * zatim stvara zadati broj niti. Pri instanciranju nit dobija redni broj pod
 * kojim je stvorena. Svaka nit ispisuje svoj redni broj i svoj identifikator.
 */

 /*
 #include <thread>
 #include <iostream>

 using namespace std;

 void foo(int i) {
     cout << "Redni broj niti je: " << i << ", a identifikator je: " << this_thread::get_id() << endl;
 };

 int main() {

    int n = 0;

    cout << "Enter a number of threads: ";
    cin >> n;

    thread t[n];
    for (int i = 0; i < n; i++) {
        t[i] = thread(foo, i);
    };

    for (int i = 0; i < n; i++) {
        t[i].join();
    };

    delete[] t;
    return 0;
 };
*/
