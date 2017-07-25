import java.io.File;
import java.util.*;

class ChartParser {

	private Action array[37][93]; // magic num but obv enough

    public ChartParser() {

    }

    public load(String in) {
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
    						array[i][j] = Action.DOUBLE;
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
    	} catch (Exceptopn ex) {
    		ex.printStackTrace();
    	}
    }

}