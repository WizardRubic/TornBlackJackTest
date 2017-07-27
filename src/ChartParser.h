#ifndef CHARTPARSER_H
#define CHARTPARSER_H

#include <vector>
#include <cstdlib>
#include <ctime>
#include <iostream> 
#include <fstream> 
#include <string> 
#include <vector>
#define ACE 11

#define HIT 0
#define STAND 1
#define DOUBLE 2
#define SPLIT 3
#define SURRENDER 4

using namespace std;

class ChartParser {
	public:
		ChartParser ();
		ChartParser(const ChartParser &obj);
		int load(string fileName); // takes in file name
		int query(vector<int> & cards, vector<int> & dealerCards, bool split); // take in hand, dealer hand and spits out decision
		virtual ~ChartParser();
	private:
		vector<vector<int>> table; 
		int sumOfVector(vector<int> & cards);

};

#endif