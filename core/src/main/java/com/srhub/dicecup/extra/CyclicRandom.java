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
package com.srhub.dicecup.extra;

import java.util.Collections;
import java.util.List;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class CyclicRandom.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class CyclicRandom extends Random {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4509714508645300940L;

	/** The numbers. */
	private final List<Integer> numbers;

	/**
	 * Instantiates a new cyclic random.
	 *
	 * @param numbers
	 *            the numbers
	 */
	public CyclicRandom(final List<Integer> numbers) {
		// shift left once so that the first #next return first index of list
		Collections.rotate(numbers, 1);
		this.numbers = numbers;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Random#next(int)
	 */
	@Override
	public int next(final int bits) {
		Collections.rotate(numbers, -1);
		return numbers.get(0);
	}
}
