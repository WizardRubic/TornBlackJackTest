class User {
	private double cashTotal;
	private int handsPlayed;
	private double cashChanged;

	public User() {
		cashTotal = 0;
		handsPlayed = 0;
		cashChanged = 0;
	}

	public User(double startCash) {
		cashTotal = startCash;
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
}