﻿#include "std_lib_facilities.h"


int main()
{
	double lval;
	double rval;
	double res;
	char op;

	cout << ">";

	cin >> lval >> op >> rval;

	switch (op) {
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

	cout << "=" << res << endl;

	return 0;
}

int main()
{
	double lval;
	double rval;
	double res;
	char op;

	cout << ">";

	cin >> lval >> op >> rval;

	switch (op) {

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

	cout << "=" << res << endl;

}
