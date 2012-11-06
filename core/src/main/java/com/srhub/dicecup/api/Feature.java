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
package com.srhub.dicecup.api;

import java.util.List;

import com.srhub.dicecup.core.Group;
import com.srhub.dicecup.features.Features;

/**
 * A feature manipulates the roll of a {@link Group} of dice.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public interface Feature {

	/**
	 * Apply a function to a dice roll.
	 *
	 * @param input
	 *            the input dice roll.
	 * @return the transformed list
	 */
	public List<Integer> apply(final List<Integer> input);

	/**
	 * Sets the {@link Dice} to be rolled. Needs to be set as the methods of
	 * {@link Features} are agnostic of the dice to be rolled.
	 * <p>
	 * If you want to implement your own {@link Feature} it is highly
	 * recommended to extend {@link AbstractFeature}.
	 *
	 * @param dice
	 *            the new dice
	 */
	void setDice(Dice dice);
}
