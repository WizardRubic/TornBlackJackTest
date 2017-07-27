import java.io.File;
import java.util.*;

class ChartParser {
	private Action[][] array; // magic num but obv enough

    public ChartParser(String in) {
    	load(in);
    	array = new Action[37][93];
    }

    public void load(String in) {
    	try {
    		File file = new File(in);
    		Scanner scanner = new Scanner(in);
    		for(int i = 0; i<93; i++) {
    			for (int j = 0; j<37; j++) {
    				switch (scanner.nextInt()) {
    					case 0:
    						array[i][j] = Action.HIT;
    						break;
    					case 1:
    						array[i][j] = Action.STAND;
    						break;
    					case 2:
    						array[i][j] = Action.DOUBLEDOWN;
    						break;
    					case 3:
    						array[i][j] = Action.SPLIT;
    						break;
    					case 4:
    						array[i][j] = Action.SURRENDER;
    						break;
    					default:
    						System.out.printf("Error in ChartParser load!\n");
    						break;
    				}
    			}
    		}
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }

}