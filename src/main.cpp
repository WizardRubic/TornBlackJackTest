#include <iostream>
#include <vector>
#include <sstream>

#include "Deck.h"
#include "ChartParser.h"
// #include <cstdlib>
// #include <ctime>

// #define TEST

#define HIT 0
#define STAND 1
#define DOUBLE 2
#define SPLIT 3
#define SURRENDER 4


#define ACE 11

#define BASEBET 20
// #define TOKENS 200
#define STARTTOTAL 2000

using namespace std;


// TOOD
// torn has strange split rules. 
// example:
// dealer: A-x
// user: 88
// user splits:
// user[0]: 8-5
// user[1]: 8-3
// user[0] hand is not allowed to double or surrender and the dealer plays his hand after user[0] is resolved
// user[1] then goes. this time the dealer has A-x except x is faceup and user[1] is allowed to double or surrender.

	// consider building a chart of the second case, optimal strat when we know both cards

// Test the following combos:

// add stats to show money win/loss ratio



// DONE
// need to make it so 5 card charlie loses to dealer nat 21? yes, our 5 card will lose to their 21.
// TODO make it so 5 card charlie on dealer beats what w/e we got
// see split rules about what's allowed
// second hand can split
	// i've decided to ignore cases of splits past the first one since they're rare enough for it to only marginally affect the results.
	
// Fix the split glitch
// Replace determineAction!
	// take in an actual chart
	// first hand deal with aces after don't
// do we need to see what happens if we get a 21, does the dealer get to draw til 5 card? inconsequential as nat beats 5 card
// can the dealer get 5 card charlies? YES
// dealer keeps playing after we get 5 card 


