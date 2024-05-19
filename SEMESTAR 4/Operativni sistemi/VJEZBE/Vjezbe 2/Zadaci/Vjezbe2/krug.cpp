/* Napisati program koji proverava koje se od zadatih tacaka nalaze u/na
 * kruznici, a koje su van kruznice.
 * Tacke se nalaze u vektoru points:
 *
 * struct Point { double x, y; };
 * vector<Point> points;
 *
 * Definisati klasu:
 *
 * class Circle {
 *   ...
 * public:
 *   Circle(const Point& t, const double r_);
 *   void check(const vector<Point> p, vector<bool>& belong);
 * };
 *
 * Objekt tipa Circle, za svaku tacku iz vektora, izracunava da li se ona nalazi:
 *     - u krugu (ili na kruznici)
 *         + korespodentni element vektora belong dobija vrednost true ili
 *     - van kruga
 *         + korespodentni element vektora belong dobija vrednost false.
 *
 * Stvoriti nit koja poziva funkciju check nad objektom klase Circle.
 */

 /*
#include <iostream>
#include <thread>
#include <vector>

using namespace std;

struct Point {
    double x, y;
};

class Circle {
  Point p0;
  double r;
  bool in(const Point &p) {
    return (p.x - p0.x) * (p.x - p0.x) + (p.y - p0.y) * (p.y - p0.y) <= r * r;
  };

public:
    Circle(const Point& t, const double r_) : p0(t), r(r) {};

    void check(const vector<Point> p, vector<bool>& belong);
};

 void Circle::check(const vector<Point> p, vector<bool>& belong) {

    for (int i = 0; i < p.size(); i++) {
        belong[i] = in(p[i]);
    }
 };

 void f(Circle &c, vector<Point> p, vector<bool> &b) {
    c.check(p, ref(b));
 };

 int main() {
  Circle k({0, 0}, 3);
  vector<Point> p = {{1, 3}, {3, 3}, {0, 0}, {1, 1}, {0, 3}};
  vector<bool> b(p.size());

  thread t(f, ref(k), p, ref(b));
  t.join();

  for (int i = 0; i < b.size(); ++i) {
    cout << "Tacka " << i << " {" << p[i].x << ", " << p[i].y << "}";
    if (b[i])
      cout << " je u/na krugu." << endl;
    else
      cout << " je van kruga." << endl;
  }

  return 0;
}
*/
