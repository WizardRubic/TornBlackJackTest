// BlackjackMain
// This class contains the main loop of our simulation
//http://codr.io/lykh7q5 (deck.java)

public class BlackjackMain {
    public static void main(String[] args) {
        // inside this main loop we can instantiate a Deck by calling it's constructor:
        Deck test = new Deck();
        for(int i = 0; i<52; i++) {
	        System.out.printf("%d|",test.draw());
	    }
    }
}