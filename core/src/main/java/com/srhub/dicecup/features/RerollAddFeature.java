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
 * Reroll all dice with the given number of pips and add add the new result to
 * the roll result while keeping the old dice result.
 * 
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
class RerollAddFeature extends AbstractFeature {

	/** The reroll at. */
	private final int rerollAt;

	/**
	 * Instantiates the feature.
	 * 
	 * @param rerollAt
	 *            the reroll at
	 */
	protected RerollAddFeature(final int rerollAt) {
		this.rerollAt = rerollAt;
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
			if (next == rerollAt) {
				int r;
				while ((r = dice.roll()) == rerollAt) {
					output.add(r);
				}
				// add last one that isn't rerolled
				output.add(r);
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
		return "RerollAddFeature [rerollAt=" + rerollAt + "]";
	}

}