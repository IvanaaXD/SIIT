/* Napraviti konkurentni program koji stvara jednu nit. Nit ima 2 parametra.
 * Jedan je referenca na ulaznu listu a drugi referenca na izlaznu. Nit treba da
 * elemente ulazne liste prebaci u izlaznu tako da stoje u obrnutom redosledu.
 * Ispisati izgled izlazne liste nakon rada niti.
 */

 /*
 #include <thread>
 #include <iostream>
 #include <list>

 using namespace std;

 void foo(list<int> &l1, list<int> &l2) {
    list<int>::reverse_iterator it;
    for (it = l1.rbegin(); it != l1.rend(); it++) {
       l2.push_back(*it);
    }
 };

 int main() {

    list<int> l1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    list<int> l2;

    thread t(foo, ref(l1), ref(l2));
    t.join();

    list<int>::const_iterator it;
    for (it = l2.begin(); it != l2.end(); it++) {
        cout << *it << " ";
    }

    return 0;
 };
*/
