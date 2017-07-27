import java.util.*;

class Decision {
    
    private static final int ACE = 11;
    private ChartParser cp;

    public Decision(String in) {
        cp = new ChartParser(in);
    }

    // DetermineDealerAction()
    // Returns what action the dealer should take
    public Action determineDealerAction(ArrayList<Integer> dealerHand) {
        if (sumOfArrayList(dealerHand) < 17) {
        	return Action.HIT;
    	} else {
    		return Action.STAND;
    	}
    }
    
    // DetermineUserAction()
    // Determine what action the user should take
    // hitAndStandOnly indicates if first or second hand of a split, can only hit and stand if first hand
    public Action determineUserAction(ArrayList<Integer> userHand, ArrayList<Integer> dealerHand, boolean hitAndStandOnly) {
        // if userHand is size 3 or greater and has at least one ace in it
        // if non weak aces eval to 10 or under use 2 card version
        // if 4 cards use 4 card version
        // else eval as sum of all, or 4 card chart 

        int uHandSize = userHand.size();
        int dHandSize = dealerHand.size();
        int uLowSum = lowSumOfArrayList(userHand);
        int dHighSum = sumOfArrayList(dealerHand);
        boolean dAce[] = new boolean[2]; // only set to non zero if there is an ace in dealer hand 
        boolean uAce[] = new boolean[5];
        int val, x, y, acePosition = -1; // val is used to save function calls, x, y are positions, acePosition is one ace in the hand
        // Mark the aces 
        for (int i = 0; i < 5; i++) {
            val = dealerHand.get(i);
            if (val==11) {
                dAce[i] = true;
            } else {
                dAce[i] = false;
            }
        }
        for (int i = 0; i < 2; i++) {
            val = userHand.get(i);
            if (val==11) {
                acePosition = i;
                uAce[i] = true;
            } else {
                uAce[i] = false;
            }
        }

        // quad 1 of chart
        if ((uHandSize == 2) && (dHandSize==1) && (hitAndStandOnly==false)) {
            // x value is dealer's card val-2 
            x = dealerHand.get(0) - 2;
            // y value is determined by whether ace is present in hand
                // whether pair
                // else combined total

            // Handle ace in userHand
            if (doesArrayHaveTrue(uAce) == true) {
                y = userHand.get(cStyleNot(acePosition)) + 13;
            }
        }


        return Action.HIT;
    }
    // returns if a bool array has a true element
    private boolean doesArrayHaveTrue(boolean arg[]) {
        for (boolean a : arg) {
            if (a == true) {
                return true;
            }
        }
        return false;
    }

    // function to do c style ! operator
    private int cStyleNot(int i) {
        if (i == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    // Evals at highest possible value
    private int sumOfArrayList(ArrayList<Integer> in) {
        int total = 0;
        int aces = 0;
        for(int cur : in) {
            total = total + cur;
            if (cur == ACE) {
            	aces++;
            }
        }
        while ((total>21) && (aces>0)) {
        	total-=10;
        	aces--;
        }
        return total;
    }

    // Evals at lowest possible value
    public int lowSumOfArrayList(ArrayList<Integer> in) {
        int total = 0;
        for(int cur : in) {
            if (cur == ACE) {
                total++;
            } else {
                total = total + cur;
            }
        }
        return total;
    }

    // determineWinner() 
    // returns a multiplier for the amount won, x0, x1, x2, x2.5
    public Winner determineWinner(ArrayList<Integer> userHand, ArrayList<Integer> dealerHand) {
        double ret;
        int uHandStr, dHandStr; 
        // 25 nat 21
        // 24 5 card charlie
        // sum for all else

        // Handle double in main
        // Evaluate hand strength
        int uSum = sumOfArrayList(userHand);
        int dSum = sumOfArrayList(dealerHand);
        if ((uSum == 21) && (userHand.size()==2)) {
            uHandStr = 25;
        } else if ((uSum <= 21) && (userHand.size()==5)) {
            uHandStr = 24;
        } else {
            uHandStr = uSum;
        }
        if ((dSum == 21) && (dealerHand.size()==2)) {
            dHandStr = 25;
        } else if ((dSum <= 21) && (dealerHand.size()==5)) {
            dHandStr = 24;
        } else {
            dHandStr = dSum;
        }

        // Determine who has stronger hand
        // if dealer, always 0
        // if tie ret 1
        // if user, return 2 or 2.5
        if ((uHandStr == 25) && (dHandStr!=25)) {
            return Winner.A_25;
        } else if (uHandStr > dHandStr) {
            return Winner.B_20;
        } else if (uHandStr == dHandStr) {
            return Winner.C_10;
        } else {
            return Winner.D_00;
        }
    }
}