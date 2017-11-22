// general program flow 
// 1. wait for buttons load
// 2. create a reference to them 
// 2a. move the play button down to the proper position
// 3. wait for cards to appear
// 4. read in cards and remove the appropriate buttons
// 5. wait for a change in the cards
// 6. remove appropriate buttons
// 7. If the hand ends, we want to move the button down so we can keep clicking
// 8. Move the play button down



// call waitForButtonsToLoad()
// upon load set up all the buttons with hideButtons() and playSetup()
// call wait waitForCards(), should check for all action buttons being disabled, if all are disabled then we keep waiting
	// Need to implement a check that breaks the wait loop if we have finished the hand since that disables all the buttons, when this happens hide all the buttons
//	when the cards are found, waitForCards will call the appropriate showButton function

// after calling the showButton function we wait for buttons to get disabled. once this happens we wait til they aren't all disabled.

//TODO
// Have to hide start button otherwise we can still click it and fuck the game up.

// Query chart with hands
class Chart {

	// Populate a 2d array 
	constructor() {
		console.log("ENTERED CONSTRUCTOR");
		this.actionChart = [
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,0,0,0,0,0,0,0,0,0],
			[0,2,2,2,2,0,0,0,0,0,2,2,2,0,0,0,0,0,2,0,0,0,0,0,0,0,4,4,0,0,0,0,0,0,0,4,0],
			[2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,0,2,2,2,2,2,2,0,0,0,4,4,2,2,2,2,2,0,4,4,0],
			[2,2,2,2,2,2,2,2,2,0,2,2,2,2,2,2,2,0,2,2,2,2,2,0,0,0,4,4,2,2,2,2,2,0,4,4,0],
			[0,0,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,0,0,1,1,0,0,0,4,4,0,0,0,0,0,0,4,4,1],
			[1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,0,0,1,1,0,0,0,4,4,0,0,0,0,0,0,4,4,4],
			[1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,0,0,1,1,0,0,0,4,4,0,0,0,0,0,0,4,4,4],
			[1,1,1,1,1,0,0,4,4,4,1,1,1,0,0,4,4,4,1,1,1,1,1,0,0,0,4,4,0,0,0,0,0,4,4,4,4],
			[1,1,1,1,1,0,0,4,4,4,1,1,1,0,0,4,4,4,1,1,1,1,1,0,4,4,4,4,0,0,0,0,0,4,4,4,4],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,4,4,4,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,4,4,1,1,1,1,1,1,4,4,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,4,1,1,1,1,1,1,1,4,1],
			[0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,2,2,2,2,2,0,1,4,4,4,0,0,0,0,0,0,4,4,0],
			[0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,2,2,2,2,2,0,1,4,4,4,0,0,0,0,0,0,4,4,0],
			[0,0,2,2,2,0,0,0,0,0,2,2,2,0,0,0,0,0,2,0,0,0,0,0,1,4,4,4,0,0,0,0,0,0,4,4,0],
			[0,0,2,2,2,0,0,0,0,0,2,2,2,0,0,0,0,0,2,0,0,0,0,0,1,4,4,4,0,0,0,0,0,0,4,4,0],
			[0,2,2,2,2,0,0,0,0,0,2,2,2,0,0,0,0,0,2,0,0,0,0,0,1,4,4,4,0,0,0,0,0,0,4,4,0],
			[1,2,2,2,2,1,1,0,0,0,2,2,2,1,1,0,0,0,2,1,1,0,0,1,1,4,4,4,1,1,1,1,1,1,4,4,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,4,1,1,1,1,1,1,1,4,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],
			[3,3,3,3,3,3,0,0,0,0,3,3,3,3,0,0,0,0,3,3,0,0,0,0,4,4,4,4,0,0,0,0,0,0,0,0,0],
			[3,3,3,3,3,3,0,0,0,0,3,3,3,3,0,0,0,0,3,3,0,0,0,0,4,4,4,4,0,0,0,0,0,0,0,0,0],
			[0,0,0,3,3,0,0,0,0,0,0,3,3,0,0,0,0,0,3,0,0,0,0,0,4,4,4,4,0,0,0,0,0,0,0,0,0],
			[2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,0,0,2,2,2,2,0,0,4,4,4,4,2,2,2,2,2,0,4,4,0],
			[3,3,3,3,3,0,0,0,0,0,3,3,3,0,0,0,0,0,3,0,0,0,0,0,4,4,4,4,0,0,0,0,0,4,4,4,0],
			[3,3,3,3,3,3,0,0,0,0,3,3,3,3,0,0,0,0,3,3,0,0,0,0,4,4,4,4,0,0,0,0,0,4,4,4,0],
			[3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,3,3,3,3,3,4,4,4,4],
			[3,3,3,3,3,1,3,3,1,1,3,3,3,1,3,3,1,1,3,1,3,3,1,1,4,4,4,4,1,1,1,1,1,1,4,4,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1],
			[3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,3,3,3,3,3,3,4,4,3],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1],
			[1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,0,0,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,0,1],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[1,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,0,0,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,0,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,0,0,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,0,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[0,0,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0,0],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,0,0,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1],
			[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
		];
		this.userHand = [];
		this.dealerHand = [];
		console.log(this.actionChart);
	}
	addToUserHand(newHand) {
		this.userHand = newHand.slice();
		console.log("USER HAND");
		console.log(this.userHand);
	}
	addToDealerHand(newHand) {
		this.dealerHand = newHand.slice();
		console.log("DEALER HAND");
		console.log(this.dealerHand);
	}
	// Query chart function, returns 01234
	// Use hand logic from main program/
