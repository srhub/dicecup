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
package com.srhub.task.core;

import com.srhub.dicecup.core.Roll;

/**
 * Holds result roll for attacker and defender
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class Rolls {

	/** The attacker roll. */
	private final Roll attackerRoll;

	/** The defender roll. */
	private final Roll defenderRoll;

	/**
	 * Instantiates a new rolls.
	 *
	 * @param attackerRoll
	 *            the attacker roll
	 * @param defenderRoll
	 *            the defender roll
	 */
	public Rolls(final Roll attackerRoll, final Roll defenderRoll) {
		super();
		this.attackerRoll = attackerRoll;
		this.defenderRoll = defenderRoll;
	}

	/**
	 * Gets the attacker roll.
	 *
	 * @return the attacker roll
	 */
	public Roll getAttackerRoll() {
		return attackerRoll;
	}

	/**
	 * Gets the defender roll.
	 *
	 * @return the defender roll
	 */
	public Roll getDefenderRoll() {
		return defenderRoll;
	}

}
