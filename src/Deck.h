#ifndef DECK_H
#define DECK_H

#include <vector>
#include <cstdlib>
#include <ctime>

#define ACE 11

using namespace std;

class Deck {
	public:
		Deck ();
		Deck(const Deck &obj);
		int draw(); // draws a card from the deck and returns the value
		void shuffle(); // merges left and used vars into left
		virtual ~Deck();
	private:
		vector<int> left; // cards left in the deck
		vector<int> used; // cards used up
};

#endif