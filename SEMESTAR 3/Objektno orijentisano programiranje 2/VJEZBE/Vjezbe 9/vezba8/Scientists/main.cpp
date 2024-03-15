#include <iostream>
#include <vector>
#include <list>
#include <string>
#include <fstream>

#include "FullName.h"
#include "Scientist.h"

using namespace std;

// global variables
vector<FullName> scientistsNames;		//vector containing full names of scientists
list<int> scientistsIds;				//list containing ids of scientists
list<Scientist> scientists;				//list containing scientists (objects of class Scientist)

// function declarations
void loadScientists(ifstream& in);

void printNames();						//prints scientists' names
void printIds();						//prints scientists' ids
void printNamesAndIds();				//prints scientists' ids and names
void printNamesAndIdsInReverse();		//prints scientists' in reverse
void addScientist();					//adds new scientist

void fillScientistsList();				//fills scientists with scientists objects (formed of full names and ids)
void removeDuplicatesAndSortById();		//removes duplicates scientists and sorts them based on their id value
void printScientistsList();				//prints the list of scientists

// main
void main()
{
	ifstream in("scientists.txt");
	if (!in)
	{
		cerr << "ERROR: wrong input file name!";
		exit(-1);
	}

	loadScientists(in);

	printNames();
	cout << endl;
	
	printIds();
	cout << endl;

	printNamesAndIds();
	cout << endl;

	printNamesAndIdsInReverse();
	cout << endl;

	addScientist();

	printNamesAndIds();
	cout << endl;

	fillScientistsList();

	printScientistsList();
	cout << endl;

	removeDuplicatesAndSortById();
	
	printScientistsList();
	cout << endl;

	return;
}

void loadScientists(ifstream& in)
{
	while(!in.eof())
	{
		int id;
		string name;
		string surname;
		
		// @TODO: read id, name and surname from input file stream
		// @TODO: create Fullname object from name and surname and add object to the proper vector
		// @TODO: add id to the proper list
		// NOTE: vector and list are already defined as global variables -> NO NEED TO MAKE NEW ONES ! <-

		in >> id >> name >> surname;

		FullName fn(name, surname);
		scientistsNames.push_back(fn);

		scientistsIds.push_back(id);
	}
}

void printNames()
{
	// @TODO: using iterators print the vector of names

	vector<FullName>::iterator scientistsNamesI;

	for (scientistsNamesI = scientistsNames.begin(); scientistsNamesI != scientistsNames.end(); scientistsNamesI++) {
		cout << *scientistsNamesI << endl;
	}
}

void printIds()
{
	// @TODO: using iterators print the list of ids

	list<int>::iterator scientistsIdsI;

	for (scientistsIdsI = scientistsIds.begin(); scientistsIdsI != scientistsIds.end(); ++scientistsIdsI) {
		cout << *scientistsIdsI << endl;
	}
}

void printNamesAndIds()
{
	// @TODO: using iterators print scientist id and the its name and surname
	// using list of ids and vector of names

	list<Scientist>::iterator scientistsI;

	for (scientistsI = scientists.begin(); scientistsI != scientists.end(); scientistsI++) {
		cout << *scientistsI << endl;
	}
}

void printNamesAndIdsInReverse()
{
	// @TODO: using iterators print scientist id and the its name and surname
	// using list of ids and vector of names, in reverse

	list<Scientist>::reverse_iterator scientistsI;
	list<int>::reverse_iterator scientistsIdsI;

	int i = 0;
	int ii = 0;
	for (scientistsI = scientists.rbegin(); scientistsI != scientists.rend(); ++scientistsI) {
		i++;
		for (scientistsIdsI = scientistsIds.rbegin(); scientistsIdsI != scientistsIds.rend(); ++scientistsIdsI) {
			ii++;
			if (i == ii) {
				cout << *scientistsI << *scientistsIdsI << endl;
			}
		}
	}
}

void addScientist()
{
	FullName newScientist("Petar", "Petrovic");
	int newID = 999;
	
	FullName findName("Nikola", "Tesla");
	int findId = 123;

	// @TODO: add new scientist "Petar Petrovic" with "999" ID, after
	// "Nikola Tesla" who has "123" as ID
	
	FullName newScientisttt("Nikola", "Tesla");
	int newIDdd = 123;

	FullName newScientistt("Petar", "Petrovic");
	int newIDd = 999;
}

void fillScientistsList()
{
	// @TODO: fill the list of scientists by creating objects of class Scientist
	// NOTE: use existing vector of names and list of ids to create Scientist objects

	list<int>::iterator scientistsIdsI = scientistsIds.begin();
	vector<FullName>::iterator scientistsNamesI = scientistsNames.begin();

	while (scientistsIdsI != scientistsIds.end() && scientistsNamesI != scientistsNames.end())
	{
		Scientist s(*scientistsNamesI, *scientistsIdsI);

		scientists.push_back(s);

		++scientistsIdsI;
		++scientistsNamesI;
	}
}


void removeDuplicatesAndSortById()
{
	// @TODO sort the list of scientists and remove duplicate occurencies
	
	scientists.sort([](const Scientist& a, const Scientist& b) {
		return a.getId() < b.getId();
		});

	scientists.unique([](const Scientist& a, const Scientist& b) {
		return a.getId() == b.getId();
		});
}

void printScientistsList()
{
	// @TODO print the list of scientists

	for (const Scientist& s : scientists) {
		cout << s << endl;
	}
}
