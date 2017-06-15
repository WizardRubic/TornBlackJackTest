#include <iostream>
#include <vector>
#include "Deck.h"
// #include <cstdlib>
// #include <ctime>

#define HIT 0
#define STAND 1
#define DOUBLE 2
#define SPLIT 3
#define SURRENDER 4


#define ACE 11

#define BASEBET 10

using namespace std;


// TOOD
// Fix the split glitch
// Replace determineAction!
	// take in an actual chart
	// first hand deal with aces after don't
// need to make it so 5 card charlie loses to dealer nat 21? yes, our 5 card will lose to their 21.
// TODO make it so 5 card charlie on dealer beats what w/e we got
// see split rules about what's allowed
// second hand can split



// DONE
// do we need to see what happens if we get a 21, does the dealer get to draw til 5 card? inconsequential as nat beats 5 card
// can the dealer get 5 card charlies? YES
	



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





// determineAction()
// pre: not busted
// THIS CODE IS A MESS. TEMP HOT FIX JUST TO SEE IF WE CAN BEAT THE GAME. ANSWER IS YES!
int determineAction(vector<int> & cards, vector<int> & dealerCards, bool s) {
	bool containsAce = false;
	bool containsPair = false;
	int handVal = sumOfVector(cards);
	if (cards.size() == 2) {
		for (int i : cards) {
			if (i == ACE) {
				containsAce = true;
			}
		}
		if (cards[0] == cards[1]) {
			containsPair = true;
		}
		if (containsAce) {
			if( ((handVal>=13) && (handVal<=17)) && ((dealerCards[0] <= ACE) && (dealerCards[0] >= 7)) ) {
				 return HIT;
			} else if (  ((handVal>=13)&&(handVal<=14))  && ((dealerCards[0] <= 4) && (dealerCards[0] >= 2))  ) {
				return HIT;
			} else if (  ((handVal>=15)&&(handVal<=16))  && ((dealerCards[0] <= 3) && (dealerCards[0] >= 2))  ) {
				return HIT;
			} else if ( (handVal==17)  && (dealerCards[0] == 2)  ) {
				return HIT;
			} else if ( (handVal==18)  && ((dealerCards[0] <= ACE) && (dealerCards[0] >= 9))  ) {
				return HIT;
			} else if (  ((handVal>=13)&&(handVal<=18))  && ((dealerCards[0] <= 6) && (dealerCards[0] >= 5))  ) {
				if (s) { 
					return HIT;
				}
				return DOUBLE;
			} else if ( ((handVal>=15)&&(handVal<=18))  && (dealerCards[0] == 4)  ) {
				if (s) { 
					return HIT;
				}
				return DOUBLE;
			} else if ( ((handVal>=17)&&(handVal<=18))  && (dealerCards[0] == 3)  ) {
				if (s) { 
					return HIT;
				}
				return DOUBLE;
			} else if (  ((handVal>=19)&&(handVal<=20))  && ((dealerCards[0] <= ACE) && (dealerCards[0] >= 2))  ) {
				return STAND;
			} else if ( (handVal==18)  && (dealerCards[0] == 2)  ) {
				return STAND;
			} else if ( (handVal==18)  && ((dealerCards[0] <= 8) && (dealerCards[0] >= 7))  ) {
				return STAND;
			} else if (handVal==12) {
				return SPLIT;
			} else if (handVal == 21) {
				return STAND;
			}
		} else if (containsPair) {
			if( ((handVal==4) || (handVal==6) || (handVal==14)) &&  ((dealerCards[0] >= 2) && (dealerCards[0] <= 7))   ) {
				return SPLIT;
			} else if ( ((handVal==12)|| (handVal==18) ) &&  ((dealerCards[0] >= 2) && (dealerCards[0] <= 6))   ) {
				return SPLIT;
			} else if (handVal == 16) {
				return SPLIT;
			} else if ( ((handVal==18) ) &&  ((dealerCards[0] >= 8) && (dealerCards[0] <= 9))   ) {
				return SPLIT;
			} else if (handVal == 20) {
				return STAND;
			} else if ( ((handVal==18) ) &&  ( (dealerCards[0] == 7))   ) {
				return STAND;
			} else if ( ((handVal==18) ) &&  ((dealerCards[0] >= 10) && (dealerCards[0] <= ACE))   ) {
				return STAND;
			} else if ( ((handVal==10) ) &&  ((dealerCards[0] >= 2) && (dealerCards[0] <= 9))   ) {
				if (s) { 
					return HIT;
				}
				return DOUBLE;
			} else if ( ((handVal==10) ) &&  ((dealerCards[0] >= 10) && (dealerCards[0] <= ACE))   ) {
				return HIT;
			} else if ( ((handVal==8) ) &&  ((dealerCards[0] >= 2) && (dealerCards[0] <= 4))   ) {
				return HIT;
			} else if (    ( (handVal==8) || (handVal==12)  )   &&    ((dealerCards[0] >= 7) && (dealerCards[0] <= ACE))   ) {
				return HIT;
			} else if (    ( (handVal==6) || (handVal==14)  )   &&    ((dealerCards[0] >= 8) && (dealerCards[0] <= ACE))   ) {
				return HIT;
			} else if (    ( (handVal==4)   )   &&    ((dealerCards[0] >= 8) && (dealerCards[0] <= ACE))   ) {
				return HIT;
			} else if (    ( (handVal==8)   )   &&    ((dealerCards[0] >= 5) && (dealerCards[0] <= 6))   ) {
				return SPLIT;
			}
		} 
	}
	if (    ( (handVal>=5)&&(handVal<=8) )      ) {
		return HIT;
	} else if ((handVal==9)&&(dealerCards[0] == 2)) {
		return HIT;
	} else if (    ( (handVal==9)   )   &&    ((dealerCards[0] >= 7) && (dealerCards[0] <= ACE))   ) {
		return HIT;
	} else if (    (handVal==9)   &&    ((dealerCards[0] >= 3) && (dealerCards[0] <= 6))   ) {
		if (cards.size() == 2){
			if (s) { 
					return HIT;
			}
			return DOUBLE;
		} else {
			return HIT;
		}
	} else if (    (handVal==10)   &&    ((dealerCards[0] >= 2) && (dealerCards[0] <= 9))   ) {
		if (cards.size() == 2){
			if (s) { 
					return HIT;
			}	
			return DOUBLE;
		} else {
			return HIT;
		}
	} else if (    (handVal==10)   &&    ((dealerCards[0] >= 10) && (dealerCards[0] <= ACE))   ) {
		return HIT;
	} else if (    (handVal==11)   &&    ((dealerCards[0] >= 2) && (dealerCards[0] <= 10))   ) {
		if (cards.size() == 2){
			if (s) { 
					return HIT;
			}
			return DOUBLE;
		} else {
			return HIT;
		}
	} else if (    (handVal==11)   &&    ((dealerCards[0] == ACE))   ) {
		return HIT;
	} else if (    (handVal==12)   &&    ((dealerCards[0] >= 2) && (dealerCards[0] <= 3))   ) {
		return HIT;
	} else if (    (handVal==12)   &&    ((dealerCards[0] >= 4) && (dealerCards[0] <= 6))   ) {
		return STAND;
	} else if (    ( (handVal>=12)&&(handVal<=14) )   &&    ((dealerCards[0] >= 7) && (dealerCards[0] <= ACE))   ) {
		return HIT;
	} else if (    ( (handVal>=13)&&(handVal<=16) )   &&    ((dealerCards[0] >= 2) && (dealerCards[0] <= 6))   ) {
		return STAND;
	} else if (    ( (handVal>=17)&&(handVal<=19) )   ) {
		return STAND;
	} else if (    ( (handVal>=15)&&(handVal<=16) )   &&    ((dealerCards[0] >= 7) && (dealerCards[0] <= 8))   ) {
		return HIT;
	} else if (    ( (handVal==15) )   &&    ((dealerCards[0] == 9) || (dealerCards[0] == ACE))   ) {
		return HIT;
	} else if (    ( (handVal==16) )    &&    ((dealerCards[0] >= 9) && (dealerCards[0] <= ACE))   ) {
		if (cards.size() == 2){
			return SURRENDER;
		} else {
			return STAND;
		}
	} else if (    ( (handVal==15) )    &&    ((dealerCards[0] ==10))   ) {
		if (cards.size() == 2){
			return SURRENDER;
		} else {
			return STAND;
		}
	} else if ((handVal == 21) || (handVal == 20)) {
				return STAND;
	}	
	cout << "UNCAUGHT HAND: " << "mycards: ";
	for (int a :cards){
		cout << a << " "; 
	}
	cout << "opp cards: ";
	for(int a : dealerCards) {
		cout << a << " ";
	}
	cout<<endl;
	// Aces can count as 1 or 11. past first 2 cards assume they're 11 instead of ace for summaton unless it'd be a bust in which case assume 1.
	// Use chart
	// Eval based on num of cards in hand (vector size)
	return 30;
}




