/* Napisati konkurentni program koji koristi STL kontejner <map>. U main-u
 * inicijalizovati mapu tako da sadrzi 10 elemenata pri cemu je kljuc broj od
 * 1-10 a vrednost id niti main.
 *
 * Kreirati 10 niti klasom thread. Svakoj niti se prosleduje njen redni broj
 * prilikom stvaranja i referenca na mapu. Svaka nit u mapi treba upise svoj id
 * na element kojem je kljuc redni broj date niti (1-10).
 *
 * Na kraju programa iz mape ispisati id-eve niti u obrnutom redosledu rednog
 * broja niti.
 */

 /*
 #include <map>
 #include <thread>
 #include <iostream>

 using namespace std;

 int n = 10;

void foo(map<int, thread::id>& mapa, int i) {
    mapa[i] = this_thread::get_id();
}

 int main() {

    map<int, thread::id> mapa;
    for (int i = 1; i <= n; i++) {
        mapa[i] = this_thread::get_id();
    }

    thread t[n];
    for (int i = 1; i < n+1; i++) {
        t[i] = thread(foo, ref(mapa), i);
    };

    for (int i = 1; i < n+1; i++) {
        t[i].join();
    };

    for (auto it = mapa.begin(); it != mapa.end(); it++) {
        cout << it->first << " " << it->second << endl;
    }

    return 0;
 };
*/
