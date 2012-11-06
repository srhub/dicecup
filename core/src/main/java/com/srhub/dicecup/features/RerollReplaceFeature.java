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
 * Reroll all dice with the given number of pips and replace the new result with
 * the old one.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
class RerollReplaceFeature extends AbstractFeature {

	/** The reroll at. */
	private final int rerollAt;

	/**
	 * Instantiates the feature.
	 *
	 * @param rerollAt
	 *            the reroll at
	 */
	protected RerollReplaceFeature(final int rerollAt) {
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
				do {
					r = dice.roll();
				} while (rerollAt == r);
				output.add(r);
			} else {
				output.add(next);
			}
		}

		Collections.sort(output);
		return output;
	}

	@Override
	public int hashCode() {
		final int prime = 61;
		int result = 1;
		result = prime * result + rerollAt;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final RerollReplaceFeature other = (RerollReplaceFeature) obj;
		if (rerollAt != other.rerollAt) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RerollReplaceFeature [rerollAt=" + rerollAt + "]";
	}

}