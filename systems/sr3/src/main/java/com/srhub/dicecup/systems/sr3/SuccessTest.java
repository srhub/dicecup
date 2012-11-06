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

import java.util.List;

import com.srhub.dicecup.core.Cup;
import com.srhub.dicecup.core.Roll;
import com.srhub.dicecup.systems.base.Result;
import com.srhub.dicecup.util.Lists;

/**
 * A success test determines if a character can accomplish a task and how well
 * he does it. The number of dice represents the attribute or skill rating and
 * the target number the difficulty of the task.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 *
 */
public class SuccessTest {

	public Result<Roll> roll(final int rating, final int targetNumber)
			throws RuleOfOneException {
		final Roll roll = new Cup().add(rating).roll();
		return evaluate(roll, targetNumber);
	}

	/**
	 * Returns the number of success.
	 *
	 * @param diceRoll
	 *            the dice roll
	 * @param targetNumber
	 *            the target number
	 * @return the int
	 * @throws RuleOfOneException
	 *             the rule of one exception
	 */
	public Result<Roll> evaluate(final Roll roll, final int targetNumber)
			throws RuleOfOneException {

		final List<Integer> all = roll.all();

		if (roll.size() == Lists.count(all, Sr3.FAIL_AT)) {
			throw new RuleOfOneException();
		}

		final int successes = Lists.greaterThanOrEquals(all, targetNumber)
				.size();

		return new Result<Roll>(roll, successes);
	}
}