// TODO Translate query method from chartparser.java
	query(hitAndStandOnly) {
		var uHandSize = this.userHand.length;
		var dHandSize = this.dealerHand.length;
		var uLowSum = this.lowSumOfArrayList(this.userHand);
		var uAce = [];
        // boolean uAce[] = new boolean[userHand.size()];
        var val;
        var x;
        var y;
        var uAcePosition = -1;
        var i;
        for (i = 0; i < this.userHand.length; i++) {
            val = this.userHand[i];
            if (val==11) {
                uAcePosition = i;
                uAce[i] = true;
            } else {
                uAce[i] = false;
            }
        }
        if ((uHandSize == 2) && (dHandSize==1) && (hitAndStandOnly==false)) {
            x = this.detOddQuadX(this.dealerHand);
            if (this.userHand[0]==this.userHand[1]) { 
                y = this.userHand[0] + 22;
            } else if (this.doesArrayHaveTrue(uAce) == true) { 
                y = this.userHand[this.cStyleNot(uAcePosition)] + 13;
            } else {
                y = this.userHand[0] + this.userHand[1] - 5; 
            }
            return (this.actionChart[y])[x];
        }
        if ((uHandSize == 2) && (dHandSize==2) && (hitAndStandOnly==false)) {
            x = this.detEvenQuadX(this.dealerHand);
            if (this.userHand[0]==this.userHand[1]) { 
                y = this.userHand[0] + 22;
            } else if (this.doesArrayHaveTrue(uAce) == true) { 
                y = this.userHand[this.cStyleNot(uAcePosition)] + 13;
            } else { 
                y = this.userHand[0] + this.userHand[1] - 5; 
            }
            return (this.actionChart[y])[x];
        }
        if ((uHandSize == 2) && (dHandSize==1) && (hitAndStandOnly==true)) {
            x = this.detOddQuadX(this.dealerHand);
            if (this.doesArrayHaveTrue(uAce) == true) {
                y = this.userHand[this.cStyleNot(uAcePosition)] + 48;
            } else { 
                y = this.userHand[0] + this.userHand[1] + 30; 
            }
            return (this.actionChart[y])[x];
        }
        if ((uHandSize == 2) && (dHandSize==2) && (hitAndStandOnly==true)) {
            x = this.detEvenQuadX(this.dealerHand);
            if (this.doesArrayHaveTrue(uAce) == true) { 
                y = this.userHand[this.cStyleNot(uAcePosition)] + 48;
            } else { 
                y = this.userHand[0] + this.userHand[1] + 30; 
            }
            return (this.actionChart[y])[x];
        }
        var nonAce = []; 
        var lowSumNonAce;
        var firstAce = true;
        if ((uHandSize == 3) && (dHandSize==1) && (hitAndStandOnly==true)) {
            x = this.detOddQuadX(this.dealerHand);
            
            for (i=0;i<this.userHand.length;i++) {
                if (this.userHand[i]==11) {
                    if(firstAce) {
                        firstAce == false;
                    } else {
                        nonAce.push(11);
                    }
                } else {
                    nonAce.push(this.userHand[i]);
                }
            }
            lowSumNonAce = this.lowSumOfArrayList(nonAce);
            if ((this.doesArrayHaveTrue(uAce) == true) && (lowSumNonAce<10)){ 
                // Enters this case
                y = lowSumNonAce + 48;
            } else { 
                y = this.sumOfArrayList(this.userHand) + 54; 
            }
            return (this.actionChart[y])[x];
        }
        if ((uHandSize == 3) && (dHandSize==2) && (hitAndStandOnly==true)) {
            x = this.detEvenQuadX(this.dealerHand);
            for (i=0;i<this.userHand.length;i++) {
                if (this.userHand[i]==11) {
                    if(firstAce) {
                        firstAce == false;
                    } else {
                        nonAce.push(11);
                    }
                } else {
                    nonAce.push(this.userHand[i]);
                }
            }
            lowSumNonAce = this.lowSumOfArrayList(nonAce);
            if ((this.doesArrayHaveTrue(uAce) == true) && (lowSumNonAce<10)){
                y = lowSumNonAce + 48;
            } else { 
                y = this.sumOfArrayList(this.userHand) + 54; 
            }
            return (this.actionChart[y])[x];
        }

        if ((uHandSize == 4) && (dHandSize==1) && (hitAndStandOnly==true)) {
            x = this.detOddQuadX(this.dealerHand);
            y = this.lowSumOfArrayList(this.userHand) + 71;
            return (this.actionChart[y])[x];
        }
        if ((uHandSize == 4) && (dHandSize==2) && (hitAndStandOnly==true)) {
            x = this.detEvenQuadX(this.dealerHand);
            y = this.lowSumOfArrayList(this.userHand) + 71;
            return (this.actionChart[y])[x];
        }
        console.log("error in query.");
        console.log(this.dealerHand);
        console.log(this.userHand);
        console.log(this.dealerHand.length);
        console.log(this.userHand.length);
        console.log(hitAndStandOnly);
        return 0;
	}
	detOddQuadX(dHand) {
        var x;
        x = dHand[0] - 2;
        return x;
    }
    detEvenQuadX(dHand) {
        var x;
        var val;
        var dAcePosition = -1;
        var dHighSum = this.sumOfArrayList(dHand);
        var dAce = [];
        var i;
        for (i = 0; i < dHand.length; i++) {
            val = dHand[i];
            if (val==11) {
                dAcePosition = i;
                dAce[i] = true;
            } else {
                dAce[i] = false;
            }
        }
        if ((dHand[1] == 11) && (dHand[0]==dHand[1])) { // Handle pair 
            x = 36;
        } else if(dHighSum == 21) {
            x = 27;
        } else if (this.doesArrayHaveTrue(dAce)){
            x = dHand[this.cStyleNot(dAcePosition)] + 26;
        } else {
            x = dHand[0] + dHand[1] + 6;
        }
        return x;
    }

    isContinueShowing() {

    }

    // returns if a bool array has a true element
    doesArrayHaveTrue(arg) {
    	var i;
    	var a;
        for (i = 0; i<arg.length; i++) {
        	a = arg[i];
            if (a == true) {
                return true;
            }
        }
        return false;
    }
    // function to do c style ! operator
    cStyleNot(i) {
        if (i == 0) {
            return 1;
        } else {
            return 0;
        }
    }
    // Evals at highest possible value
    sumOfArrayList(input) {
        var total = 0;
        var aces = 0;
        var i;
        var cur;
        for(i = 0;i<input.length; i++) {
        	cur = input[i];
            total = total + cur;
            if (cur == 11) {
            	aces++;
            }
        }
        while ((total>21) && (aces>0)) {
        	total-=10;
        	aces--;
        }
        return total;
    }

	lowSumOfArrayList(cards) {
		var total = 0;
		var cur;
		var i;
        for(i = 0; i<cards.length; i++) {
  				cur = cards[i];
            if (cur == 11) {
                total++;
            } else {
                total = total + cur;
            }
        }
        return total;
	}

}

