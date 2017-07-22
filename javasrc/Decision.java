import java.util.*;

class Decision {
    
    private static final int ACE = 11;

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
        return Action.HIT;
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

    // determineWinner() 
    // returns a multiplier for the amount won, x0, x1, x2, x2.5
    public double determineWinner(ArrayList<Integer> userHand, ArrayList<Integer> dealerHand) {
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
            dHandStr = 2;
        } else if (dSum <= 21) && (dealerHand.size()==5)) {
            dHandStr = 1;
        } else {
            dHandStr = dSum;
        }

        // Determine who has stronger hand
        // if dealer, always 0
        // if tie ret 1
        // if user, return 2 or 2.5
        if ((uHandStr == 25) && (dHandStr!=25)) {
            return 2.5;
        } else if (uHandStr > dHandStr) {
            return 2.0;
        } else if (uHandStr == dHandStr) {
            return 1.0;
        } else {
            return 0.0;
        }
    }
}