int sumOfVector(vector<int> & cards) {
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

// printStats
// Hands played
// User bet
// EV of each bet
// Starting Total
// Lowest point
// Ending Total

void printStats(int tokens, double userTotal, double lowest) {
	cout << "Tokens Spent:   " << tokens << endl;
	cout << "Basebet:        " << BASEBET << endl;
	double profit = (double)((userTotal - STARTTOTAL))/tokens;
	double ev = BASEBET + profit;
	cout << "EV of each bet: " << ev << endl;
	cout << "Profit/hand:    " << profit << endl;
	cout << "Starting total: " << STARTTOTAL << endl;
	cout << "Lowest point:   " << lowest << endl;
	cout << "Ending Total:   " << userTotal << endl;
}



#ifdef TEST
int main (int argc, char *argv[]) {
	cout << "Entered Testing" << endl;
	ChartParser cp;
	cp.load("testin");
	vector<int> dealerHand;
	vector<int> userHand;

	// test case no split, pair
	userHand.push_back(4);
	userHand.push_back(4);
	dealerHand.push_back(3);
	cout << cp.query(userHand, dealerHand, 0) << endl; // expected 0

	// test case no split, sum = 9
	userHand.clear();
	dealerHand.clear();
	userHand.push_back(3);
	userHand.push_back(6);
	dealerHand.push_back(2);
	cout << cp.query(userHand, dealerHand, 0) << endl; // expected 0

	// test case no split, sum = 10
	userHand.clear();
	dealerHand.clear();
	userHand.push_back(6);
	userHand.push_back(4);
	dealerHand.push_back(2);
	cout << cp.query(userHand, dealerHand, 0) << endl; // expected 2

	// test case no split, 3 cards, sum = 12
	userHand.clear();
	dealerHand.clear();
	userHand.push_back(6);
	userHand.push_back(4);
	userHand.push_back(2);
	dealerHand.push_back(2);
	cout << cp.query(userHand, dealerHand, 0) << endl; // expected 0

	// test case no split, 3 cards, sum = 13
	userHand.clear();
	dealerHand.clear();
	userHand.push_back(6);
	userHand.push_back(4);
	userHand.push_back(3);
	dealerHand.push_back(2);
	cout << cp.query(userHand, dealerHand, 0) << endl; // expected 1


	// test case split, 2 cards, sum = 10
	userHand.clear();
	dealerHand.clear();
	userHand.push_back(6);
	userHand.push_back(4);
	dealerHand.push_back(2);
	cout << cp.query(userHand, dealerHand, 1) << endl; // expected 0


	// test case split, 2 cards, sum = 16, dealer =9
	userHand.clear();
	dealerHand.clear();
	userHand.push_back(10);
	userHand.push_back(6);
	dealerHand.push_back(9);
	cout << cp.query(userHand, dealerHand, 1) << endl; // expected 1

	// test case no split, 2 cards, sum = 10, dealer =10
	userHand.clear();
	dealerHand.clear();
	userHand.push_back(7);
	userHand.push_back(3);
	dealerHand.push_back(10);
	cout << cp.query(userHand, dealerHand, 0) << endl; // expected 0

	// test case no split, 2 cards, sum = 10, dealer =9
	userHand.clear();
	dealerHand.clear();
	userHand.push_back(7);
	userHand.push_back(3);
	dealerHand.push_back(9);
	cout << cp.query(userHand, dealerHand, 0) << endl; // expected 2

	// test case no split, 2 cards, sum = 10, dealer =9
	userHand.clear();
	dealerHand.clear();
	userHand.push_back(4);
	userHand.push_back(9);
	userHand.push_back(3);
	dealerHand.push_back(10);
	cout << cp.query(userHand, dealerHand, 0) << endl; // expected 0

	// cout << (cp.table[11][8]) << endl;
	return 0;
}
#else 
int main (int argc, char *argv[]) {
	cout << "Entered Main" << endl;
	if (argc!=3) {
		cout << "USAGE: bjsim.exe <# of tokens> <chartFileFromSheets>"<<endl;
		return 0;
	}

	// init sim
	Deck deck; // starts off preshuffled
	vector<int> dealerHand;
	vector<int> userHand[4]; // userHands[1] to 3 only get used if split
	ChartParser cp;// make a chart parser
	cp.load(argv[2]);
	int tokens;
	stringstream t(argv[1]);
	t >> tokens;

	double userTotal=STARTTOTAL;

	bool handDone;
	bool userBust[4];
	bool dealNeeded;
	int currentBet;

	int splitTo = 1; // number of hands, can be increased when a split happens

	double lowest = STARTTOTAL;

	bool splitBool = false; // split
	for(int y = 0; y<tokens; y++) {
		splitTo = 1;
		
		// cout << "----------------- NEW HAND ------------" << endl; // Print output

		// Deal initial 3 cards and take the bet
		userHand[0].push_back(deck.draw());
		userHand[0].push_back(deck.draw());
		dealerHand.push_back(deck.draw());
		userTotal -= BASEBET;
		dealNeeded = false;

		//  // Print output		
		// cout << "dealerHand: ";
		// for(int debugA :dealerHand) {
		// 	cout << debugA << " ";
		// }
		// cout << endl;
		//  // End print output

		currentBet = BASEBET;
		
		// Loop through every non empty userHand
		// Every loop must call determineAction with the current hand
		// Then it makes a decision 
		bool surrender = false;
		splitBool = false; // split
		for (int a = 0; userHand[a].size()>=2 && a<4; a++) {
			// cout << "entered a = " << a << endl;
			handDone = false;
			userBust[a] = false;
			// loop til current hand is finished
			while (!handDone) {

				// // Print output		
				// cout << "userhand[0]: ";
				// for(int debugA :userHand[0]) {
				// 	cout << debugA << " ";
				// }
				// cout << endl;
				// cout << "userhand[1]: ";
				// for(int debugA :userHand[1]) {
				// 	cout << debugA << " ";
				// }
				// cout << endl;
				// cout << "userhand[2]: ";
				// for(int debugA :userHand[2]) {
				// 	cout << debugA << " ";
				// }
				// cout << endl;
				// cout << "userhand[3]: ";
				// for(int debugA :userHand[3]) {
				// 	cout << debugA << " ";
				// }
				// cout << endl;
				// // End Print output		

				switch(cp.query(userHand[a], dealerHand, splitBool)) {
					case HIT:
						// cout << "HIT CASE entered"<< endl;
						userHand[a].push_back(deck.draw());
						if (sumOfVector(userHand[a]) > 21) {
							// cout << "Busted on hand "<< a << endl;
							handDone = true;
							userBust[a] = true;
						} else if (userHand[a].size()>=5) {
							// cout << "5 card on hand "<< a << endl;
							// Five card charlie
							handDone = true;
						}
						break;

					case STAND:
						// cout << "STAND CASE entered"<< endl;
						handDone = true; 
						break;

					case DOUBLE:
						// cout << "DOUBLE CASE entered"<< endl;
						userTotal -= BASEBET;
						currentBet = BASEBET * 2;
						userHand[a].push_back(deck.draw());
						if (sumOfVector(userHand[a]) > 21) {
							userBust[a] = true;
						} 
						handDone = true;
						break;

					case SPLIT:
						// cout << "SPLIT CASE entered"<< endl;
						if (a < 3) {
							splitBool = true;
							// double the bet
							userTotal -= BASEBET;
							// split the pair
							userHand[splitTo].push_back((userHand[a])[1]);
							userHand[a].pop_back();
							//draw another card for both
							userHand[a].push_back(deck.draw());
							userHand[splitTo].push_back(deck.draw());
							splitTo++;
						} 
						break;

					case SURRENDER:
						// cout << "SURRENDER CASE entered"<< endl;
						handDone = true;
						surrender = true;
						break;
					default:
						// cout << "defaulted, a = " << a << endl;
						handDone = true;

						break;
				}	
			}

		}
		// Determine if deal is needed
		for (int a = 0; userHand[a].size()>=2 && a<4; a++ ) {
			if ((userBust[a] == false) && (surrender == false)) {
				dealNeeded = true;
			}
		}
		int dealerFive = 0;
		if (dealNeeded) {
			while (sumOfVector(dealerHand) < 17) {
				dealerHand.push_back(deck.draw());
				if ((dealerHand.size()==5) && (sumOfVector(dealerHand) <= 21)){
					dealerFive = 1;
					break;
				}
			}
		}
// // Print output		
// 		cout << "dealerHand -----: ";
// 		for(int debugA :dealerHand) {
// 			cout << debugA << " ";
// 		}
// 		cout << endl;

// 		// Print all the hands after the game
// 		cout << "userhand[0]: ";
// 		for(int debugA :userHand[0]) {
// 			cout << debugA << " ";
// 		}
// 		cout << endl;
// 		cout << "userhand[1]: ";
// 		for(int debugA :userHand[1]) {
// 			cout << debugA << " ";
// 		}
// 		cout << endl;
// 		cout << "userhand[2]: ";
// 		for(int debugA :userHand[2]) {
// 			cout << debugA << " ";
// 		}
// 		cout << endl;
// 		cout << "userhand[3]: ";
// 		for(int debugA :userHand[3]) {
// 			cout << debugA << " ";
// 		}
// 		cout << endl;
// // End Print output		

		// After going through all the hands determine the winning amounts of each hand
		int dealerSum = sumOfVector(dealerHand);
		for (int a = 0; userHand[a].size()>=2 && a<4; a++ ) {
			int curHandTotal = sumOfVector(userHand[a]);
			if (surrender == true) {
				userTotal+=currentBet*0.5;
				break;
			}
			if (userBust[a] == false) {
				// 5 card
				if ((userHand[a].size()==5) && (!((dealerSum == 21)&&(dealerHand.size()==2)))){
					if (!dealerFive) {
						userTotal+=currentBet*2;
					} else {
						userTotal+=currentBet;
					}
				} else if (((curHandTotal == 21)&&(userHand[a].size()==2)) && (!((dealerSum == 21)&&(dealerHand.size()==2))) )  {
					// natural 21 and dealer doesn't have nat 21
					userTotal+=currentBet * 2.5;
				} else if (((curHandTotal == 21)&&(userHand[a].size()==2)) && ((dealerSum == 21)&&(dealerHand.size()==2)) )  {
					// tie with both players nat 21
					userTotal+=currentBet;
				} else if (dealerSum > 21) { // dealer bust
					userTotal+=currentBet*2;
				} else if (dealerSum < curHandTotal) { // user hand is bigger
					if (!dealerFive) {
						userTotal+=currentBet*2;
					}
				} else if (dealerSum == curHandTotal) {
					if (!(((dealerSum == 21) && (dealerHand.size()==2)))) { // if both hands are equal and dealer no nat 21
						if (!dealerFive) {
							userTotal+=currentBet;
						}
					}
				}
			}
		}

		// After going through all the hands we must clear everything
		deck.shuffle();
		for(int d = 0; d<4;d++){
			userHand[d].clear();
		}
		dealerHand.clear();
// // Print output		
// 		cout << userTotal << endl;
// // End Print output		
		if (userTotal < lowest) {
			lowest = userTotal;
		}
	}
	printStats(tokens, userTotal,lowest);

	return 0;
}

#endif