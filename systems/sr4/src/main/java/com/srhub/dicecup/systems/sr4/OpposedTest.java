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

import com.srhub.task.api.OpposedTask;
import com.srhub.task.core.DefaultOpposedTest.Result;

/**
 * The Class OpposedTest.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class OpposedTest {

	public static Result roll(final int attackerRating, final int attackerEdge,
			final int defenderRating, final int defenderEdge) {

		final OpposedTask<Result> newOpposedTask = Sr4.newOpposedTask();

		return newOpposedTask.evaluate(defenderRating,
				diceCount(attackerRating, attackerEdge), attackerRating,
				diceCount(defenderRating, defenderEdge));

	}

	private static int[] diceCount(final int rating, final int edge) {
		final int[] diceCount = new int[2];
		if (edge > 0) {
			diceCount[0] = 0;
			diceCount[1] = rating + edge;
			return diceCount;
		}

		diceCount[0] = rating;
		diceCount[1] = 0;
		return diceCount;
	}

}
