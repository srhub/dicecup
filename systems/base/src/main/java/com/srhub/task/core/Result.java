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
package com.srhub.task.core;

import com.srhub.task.util.Party;

/**
 * Result of a test
 *
 * @param <T>
 *            the generic type
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class Result<T> {

	/** The roll. */
	private final T roll;

	/** The compound. */
	private final int compound;

	/** The winner. */
	private final Party winner;

	/**
	 * Instantiates a new result.
	 *
	 * @param roll
	 *            the roll
	 */
	public Result(final T roll) {
		this(roll, 0, Party.NONE);
	}

	/**
	 * Instantiates a new result.
	 *
	 * @param roll
	 *            the roll
	 * @param compound
	 *            the compound
	 */
	public Result(final T roll, final int compound) {
		this(roll, compound, Party.ATTACKER);
	}

	/**
	 * Instantiates a new result.
	 *
	 * @param roll
	 *            the roll
	 * @param compound
	 *            the compound
	 * @param winner
	 *            the winner
	 */
	public Result(final T roll, final int compound, final Party winner) {
		super();
		this.roll = roll;
		this.compound = compound;
		this.winner = winner;

	}

	/**
	 * Compound describes the number of successes or the highest result
	 *
	 * @return the compound
	 */
	public int getCompound() {
		return compound;
	}

	/**
	 * Gets the winner.
	 *
	 * @return the winner
	 */
	public Party getWinner() {
		return winner;
	}

	/**
	 * Gets the roll.
	 *
	 * @return the roll
	 */
	public T getRoll() {
		return roll;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roll == null) ? 0 : roll.hashCode());
		result = prime * result + compound;
		result = prime * result + ((winner == null) ? 0 : winner.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
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
		final Result other = (Result) obj;
		if (roll == null) {
			if (other.roll != null) {
				return false;
			}
		} else if (!roll.equals(other.roll)) {
			return false;
		}
		if (compound != other.compound) {
			return false;
		}
		if (winner != other.winner) {
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
		return "Result [roll=" + roll + ", compound=" + compound + ", winner="
				+ winner + "]";
	}

}
