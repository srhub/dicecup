package com.srhub.test.core;

import java.util.List;

import com.srhub.dicecup.core.Roll;
import com.srhub.dicecup.util.Lists;
import com.srhub.test.api.SuccessTest;
import com.srhub.test.core.Tests.Counter;
import com.srhub.test.core.Tests.GlitchThrower;

public class DefaultSuccessTest implements SuccessTest {

	private final boolean netto;
	private final List<GlitchThrower> criticials;
	private final Counter failCounter;

	protected DefaultSuccessTest(final boolean netto,
			final List<GlitchThrower> criticials, final Counter failCounter) {
		this.netto = netto;
		this.criticials = criticials;
		this.failCounter = failCounter;
	}

	@Override
	public int evaluate(final Roll roll, final int target) {

		final int successes = Lists.greaterThanOrEquals(roll.all(), target)
				.size();
		final int fails = failCounter.count(roll);

		for (final GlitchThrower glitchThrower : criticials) {
			glitchThrower.evaluate(roll);
		}

		if (netto) {
			return successes - fails;
		}

		return successes;
	}
}
