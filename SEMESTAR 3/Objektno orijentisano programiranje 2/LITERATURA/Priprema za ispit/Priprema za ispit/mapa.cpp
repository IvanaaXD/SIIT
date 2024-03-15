#include <iostream>
using namespace std;
#include <map>


void foo(std::map<float, int>& mapa, std::map<int, float>& mapa2) {
	std::map<float, int>::iterator it;
	for (it = mapa.begin(); it != mapa.end(); ++it) {
		mapa2[it->second] = it->first;
	}
	std::map<int, float>::iterator it2;
	for (it2 = mapa2.begin(); it2 != mapa2.end(); ++it2) {
		cout << it2->first << endl;
		cout << it2->second << endl;
	}
}
