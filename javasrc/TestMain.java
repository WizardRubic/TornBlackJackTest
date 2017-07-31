import java.util.*;

class TestMain {
	public static void main(String[] args) {
		ArrayList<Integer> utest = new ArrayList<Integer>();
		ArrayList<Integer> dtest = new ArrayList<Integer>();
		Decision decision = new Decision("input");
		dtest.add(10);
		dtest.add(11);
		utest.add(10);
		utest.add(7);
		System.out.printf("expected 0| result - %s\n",decision.determineWinner(utest,dtest).toString());
		// =====
		dtest.clear();
		utest.clear();
		dtest.add(10);
		dtest.add(11);
		utest.add(2);
		utest.add(2);
		utest.add(2);
		utest.add(2);
		utest.add(3);
		System.out.printf("expected 0| result - %s\n",decision.determineWinner(utest,dtest).toString());
		// =====
		dtest.clear();
		utest.clear();
		dtest.add(10);
		dtest.add(10);
		utest.add(2);
		utest.add(2);
		utest.add(2);
		utest.add(2);
		utest.add(3);
		System.out.printf("expected 2.0| result - %s\n",decision.determineWinner(utest,dtest).toString());
		// =====
		dtest.clear();
		utest.clear();
		dtest.add(11);
		dtest.add(11);
		dtest.add(2);
		dtest.add(2);
		dtest.add(5);
		utest.add(2);
		utest.add(2);
		utest.add(2);
		utest.add(2);
		utest.add(3);
		System.out.printf("expected 1.0| result - %s\n",decision.determineWinner(utest,dtest).toString());
		// =====
		dtest.clear();
		utest.clear();
		dtest.add(11);
		dtest.add(11);
		dtest.add(2);
		dtest.add(2);
		dtest.add(5);
		utest.add(10);
		utest.add(11);
		System.out.printf("expected 2.5| result - %s\n",decision.determineWinner(utest,dtest).toString());
		// =====
		dtest.clear();
		utest.clear();
		utest.add(10);
		utest.add(10);
		dtest.add(10);
		dtest.add(5);
		dtest.add(7);
		System.out.printf("expected 2| result - %s\n",decision.determineWinner(utest,dtest).toString());
		// =====
		dtest.clear();
		utest.clear();
		utest.add(8);
		utest.add(9);
		dtest.add(10);
		dtest.add(3);
		dtest.add(4);
		System.out.printf("expected 1| result - %s\n",decision.determineWinner(utest,dtest).toString());
	}
}