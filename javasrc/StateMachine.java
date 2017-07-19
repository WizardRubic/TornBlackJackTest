class StateMachine {
	State current;

	public StateMachine() {
		current = State.DEDUCT_BASEBET_1;
	}

	public State getState() {
		return current;
	}
	public State setState(State next) {
		return current = next;
	}
}