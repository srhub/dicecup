package com.srhub.test.core;

import java.util.List;

public class SuccessTestResult {

	private final List<Integer> roll;
	private final List<Integer> successes;
	private final List<Integer> fails;
	private final int nettoSuccesses;

	public SuccessTestResult(final List<Integer> roll,
			final List<Integer> successes, final List<Integer> fails,
			final int nettoSuccesses) {
		super();
		this.roll = roll;
		this.successes = successes;
		this.fails = fails;
		this.nettoSuccesses = nettoSuccesses;
	}

	public List<Integer> getRoll() {
		return roll;
	}

	public List<Integer> getSuccesses() {
		return successes;
	}

	public List<Integer> getFails() {
		return fails;
	}

	public int getNettoSuccesses() {
		return nettoSuccesses;
	}

}
