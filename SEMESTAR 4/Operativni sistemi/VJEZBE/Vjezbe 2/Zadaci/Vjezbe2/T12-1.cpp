/* Sabrati korespodentne elemente vektora a i b, a zbirove smestiti na
 * odgovarajuce pozicije vektora c. Obezbediti da svako sabiranje obavlja
 * posebna nit.
 */

 /*
#include <vector>
#include <thread>
#include <iostream>

using namespace std;

void foo(const vector<int>& a, const vector<int>& b, int i, vector<int>& c) {
    c[i] = a[i] + b[i];
};

int main() {

    vector<int> a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    vector<int> b = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    vector<int> c (a.size());

    int n = a.size();
    thread t[n];

    for (int i = 0; i < n; i++) {
        t[i] = thread(foo, ref(a), ref(b), i, ref(c));
    }

    for (int i = 0; i < n; i++) {
        t[i].join();
    }

    vector<int>::const_iterator it;
    for (it = c.begin(); it!= c.end(); it++) {
        cout << *it << " ";
    }

    return 0;
};
*/
