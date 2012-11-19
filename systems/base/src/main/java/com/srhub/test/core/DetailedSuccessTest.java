package com.srhub.test.core;

import java.util.List;

import com.srhub.dicecup.core.Roll;
import com.srhub.dicecup.util.Lists;
import com.srhub.test.api.SuccessTest;
import com.srhub.test.core.Tests.Function;
import com.srhub.test.core.Tests.GlitchThrower;

public class DetailedSuccessTest implements SuccessTest<SuccessTestResult> {

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
	protected DetailedSuccessTest(final boolean netto,
			final List<GlitchThrower> criticials, final Function failFunction) {
		this.netto = netto;
		this.criticials = criticials;
		this.failFunction = failFunction;
	}

	@Override
	public SuccessTestResult evaluate(final Roll roll, final int target) {

		final List<Integer> successes = Lists.greaterThanOrEquals(roll.all(),
				target);
		final List<Integer> fails = failFunction.apply(roll);
		for (final GlitchThrower glitchThrower : criticials) {
			glitchThrower.evaluate(roll);
		}
		final int nettoSuccesses = netto ? successes.size() - fails.size()
				: successes.size();

		return new SuccessTestResult(roll.all(), successes, fails,
				nettoSuccesses);

	}

}
