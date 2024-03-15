#include "FullName.h"

using namespace std;

/*
@TODO: Complete the FullName class definition:
- add implementation for constructor with parameters
- add implementation for operator ==
- add implementation for get methods
*/

FullName::FullName(string name, string surname) : name(name), surname(surname) {};

string FullName:: getName() const {
	return name;
}

string FullName::getSurname() const {
	return surname;
}

bool FullName::operator==(const FullName& rhs) {
	return (name == rhs.name) && (surname == rhs.surname);
}

ostream& operator<<(ostream& os, const FullName& fullName) {
	os << fullName.getName() << " " << fullName.getSurname();
	return os;
}
