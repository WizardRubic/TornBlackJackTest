import java.math.BigDecimal;

class User {
	private BigDecimal cashTotal;
	private int handsPlayed;
	private BigDecimal cashChanged;
	protected BigDecimal baseBet;
	private BigDecimal startCash;
	public User() {
		cashTotal = new BigDecimal(0);
		handsPlayed = 0;
		cashChanged = new BigDecimal(0);
	}

	public User(double startCash) {
		cashTotal = new BigDecimal(startCash);
		this.startCash = new BigDecimal(startCash);
		handsPlayed = 0;
		cashChanged = new BigDecimal(0.0);
	}

	public BigDecimal getCash() {
		return cashTotal;
	}
	public void setCash(BigDecimal amt) {
		cashTotal = amt;
	}
	public void changeCash(BigDecimal delta) {
		cashChanged = cashChanged.add(delta);
		cashTotal = cashTotal.add(delta);
	}
	public void incrementHandsPlayed() {
		handsPlayed++;
	}
	public int getHandsPlayed() {
		return handsPlayed;
	}
	public BigDecimal getProfitPerHand() {
		return cashChanged.divide(new BigDecimal(handsPlayed));
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("UserStats:\n");
		sb.append("Number of hands: ");
		sb.append(getHandsPlayed());
		sb.append("\n");
		sb.append("Profit per hand: ");
		sb.append(getProfitPerHand());
		sb.append("\n");
		sb.append("startCash: ");
		sb.append(startCash);
		sb.append("\nendCash: ");
		sb.append(cashTotal);
		sb.append("\n");
		return sb.toString();
	}
	public BigDecimal getBaseBet() {
		return baseBet;
	}
	public void updateBaseBet() {
		return;
	}
}