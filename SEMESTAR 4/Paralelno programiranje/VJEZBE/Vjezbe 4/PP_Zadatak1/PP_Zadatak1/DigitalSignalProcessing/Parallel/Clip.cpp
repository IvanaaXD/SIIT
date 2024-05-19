#include "Clip.h"

extern concurrent_vector<short> highPass2ClipQueue;
extern concurrent_vector<unsigned char> clip2CounterVector;

struct ClipStruct {

    char lower, upper;

    ClipStruct(char lower, char upper) : lower(lower), upper(upper) {}

    void operator() (const blocked_range<size_t>& range) const {
        for (int i = range.begin(); i != range.end(); i++) {
            short data = highPass2ClipQueue[i];

            if (data < lower)
            {
                data = lower;
            }
            else if (data > upper)
            {
                data = upper;
            }

            clip2CounterVector.push_back(data);
        }
    }

};



RetVal Clip(char lowerValue, char upperValue)
{
   
    parallel_for(blocked_range<size_t>(0, highPass2ClipQueue.size()), ClipStruct(lowerValue, upperValue));

    /*short data;

    // clipping loop
    while(!highPass2ClipQueue.empty())
    {
        data = highPass2ClipQueue.front();
        highPass2ClipQueue.pop();

        if(data<lowerValue)
        {
            data = lowerValue;
        }
        else if(data>upperValue)
        {
            data = upperValue;
        }

        clip2CounterVector.push_back(data);
    }*/

    return RET_OK;
}
