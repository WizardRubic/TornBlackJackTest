import java.math.BigDecimal;

class PercentUser extends User {
	public PercentUser() {
		super();
		setCash(new BigDecimal("2000.00"));
	}
	public PercentUser(String start) {
		super(start);
	}
	@Override 
    public void updateBaseBet() {
    	// System.out.printf("IN UPDATEBASEBET OVERWRITTEN!\n");
    	BigDecimal val = super.getCash().multiply(new BigDecimal("0.01"));
    	if (val.compareTo(new BigDecimal("100.0")) == -1) {
			this.baseBet = val;
		} else {
			this.baseBet = new BigDecimal("100.0");
		}
	}
}