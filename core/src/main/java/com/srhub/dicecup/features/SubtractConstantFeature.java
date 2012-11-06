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

import java.util.LinkedList;
import java.util.List;

/**
 * Subtract a constant from each dice.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
class SubtractConstantFeature extends AbstractFeature {

	/** The constant. */
	private final int constant;

	/**
	 * Instantiates the feature.
	 *
	 * @param constant
	 *            the constant
	 */
	public SubtractConstantFeature(final int constant) {
		this.constant = constant;
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

		final List<Integer> output = new LinkedList<>();
		for (final int i : input) {
			output.add(i - constant);
		}

		return output;
	}

	@Override
	public int hashCode() {
		final int prime = 73;
		int result = 1;
		result = prime * result + constant;
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
		final SubtractConstantFeature other = (SubtractConstantFeature) obj;
		if (constant != other.constant) {
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
		return "SubtractConstantFeature [constant=" + constant + "]";
	}

}