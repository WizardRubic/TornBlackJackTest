// BlackjackMain
// This class contains the main loop of our simulation
//http://codr.io/lykh7q5 (deck.java)

import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;

public class BlackjackMain {

    public static BigDecimal prevcash;
    public static ArrayList<Integer> debugHand;

    public static void main(String[] args) {
        // Check input
        if (args.length != 3) {
            System.out.printf("Improper Usage: <hands> <input chart> <number of sessions>");
            System.exit(0);
        }
        MathContext mathContext = new MathContext(15,RoundingMode.HALF_UP);
        BigDecimal total = new BigDecimal("0.0", mathContext);
        User user;
        int iterations;
        iterations = Integer.parseInt(args[2]);
        int numberOfTimesWon = 0;
        int numberOfTimesLoss = 0;
        // loop through and run the simulation many times with the given inputs
        for(int i = 0; i <iterations; i++){
            user = mainLoop(args);
            System.out.printf("%s", user);
            BigDecimal cashChanged = user.getCashChanged();
            total = total.add(cashChanged, mathContext);
            if (cashChanged.compareTo(new BigDecimal("0.0"))==1) {
                numberOfTimesWon++;
            } else {
                numberOfTimesLoss++;
            }
        }
        
        System.out.printf("---------\naverage profit: %s\nnumber of times won: %d\nnumber of times loss or break even: %d\n", total.divide(new BigDecimal(iterations), mathContext), numberOfTimesWon, numberOfTimesLoss);

    }


    public static User mainLoop(String[] args) {
        

        // Setup:
        Deck deck = new Deck();
        prevcash = new BigDecimal("0.0");
        MathContext mathContext = new MathContext(15,RoundingMode.HALF_UP);

        // User user = new User("2000");
        PercentUser user = new PercentUser("2000");
        StateMachine sm = new StateMachine();
        Decision decision = new Decision(args[1]);
        Action action;
        int curFirstHand = 0;
        int iterations = 0;
        int tokens = Integer.parseInt(args[0]);
        int dd = 1; // multiplier for double down

        ArrayList<ArrayList<Integer>> hands = new ArrayList<ArrayList<Integer>>(4);
        ArrayList<Integer> dealerHand = new ArrayList<Integer>();
        debugHand = new ArrayList<Integer>();
        prepareHands(hands);
        boolean done=false;
        // loop thruogh states
        while(done == false) {
            switch (sm.getState()) {
                case DEDUCT_BASEBET_1: 
                    prevcash = user.getCash();
                    // Check if we're done
                    iterations++;
                    if (iterations>tokens) {
                        done = true;
                        break;
                    }
                    user.incrementHandsPlayed();
                    debug("--------------------- Hand: %d ---------------------\n", iterations);
                    // Deduct the bet size
                    user.updateBaseBet();
                    debug("user.getBaseBet():%s\n", user.getBaseBet());
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("-1.0", mathContext)));
                    // Shuffle the deck
                    deck.shuffle();
                    // Clear out the hands
                    clearHands(hands);
                    dealerHand.clear();
                    debugHand.clear();

                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.DEAL_INITIAL_CARDS_2);
                    break;
                case DEAL_INITIAL_CARDS_2:
                    // Give user 2 cards
                    hands.get(0).add(deck.draw());
                    hands.get(0).add(deck.draw());
                    // Give dealer 1
                    dealerHand.add(deck.draw());

                    // Query chart
                    action = decision.determineUserAction(hands.get(0), dealerHand, false);
                    
                    verboseDebug(sm, hands, dealerHand, user);

