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

/**
 * A dice that always returns the same result.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class FixedDice extends AbstractDice {

	/** The faces. */
	private final int faces;

	/** The result. */
	private final int result;

	/**
	 * Instantiates a new fixed dice.
	 *
	 * @param faces
	 *            the faces
	 * @param result
	 *            the result
	 */
	public FixedDice(final int faces, final int result) {
		this.faces = faces;
		this.result = result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.dicecup.api.Dice#roll()
	 */
	@Override
	public int roll() {
		return result;
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
		result = prime * result + this.result;
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
		final FixedDice other = (FixedDice) obj;
		if (faces != other.faces) {
			return false;
		}
		if (result != other.result) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "FixedDice [faces=" + faces + ", result=" + result + "]";
	}

}
