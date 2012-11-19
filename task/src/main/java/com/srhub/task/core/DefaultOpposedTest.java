package com.srhub.task.core;

import java.util.LinkedList;
import java.util.List;

import com.srhub.dicecup.core.Roll;
import com.srhub.task.api.GlitchException;
import com.srhub.task.api.MultiGlitchException;
import com.srhub.task.api.OpposedTest;
import com.srhub.task.api.SuccessTest;
import com.srhub.task.core.Tests.Function;
import com.srhub.task.core.Tests.GlitchThrower;

/**
 * Default implementation of an {@link OpposedTest}.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class DefaultOpposedTest implements
		OpposedTest<DefaultOpposedTest.Result> {

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
	protected DefaultOpposedTest(final boolean netto,
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
	public DefaultOpposedTest.Result evaluate(final Roll attackRoll,
			final int attackTarget, final Roll defendRoll,
			final int defendTarget) {

		SuccessTest<DefaultSuccessTest.Result> attackTest = null;
		SuccessTest<DefaultSuccessTest.Result> defendTest = null;

		final List<GlitchException> glitches = new LinkedList<>();
		try {
			attackTest = new DefaultSuccessTest(netto, attackCriticals,
					failFunction);
		} catch (final GlitchException e) {
			glitches.add(e);
		}
		try {
			defendTest = new DefaultSuccessTest(netto, defendCriticals,
					failFunction);
		} catch (final GlitchException e) {
			glitches.add(e);
		}

		if (glitches.size() > 0) {
			throw new MultiGlitchException(glitches);
		}

		final DefaultSuccessTest.Result attacker = attackTest.evaluate(
				attackRoll, attackTarget);
		final DefaultSuccessTest.Result defender = defendTest.evaluate(
				defendRoll, defendTarget);

		final int result = attacker.getNettoSuccesses()
				- defender.getNettoSuccesses();

		final Party winner;
		if (result == 0) {
			winner = Party.NONE;
		} else if (result > 0) {
			winner = Party.ATTACKER;
		} else {
			winner = Party.DEFENDER;
		}

		return new DefaultOpposedTest.Result(attacker, defender, winner);
	}

	public static class Result {

		private final DefaultSuccessTest.Result ofAttacker;
		private final DefaultSuccessTest.Result ofDefender;
		private final Party winner;

		public Result(final DefaultSuccessTest.Result ofAttacker,
				final DefaultSuccessTest.Result ofDefender, final Party winner) {
			super();
			this.ofAttacker = ofAttacker;
			this.ofDefender = ofDefender;
			this.winner = winner;
		}

		public DefaultSuccessTest.Result ofAttacker() {
			return ofAttacker;
		}

		public DefaultSuccessTest.Result ofDefender() {
			return ofDefender;
		}

		public Party winner() {
			return winner;
		}
	}
}
