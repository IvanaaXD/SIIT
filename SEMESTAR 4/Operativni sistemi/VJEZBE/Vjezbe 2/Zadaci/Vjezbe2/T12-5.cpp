/* Napraviti konkurentni program koji pravi srpsko engleski recnik iz englesko
 * srpskog recnika. Recnik ima proizvoljan broj reci (bitno je da bude vise od
 * jedne). Posao treba obaviti u jednoj niti. Ispisati englesko srpski i srpsko
 * engleski recnik na kraju programa.
 *
 * Napomena: ispis prevedenih reci ne mora biti u redosledu unesenih reci
 * prilikom formiranja recnika, ali prevod mora biti tacan.
 */

 /*
 #include <thread>
 #include <map>
 #include <iostream>

 using namespace std;

 void foo(map<string, string> &eng_srp,map<string, string> &srp_eng) {
    for (auto it = eng_srp.begin(); it != eng_srp.end(); it++) {
        srp_eng[it->second] = it->first;
    };
 };

 int main() {

    map<string, string> eng_srp = {
        {"dog", "pas"},
        {"apple", "jabuka"},
        {"cat", "macka"},
    };
    map<string, string> srp_eng;

    thread t(foo, ref(eng_srp), ref(srp_eng));
    t.join();

    for (auto it = srp_eng.begin(); it != srp_eng.end(); it++) {
        cout << it->first << " " << it->second << endl;
    };

    return 0;
 };
*/
