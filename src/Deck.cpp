#include "Deck.h"

Deck::Deck () {
	// Initialize the vector left with all 52 cards
	for(int i = 1; i<10; i++) {
		for (int j = 0; j < 4; j++){
			left.push_back(i);
		}
	}
	for (int i = 0; i < 16; i++) {
		left.push_back(10);
	}
	srand (time(NULL));
}

Deck::Deck(const Deck &obj) {
	left = obj.left;
	used = obj.used;
}

int Deck::draw () {
	if (left.size() <= 0) {
		return -1;
	}
	int pos = rand() % left.size();
	int ret = left[pos];
	left.erase(left.begin() + pos);
	used.push_back(ret);
	if (ret == 1) {
		return ACE;
	}
	return ret;
}


void Deck::shuffle () {
	for (int a : used) {
		left.push_back(a);
	}
	used.clear();
}

Deck::~Deck () {

}