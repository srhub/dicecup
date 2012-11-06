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
import java.util.LinkedList;
import java.util.List;

/**
 * Drop the highest dice from the dice roll.
 * 
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
class DropHighestFeature extends AbstractFeature {

	/** The n. */
	private final int n;

	/**
	 * Instantiates the feature.
	 * 
	 * @param n
	 *            the n
	 */
	public DropHighestFeature(final int n) {
		this.n = n;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.srhub.dicecup.features.AbstractDiceAgnosticFeature#apply(java.util
	 * .List)
	 */
	@Override
	public List<Integer> apply(final List<Integer> input) {
		if (input.isEmpty()) {
			return input;
		}

		final int size = input.size();
		if (size < n) {
			return Collections.emptyList();
		}

		final LinkedList<Integer> output = new LinkedList<>(input);
		Collections.sort(output);
		for (int i = 0; i < n; i++) {
			output.removeLast();
		}

		return output;
	}

	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 1;
		result = prime * result + n;
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
		final DropHighestFeature other = (DropHighestFeature) obj;
		if (n != other.n) {
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
		return "DropHighestFeature [n=" + n + "]";
	}

}