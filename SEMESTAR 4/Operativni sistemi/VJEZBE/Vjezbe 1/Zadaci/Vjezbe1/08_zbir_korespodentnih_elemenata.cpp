/* Napraviti konkurentni program  (koristeći funkciju f()) koji sabira
 * korespodentne elemente kontejnera  a i b, a rezultat smešta u vektor zbir.
 *
 * Dato je telo niti:
 *
 * void f(ci a_begin, ci a_end, ci b_begin, vector<double>::iterator sum_begin);
 *
 * pri cemu je ci definisano kao
 *
 * typedef vector<double>::const_iterator ci;
 *
 * Program optimizovati za procesor sa 2 jezgra.
 *
 * Napomene: Podrazumeva se da su kontejneri a i b iste dužine.
 */

#include <iostream>
#include <vector>
#include <thread>

using namespace std;

typedef vector<double>::const_iterator ci;

void foo(ci a_begin, ci a_end, ci b_begin, vector<double>::iterator sum_begin) {

    while (a_begin != a_end) {
        *sum_begin = *a_begin + *b_begin;
        a_begin++;
        b_begin++;
        sum_begin++;
    }
}

int main()
{
    vector<double> a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
    vector<double> b = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
    vector<double> c (a.size());

    int n = a.size();

    ci half = a.begin() + n/2;

    vector<double>::iterator sum_begin1 = c.begin();
    vector<double>::iterator sum_begin2 = c.begin() + n/2;

    thread t1(foo, a.begin(), half, b.begin(), sum_begin1);
    thread t2(foo, half, a.end(), half, sum_begin2);

    t1.join();
    t2.join();

    for (int i = 0; i < n; i++) {
        cout << "Zbir je: " << c[i] << endl;
    }

    return 0;
}
