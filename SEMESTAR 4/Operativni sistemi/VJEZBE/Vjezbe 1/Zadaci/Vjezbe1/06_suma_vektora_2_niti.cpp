/* Napraviti konkrentni program koji izračunava sumu svih elemenata vektora,
 * koristeći funkciju f():
 *
 * f(ci begin, ci end, double& zbir);
 *
 * pri cemu je ci defi+nisano kao
 *
 * typedef vector<double>::const_iterator ci;
 *
 * Podeliti racunanje sume na 2 dela tako da prvu polovinu vektora sumira prva
 * nit, a drugu polovinu druga nit.
 *
 * Napomena: ovakva optimizacija sumiranja je znacajna kada se radi na
 * dvojezgarnom procesoru za vektore velike duzine.
 */

/*#include <iostream>
#include <vector>
#include <thread>

using namespace std;

typedef vector<double>::const_iterator ci;

void foo(ci begin, ci end, double& zbir) {

    while (begin != end) {
        zbir += *begin;
        begin++;
    }
}

int main()
{
    vector<double> v = {1,2,3,4,5,6,7,8,9,10};
    double zbir = 0;

    ci c = v.begin() + v.size() / 2;

    thread t1(foo, v.begin(), c, ref(zbir));
    thread t2(foo, c, v.end(), ref(zbir));

    t1.join();
    t2.join();

    cout << "Zbir je: " << zbir << endl;

    return 0;
}
*/
