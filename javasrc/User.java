class User {
	private double cashTotal;
	private int handsPlayed;
	private double cashChanged;
	protected double baseBet;
	private double startCash;
	public User() {
		cashTotal = 0;
		handsPlayed = 0;
		cashChanged = 0;
	}

	public User(double startCash) {
		cashTotal = startCash;
		this.startCash = startCash;
		handsPlayed = 0;
		cashChanged = 0;
	}

	public double getCash() {
		return cashTotal;
	}
	public void setCash(double amt) {
		cashTotal = amt;
	}
	public void changeCash(double delta) {
		cashChanged += delta;
		cashTotal += delta;
	}
	public void incrementHandsPlayed() {
		handsPlayed++;
	}
	public double getHandsPlayed() {
		return handsPlayed;
	}
	public double getProfitPerHand() {
		return cashChanged / handsPlayed;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("UserStats:\n");
		sb.append("Profit per hand: ");
		sb.append(getProfitPerHand());
		sb.append("\n");
		return sb.toString();
	}
	public double getBaseBet() {
		return 20.0;
	}
	public void updateBaseBet() {
		return;
	}
}