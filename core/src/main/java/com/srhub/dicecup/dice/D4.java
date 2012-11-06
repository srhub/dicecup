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
package com.srhub.dicecup.dice;

import com.srhub.dicecup.api.Dice;

/**
 * A fair D4.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class D4 {

	/** The Constant FACES. */
	private static final int FACES = 4;

	/** The Constant D4. */
	public static final Dice D4 = new RandomDice(FACES);

	/**
	 * Roll the dice.
	 *
	 * @return the int
	 */
	public static int roll() {
		return D4.roll();
	}

}