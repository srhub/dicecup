package com.srhub.test.core;


public class OpposedTestResult {

	private final SuccessTestResult ofAttacker;
	private final SuccessTestResult ofDefender;
	private final Party winner;

	public OpposedTestResult(final SuccessTestResult ofAttacker,
			final SuccessTestResult ofDefender, final Party winner) {
		super();
		this.ofAttacker = ofAttacker;
		this.ofDefender = ofDefender;
		this.winner = winner;
	}

	public SuccessTestResult ofAttacker() {
		return ofAttacker;
	}

	public SuccessTestResult ofDefender() {
		return ofDefender;
	}

	public Party winner() {
		return winner;
	}
}
