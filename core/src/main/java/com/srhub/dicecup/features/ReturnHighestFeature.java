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
import java.util.List;

/**
 * Return the <code>n</code> highest results.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
class ReturnHighestFeature extends AbstractFeature {

	/** The n. */
	private final int n;

	/**
	 * Instantiates the feature.
	 *
	 * @param n
	 *            the n
	 */
	public ReturnHighestFeature(final int n) {
		this.n = n;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.dicecup.features.AbstractFeature#apply(java.util.List)
	 */
	@Override
	public List<Integer> apply(final List<Integer> input) {
		if (input.isEmpty()) {
			return input;
		}

		final int size = input.size();
		if (n > size) {
			return input;
		}

		Collections.sort(input);
		return input.subList(size - n - 1, size - 1);
	}

	@Override
	public int hashCode() {
		final int prime = 67;
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
		final ReturnHighestFeature other = (ReturnHighestFeature) obj;
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
		return "ReturnHighestFeature [n=" + n + "]";
	}

}