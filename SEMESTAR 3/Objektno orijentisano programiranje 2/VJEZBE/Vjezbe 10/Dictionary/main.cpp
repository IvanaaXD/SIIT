#include <iostream>
#include <iostream>
#include <fstream>
#include <string>
#include <set>
#include <cctype>
#include <map>
#include <algorithm>

using namespace std;

void readInputFile(ifstream& in);
void erasePunctuation(string& word);
void printOccurencesForTheWord(string findWord);
void generateDictonary(ofstream& out);
void generateStats(ofstream& out);

// used for storing words read from the input file
set<string> dictionary;

// used for storing pairs of words and their occurences read from the input file
map<string, int> occurances;

// array used for generating the dictonary file (a - a, at, ace...)
char alphabet[] = "abcdefghijklmnopqrstuvwxyz";


int main()
{
	string word;
	
	ifstream in("hobbit.txt");
	if (!in)
	{
		cerr << "Invalid input file name";
		exit(-1);
	}

	ofstream outDictionary("dict.txt");
	if (!outDictionary)
	{
		cerr << "Invalid output file name";
		exit(-1);
	}

	ofstream outStats("stats.txt");
	if (!outStats)
	{
		cerr << "Invalid output file name";
		exit(-1);
	}

	// reads the input file and fills the set: dictionary and the map: occurances
	readInputFile(in);

	// prints the desired key-value pair, based on the key
	printOccurencesForTheWord("dwarf");
	printOccurencesForTheWord("dwarfs");

	// generates the output file-dictonary
	generateDictonary(outDictionary);

	// generates the output file containing statistics of the word length usage
	generateStats(outStats);

	return 0;
}


bool isOnlyPunctuation(const string& str)
{
	return all_of(str.begin(), str.end(), [](unsigned char c) { return ispunct(c); });
}


bool containsPunctuation(const string& str)
{
	return any_of(str.begin(), str.end(), ::ispunct);
}


void readInputFile(ifstream& in)
{
	string word;

	while ((in >> word))
	{

		// if a word is just punctuation skip it

		if (isOnlyPunctuation(word)) {
			continue;
		}
		
		// if a word contains punctuation erase it from the word

		if (containsPunctuation(word)) {
			erasePunctuation(word);
		}

		// lower the case of the word

		transform(word.begin(), word.end(), word.begin(), ::tolower);

		// fill the dictionary set with the words and the 
		// occurances map with number of occurences of the word

		cout << word << endl;
		dictionary.insert(word);
		occurances[word]++;
	}
}


void erasePunctuation(string& word)
{
	// there is a function that can check if the character is punctuation

	word.erase(remove_if(word.begin(), word.end(), ::ispunct), word.end());
}


void printOccurencesForTheWord(string findWord)
{
	// print the number of the occurences for the desired word

	auto it = occurances.find(findWord);

	if (it != occurances.end())
	{
		cout << "Occurrences of \"" << findWord << "\": " << it->second << endl;
	}
	else
	{
		cout << "Word \"" << findWord << "\" not found." << endl;
	}
}


void generateDictonary(ofstream& out)
{
	// write out to the output file the dictonary of the words from the input file
	// use the set and the alphabet array

	for (char letter : alphabet)
	{
		for (const string& word : dictionary)
		{
			if (word.front() == letter)
			{
				out << word << endl;
			}
		}
	}
}


void generateStats(ofstream& out)
{
	// create a map which will contain pairs of: word length, and occurences of that word length
	// example: dictonary contains:
	// ate, ate, bar, barn
	// map contains [0] -> 3, 3, [1] -> 4, 1

	// write out to the output the statistics of the usage of certain word length

	map<int, int> lengthOccurrences;

	for (const string& word : dictionary)
	{
		lengthOccurrences[word.length()]++;
	}

	for (const auto& entry : lengthOccurrences)
	{
		out << "Word length " << entry.first << ": " << entry.second << " occurrences" << endl;
	}
}
