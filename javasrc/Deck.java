import java.util.*;

// Constructor needs to set the deck up, the 
public class Deck {
//seen examples wh
// we shouldn't have a main method except in the other file where we have our main loop
//  generally you'll have one class which contains your main method and you'll specify which file the main method is in when compiling the program. 
//  the command to do so is javac *.java into java -cp . MainContainer

// public static void main(String[] args) {
// //idk what im doing but i did things 
// }

    private static final int ACE = 11;

    private ArrayList<Integer> left; // Stores cards left in the deck
    private ArrayList<Integer> used; // Stores used cards
    private Random random;

    public Deck() {
        // Setup array lists
        left = new ArrayList<Integer>();
        used = new ArrayList<Integer>();
        
        // 1 is an ACE
        // Add 4 copies of values from 1-9        
        for(int i = 1; i<10; i++) {
            for (int j = 0; j < 4; j++){
                left.add(i);
            }
        }
        // Add 16 10's to the deck
        for (int i = 0; i < 16; i++) {
            left.add(10);
        }
        
        // Set up random number generator
        random = new Random();
    }
    
    // draw()
    // draws a card from the deck and returns the value
    public int draw() {
        int ret;
        if (left.size() <= 0) {
            return -1;
        }
        int pos = random.nextInt(left.size());
        ret = left.get(pos);
        left.remove(pos);
        used.add(ret);
        if (ret == 1) {
            return ACE;
        }
        return ret;
    }
    
    public void shuffle() {
        // put the elements back into the deck
        for(int i = 0; i<used.size(); i++) {
            left.add(used.get(i));
        }
        used.clear();
    }
}