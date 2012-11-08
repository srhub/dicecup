/*
 * Copyright 2012 Oliver Schrenk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.srhub.test.core;

import java.util.LinkedList;
import java.util.List;

import com.srhub.dicecup.core.Roll;
import com.srhub.test.api.GlitchException;
import com.srhub.test.api.MultiGlitchException;
import com.srhub.test.api.OpposedTest;
import com.srhub.test.api.SuccessTest;
import com.srhub.test.core.Tests.Counter;
import com.srhub.test.core.Tests.GlitchThrower;

/**
 * The Class DefaultOpposedTest.
 *
 * GlitchException is phas to be made by user
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class DefaultOpposedTest implements OpposedTest {

	/** The netto. */
	private final boolean netto;

	/** The attack criticals. */
	private final List<GlitchThrower> attackCriticals;

	/** The defend criticals. */
	private final List<GlitchThrower> defendCriticals;

	/** The fail counter. */
	private final Counter failCounter;

	/**
	 * Instantiates a new default opposed test.
	 *
	 * @param netto
	 *            the netto
	 * @param attackCritcials
	 *            the attack critcials
	 * @param defendCriticals
	 *            the defend criticals
	 * @param failCounter
	 *            the fail counter
	 */
	protected DefaultOpposedTest(final boolean netto,
			final List<GlitchThrower> attackCritcials,
			final List<GlitchThrower> defendCriticals, final Counter failCounter) {
		this.netto = netto;
		this.attackCriticals = attackCritcials;
		this.defendCriticals = defendCriticals;
		this.failCounter = failCounter;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.test.api.OpposedTest#evaluate(com.srhub.dicecup.core.Roll,
	 * int, com.srhub.dicecup.core.Roll, int)
	 */
	@Override
	public int evaluate(final Roll attackRoll, final int attackTarget,
			final Roll defendRoll, final int defendTarget) {

		SuccessTest attackTest = null;
		SuccessTest defendTest = null;

		final List<GlitchException> glitches = new LinkedList<>();
		try {
			attackTest = new DefaultSuccessTest(netto, attackCriticals,
					failCounter);
		} catch (final GlitchException e) {
			glitches.add(e);
		}
		try {
			defendTest = new DefaultSuccessTest(netto, defendCriticals,
					failCounter);
		} catch (final GlitchException e) {
			glitches.add(e);
		}

		if (glitches.size() > 0) {
			throw new MultiGlitchException(glitches);
		}

		final int attacker = attackTest.evaluate(attackRoll, attackTarget);
		final int defender = defendTest.evaluate(defendRoll, defendTarget);

		return attacker - defender;
	}
}
