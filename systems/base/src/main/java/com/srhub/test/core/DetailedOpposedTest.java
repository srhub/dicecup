package com.srhub.test.core;

import java.util.LinkedList;
import java.util.List;

import com.srhub.dicecup.core.Roll;
import com.srhub.test.api.GlitchException;
import com.srhub.test.api.MultiGlitchException;
import com.srhub.test.api.OpposedTest;
import com.srhub.test.api.SuccessTest;
import com.srhub.test.core.Tests.Function;
import com.srhub.test.core.Tests.GlitchThrower;

public class DetailedOpposedTest implements OpposedTest<OpposedTestResult> {

	/** The netto. */
	private final boolean netto;

	/** The attack criticals. */
	private final List<GlitchThrower> attackCriticals;

	/** The defend criticals. */
	private final List<GlitchThrower> defendCriticals;

	/** The fail counter. */
	private final Function failFunction;

	/**
	 * Instantiates a new default opposed test.
	 *
	 * @param netto
	 *            the netto
	 * @param attackCritcials
	 *            the attack critcials
	 * @param defendCriticals
	 *            the defend criticals
	 * @param failFunction
	 *            the fail counter
	 */
	protected DetailedOpposedTest(final boolean netto,
			final List<GlitchThrower> attackCritcials,
			final List<GlitchThrower> defendCriticals,
			final Function failFunction) {
		this.netto = netto;
		this.attackCriticals = attackCritcials;
		this.defendCriticals = defendCriticals;
		this.failFunction = failFunction;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.test.api.OpposedTest#evaluate(com.srhub.dicecup.core.Roll,
	 * int, com.srhub.dicecup.core.Roll, int)
	 */
	@Override
	public OpposedTestResult evaluate(final Roll attackRoll, final int attackTarget,
			final Roll defendRoll, final int defendTarget) {

		SuccessTest<SuccessTestResult> attackTest = null;
		SuccessTest<SuccessTestResult> defendTest = null;

		final List<GlitchException> glitches = new LinkedList<>();
		try {
			attackTest = new DetailedSuccessTest(netto, attackCriticals,
					failFunction);
		} catch (final GlitchException e) {
			glitches.add(e);
		}
		try {
			defendTest = new DetailedSuccessTest(netto, defendCriticals,
					failFunction);
		} catch (final GlitchException e) {
			glitches.add(e);
		}

		if (glitches.size() > 0) {
			throw new MultiGlitchException(glitches);
		}

		final SuccessTestResult attacker = attackTest.evaluate(attackRoll, attackTarget);
		final SuccessTestResult defender = defendTest.evaluate(defendRoll, defendTarget);

		final int result = attacker.getNettoSuccesses() - defender.getNettoSuccesses();

		final Party winner;
		if (result == 0) {
			winner = Party.NONE;
		} else if (result > 0) {
			winner = Party.ATTACKER;
		} else {
			winner = Party.DEFENDER;
		}


		return new OpposedTestResult(attacker, defender, winner);
	}
}
