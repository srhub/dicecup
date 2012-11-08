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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.srhub.dicecup.api.Dice;
import com.srhub.dicecup.core.Cup;
import com.srhub.dicecup.core.Roll;
import com.srhub.dicecup.dice.CyclicDice;
import com.srhub.dicecup.features.Features;
import com.srhub.task.core.Result;

public class SuccessTestTest {

	@Test
	public void test() throws RuleOfOneException {

		final Dice dice = CyclicDice.add(1, 2, 4, 5, 5).build(6);
		final Cup cup = Cup.add(dice, Features.EXPLODE_AT(6)).build();
		final Roll roll = cup.roll(5);

		final SuccessTest successTest = new SuccessTest();

		final Result<Roll> result = successTest.evaluate(roll, 3);

		assertEquals(3, result.getCompound());

	}
}
