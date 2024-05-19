/* Napraviti sekvencijalni program koji izračunava sumu svih elemenata vektora
 * sekvencijalno, koristeći funkciju f():
 *
 * f(ci begin, ci end, double& zbir);
 *
 * pri cemu je ci definisano kao
 *
 * typedef vector<double>::const_iterator ci;
 */

/*#include <iostream>
#include <vector>

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

    foo(v.begin(), v.end(), zbir);

    cout << "Zbir je: " << zbir << endl;

    return 0;
}
*/
