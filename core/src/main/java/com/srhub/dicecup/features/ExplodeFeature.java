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

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Explode all dice with the given number of pips - meaning that a dice gets
 * rerolled and the new result cumulatively added until the new result doesn't
 * show a number that has to be exploded.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
class ExplodeFeature extends AbstractFeature {

	/** The explode at. */
	private final int explodeAt;

	/**
	 * Instantiates the feature.
	 *
	 * @param explodeAt
	 *            the number to explode the dice at
	 */
	protected ExplodeFeature(final int explodeAt) {
		this.explodeAt = explodeAt;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.dicecup.features.AbstractFeature#apply(java.util.List)
	 */
	@Override
	public List<Integer> apply(final List<Integer> input) {

		final LinkedList<Integer> output = new LinkedList<>();
		final Iterator<Integer> iterator = input.iterator();
		while (iterator.hasNext()) {
			final int next = iterator.next();
			if (next == explodeAt) {
				int sum = explodeAt;
				int r;
				do {
					r = dice.roll();
					sum += r;
				} while (r == explodeAt);
				output.add(sum);
			} else {
				output.add(next);
			}
		}

		Collections.sort(output);
		return output;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExplodeFeature [explodeAt=" + explodeAt + "]";
	}

}