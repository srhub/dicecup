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
package com.srhub.dicecup.systems.sr4;

import java.util.List;

import com.srhub.dicecup.core.Cup;
import com.srhub.dicecup.dice.Dices;
import com.srhub.dicecup.features.Features;
import com.srhub.dicecup.util.Lists;

/**
 * A success test determines if a character can accomplish a task and how well
 * he does it. The number of dice represents the attribute or skill rating and
 * the threshold the difficulty of the task.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 *
 */
public class SuccessTest {

	/** The Constant CRITICAL. */
	private static final boolean CRITICAL = true;

	/**
	 * Roll.
	 *
	 * @param rating
	 *            the rating
	 * @param threshold
	 *            the threshold
	 * @return the int
	 * @throws Glitch
	 *             the glitch exception
	 */
	public int roll(final int rating, final int threshold)
			throws GlitchException {
		return roll(rating, 0, threshold);
	}

	/**
	 * Roll.
	 *
	 * @param rating
	 *            the rating
	 * @param edge
	 *            the edge
	 * @param threshold
	 *            the threshold
	 * @return the int
	 * @throws Glitch
	 *             the glitch exception
	 */
	public int roll(final int rating, final int edge, final int threshold)
			throws GlitchException {
		if (edge > 0) {
			return evaluate(Cup.add(Dices.D6, Features.REROLL_ADD_AT(6))
					.build().roll(rating + edge).all(), threshold);
		}

		return evaluate(Cup.add(Dices.D6).build().roll(rating).all(), threshold);
	}

	/**
	 * * Returns the number of success.
	 *
	 * @param roll
	 *            the roll
	 * @param threshold
	 *            the threshold
	 * @return the netto successes
	 * @throws Glitch
	 *             the glitch exception
	 */
	protected int evaluate(final List<Integer> roll, final int threshold)
			throws GlitchException {

		final int fails = Lists.count(roll, Sr4.FAIL_AT);
		final int successes = Lists.greaterThanOrEquals(roll,
				Sr4.DEFAULT_TARGET_NUMBER).size();

		if (roll.size() / 2 <= fails) {
			if (successes == 0) {
				throw new GlitchException(CRITICAL);
			}

			throw new GlitchException();
		}

		return successes - threshold;
	}
}