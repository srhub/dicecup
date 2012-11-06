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
package com.srhub.dicecup.features;

import java.util.List;

import com.srhub.dicecup.api.Dice;
import com.srhub.dicecup.api.Feature;

/**
 * A feature with default {@link #setDice(Dice)} implementation
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
abstract class AbstractFeature implements Feature {

	/** The dice. */
	protected Dice dice;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.dicecup.api.Feature#apply(java.util.List)
	 */
	@Override
	public abstract List<Integer> apply(final List<Integer> input);

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.dicecup.api.Feature#setDice(com.srhub.dicecup.api.Dice)
	 */
	@Override
	public void setDice(final Dice dice) {
		this.dice = dice;
	}
}