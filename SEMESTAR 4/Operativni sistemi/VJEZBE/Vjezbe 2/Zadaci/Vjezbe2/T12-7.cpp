/* Napraviti program za evidenciju identifikatora niti. U programu definisati
 * STL kontejner sa 5 elemenata (isto toliko ce biti i niti). Elementi
 * kontejnera su objekti koji predstavljaju identifikatore niti. Svaka nit treba
 * u dati STL kontejner da upise svoj identifikator i to u odgovarajuci element.
 *
 * Dakle, prva nit upisuje svoj identifikator u prvi element vektora, druga nit
 * u drugi element i tako redom. Kada se sve niti zavrse, potrebno je ispisati
 * identifikatore uskladistene u STL kontejneru.
 */

 /*
 #include <thread>
 #include <iostream>
 #include <vector>

 using namespace std;

 int n = 5;

 void foo(vector<thread::id>::iterator it) {
    *it = this_thread::get_id();
 };

 int main() {

    vector<thread::id> v (n);

    thread t[n];
    for (int i = 0; i < n; i++) {
        t[i] = thread(foo, v.begin() + i);
    }

    for (int i = 0; i < n; i++) {
        t[i].join();
    }

    for(int i = 0; i < n; i++) {
        cout << v[i] << " ";
    }

    return 0;
 };
*/
