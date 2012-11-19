package com.srhub.task.core;

import java.util.List;

import com.srhub.dicecup.core.Roll;
import com.srhub.dicecup.util.Lists;
import com.srhub.task.api.SuccessTest;
import com.srhub.task.core.Tests.Function;
import com.srhub.task.core.Tests.GlitchThrower;

/**
 * Default implementation of a {@link SuccessTest}
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class DefaultSuccessTest implements
		SuccessTest<DefaultSuccessTest.Result> {

	/** The netto. */
	private final boolean netto;

	/** The criticials. */
	private final List<GlitchThrower> criticials;

	/** The fail counter. */
	private final Function failFunction;

	/**
	 * Instantiates a new default success test.
	 *
	 * @param netto
	 *            the netto
	 * @param criticials
	 *            the criticials
	 * @param failFunction
	 *            the fail counter
	 */
	protected DefaultSuccessTest(final boolean netto,
			final List<GlitchThrower> criticials, final Function failFunction) {
		this.netto = netto;
		this.criticials = criticials;
		this.failFunction = failFunction;
	}

	@Override
	public Result evaluate(final Roll roll, final int target) {

		final List<Integer> successes = Lists.greaterThanOrEquals(roll.all(),
				target);
		final List<Integer> fails = failFunction.apply(roll);
		for (final GlitchThrower glitchThrower : criticials) {
			glitchThrower.evaluate(roll);
		}
		final int nettoSuccesses = netto ? successes.size() - fails.size()
				: successes.size();

		return new Result(roll.all(), successes, fails, nettoSuccesses);

	}

	public static class Result {

		private final List<Integer> roll;
		private final List<Integer> successes;
		private final List<Integer> fails;
		private final int nettoSuccesses;

		public Result(final List<Integer> roll, final List<Integer> successes,
				final List<Integer> fails, final int nettoSuccesses) {
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

}
