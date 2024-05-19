#include "WriteOutFile.h"

extern concurrent_hash_map<unsigned char, unsigned int> couterValues;

RetVal WriteOutFile(string fileName)
{
    ofstream outputFile(fileName.c_str());

	if (outputFile.is_open() == false)
	{
        cout << "WriteOutFile: Output file " << fileName << " could not be opened." << endl;
		return RET_ERROR;
	}

    concurrent_hash_map<unsigned char, unsigned int> ::const_accessor ca;
    for(int i=0; i<256; i++)
    {
        if(couterValues.find(ca, i))
        {
            outputFile << i << ":\t" << ca->second << endl;
        }
    }

    outputFile.close();

    return RET_OK;
}
