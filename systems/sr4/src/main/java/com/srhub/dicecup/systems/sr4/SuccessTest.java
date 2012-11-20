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

import com.srhub.task.api.Glitch;
import com.srhub.task.core.DefaultSuccessTest.Result;

/**
 * A success test determines if a character can accomplish a task and how well
 * he does it. The number of dice represents the attribute or skill rating and
 * the threshold the difficulty of the task.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 *
 */
public class SuccessTest {

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
	public Result roll(final int rating, final int threshold) {
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
	 */
	public Result roll(final int rating, final int edge, final int threshold) {
		if (edge > 0) {
			// if edge is spent, all dice showing a 6 will e rerolled and
			// added to the original roll
			return Sr4.newSuccessTask().evaluate(threshold, 0, rating + edge);
		}

		// no edge spend
		return Sr4.newSuccessTask().evaluate(threshold, rating);
	}
}