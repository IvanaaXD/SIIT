#pragma once
#include <string>
#include <iostream>
#include <ostream>

using namespace std;

class FullName
{
	std::string name;
	std::string surname;
public:
	FullName(std::string n, std::string s);
	std::string getName() const;
	std::string getSurname() const;
	bool operator==(const FullName& rhs);

	friend::ostream& operator<<(ostream& os, const FullName& fullName);
};