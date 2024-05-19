#include "tbb/concurrent_hash_map.h"
#include "tbb/blocked_range.h"
#include "tbb/parallel_for.h"
#include <iostream>

using namespace std;
using namespace tbb;

// TODO: Create a simple MyHashCompare structure
struct MyHashCompare {

	static size_t hash(const int& x) {
		return x;
	};

	static bool equal(const int& x, const int& y) {
		return x == y;
	};
};

// A concurrent hash table that maps ints to ints
typedef concurrent_hash_map<int, int, MyHashCompare> IntTable;

// TODO: Create a simple Histogram class which will count the number of occurrences
struct Histogram {
	IntTable& it;

	Histogram(IntTable& it) : it(it) {};

	void operator() (const blocked_range<int*>& range) const {
		for (int* i = range.begin(); i != range.end(); i++) {
			IntTable::accessor a;
			it.insert(a, *i);
			a->second += 1;
		};
	};
	
};

const size_t N = 1000000;
int Data[N];

// Count occurrences
int main() {
	// Construct empty table
	IntTable table;
	
	// Create some data to work with
	for (int i = 0; i < N; i++) {
		Data[i] = rand() % 10;
	}

	// TODO: Put occurrences into the table using parallel_for
	parallel_for(
		blocked_range<int*>(Data, Data+N),
		Histogram(table),
		auto_partitioner()
	);

	// Display the occurrences
	for (std::pair<int, int> t : table) {
		cout << t.first << " se pojavljuje " << t.second << " puta." << endl;
	}

	return 0;
}
