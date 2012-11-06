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
package com.srhub.dicecup.systems.sr3;

import com.srhub.dicecup.core.Roll;
import com.srhub.dicecup.systems.base.Party;
import com.srhub.dicecup.systems.base.Result;
import com.srhub.dicecup.systems.base.Rolls;

/**
 * A success contest is used when two characters compete against each other, but
 * not by comparing the same skill or attribute as in the {@link OpposedTest}
 * but by comparing different attributes or skills complementary to each other.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 *
 */
public class SuccessContest {

	/**
	 * Rolls the dice and returns the number of success. A negative result
	 * represents successes in favor of the character, while a positive result
	 * represents successes in favor of the opponent. A result of <code>0</code>
	 * represents a tie between both parties (both have zero successes or just
	 * an equal number of successes).
	 *
	 * @param attackerRating
	 *            Attribute or skill rating of the character he uses actively
	 * @param attackerDefense
	 *            Attribute or skill rating of the character he uses to defend
	 *            against the actions of the opponent
	 * @param defenderRating
	 *            Attribute or skill rating of the opponent he uses actively
	 * @param defenderDefense
	 *            Attribute or skill rating of the opponent he uses to defend
	 *            against the actions of the character
	 * @return A negative result represents successes in favor of the character,
	 *         while a positive result represents successes in favor of the
	 *         opponent. A result of <code>0</code> represents a tie between
	 *         both parties (both have zero successes or just an equal number of
	 *         successes).
	 * @throws RuleOfOneException
	 *             Thrown when all dice show only a <code>1</code>.
	 */
	public Result<Rolls> roll(final int attackerRating,
			final int attackerDefense, final int defenderRating,
			final int defenderDefense) throws RuleOfOneException {

		final Result<Roll> attackerResult;
		final Result<Roll> defenderResult;
		try {
			attackerResult = new SuccessTest().roll(attackerRating,
					defenderDefense);
		} catch (final RuleOfOneException e) {
			throw new RuleOfOneException(Party.ATTACKER);
		}

		try {
			defenderResult = new SuccessTest().roll(defenderRating,
					attackerDefense);
		} catch (final RuleOfOneException e) {
			throw new RuleOfOneException(Party.DEFENDER);
		}

		return evaluate(attackerResult, defenderResult);
	}

	/**
	 * Evaluate.
	 *
	 * @param attackerResult
	 *            the attacker result
	 * @param defenderResult
	 *            the defender result
	 * @return the result
	 * @throws RuleOfOneException
	 *             the rule of one exception
	 */
	public Result<Rolls> evaluate(final Result<Roll> attackerResult,
			final Result<Roll> defenderResult) throws RuleOfOneException {

		final Rolls rolls = new Rolls(attackerResult.getRoll(),
				defenderResult.getRoll());
		final int nettoSuccesses = attackerResult.getCompound()
				- defenderResult.getCompound();

		if (nettoSuccesses > 0) {
			return new Result<Rolls>(rolls, nettoSuccesses, Party.ATTACKER);
		}

		if (nettoSuccesses > 0) {
			return new Result<Rolls>(rolls, -nettoSuccesses, Party.DEFENDER);
		}

		// its a tie
		return new Result<Rolls>(rolls);
	}
}