// TODO check for continue button
//document.getElementsByClassName("win-lose-wrap bj-show").length

class StartContainer {
	constructor() {
		this.actionButtons = undefined;
		this.play = undefined;
		this.hit = undefined;
		this.stand = undefined;
		this.doubledown = undefined;
		this.split = undefined;
		this.insurance = undefined;
		this.surrender = undefined;
		this.continue = undefined;
		this.chart = new Chart(); 
	}

	startLoop() {
		this.waitForButtonsToLoad();
	}

	waitForButtonsToLoad() {
		var context = this;
		this.actionButtons = document.getElementsByClassName("action-btn-wrap");
		// check if the actionbuttons have loaded, if not recurse
		if((typeof this.actionButtons === undefined) || (this.actionButtons.length!=7)) {
	        setTimeout(function() {
	            context.waitForButtonsToLoad();
	        }, 100);
		} else {
			this.play = this.actionButtons[0];
			this.hit = this.actionButtons[1];
			this.stand = this.actionButtons[2];
			this.doubledown = this.actionButtons[3];
			this.split = this.actionButtons[4];
			this.insurance = this.actionButtons[5];
			this.surrender = this.actionButtons[6];
			this.continue = document.querySelectorAll(".continue")[0];
            this.yesButton = document.getElementsByClassName("confirm-action yes")[0];
			this.setupPlayButton();
			//this.setupContinueButton();
			this.hideButtons();
			// this.showHit();
			// this.showStand();
			// this.showDouble();

			this.waitForCards();
			return;
		}
	}
	waitForCards() {
        if(document.getElementsByClassName("new-bet-wrap")[0].getAttribute("class").search("bj-show")==-1) {
            this.hidePlay();
            this.hideContinue();
        } else {
            this.showPlay();
        }
        if(document.getElementsByClassName("win-lose-wrap")[0].getAttribute("class").search("bj-show")==-1) {
            this.hideContinue();
        } else {
            this.showContinue();
        }
        // try {
        //     if(typeof document.getElementsByClassName("game-status-wrap l-confirm")[0] !== undefined) {
        //         document.getElementsByClassName("game-status-wrap l-confirm")[0].style.zIndex = "30";
        //     }
        // } catch (exception) {

        // }


		this.hideButtons();
		// Search action buttons 1-6 for disabled, if all disabled then we have to wait
		// this.hideButtons();
		var i;
		var context = this;
		var recurse = 1;
		for(i = 1; i < 7; i++) {
			if(this.actionButtons[i].getAttribute("class").search("disable") == -1) {
				recurse = 0;
				break;
			}
		}

		// TODO handle case when hand finishes as buttons all get disabled then
		// Recurse if it isn't our turn yet.
		if(recurse == 1) {
			setTimeout(function() {
				context.waitForCards();
			}, 10);
		} else {
			// TODO
			// Move the continue button
			// this.unSetupContinueButton();

			// If it is our turn, we need to read in the cards and query the chart
			// Read in cards
			var cardContainerNode = document.getElementsByClassName("cards");
			var dealerHandNode = cardContainerNode[0].children;
			var userHandNode = cardContainerNode[1].children;
			var regexExtractCardValue = /([0-9]+|J|Q|K|A)/;
			var extractedString;
			var regexReturn;
			var value;
			var handArray = [];
			var dealerHandClasses;
			var userHandClasses;
			// Read in dealers hand. 
			for(i = 0; i < 5; i++) {
				// Add this value to dealer hand
				dealerHandClasses = dealerHandNode[i].getAttribute("class");
				if(dealerHandClasses == null) {
					regexReturn = null;
				} else {
					regexReturn = dealerHandClasses.match(regexExtractCardValue); // Null if less than 5 cards
				}
				
				if (regexReturn == null) {
					console.log("don't add, regexReturn is null.");
				} else {
					extractedString = regexReturn[0];
					if((extractedString==="J")||(extractedString==="Q")||(extractedString==="K")) {
						value = 10;
					} else if (extractedString==="A") {
						value = 11;
					} else if(typeof extractedString !== undefined) {
						value = parseInt(extractedString);
					} else {
						console.log("glitch, regexReturn[0] is some strange string");
					}
					// Add the value to dealer hand
					handArray.push(value);
				}
			}
			// Deal with the lag at the start 
			if(handArray==[]) {
				return;
			}

			this.chart.addToDealerHand(handArray);
			handArray = [];
			// Read in user hand. 
			for(i = 0; i < 5; i++) {
				// Add this value to user hand
				userHandClasses = userHandNode[i].getAttribute("class");
				if (userHandClasses==null) {
					regexReturn = null;
				} else {
					regexReturn = userHandClasses.match(regexExtractCardValue); // Null if less than 5 cards
				}
				if (regexReturn == null) {
					console.log("don't add, regexReturn is null.");
				} else {
					extractedString = regexReturn[0];
					if((extractedString==="J")||(extractedString==="Q")||(extractedString==="K")) {
						value = 10;
					} else if (extractedString==="A") {
						value = 11;
					} else if(typeof extractedString !== undefined) {
						value = parseInt(extractedString);
					}
					// Add the value to user hand
					handArray.push(value);
				}
			}
			this.chart.addToUserHand(handArray);
			handArray = [];
			// Query chart
			var action = this.chart.query(this.hitOrStandOnly());
			switch(action) {
				case 0:
					this.showHit();
					break;
				case 1:
					this.showStand();
					break;
				case 2:
					this.showDouble();
					break;
				case 3:
					this.showSplit();
					break;
				case 4:
					this.showSurrender();
					break;
			}

			// After querying chart wait for next turn before calling waitForCards again
			this.waitForDisable();

		}
	}
    hidePlay() {
        this.play.style.display = "none";
    }
    showPlay() {
        this.play.style.display = "block";
    }
	waitForDisable(){
        try {
            if(typeof document.getElementsByClassName("game-status-wrap l-confirm")[0] !== undefined) {
                document.getElementsByClassName("game-status-wrap l-confirm")[0].style.zIndex = "30";
            }
        } catch (exception) {
            
        }

		var context = this;
		// If all the buttons aren't disabled we have to recurse
		var i;
		var recurse = 0;
		for(i = 1; i < 7; i++) {
			if(this.actionButtons[i].getAttribute("class").search("disable") == -1) {
				recurse = 1;
				break;
			}
		}
		if(recurse == 1) {
			setTimeout(function() {
				context.waitForDisable();
			}, 100);
		} else {
			this.waitForCards();
		}
	}