                    // Advance state
                    switch(action) {
                        case HIT:
                            sm.setState(State.DEAL_CARD_5);
                            break;
                        case STAND:
                            sm.setState(State.DET_DEALER_ACTION_6);
                            break;
                        case SURRENDER:
                            sm.setState(State.REFUND_3);
                            break;
                        case SPLIT:
                            sm.setState(State.SPLIT_4);
                            break;
                        case DOUBLEDOWN:
                            sm.setState(State.DEAL_CARD_43);
                            break;
                        default:
                            System.out.printf("broken switch state");
                            break;
                    }   
                    break;
                case REFUND_3:
                    // refund 0.5
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("0.5"), mathContext));

                    verboseDebug(sm, hands, dealerHand, user);

                    sm.setState(State.DEDUCT_BASEBET_1);
                    debugPrintInfo(hands, dealerHand, user);
                    break; 
                case SPLIT_4:
                    debug("SPLIT!\n");
                    curFirstHand = 0;
                    // Charge user for split
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("-1"), mathContext));
                    // Create 2 hands
                    hands.get(1).add(hands.get(0).remove(0));
                    hands.get(0).add(deck.draw());
                    hands.get(1).add(deck.draw());

                    verboseDebug(sm, hands, dealerHand, user);

                    // Advance state
                    sm.setState(State.HANDLE_FIRST_7);
                    break;
                case DEAL_CARD_5:
                    // Draw a card
                    hands.get(0).add(deck.draw());
                    // Check if 5 cards
                    if (hands.get(0).size() == 5) {
                        sm.setState(State.DET_DEALER_ACTION_6);
                        break;
                    }
                    // Check if bust
                    if(decision.lowSumOfArrayList(hands.get(0)) > 21) {
                        sm.setState(State.DET_DEALER_ACTION_6);
                        break;
                    }
                    // Query for next action
                    action = decision.determineUserAction(hands.get(0), dealerHand, true);
                    verboseDebug(sm, hands, dealerHand, user);

                    // Advance state
                    switch(action) {
                        case HIT:
                            sm.setState(State.DEAL_CARD_5);
                            break;
                        case STAND:
                            sm.setState(State.DET_DEALER_ACTION_6);
                            break;
                        default:
                            System.out.printf("broken switch state, possible cause, non hit or stand");
                            break;
                    }
                    break; 
                case DET_DEALER_ACTION_6:
                    // Check if 5 cards
                    if (dealerHand.size() == 5) {
                        sm.setState(State.DET_WINNER_9);
                        break;
                    }
                    action = decision.determineDealerAction(dealerHand);
                    
                    verboseDebug(sm, hands, dealerHand, user);

                    switch(action) {
                        case HIT:
                            sm.setState(State.DEALER_DRAW_8);
                            break;
                        case STAND:
                            sm.setState(State.DET_WINNER_9);
                            break;
                        default:
                            System.out.printf("broken switch state, possible cause, non hit or stand");
                            break;
                    }
                    break;
                case HANDLE_FIRST_7:
                    // Query chart
                    action = decision.determineUserAction(hands.get(0), dealerHand, true);
                    
                    verboseDebug(sm, hands, dealerHand, user);

                    // Change states
                    switch(action) {
                        case HIT:
                            sm.setState(State.DEAL_CARD_10);
                            break;
                        case STAND:
                            sm.setState(State.DET_DEALER_ACTION_11);
                            break;
                        default:
                            System.out.printf("broken switch state, possible cause, non hit or stand");
                            break;
                    }
                    break;
                case DEALER_DRAW_8:
                    // Dealer draw
                    dealerHand.add(deck.draw());
                    verboseDebug(sm, hands, dealerHand, user);
                    // Advance state
                    sm.setState(State.DET_DEALER_ACTION_6);
                    break;
                case DET_WINNER_9:
                    verboseDebug(sm, hands, dealerHand, user);
                    // Switch states
                    switch(decision.determineWinner(hands.get(0), dealerHand)) {
                        case D_00:
                            sm.setState(State.PLUS_13);
                            break;
                        case C_10:
                            sm.setState(State.PLUS_14);
                            break;
                        case B_20:
                            sm.setState(State.PLUS_15);
                            break;
                        case A_25:
                            sm.setState(State.PLUS_16);
                            break;
                        default:
                            System.out.printf("DET_WINNER_9 switch broken");
                            break;
                    }
                    break;
                case DEAL_CARD_10:
                    // Deal a card
                    hands.get(0).add(deck.draw());
                    // Check if 5 card
                    if(hands.get(0).size()==5) {
                        sm.setState(State.DET_DEALER_ACTION_11);
                        break;
                    }
                    // Check if bust
                    if(decision.lowSumOfArrayList(hands.get(0)) > 21) {
                        sm.setState(State.DET_DEALER_ACTION_11);
                        break;
                    }
                    // Query table
                    action = decision.determineUserAction(hands.get(0),dealerHand,true);
                    
                    verboseDebug(sm, hands, dealerHand, user);

                    // Change state
                    switch(action) {
                        case HIT:
                            sm.setState(State.DEAL_CARD_10);
                            break;
                        case STAND:
                            sm.setState(State.DET_DEALER_ACTION_11);
                            break;
                        default:
                            System.out.printf("DEAL_CARD_10 switch broken");
                            break;
                    }
                    break;
                case DET_DEALER_ACTION_11:
                    // Determine dealer action
                    action = decision.determineDealerAction(dealerHand);
                    // Check if 5 card
                    if(dealerHand.size()==5) {
                        sm.setState(State.DET_WINNER_17);
                        break;
                    }
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change state
                    switch(action) {
                        case HIT:
                            sm.setState(State.DEALER_DRAW_12);
                            break;
                        case STAND:
                            sm.setState(State.DET_WINNER_17);
                            break;
                        default: 
                            System.out.printf("DET_DEALER_ACTION_11 switch broken");
                            break;
                    }
                    break;
                case DEALER_DRAW_12:
                    // Dealer draw
                    dealerHand.add(deck.draw());
                    verboseDebug(sm, hands, dealerHand, user);
                    // Advance state
                    sm.setState(State.DET_DEALER_ACTION_11);
                    break;
                case PLUS_13:
                    // increase user total
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("0.0"), mathContext).multiply(new BigDecimal(dd), mathContext));
                    dd = 1;
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.DEDUCT_BASEBET_1);
                    debugPrintInfo(hands, dealerHand, user);
                    break;
                case PLUS_14:
                    // increase user total
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("1.0"), mathContext).multiply(new BigDecimal(dd), mathContext));
                    dd = 1;
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.DEDUCT_BASEBET_1);
                    debugPrintInfo(hands, dealerHand, user);
                    break;
                case PLUS_15:
                    // increase user total
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("2.0"), mathContext).multiply(new BigDecimal(dd), mathContext));
                    dd = 1;
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.DEDUCT_BASEBET_1);
                    debugPrintInfo(hands, dealerHand, user);
                    break;
                case PLUS_16:
                    // increase user total
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("2.5"), mathContext).multiply(new BigDecimal(dd), mathContext));
                    dd = 1;
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.DEDUCT_BASEBET_1);
                    debugPrintInfo(hands, dealerHand, user);
                    break;
                case DET_WINNER_17:
                    verboseDebug(sm, hands, dealerHand, user);
                    // Switch states
                    switch(decision.determineWinner(hands.get(0), dealerHand)) {
                        case D_00:
                            sm.setState(State.PLUS_18);
                            break;
                        case C_10:
                            sm.setState(State.PLUS_19);
                            break;
                        case B_20:
                            sm.setState(State.PLUS_20);
                            break;
                        case A_25:
                            sm.setState(State.PLUS_21);
                            break;
                        default:
                            System.out.printf("DET_WINNER_17 switch broken");
                            break;
                    }
                    break;
                case PLUS_18:
                    // increase user total
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("0.0"), mathContext).multiply(new BigDecimal(dd), mathContext));
                    dd = 1;
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.HANDLE_SECOND_22);
                    break;
                case PLUS_19:
                    // increase user total
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("1.0"), mathContext).multiply(new BigDecimal(dd), mathContext));
                    dd = 1;
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.HANDLE_SECOND_22);
                    break;
                case PLUS_20:
                    // increase user total
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("2.0"), mathContext).multiply(new BigDecimal(dd), mathContext));
                    dd = 1;
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.HANDLE_SECOND_22);
                    break;
                case PLUS_21:
                    // increase user total
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("2.5"), mathContext).multiply(new BigDecimal(dd), mathContext));
                    dd = 1;
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.HANDLE_SECOND_22);
                    break;
                case HANDLE_SECOND_22:
                    // Clear out dealer hands
                    for(int i = 0; dealerHand.size()>2; i++) {
                        // dealerHand.remove(2);
                        debugHand.add(dealerHand.remove(2));
                    }
                    // Query chart
                    action = decision.determineUserAction(hands.get(curFirstHand+1),dealerHand,false);
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change state
                    switch(action) {
                        case HIT:
                            sm.setState(State.DEAL_CARD_26);
                            break;
                        case STAND:
                            sm.setState(State.DET_DEALER_ACTION_27);
                            break;
                        case SURRENDER:
                            sm.setState(State.REFUND_23);
                            break;
                        case SPLIT:
                            sm.setState(State.SPLIT_24);
                            break;
                        case DOUBLEDOWN:
                            sm.setState(State.DEAL_CARD_25);
                            break;
                        default: 
                            System.out.printf("HANDLE_SECOND_22 broken\n");
                            break;
                    }
                    break;
                case REFUND_23:
                    // Refund
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("0.5")));
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change state
                    sm.setState(State.DEDUCT_BASEBET_1);
                    debugPrintInfo(hands, dealerHand, user);
                    break;
                case SPLIT_24:
                    debug("SPLIT_24\n");
                    // Increment the first hand counter
                    curFirstHand++;
                    // Charge the user for a split
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("-1"), mathContext));
                    // Split the hand
                    hands.get(curFirstHand+1).add(hands.get(curFirstHand).remove(1));
                    // Add a card to both the new hands
                    hands.get(curFirstHand+1).add(deck.draw());
                    hands.get(curFirstHand).add(deck.draw());
                    verboseDebug(sm, hands, dealerHand, user);
                    // Advance the state
                    sm.setState(State.HANDLE_FIRST_28);
                    break;
                case DEAL_CARD_25:
                    debug("Double Down \n");
                    // Double down
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("-1"), mathContext));
                    dd = 2;
                    // Draw a card
                    hands.get(curFirstHand+1).add(deck.draw());
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change state
                    sm.setState(State.DET_DEALER_ACTION_27);
                    break;
                case DEAL_CARD_26:
                    // Draw a card
                    hands.get(curFirstHand+1).add(deck.draw());
                    // Check if 5 card
                    if(hands.get(curFirstHand+1).size()==5) {
                        sm.setState(State.DET_DEALER_ACTION_27);
                        break;
                    }
                    // Check if bust
                    if(decision.lowSumOfArrayList(hands.get(curFirstHand+1)) > 21) {
                        sm.setState(State.DET_DEALER_ACTION_27);
                        break;
                    }
                    // Query chart
                    action = decision.determineUserAction(hands.get(curFirstHand+1),dealerHand,true);
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change state
                    switch(action) {
                        case HIT:
                            sm.setState(State.DEAL_CARD_26);
                            break;
                        case STAND:
                            sm.setState(State.DET_DEALER_ACTION_27);
                            break;
                        default: 
                            System.out.printf("broken DEAL_CARD_26");
                            break;
                    }
                    break;
                case DET_DEALER_ACTION_27:
                    // Check if 5 cards
                    if (dealerHand.size() == 5) {
                        sm.setState(State.DET_WINNER_34);
                        break;
                    }
                    action = decision.determineDealerAction(dealerHand);
                    verboseDebug(sm, hands, dealerHand, user);
                    // Advance State
                    switch(action) {
                        case HIT:
                            sm.setState(State.DEALER_DRAW_32);
                            break;
                        case STAND:
                            sm.setState(State.DET_WINNER_34);
                            break;
                        default:
                            System.out.printf("broken DET_DEALER_ACTION_27");
                            break;
                    }
                    break;
                case HANDLE_FIRST_28:
                    // Query chart
                    action = decision.determineUserAction(hands.get(curFirstHand), dealerHand, true);
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    switch(action) {
                        case HIT:
                            sm.setState(State.DEAL_CARD_30);
                            break;
                        case STAND:
                            sm.setState(State.DET_DEALER_ACTION_29);
                            break;
                        default:
                            System.out.printf("broken HANDLE_FIRST_28");
                            break;
                    }
                    break;
                case DET_DEALER_ACTION_29: 
                    // Check if 5 cards
                    if (dealerHand.size() == 5) {
                        sm.setState(State.DET_WINNER_33);
                        break;
                    }
                    action = decision.determineDealerAction(dealerHand);
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change state
                    switch(action) {
                        case HIT:
                            sm.setState(State.DEALER_DRAW_31);
                            break;
                        case STAND:
                            sm.setState(State.DET_WINNER_33);
                            break;
                        default:
                            System.out.printf("broken DET_DEALER_ACTION_29");
                            break;
                    }
                    break;
                case DEAL_CARD_30:
                    // Draw a card
                    hands.get(curFirstHand).add(deck.draw());
                    // Check if 5 cards
                    if (hands.get(curFirstHand).size() == 5) {
                        sm.setState(State.DET_DEALER_ACTION_29);
                        break;
                    }
                    // Check if bust
                    if(decision.lowSumOfArrayList(hands.get(curFirstHand)) > 21) {
                        sm.setState(State.DET_DEALER_ACTION_27);
                        break;
                    }
                    // Query for next action
                    action = decision.determineUserAction(hands.get(curFirstHand), dealerHand, true);
                    verboseDebug(sm, hands, dealerHand, user);
                    // Advance state
                    switch(action) {
                        case HIT:
                            sm.setState(State.DEAL_CARD_30);
                            break;
                        case STAND:
                            sm.setState(State.DET_DEALER_ACTION_29);
                            break;
                        default:
                            System.out.printf("broken switch state, possible cause, non hit or stand");
                            break;
                    }
                    break;
                case DEALER_DRAW_31:
                    // Dealer draw
                    dealerHand.add(deck.draw());
                    verboseDebug(sm, hands, dealerHand, user);
                    // Advance state
                    sm.setState(State.DET_DEALER_ACTION_29);
                    break;
                case DEALER_DRAW_32:
                    // Dealer draw
                    dealerHand.add(deck.draw());
                    verboseDebug(sm, hands, dealerHand, user);
                    // Advance state
                    sm.setState(State.DET_DEALER_ACTION_27);
                    break;
                case DET_WINNER_33:
                    verboseDebug(sm, hands, dealerHand, user);
                    // Switch states
                    switch(decision.determineWinner(hands.get(curFirstHand), dealerHand)) {
                        case D_00:
                            sm.setState(State.PLUS_39);
                            break;
                        case C_10:
                            sm.setState(State.PLUS_40);
                            break;
                        case B_20:
                            sm.setState(State.PLUS_41);
                            break;
                        case A_25:
                            sm.setState(State.PLUS_42);
                            break;
                        default:
                            System.out.printf("DET_WINNER_33 switch broken");
                            break;
                    }
                    break;
                case DET_WINNER_34:
                    verboseDebug(sm, hands, dealerHand, user);
                    // Switch states
                    switch(decision.determineWinner(hands.get(curFirstHand+1), dealerHand)) {
                        case D_00:
                            sm.setState(State.PLUS_35);
                            break;
                        case C_10:
                            sm.setState(State.PLUS_36);
                            break;
                        case B_20:
                            sm.setState(State.PLUS_37);
                            break;
                        case A_25:
                            sm.setState(State.PLUS_38);
                            break;
                        default:
                            System.out.printf("DET_WINNER_34 switch broken");
                            break;
                    }
                    break;
                case PLUS_35:
                    // increase user total
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("0.0"), mathContext).multiply(new BigDecimal(dd), mathContext));
                    dd = 1;
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.DEDUCT_BASEBET_1);
                    debugPrintInfo(hands, dealerHand, user);
                    break;
                case PLUS_36:
                    // increase user total
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("1.0"), mathContext).multiply(new BigDecimal(dd), mathContext));
                    dd = 1;
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.DEDUCT_BASEBET_1);
                    debugPrintInfo(hands, dealerHand, user);
                    break;
                case PLUS_37:
                    // increase user total
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("2.0"), mathContext).multiply(new BigDecimal(dd), mathContext));
                    dd = 1;
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.DEDUCT_BASEBET_1);
                    debugPrintInfo(hands, dealerHand, user);
                    break;
                case PLUS_38:
                    // increase user total
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("2.5"), mathContext).multiply(new BigDecimal(dd), mathContext));
                    dd = 1;
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.DEDUCT_BASEBET_1);
                    debugPrintInfo(hands, dealerHand, user);
                    break;
                case PLUS_39:
                    // increase user total
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("0.0"), mathContext).multiply(new BigDecimal(dd), mathContext));
                    dd = 1;
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.HANDLE_SECOND_22);
                    break;
                case PLUS_40:
                    // increase user total
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("1.0"), mathContext).multiply(new BigDecimal(dd), mathContext));
                    dd = 1;
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.HANDLE_SECOND_22);
                    break;
                case PLUS_41:
                    // increase user total
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("2.0"), mathContext).multiply(new BigDecimal(dd), mathContext));
                    dd = 1;
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.HANDLE_SECOND_22);
                    break;
                case PLUS_42:
                    // increase user total
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("2.5"), mathContext).multiply(new BigDecimal(dd), mathContext));
                    dd = 1;
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change states
                    sm.setState(State.HANDLE_SECOND_22);
                    break;
                case DEAL_CARD_43:
                    debug("Double Down \n");
                    // Double down
                    user.changeCash(user.getBaseBet().multiply(new BigDecimal("-1.0", mathContext)));
                    dd = 2;
                    // Draw a card
                    hands.get(0).add(deck.draw());
                    verboseDebug(sm, hands, dealerHand, user);
                    // Change state
                    sm.setState(State.DET_DEALER_ACTION_6);
                    break;
                default:
                    System.out.printf("In default");
                    break;
            }
        }
        // System.out.printf("%s",user.toString());
        return user;
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

    public static void debug(String arg1, Object... args) {
        // System.out.printf(arg1, args);
    }

    private static String formatHand(ArrayList<Integer> input) {
        StringBuffer sb = new StringBuffer();
        for(int i : input) {
            sb.append(i);
            sb.append(" ");
        }
        return sb.toString();
    }

    private static void debugPrintInfo(ArrayList<ArrayList<Integer>> hands, ArrayList<Integer> dealerHand, User user) {
        // int i = 0;
        // // Print all the user hands
        // for(ArrayList<Integer> hand : hands) {
        //     debug("userHand %d: %s\n",i, formatHand(hand));
        //     i++;
        // }
        // debug("dealerHand: %s\n", formatHand(dealerHand));
        // debug("prevcash: %s\n", prevcash);
        // debug("new total: %s\n", user.getCash());
        // debug("debugHand: %s\n", formatHand(debugHand));  
    }

    private static void verboseDebug(StateMachine sm, ArrayList<ArrayList<Integer>> hands, ArrayList<Integer> dealerHand, User user) {
        // debug("State: %s\n", sm.getState().toString());
        // int i = 0;
        // // Print all the user hands
        // for(ArrayList<Integer> hand : hands) {
        //     debug("userHand %d: %s\n",i, formatHand(hand));
        //     i++;
        // }
        // debug("dealerHand: %s\n", formatHand(dealerHand));
        // debug("new total: %s\n", user.getCash());
    }

}