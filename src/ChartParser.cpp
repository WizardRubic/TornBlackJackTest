#include "ChartParser.h"



ChartParser::ChartParser() {

} 

ChartParser::ChartParser(const ChartParser &obj) {
	// itereate through all hand combos, 
	// use a: map <vector<int>, int>
	// to store the hand chart, the second int hit/stand/we
	// the virst vector<int> is the possible hand... 
} 

int ChartParser::load(string fileName) {
	ifstream f(fileName);
	string	line;

	int i;
	vector<int> temp;
	int j=0;
	int x=0; 
	while(f >> i) {
		j++;
		x++;
		temp.push_back(i);
		if (j%10==0){ 
			table.push_back(temp);
			temp.clear();
		}
		if(x==500){
			break;
		}
	}

	f.close();	
	return 0;
}



// To properly query the table, we need whether this is the first two cards or not
// if it is the first 2 we need the values. 
// if not, we need the total
// maybe take the hand and sort it, then put in map?
// need to go through all 2m combos and hash em all too to initially create the map

// Only makes sense to do a hash map if we're planning on doing tons of values. 

int ChartParser::query(vector<int> & cards, vector<int> & dealerCards, bool split) {
	// First see if we are at the first 2 cards. 
		// if we are, then we need to evaluate based on the top half of the chart
			// check for pair, if pair, evaluate based on that sect
			// cehck for aces, if aces evl based on sec
			// if neither pair or aces, eval based on sum
		// if we aren't eval based on bottom half
			// this is solely based on the sums and deal hand
		// if split, turn all doubles to hit and turn all surrenders to 
	int ret;
	if(cards.size() == 2) {
		if (cards[0]==cards[1]) {

			ret = table[cards[0]+22][dealerCards[0]-2];
			if (split) {
				if (ret == DOUBLE) {
					ret = HIT;
				} 
				if (ret == SURRENDER) {
					ret = STAND;
				}
			}
			return ret;
		} else if ((cards[0]==ACE) || (cards[1] == ACE)) {
			int ind;
			if (cards[0]==ACE) {
				ind = 1;
			} else {
				ind = 0;
			}
			ret = table[cards[ind]+13][dealerCards[0]-2];
			if (split) {
				if (ret == DOUBLE) {
					ret = HIT;
				} 
				if (ret == SURRENDER) {
					ret = STAND;
				}
			}
			return ret;
		} else {
			int sum;
			sum = sumOfVector(cards);
			ret = table[sum-5][dealerCards[0]-2];
			if (split) {
				if (ret == DOUBLE) {
					ret = HIT;
				} 
				if (ret == SURRENDER) {
					ret = STAND;
				}
			}
			return ret;
		}
	} else {
		int sum;
		sum = sumOfVector(cards);
		return table[sum+28][dealerCards[0]-2];
	}
	return 30;
}


int ChartParser::sumOfVector(vector<int> & cards) {
	// Aces can count as 1 or 11. past first 
	// 2 cards assume they're 11 instead of ace 
	// for summaton unless it'd be a bust in which case assume 1.
	
	int sum = 0;
	int numOfAces = 0; 
	for(int a : cards) {
		if (a == ACE) {
			numOfAces++;
		} else {
			sum+=a;
		}
	}
	// start by assuming all aces are 1. if we can do so promote the value to 11
	// numOfAces is number of aces that haven't been considered for promotion
	while (numOfAces > 0) {
		if ((sum+numOfAces)<=11) {
			sum+=11;
		} else {
			sum++;
		}
		numOfAces--;
	}
	return sum;
}

ChartParser::~ChartParser () {

}