	// Determine whether we're in a hit or stand only mode
	hitOrStandOnly() {
		var hitOrStandOnly = true;
		var i;
		for(i = 3; i < 7; i++) {
			if(this.actionButtons[i].getAttribute("class").search("disable") == -1) {
				hitOrStandOnly = false;
				break;
			}
		}
		return hitOrStandOnly;
	}


	// Move the continue button to the appropriate spot. doesn't disable it properly though.
	// TODO fix disabling of continue button
	// setupContinueButton() {
	// 	if(typeof this.continue !== undefined) {
	// 		this.continue.style.position = "relative";
	// 		this.continue.style.top = "50px";
	// 	}
	// }
	// unSetupContinueButton() {
	// 	if(typeof this.continue !== undefined) {
	// 		this.continue.style.position = "static";
	// 		this.continue.style.top = "0px";
	// 	}
	// }

    showContinue() {
        if(typeof this.continue !== undefined) {
            this.continue.style.display = "block";
            this.continue.style.position = "relative";
            this.continue.style.top = "50px";
        }
    }
    hideContinue() {
        if(typeof this.continue !== undefined) {
            this.continue.style.display = "none";
            // this.continue.style.position = "relative";
            // this.continue.style.top = "0px";
        }
    }

	// Move the play button to the appropriate spot. 
	setupPlayButton() {
		if(typeof this.play !== undefined) {
			this.play.style.position = "relative";
			this.play.style.top = "282px";
			this.play.style.right = "55px";
			this.play.style.zIndex = "100";
		}
	}

