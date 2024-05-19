/* Napisati program za unos reci sa tastature sa ukljucenom proverom ispravnosti
 * unosa. Dat je vektor recnik koji predstavlja reci koje sistem prepoznaje kao
 * ispravne. Funkcija proveri_ispravnost() utvrdjuje da li se prosledjena rec
 * nalazi u recniku.
 *
 * U glavnom programu korisnik unosi jednu po jednu rec. Nakon unosa reci, u
 * posebnoj niti se vrsi provera ispravnosti reci.
 *
 * Kada korisnik zavrsi unos svih reci, za svaku rec se ispisuje da li je
 * ispravno unesena.
 *
 * Primer ispisa:
 *
 *     Rec 1 ispravno napisana.
 *     Rec 2 neispravno napisana.
 *     Rec 3 ispravno napisana.
 */

 /*
#include <iostream>
#include <string>
#include <thread>
#include <vector>

using namespace std;

vector<string> recnik {"black", "red", "blue", "yellow", "white"};

void proveri_ispravnost(string rec, bool *rez) {
    *rez = false;

    vector<string> :: iterator it;
    for (it = recnik.begin(); it != recnik.end(); it++) {
        if (*it == rec) {
            *rez = true;
            break;
        };
    };
};

int main() {
  vector<thread *> niti;
  vector<bool *> rezultati;
  string rec;

  string finish = "";
  bool done = true;
  while (done) {

    cout << "Enter a string (type 'x' to exit): ";
    getline(cin, finish);

    if (finish == "x" || finish == "X") {
        done == false;
        break;
    };

    rezultati.push_back(new bool);
    niti.push_back(new thread(proveri_ispravnost, finish, rezultati[rezultati.size() - 1]));
  }

  int i = 0;
  for (vector<thread *>::iterator it = niti.begin(); it != niti.end(); it++) {
    (*it)->join();
  };


  for (int i = 1; i < rezultati.size() + 1; i++) {
    bool ispravna = *(rezultati[i]);
    cout << "Rec " << i << " napisana: " << (ispravna == true ? "ispravno" : "neispravno") << endl;
  };
}
*/
