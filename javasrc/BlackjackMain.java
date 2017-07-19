// BlackjackMain
// This class contains the main loop of our simulation
//http://codr.io/lykh7q5 (deck.java)

import java.util.*;

public class BlackjackMain {
    public static void main(String[] args) {
        // Setup:
        Deck deck = new Deck();
        StateMachine sm = new StateMachine();
        Decision decision = new Decision();
        int iterations = 0;
        int hands = 1;
        ArrayList<Integer> Userhand = new ArrayList<Integer>();

        boolean done=false;
        while(done == true) {
            switch (sm.getState()) {
                case DEDUCT_BASEBET_1: 
                    // Check if we're done
                    iterations++;
                    if (iteractions>hands) {
                        done = true;
                        break;
                    }
                    // Deduct the bet size
                    // Shuffle the deck
                    // Clear out the hands
                    System.out.printf("In state 1");
                    

                    break;
                case DEAL_INITIAL_CARDS_2:
                    // Give user 2 cards
                    // Give dealer 1
                    // Query chart
                        // Advance state
                    break;
                case REFUND_3:
                    // 
                default:
                    System.out.printf("In default");
                    break;
            }
        }

    }
}