	hideButtons() {
		var i;
		for(i=1;i<7;i++) {
			this.actionButtons[i].style.position = "relative";
			this.actionButtons[i].style.display = "none";
		}
		// // // Move hit
		this.hit.style.left = "90px";
		this.hit.style.zIndex = "10";

		// // Move stand
		this.stand.style.right = "90px";
		this.stand.style.zIndex = "10";
		// // Move doubledown
		this.doubledown.style.left = "135px";
		this.doubledown.style.bottom = "35px";
		this.doubledown.style.zIndex = "10";
		// // Move split
		this.split.style.left = "45px";
		this.split.style.bottom = "35px";
		this.split.style.zIndex = "10";
		// // Move insurance
		this.insurance.style.right = "45px";
		this.insurance.style.bottom = "35px";
		this.insurance.style.zIndex = "10";
		// // Move surrender
		this.surrender.style.right = "135px";
		this.surrender.style.bottom = "35px";
		this.surrender.style.zIndex = "10";

	}
	showHit() {
		var i;
		for(i=1;i<7;i++) {
			this.actionButtons[i].style.position = "relative";
			this.actionButtons[i].style.display = "block";
			this.actionButtons[i].style.zIndex = "10";
		}
		// this.hit.style.display = "block";
		this.hit.style.zIndex = "20";
	}
	showStand() {
		var i;
		for(i=1;i<7;i++) {
			this.actionButtons[i].style.position = "relative";
			this.actionButtons[i].style.display = "block";
			this.actionButtons[i].style.zIndex = "10";
		}
		// this.hit.style.display = "block";
		this.stand.style.zIndex = "20";
	}
	showDouble() {
        // Move the confirm button the appropriate position
        this.yesButton.style.position = "relative";
        this.yesButton.style.top = "428px";
        this.yesButton.style.left = "20px";
        this.yesButton.style.zIndex = "30";

		var i;
		for(i=1;i<7;i++) {
			this.actionButtons[i].style.position = "relative";
			this.actionButtons[i].style.display = "block";
			this.actionButtons[i].style.zIndex = "10";
		}
		// this.hit.style.display = "block";
		this.doubledown.style.zIndex = "20";
	}
	showSplit() {
        // Move the confirm button the appropriate position
        this.yesButton.style.position = "relative";
        this.yesButton.style.top = "447px";
        this.yesButton.style.left = "20px";
        this.yesButton.style.zIndex = "30";

		var i;
		for(i=1;i<7;i++) {
			this.actionButtons[i].style.position = "relative";
			this.actionButtons[i].style.display = "block";
			this.actionButtons[i].style.zIndex = "10";
		}
		// this.hit.style.display = "block";
		this.split.style.zIndex = "20";
	}
	showSurrender() {
        // Move the confirm button the appropriate position
        this.yesButton.style.position = "relative";
        this.yesButton.style.top = "446px";
        this.yesButton.style.left = "20px";
        this.yesButton.style.zIndex = "30"; 
		var i;
		for(i=1;i<7;i++) {
			this.actionButtons[i].style.position = "relative";
			this.actionButtons[i].style.display = "block";
			this.actionButtons[i].style.zIndex = "10";
		}
		// this.hit.style.display = "block";
		this.surrender.style.zIndex = "20";
	}

}
// TODO make it so that yes dialog overlays	
//document.getElementsByClassName("confirm-action yes")[0].style.left = "20px"


var startContainer = new StartContainer();
startContainer.startLoop();
var chart = new Chart();



