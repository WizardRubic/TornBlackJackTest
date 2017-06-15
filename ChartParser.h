#ifndef CHARTPARSER_H
#define CHARTPARSER_H

#include <vector>
#include <cstdlib>
#include <ctime>

#define ACE 11

using namespace std;

class ChartParser {
	public:
		ChartParser ();
		ChartParser(const ChartParser &obj);
		int load(); // maybe take in a file desc and read it in?
		int query(); // take in hand? spits out decision? 
		virtual ~ChartParser();
	private:


};

#endif