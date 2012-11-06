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
package com.srhub.dicecup.dice;

import java.util.Random;

/**
 * A dice with a given number of faces using a number generator
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class RandomDice extends AbstractDice {

	/** The Constant RANDOM. */
	private static final Random RANDOM = new Random();

	/** The faces. */
	private final int faces;

	/** The random number generator. */
	private final Random random;

	/**
	 * Instantiates a new random dice using Java's default {@link Random} number
	 * generator.
	 *
	 * @param faces
	 *            the faces
	 */
	public RandomDice(final int faces) {
		this(faces, RANDOM);
	}

	/**
	 * Instantiates a new random dice with the given number generator.
	 *
	 * @param faces
	 *            the faces
	 * @param random
	 *            the random
	 */
	public RandomDice(final int faces, final Random random) {
		this.faces = faces;
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.dicecup.core.Dice#roll(int)
	 */
	@Override
	public int roll() {
		return random.nextInt(faces) + 1;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.dicecup.api.Dice#getFaces()
	 */
	@Override
	public int getFaces() {
		return faces;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + faces;
		result = prime * result + ((random == null) ? 0 : random.hashCode());
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
		final RandomDice other = (RandomDice) obj;
		if (faces != other.faces) {
			return false;
		}
		if (random == null) {
			if (other.random != null) {
				return false;
			}
		} else if (!random.equals(other.random)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "RandomDice [faces=" + faces + ", "
				+ (random != null ? "random=" + random : "") + "]";
	}

}
