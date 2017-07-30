import java.io.File;
import java.util.*;

class ChartParser {
	private Action[][] array; // magic num but obv enough

    public ChartParser(String in) {
    	array = new Action[37][93];
    	load(in);
    }

    public Action getAction(int x, int y) {
    	return array[x][y];
    }

    public void load(String in) {
    	try {
    		File file = new File(in);
    		Scanner scanner = new Scanner(file);
    		for(int j = 0; j<93; j++) {
    			for (int i = 0; i<37; i++) {
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


			// switch (scanner.nextInt()) {
			// 	case 0:
			// 		array[0][0] = Action.HIT;
			// 		break;
			// 	case 1:
			// 		array[0][0] = Action.STAND;
			// 		break;
			// 	case 2:
			// 		array[0][0] = Action.DOUBLEDOWN;
			// 		break;
			// 	case 3:
			// 		array[0][0] = Action.SPLIT;
			// 		break;
			// 	case 4:
			// 		array[0][0] = Action.SURRENDER;
			// 		break;
			// 	default:
			// 		System.out.printf("Error in ChartParser load!\n");
			// 		break;
			// }



    		scanner.close();
    	} catch (Exception ex) {
    		System.out.printf("Error in ChartParser:\n");
    		ex.printStackTrace();
    	}
    }

}