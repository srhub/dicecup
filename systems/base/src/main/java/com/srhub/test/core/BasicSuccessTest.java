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
package com.srhub.test.core;

import java.util.List;

import com.srhub.dicecup.core.Roll;
import com.srhub.dicecup.util.Lists;
import com.srhub.test.api.SuccessTest;
import com.srhub.test.core.Tests.Function;
import com.srhub.test.core.Tests.GlitchThrower;

/**
 * Default implementation of a {@link SuccessTest}
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class BasicSuccessTest implements SuccessTest<Integer> {

	/** The netto. */
	private final boolean netto;

	/** The criticials. */
	private final List<GlitchThrower> criticials;

	/** The fail counter. */
	private final Function failFunction;

	/**
	 * Instantiates a new default success test.
	 *
	 * @param netto
	 *            the netto
	 * @param criticials
	 *            the criticials
	 * @param failFunction
	 *            the fail counter
	 */
	protected BasicSuccessTest(final boolean netto,
			final List<GlitchThrower> criticials, final Function failFunction) {
		this.netto = netto;
		this.criticials = criticials;
		this.failFunction = failFunction;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.test.api.SuccessTest#evaluate(com.srhub.dicecup.core.Roll,
	 * int)
	 */
	@Override
	public Integer evaluate(final Roll roll, final int target) {

		final int successes = Lists.greaterThanOrEquals(roll.all(), target)
				.size();
		final int fails = failFunction.apply(roll).size();

		for (final GlitchThrower glitchThrower : criticials) {
			glitchThrower.evaluate(roll);
		}

		if (netto) {
			return successes - fails;
		}

		return successes;
	}
}
