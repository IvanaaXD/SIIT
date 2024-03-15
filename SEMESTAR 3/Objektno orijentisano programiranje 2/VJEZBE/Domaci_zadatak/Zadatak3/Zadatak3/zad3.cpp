#include <iostream>
#include <string>

using namespace std;

void clear() {
#ifdef _WIN32
	system("cls");  // Windows
#else
	system("clear");  // Linux, macOS
#endif
}

void function1() {

	int val1, val2;
	cout << "Izabrali ste int. Unesite brojeve: ";

	if (cin >> val1 >> val2) {
		if (val1 >= numeric_limits<int>::min() && val2 >= numeric_limits<int>::min() && val1 <= numeric_limits<int>::max() && val2 <= numeric_limits<int>::max()) {

			if (val1 > val2) {
				cout << "Broj " << val1 << " je veci od broja " << val2 << ".\n";
			}
			else if (val1 < val2) {
				cout << "Broj " << val2 << " je veci od broja " << val1 << ".\n";
			}
			else
			{
				cout << "Brojevi " << val2 << " i " << val1 << " su jednaki.\n";
			}

			cout << "Zbir brojeva " << val1 << " i " << val2 << " je: " << val1 + val2 << ".\n";
			cout << "Razlika brojeva " << val1 << " i " << val2 << " je: " << val1 - val2 << ".\n";
			cout << "Proizvod brojeva " << val1 << " i " << val2 << " je: " << val1 * val2 << ".\n";
			cout << "Kolicnik brojeva " << val1 << " i " << val2 << " je: " << val1 / val2 << ".\n";

		}
		else {
			clear();
			cin.clear();
			cin.ignore(numeric_limits<streamsize>::max(), '\n');

			cout << "Van opsega za izabrani short. Pokusajte ponovo." << endl;
			function1();
		}
	}
	else {
		clear();
		cin.clear();
		cin.ignore(numeric_limits<streamsize>::max(), '\n');

		cerr << "Netacan ulaz. Pokusajte ponovo." << endl;
		function1();
	}

}

void function2() {

	short val1, val2;
	cout << "Izabrali ste short. Unesite brojeve: ";

	if (cin >> val1 >> val2) {
		if (val1 >= numeric_limits<short>::min() && val2 >= numeric_limits<short>::min() && val1 <= numeric_limits<short>::max() && val2 <= numeric_limits<short>::max()) {

			if (val1 > val2) {
				cout << "Broj " << val1 << " je veci od broja " << val2 << ".\n";
			}
			else if (val1 < val2) {
				cout << "Broj " << val2 << " je veci od broja " << val1 << ".\n";
			}
			else
			{
				cout << "Brojevi " << val2 << " i " << val1 << " su jednaki.\n";
			}

			cout << "Zbir brojeva " << val1 << " i " << val2 << " je: " << val1 + val2 << ".\n";
			cout << "Razlika brojeva " << val1 << " i " << val2 << " je: " << val1 - val2 << ".\n";
			cout << "Proizvod brojeva " << val1 << " i " << val2 << " je: " << val1 * val2 << ".\n";
			cout << "Kolicnik brojeva " << val1 << " i " << val2 << " je: " << val1 / val2 << ".\n";

		}
		else {
			clear();
			cin.clear();
			cin.ignore(numeric_limits<streamsize>::max(), '\n');

			cout << "Van opsega za izabrani short. Pokusajte ponovo." << endl;
			function2();
		}
	}
	else {
		clear();
		cin.clear();
		cin.ignore(numeric_limits<streamsize>::max(), '\n');

		cerr << "Netacan ulaz. Pokusajte ponovo." << endl;
		function2();
	}
}

void function3() {

	double val1, val2;
	cout << "Izabrali ste double. Unesite brojeve: ";

	if (cin >> val1 >> val2) {
		if (val1 >= numeric_limits<double>::min() && val2 >= numeric_limits<double>::min() && val1 <= numeric_limits<double>::max() && val2 <= numeric_limits<double>::max()) {

			if (val1 > val2) {
				cout << "Broj " << val1 << " je veci od broja " << val2 << ".\n";
			}
			else if (val1 < val2) {
				cout << "Broj " << val2 << " je veci od broja " << val1 << ".\n";
			}
			else
			{
				cout << "Brojevi " << val2 << " i " << val1 << " su jednaki.\n";
			}

			cout << "Zbir brojeva " << val1 << " i " << val2 << " je: " << val1 + val2 << ".\n";
			cout << "Razlika brojeva " << val1 << " i " << val2 << " je: " << val1 - val2 << ".\n";
			cout << "Proizvod brojeva " << val1 << " i " << val2 << " je: " << val1 * val2 << ".\n";
			cout << "Kolicnik brojeva " << val1 << " i " << val2 << " je: " << val1 / val2 << ".\n";

		}
		else {
			clear();
			cin.clear();
			cin.ignore(numeric_limits<streamsize>::max(), '\n');

			cout << "Van opsega za izabrani double. Pokusajte ponovo." << endl;
			function3();
		}
	}
	else {
		clear();
		cin.clear();
		cin.ignore(numeric_limits<streamsize>::max(), '\n');

		cerr << "Netacan ulaz. Pokusajte ponovo." << endl;
		function3();
	}

}


int main() {

	bool b = true;

	while (b)
	{

		int num;
		cout << "Izaberite broj tipa vrijednosti koje zelite da unesete: \n 1. int \n 2. short\n 3. double\n";
		cin >> num;

		if (cin.fail()) {
			cin.clear();
			cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
		}

		switch (num) {
		case 1:
			clear();
			function1();
			b = false;
			break;
		case 2:
			clear();
			function2();
			b = false;
			break;
		case 3:
			clear();
			function3();
			b = false;
			break;
		default:
			clear();
			cout << "Pogresan unos.Probajte ponovo.\n";
			break;
		}
	}
	return 0;
}