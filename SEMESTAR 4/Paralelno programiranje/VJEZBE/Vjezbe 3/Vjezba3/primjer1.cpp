#include "tbb/concurrent_hash_map.h"
#include "tbb/blocked_range.h"
#include "tbb/parallel_reduce.h"
#include "tbb/partitioner.h"    
#include <iostream>
#include <vector>
#include <string>

/*using namespace tbb;

struct MyHashCompare {

    static size_t hash(const std::string& x) {
        size_t h = 0;
        for (const char* s = x.c_str(); *s; ++s) {
            h = (h * 17) ^ *s;
        };
        return h;
    }

    static bool equal(const std::string& x, const std::string& y) {
        return x == y;
    };
};

typedef concurrent_hash_map<std::string, int, MyHashCompare> StringTable;

// Function object for counting occurrences of strings
struct Tally {
    StringTable& table;

    // Constructor
    Tally(StringTable& table_) : table(table_) {};

    // Operator () for processing elements within a range
    void operator() (const blocked_range<std::string*>& range) const {
        for (std::string* p = range.begin(); p != range.end(); ++p) {
            StringTable::accessor a;
            table.insert(a, *p);
            a->second += 1;
        }
    }
};



int main()
{
    return 0;
}
*/