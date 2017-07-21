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
    public double determineWinner(ArrayList<Integer> userHand, ArrayList<Integer> dealerHand, boolean isDouble) {
        // if double, return 2x vals
        // Evaluate hand strength
        // Determine who has stronger hand
        // if dealer, always 0
        // if tie ret 1
        // if user, return 2 or 2.5
    	return 0;
    }
}