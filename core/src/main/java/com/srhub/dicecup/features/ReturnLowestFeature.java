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
 * Return the <code>n</code> lowest results.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
class ReturnLowestFeature extends AbstractFeature {

	/** The n. */
	private final int n;

	/**
	 * Instantiates the feature.
	 *
	 * @param n
	 *            the n
	 */
	public ReturnLowestFeature(final int n) {
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

		if (n > input.size()) {
			return input;
		}

		Collections.sort(input);
		return input.subList(0, n);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReturnLowestFeature [n=" + n + "]";
	}

}