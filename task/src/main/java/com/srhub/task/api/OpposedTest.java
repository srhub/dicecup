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
package com.srhub.task.api;

import com.srhub.dicecup.core.Roll;

/**
 * An opposed test is used when two parties (eg. a player character and a npc)
 * are in direct competition using the same skill or attribute.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 *
 */
public interface OpposedTest<T> {

	/**
	 * A positive result represents successes in favor of the character, while a
	 * negative result represents successes in favor of the opponent. A result
	 * of <code>0</code> represents a tie between both parties (both have zero
	 * successes or just an equal number of successes).
	 *
	 * The character doesn't necessarily have to be player character, neither
	 * has the opponent be a non player character, the terms are merely for
	 * distinguishing the involved parties.
	 *
	 * @param attackRoll
	 *            the roll of attacking party
	 * @param attackTarget
	 *            the target number of attacking party
	 * @param defendRoll
	 *            the roll of defending party
	 * @param defendTarget
	 *            the target number of defending party
	 * @return the int
	 */
	public T evaluate(Roll attackRoll, int attackTarget, Roll defendRoll,
			int defendTarget);

}