int main () {


	// parse the input here
	// look up hand in the chart? represent the chart as an array?


	cout << "Entered Main" << endl;

	
	// Deck deck;
	// for (int i=0; i<52; i++) {
	// 	cout << deck.draw() << endl;
	// }
	
// assume every 25 cards we shuffle the deck again


	// init sim
	Deck deck; // starts off preshuffled
	vector<int> dealerHand;
	vector<int> userHand[4]; // userHands[1] to 3 only get used if split

	double userTotal=100;

	bool handDone;
	bool userBust[4];
	bool dealNeeded;
	int currentBet;

	int splitTo = 1; // number of hands, can be increased when a split happens
// // DEBUG

	for(int y = 0; y<30000; y++) {
		splitTo = 1;
		cout << "----------------- NEW HAND ------------" << endl;
		// Deal initial 3 cards and take the bet
		userHand[0].push_back(deck.draw());
		userHand[0].push_back(deck.draw());
		dealerHand.push_back(deck.draw());
		userTotal -= BASEBET;
		dealNeeded = false;
		
		cout << "dealerHand: ";
		for(int debugA :dealerHand) {
			cout << debugA << " ";
		}
		cout << endl;

		currentBet = BASEBET;
		
		// Loop through every non empty userHand
		// Every loop must call determineAction with the current hand
		// Then it makes a decision 
		bool surrender = false;
		bool splitBool = false; // split
		for (int a = 0; userHand[a].size()>=2 && a<4; a++) {
			cout << "entered a = " << a << endl;
			handDone = false;
			userBust[a] = false;
			// loop til current hand is finished
			while (!handDone) {
				cout << "userhand[0]: ";
				for(int debugA :userHand[0]) {
					cout << debugA << " ";
				}
				cout << endl;
				cout << "userhand[1]: ";
				for(int debugA :userHand[1]) {
					cout << debugA << " ";
				}
				cout << endl;
				cout << "userhand[2]: ";
				for(int debugA :userHand[2]) {
					cout << debugA << " ";
				}
				cout << endl;
				cout << "userhand[3]: ";
				for(int debugA :userHand[3]) {
					cout << debugA << " ";
				}
				cout << endl;
				switch(determineAction(userHand[a], dealerHand, splitBool)) {
					case HIT:
						cout << "HIT CASE entered"<< endl;
						userHand[a].push_back(deck.draw());
						if (sumOfVector(userHand[a]) > 21) {
							cout << "Busted on hand "<< a << endl;
							handDone = true;
							userBust[a] = true;
						} else if (userHand[a].size()>=5) {
							cout << "5 card on hand "<< a << endl;
							// Five card charlie
							handDone = true;
						}
						break;

					case STAND:
						cout << "STAND CASE entered"<< endl;
						handDone = true; 
						break;

					case DOUBLE:
						cout << "DOUBLE CASE entered"<< endl;
						userTotal -= BASEBET;
						currentBet = BASEBET * 2;
						userHand[a].push_back(deck.draw());
						if (sumOfVector(userHand[a]) > 21) {
							userBust[a] = true;
						} 
						handDone = true;
						break;

					case SPLIT:
						cout << "SPLIT CASE entered"<< endl;
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
						cout << "SURRENDER CASE entered"<< endl;
						handDone = true;
						surrender = true;
						break;
					default:
						cout << "defaulted, a = " << a << endl;
						handDone = true;

						break;
				}	
			}

		}
		// Determine if deal is needed
		for (int a = 0; userHand[a].size()>=2 && a<4; a++ ) {
			if (userBust[a] == false) {
				dealNeeded = true;
			}
		}
		if (dealNeeded) {
			while (sumOfVector(dealerHand) < 17) {
				dealerHand.push_back(deck.draw());
			}
		}
		cout << "dealerHand -----: ";
		for(int debugA :dealerHand) {
			cout << debugA << " ";
		}
		cout << endl;

		// Print all the hands after the game
		cout << "userhand[0]: ";
		for(int debugA :userHand[0]) {
			cout << debugA << " ";
		}
		cout << endl;
		cout << "userhand[1]: ";
		for(int debugA :userHand[1]) {
			cout << debugA << " ";
		}
		cout << endl;
		cout << "userhand[2]: ";
		for(int debugA :userHand[2]) {
			cout << debugA << " ";
		}
		cout << endl;
		cout << "userhand[3]: ";
		for(int debugA :userHand[3]) {
			cout << debugA << " ";
		}
		cout << endl;

		// After going through all the hands determine the winning amounts of each hand
		int dealerSum = sumOfVector(dealerHand);
		for (int a = 0; userHand[a].size()>=2 && a<4; a++ ) {
			int curHandTotal = sumOfVector(userHand[a]);
			if (userBust[a] == false) {
				// 5 card
				if ((userHand[a].size()==5) && (!((dealerSum == 21)&&(dealerHand.size()==2)))){
					userTotal+=currentBet*2;
				} else if (((curHandTotal == 21)&&(userHand[a].size()==2)) && (!((dealerSum == 21)&&(dealerHand.size()==2))) )  {
					// natural 21 and dealer doesn't have nat 21
					userTotal+=currentBet * 2.5;
				} else if (((curHandTotal == 21)&&(userHand[a].size()==2)) && ((dealerSum == 21)&&(dealerHand.size()==2)) )  {
					// tie with both players nat 21
					userTotal+=currentBet;
				} else if (dealerSum > 21) { // dealer bust
					userTotal+=currentBet*2;
				} else if (dealerSum < curHandTotal) { // user hand is bigger
					userTotal+=currentBet*2;
				} else if (dealerSum == curHandTotal) {
					if (!(((dealerSum == 21) && (dealerHand.size()==2)))) { // if both hands are equal and dealer no nat 21
						userTotal+=currentBet;
					}
				} else if (surrender) {
					userTotal+=currentBet*0.5;
				}
			}
		}

		// After going through all the hands we must clear everything
		deck.shuffle();
		for(int d = 0; d<4;d++){
			userHand[d].clear();
		}
		dealerHand.clear();
		cout << userTotal << endl;
	}
// END DEBUG


	// cout << userTotal << endl;

	// 1. Shuffle deck
	// 2. Spit out 1 card from dealer
	// 3. Spit out 2 cards to user
	// 4. look at chart w/ function  
	// 5. take function-specified action
	// 6. if stand or surrender end player hand
	//      else if split
	//			
	// 		if not stand or surrender check if bust, if not go back to 4

	return 0;
}