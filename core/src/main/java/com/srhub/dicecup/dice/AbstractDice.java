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

import java.util.LinkedList;
import java.util.List;

import com.srhub.dicecup.api.Dice;

/**
 * Offer a default implementation of {@link Dice#roll(int)}
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public abstract class AbstractDice implements Dice {

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.dicecup.core.Dice#roll(int, int)
	 */
	@Override
	public List<Integer> roll(final int count) {

		final List<Integer> result = new LinkedList<>();
		for (int i = 0; i < count; i++) {
			result.add(roll());
		}
		return result;
	}

}
