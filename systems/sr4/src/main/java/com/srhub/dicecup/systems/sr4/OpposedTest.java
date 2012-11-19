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

import com.srhub.test.core.Party;

// TODO: Auto-generated Javadoc
/**
 * The Class OpposedTest.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class OpposedTest {

	/**
	 * Roll.
	 *
	 * @param attackerRating
	 *            the attacker rating
	 * @param defenderRating
	 *            the defender rating
	 * @return the int
	 * @throws Glitch
	 *             the glitch exception
	 */
	public static int roll(final int attackerRating, final int defenderRating)
			throws GlitchException {

		int attackerSucesses;
		try {
			attackerSucesses = new SuccessTest().roll(attackerRating,
					defenderRating);
		} catch (final GlitchException e) {
			throw new GlitchException(Party.ATTACKER, e.isCritical());
		}
		int defenderSuccesses;
		try {
			defenderSuccesses = new SuccessTest().roll(defenderRating,
					attackerRating);
		} catch (final GlitchException e) {
			throw new GlitchException(Party.DEFENDER, e.isCritical());
		}

		return attackerSucesses - defenderSuccesses;
	}

}
