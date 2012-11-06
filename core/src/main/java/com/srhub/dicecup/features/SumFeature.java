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

import java.util.ArrayList;
import java.util.List;

/**
 * Return the sum of all dice.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class SumFeature extends AbstractFeature {

	/**
	 * Instantiates the feature.
	 */
	public SumFeature() {
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

		int sum = 0;
		for (final int i : input) {
			sum += i;
		}

		final List<Integer> output = new ArrayList<>(1);
		output.add(sum);

		return output;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SumFeature";
	}

}
