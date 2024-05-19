/* Napraviti konkurentni program koji kreira 5 niti od kojih svaka izvrsava isti
 * kod (svaka nit ima isto telo niti).
 * Svaka nit dobija svoj redni broj prilikom kreiranja.
 * U telu niti svaka nit treba da ispise svoj redni broj.
 */

/*#include <iostream>
#include <thread>

using namespace std;

void foo() {
    cout<< this_thread::get_id()<< endl;
}

int main()
{
    thread t[5];
    for (int i = 0; i < 5; i++) {
        t[i] = thread(foo);
    }

    for (int i = 0; i < 5; i++) {
        t[i].join();
    }

    return 0;
}
*/
