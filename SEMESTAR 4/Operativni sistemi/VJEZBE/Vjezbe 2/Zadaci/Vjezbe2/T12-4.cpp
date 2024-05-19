/* Napraviti konkurentni program koji pronalazi element najblizi 0 iz zadatog
 * niza brojeva. Posao podeliti tako da ga izvrsavaju 3 niti. Duzina niza
 * brojeva treba da je deljiva sa 3.
 */

 /*
 #include <thread>
 #include <iostream>
 #include <math.h>

 using namespace std;

void foo(int* a, int b, int e, int& min_num) {
    min_num = a[b];
    for (int i = b + 1; i < e; i++) {
        if (abs(a[i]) < min_num) {
            min_num = a[i];
        }
    }
}

 int main() {

    int a[] = {16, 22, 30, -435, 975, -3, -75, 28, 59, 103, 134, 4};
    int n = 12;
    int k = n / 3;

    int min_num1 = 0;
    int min_num2 = 0;
    int min_num3 = 0;

    thread t1(foo, ref(a), 0, k, ref(min_num1));
    thread t2(foo, ref(a), k, 2*k, ref(min_num2));
    thread t3(foo, ref(a), 2*k, 3*k, ref(min_num3));

    t1.join();
    t2.join();
    t3.join();

    int minimum = min_num1;
    if (min_num2 < minimum) {
        minimum = min_num2;
    }
    if (min_num3 < minimum) {
        minimum = min_num3;
    }

    cout << "Broj najblizi 0 je: " << minimum << endl;

    return 0;
 };
*/
