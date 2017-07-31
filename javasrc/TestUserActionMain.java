import java.util.*;

class TestUserActionMain {
    public static void main(String args[]) {
    	ArrayList<Integer> uHand = new ArrayList<Integer>();
    	ArrayList<Integer> dHand = new ArrayList<Integer>();

    	// --------- Test Quad 1 ---------
    	uHand.add(11);
    	uHand.add(2);
    	dHand.add(5);
        System.out.printf("Entered TestUserActionMain\n");
        Decision decision = new Decision("input");
        System.out.printf("action: %s | expected: 3,15\n", decision.determineUserAction(uHand, dHand, false));

        // Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
		uHand.add(11);
    	uHand.add(11);
    	dHand.add(11);
    	System.out.printf("action: %s | expected: 9,33\n", decision.determineUserAction(uHand, dHand, false));

        // Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
		uHand.add(2);
    	uHand.add(3);
    	dHand.add(8);
    	System.out.printf("action: %s | expected: 6,0\n", decision.determineUserAction(uHand, dHand, false));

    	// --------- Test Quad 2 ---------
    	// Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(11);
    	uHand.add(2);
    	dHand.add(5);
    	dHand.add(10);
        System.out.printf("action: %s | expected: 21,15\n", decision.determineUserAction(uHand, dHand, false));

        // Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(11);
    	uHand.add(2);
    	dHand.add(11);
    	dHand.add(11);
        System.out.printf("action: %s | expected: 36,15\n", decision.determineUserAction(uHand, dHand, false));

        // Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(11);
    	uHand.add(2);
    	dHand.add(10);
    	dHand.add(11);
        System.out.printf("action: %s | expected: 27,15\n", decision.determineUserAction(uHand, dHand, false));

        // Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(11);
    	uHand.add(2);
    	dHand.add(3);
    	dHand.add(11);
        System.out.printf("action: %s | expected: 29,15\n", decision.determineUserAction(uHand, dHand, false));


        // --------- Test Quad 3 ---------
    	// Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(11);
    	uHand.add(2);
    	dHand.add(5);
        System.out.printf("action: %s | expected: 3,50\n", decision.determineUserAction(uHand, dHand, true));

    	// Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(2);
    	uHand.add(2);
    	dHand.add(11);
        System.out.printf("action: %s | expected: 9,34\n", decision.determineUserAction(uHand, dHand, true));

    	// Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(11);
    	uHand.add(11);
    	dHand.add(5);
        System.out.printf("action: %s | expected: 3,59\n", decision.determineUserAction(uHand, dHand, true));

        // --------- Test Quad 4 ---------
    	// Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(11);
    	uHand.add(2);
    	dHand.add(5);
    	dHand.add(5);
        System.out.printf("action: %s | expected: 16,50\n", decision.determineUserAction(uHand, dHand, true));

        // Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(3);
    	uHand.add(2);
    	dHand.add(5);
    	dHand.add(5);
        System.out.printf("action: %s | expected: 16,35\n", decision.determineUserAction(uHand, dHand, true));
		
		// --------- Test Quad 5 ---------
		// Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(3);
    	uHand.add(2);
    	uHand.add(2);
    	dHand.add(5);
        System.out.printf("action: %s | expected: 3,61\n", decision.determineUserAction(uHand, dHand, true));

        // Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(11);
    	uHand.add(2);
    	uHand.add(2);
    	dHand.add(5);
        System.out.printf("action: %s | expected: 3,52\n", decision.determineUserAction(uHand, dHand, true));

        // Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(11);
    	uHand.add(2);
    	uHand.add(10);
    	dHand.add(5);
        System.out.printf("action: %s | expected: 3,67\n", decision.determineUserAction(uHand, dHand, true));

        // --------- Test Quad 6 ---------
		// Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(3);
    	uHand.add(2);
    	uHand.add(2);
    	dHand.add(11);
    	dHand.add(5);
        System.out.printf("action: %s | expected: 31,61\n", decision.determineUserAction(uHand, dHand, true));

        // Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(11);
    	uHand.add(2);
    	uHand.add(2);
    	dHand.add(11);
    	dHand.add(5);
        System.out.printf("action: %s | expected: 31,52\n", decision.determineUserAction(uHand, dHand, true));

        // Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(11);
    	uHand.add(2);
    	uHand.add(10);
    	dHand.add(11);
    	dHand.add(5);
        System.out.printf("action: %s | expected: 31,67\n", decision.determineUserAction(uHand, dHand, true));

        // --------- Test Quad 7 ---------
		// Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(3);
    	uHand.add(2);
    	uHand.add(2);
    	uHand.add(2);
    	dHand.add(11);
        System.out.printf("action: %s | expected: 9,80\n", decision.determineUserAction(uHand, dHand, true));


        // --------- Test Quad 8 ---------
		// Clear hands
        uHand.clear();
        dHand.clear();
        // Add new cards
    	uHand.add(3);
    	uHand.add(2);
    	uHand.add(2);
    	uHand.add(11);
    	dHand.add(11);
        System.out.printf("action: %s | expected: 9,79\n", decision.determineUserAction(uHand, dHand, true));
		
    }
}