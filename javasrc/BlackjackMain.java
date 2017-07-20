// BlackjackMain
// This class contains the main loop of our simulation
//http://codr.io/lykh7q5 (deck.java)

import java.util.*;

public class BlackjackMain {

    private static final double BASEBET = 20;

    public static void main(String[] args) {
        System.out.printf("Entered main\n");
        // Setup:
        Deck deck = new Deck();
        User user = new User(0);
        StateMachine sm = new StateMachine();
        Decision decision = new Decision();

        int iterations = 0;
        int tokens = 1;

        ArrayList<ArrayList<Integer>> hands = new ArrayList<ArrayList<Integer>>(4);
        ArrayList<Integer> dealerHand = new ArrayList<Integer>();
        prepareHands(hands);
        // hands.get(0).add(2);

        boolean done=false;
        while(done == false) {
            switch (sm.getState()) {
                case DEDUCT_BASEBET_1: 
                    // Check if we're done
                    iterations++;
                    if (iterations>tokens) {
                        done = true;
                        break;
                    }
                    // Deduct the bet size
                    user.changeCash(-1*BASEBET);
                    // Shuffle the deck
                    deck.shuffle();
                    // Clear out the hands
                    // System.out.printf("|%d|",hands.get(0).size());
                    clearHands(hands);
                    // System.out.printf("|%d|",hands.get(0).size());
                    // System.out.printf("In state 1");
                    break;
                case DEAL_INITIAL_CARDS_2:
                    // Give user 2 cards
                    hands.get(0).add(deck.draw());
                    hands.get(0).add(deck.draw());
                    // Give dealer 1
                    hands.get(0).add(deck.draw());
                    // Query chart
                        // Advance state
                    break;
                case REFUND_3:
                    break; 
                case SPLIT_4:
                    break;
                case DEAL_CARD_5:
                    break; 
                case DET_DEALER_ACTION_6:
                    break;
                case HANDLE_FIRST_7:
                    break;
                case DEALER_DRAW_8:
                    break;
                case DET_WINNER_9:
                    break;
                case DEAL_CARD_10:
                    break;
                case DET_DEALER_ACTION_11:
                    break;
                case DEALER_DRAW_12:
                    break;
                case PLUS_13:
                    break;
                case PLUS_14:
                    break;
                case PLUS_15:
                    break;
                case PLUS_16:
                    break;
                case DET_WINNER_17:
                    break;
                case PLUS_18:
                    break;
                case PLUS_19:
                    break;
                case PLUS_20:
                    break;
                case PLUS_21:
                    break;
                case HANDLE_SECOND_22:
                    break;
                case REFUND_23:
                    break;
                case SPLIT_24:
                    break;
                case HANDLE_FIRST_25:
                    break;
                case DEAL_CARD_26:
                    break;
                case DET_DEALER_ACTION_27:
                    break;
                case HANDLE_FIRST_28:
                    break;
                case DET_DEALER_ACTION_29:
                    break;
                case DEAL_CARD_30:
                    break;
                case DEALER_DRAW_31:
                    break;
                case DEALER_DRAW_32:
                    break;
                case DET_WINNER_33:
                    break;
                case DET_WINNER_34:
                    break;
                case PLUS_35:
                    break;
                case PLUS_36:
                    break;
                case PLUS_37:
                    break;
                case PLUS_38:
                    break;
                case PLUS_39:
                    break;
                case PLUS_40:
                    break;
                case PLUS_41:
                    break;
                case PLUS_42:
                    break;
                case DEAL_CARD_43:
                    break;
                default:
                    System.out.printf("In default");
                    break;
            }
        }

    }

    private static void clearHands(ArrayList<ArrayList<Integer>> hands) {
        for(int i = 0; i<hands.size(); i++) {
            hands.get(i).clear();
        }
    }


    private static void prepareHands(ArrayList<ArrayList<Integer>> hands) {
        ArrayList<Integer> userHand1 = new ArrayList<Integer>();
        ArrayList<Integer> userHand2 = new ArrayList<Integer>();
        ArrayList<Integer> userHand3 = new ArrayList<Integer>();
        ArrayList<Integer> userHand4 = new ArrayList<Integer>();
        hands.add(userHand1);
        hands.add(userHand2);
        hands.add(userHand3);
        hands.add(userHand4);
    }

}