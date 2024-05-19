/* Napraviti konkrentni program koji izračunava sumu svih elemenata vektora,
 * koristeći funkciju f():
 *
 * f(ci begin, ci end, double& zbir);
 *
 * pri cemu je ci definisano kao
 *
 * typedef vector<double>::const_iterator ci;
 *
 * Podeliti racunanje sume na N delova tako svaka nit sabira duzina vektora/broj
 * niti elemenata vektora.
 */

/*#include <iostream>
#include <vector>
#include <thread>

using namespace std;

typedef vector<double>::const_iterator ci;
const int n = 5;

void foo(ci begin, ci end, double& zbir) {

    while (begin != end) {
        zbir += *begin;
        begin++;
    }
}

int main()
{
    vector<double> v = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
    double zbirovi[n];

    for (int i = 0; i < n; i++) {
        zbirovi[i] = 0;
    }

    int k = v.size() / n;
    ci c_begin = v.begin();
    ci c_end = c_begin + k;

    thread t[n];
    for (int i = 0; i < n - 1 ; i++) {
        t[i] = thread(foo, c_begin, c_end, ref(zbirovi[i]));
        c_begin += k;
        c_end += k;
    }

    t[n - 1] = thread(foo, c_begin, v.end(), ref(zbirovi[n - 1]));

    for (int i = 0; i < n; i++) {
        t[i].join();
    }

    double zbir = 0;
    for (int i = 0; i < n; i++) {
        zbir += zbirovi[i];
    }

    cout << "Zbir je: " << zbir << endl;

    return 0;
}
*/
