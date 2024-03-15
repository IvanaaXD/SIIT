#include "std_lib_facilities.h"


int main()
{
	double lval = 0;
	double rval;
	char op;

	cout << ">";

	cin >> lval;
		
	while (true) {
		cin >> op;

		if (op == ';') {
			cout << "=" << lval << endl;
			return 0;
		}

		cin >> rval;

		switch (op) {
		case '+':
			lval += rval;
			break;
		case '-':
			lval -= rval;
			break;
		case '*':
			lval *= rval;
			break;
		case '/':
			lval /= rval;
			break;
		default:
			cout << "Bad operator!" << endl;
			return 1;
		}
	}
}

int mainn()
{
	double lval = 0;
	double rval;
	char op;

	cout << ">";

	cin >> lval;

	while (true) {

		cin >> op;
		if (op == ';') {
			cout << "=" << lval << endl;
			return 0;
		}

		cin >> rval;

		switch (op)
		{
		case '+':
			res = lval + rval;
			break;
		case '-':
			res = lval - rval;
			break;
		case '*':
			res = lval * rval;
			break;
		case '/':
			res = lval / rval;
			break;
		default:
			cout << "Bad operator!" << endl;
			return 1;
		}
	}

}
