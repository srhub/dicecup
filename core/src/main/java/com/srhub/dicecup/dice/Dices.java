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
 * Standard dice.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class Dices {

	/** A D4. */
	public static final Dice D4 = new RandomDice(4);

	/** A D6. */
	public static final Dice D6 = new RandomDice(6);

	/** A D8. */
	public static final Dice D8 = new RandomDice(8);

	/** A D10. */
	public static final Dice D10 = new RandomDice(10);

	/** A D12. */
	public static final Dice D12 = new RandomDice(12);

	/** A D20. */
	public static final Dice D20 = new RandomDice(20);

	/** A D100. */
	public static final Dice D100 = new RandomDice(100);

}
