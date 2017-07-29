import java.util.*;

class TestUserActionMain {
    public static void main(String args[]) {
    	ArrayList<Integer> uHand = new ArrayList<Integer>();
    	ArrayList<Integer> dHand = new ArrayList<Integer>();
    	uHand.add(11);
    	uHand.add(2);
    	dHand.add(5);
        System.out.printf("Entered TestUserActionMain\n");
        Decision decision = new Decision("input");
        System.out.printf("action: %s", decision.determineUserAction(uHand, dHand, false));
    }
}