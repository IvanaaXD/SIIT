#include "Counter.h"

extern concurrent_vector<unsigned char> clip2CounterVector;
extern concurrent_hash_map<unsigned char, unsigned int> couterValues;

struct CounterStruct {

    void operator() (const blocked_range<size_t>& range) const {
        for (int i = range.begin(); i != range.end(); i++) {
            unsigned char data = clip2CounterVector[i];
            concurrent_hash_map<unsigned char, unsigned int> ::accessor a;
            couterValues.insert(a, data);
            a->second++;
        }
    }
};

RetVal Counter()
{
    parallel_for(blocked_range<size_t>(0, clip2CounterVector.size()), CounterStruct());

    /*unsigned char data;

    // array initialization
    memset(couterValues, 0x00, 256*sizeof(unsigned int));

    // count each value
    while(!clip2CounterVector.empty())
    {
        data = clip2CounterVector.back();
        clip2CounterVector.pop_back();

        couterValues[data]++;
    }*/

    return RET_OK;
}
