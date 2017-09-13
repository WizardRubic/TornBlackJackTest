import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;

class User {
	private BigDecimal cashTotal;
	private int handsPlayed;
	private BigDecimal cashChanged;
	protected BigDecimal baseBet;
	private BigDecimal startCash;
	private MathContext mathContext;
	public User() {
		mathContext = new MathContext(15,RoundingMode.HALF_UP);
		cashTotal = new BigDecimal("0", mathContext);
		handsPlayed = 0;
		cashChanged = new BigDecimal("0", mathContext);
	}

	public User(String startCash) {
		mathContext = new MathContext(15,RoundingMode.HALF_UP);
		cashTotal = new BigDecimal(startCash, mathContext);
		this.startCash = new BigDecimal(startCash, mathContext);
		handsPlayed = 0;
		cashChanged = new BigDecimal("0.0", mathContext);
	}

	public BigDecimal getCash() {
		return cashTotal;
	}
	public void setCash(BigDecimal amt) {
		cashTotal = amt;
	}
	public void changeCash(BigDecimal delta) {
		cashChanged = cashChanged.add(delta, mathContext);
		cashTotal = cashTotal.add(delta, mathContext);
	}
	public void incrementHandsPlayed() {
		handsPlayed++;
	}
	public int getHandsPlayed() {
		return handsPlayed;
	}
	public BigDecimal getProfitPerHand() {
		return cashChanged.divide(new BigDecimal(handsPlayed), mathContext);
	}
	public BigDecimal getCashChanged() {
		return cashChanged;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("UserStats:\n");
		sb.append("Number of hands: ");
		sb.append(getHandsPlayed());
		sb.append("\n");
		sb.append("Profit per hand: ");
		sb.append(getProfitPerHand().toString());
		sb.append("\n");
		sb.append("startCash: ");
		sb.append(startCash.toString());
		sb.append("\nendCash: ");
		sb.append(cashTotal.toString());
		sb.append("\n");
		return sb.toString();
	}
	public BigDecimal getBaseBet() {
		return baseBet;
	}
	public void updateBaseBet() {
		baseBet = new BigDecimal("100", mathContext);
		return;
	}
}