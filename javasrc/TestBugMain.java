class TestBugMain {
    public static void main(String[] args) {
    	int i=4;
    	debug("|%d|\n", i);
    }

    private static void debug(String arg1, Object... args) {
    	System.out.printf(arg1, args);
    }
}