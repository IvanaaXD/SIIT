// Modelovati benzinsku pumpu na kojoj se toce 3 vrste goriva:
// bezolovni, dizel i super.
// Svako vozilo koje zeli da natoci gorivo mora da stane u red za tu vrstu goriva.
// Tocenje bezolovnog traje 1 sekundu, dizela 2, a supera 3 sekunde.

// Vozilo koje zeli da natoci gorivo poziva operaciju natoci() i prosledjuje joj
// vrstu goriva koja mu je potrebna.

// Treba stvoriti po 4 vozila za svaku vrstu goriva.

// Komentari su obavezni.

/*
#include <thread>
#include <mutex>
#include <condition_variable>
#include <iostream>

using namespace std;

const int NUM_OF_CARS = 6;

class benzinska_pumpa {
    mutex m;
    condition_variable bezolovni_cv;
    condition_variable super_cv;
    condition_variable dizel_cv;
    bool bezolovni_busy;
    bool super_busy;
    bool dizel_busy;
public:
   enum vrsta_goriva { BEZOLOVNI=0, DIZEL, SUPER };

   benzinska_pumpa() : bezolovni_busy(false), super_busy(false), dizel_busy(false) {};

   void natoci(vrsta_goriva gorivo) {

       unique_lock<mutex> l(m);

       if (gorivo == DIZEL) {
            while (dizel_busy) {
                dizel_cv.wait(l);
            }

       } else if (gorivo == SUPER) {
            while (super_busy) {
                super_cv.wait(l);
            }
       } else if (gorivo == BEZOLOVNI) {
            while (bezolovni_busy) {
                bezolovni_cv.wait(l);
            }
       }

        if (gorivo == DIZEL) {
            dizel_busy = true;
            l.unlock();
            this_thread::sleep_for(chrono::seconds(2));
            l.lock();
        } else if (gorivo == SUPER) {
            super_busy = true;
            l.unlock();
            this_thread::sleep_for(chrono::seconds(3));
            l.lock();
        } else if (gorivo == BEZOLOVNI) {
            bezolovni_busy = true;
            l.unlock();
            this_thread::sleep_for(chrono::seconds(1));
            l.lock();
        }

        if (gorivo == DIZEL) {
            dizel_busy = false;
            dizel_cv.notify_all();
        } else if (gorivo == SUPER) {
            super_busy = false;
            super_cv.notify_all();
        } else if (gorivo == BEZOLOVNI) {
            bezolovni_busy = false;
            bezolovni_cv.notify_all();
        }

   };
};

string naziv_goriva(benzinska_pumpa::vrsta_goriva gorivo) {
   if(gorivo==benzinska_pumpa::BEZOLOVNI)  return "bezolovni";
   else if(gorivo==benzinska_pumpa::SUPER) return "super";
   else                                    return "dizel";
}

void vozilo(benzinska_pumpa& pumpa, benzinska_pumpa::vrsta_goriva gorivo) {
   static mutex term_m;
   {
   unique_lock<mutex> l(term_m);
   cout << "Vozilu " << this_thread::get_id()
        << " je potrban " << naziv_goriva(gorivo) << endl;
   }
   pumpa.natoci(gorivo);
   unique_lock<mutex> l(term_m);
   cout << "Vozilo " << this_thread::get_id()
        << " je natocilo " << naziv_goriva(gorivo) << endl;
}


int main() {

    benzinska_pumpa b;
    thread t[NUM_OF_CARS];

    for (int i = 0; i < NUM_OF_CARS; i++) {
        t[i] = thread(vozilo, ref(b), (benzinska_pumpa::vrsta_goriva)(i % 3));
    }

    for (int i = 0; i < NUM_OF_CARS; i++) {
        t[i].join();
    }

    return 0;

}
*/
