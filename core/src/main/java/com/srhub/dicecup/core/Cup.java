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
package com.srhub.dicecup.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.srhub.dicecup.api.Dice;
import com.srhub.dicecup.api.Feature;
import com.srhub.dicecup.dice.RandomDice;
import com.srhub.dicecup.features.Features;

/**
 * Represents a cup filled with (groups of) dice.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class Cup {

	/** The groups of dice. */
	private final List<Group> groups;

	/**
	 * Instantiates a new cup.
	 *
	 * @param groups
	 *            the groups
	 */
	private Cup(final List<Group> groups) {
		this.groups = groups;
	}

	/**
	 * Adds a fair dice with the given number of faces to the cup
	 *
	 * @param number
	 *            of faces
	 * @return this cup
	 */
	public static Builder add(final int faces) {
		return add(new RandomDice(faces));
	}

	/**
	 * Adds a fair dice with the given number of faces to the cup that can be
	 * identified using an <code>id</code>
	 *
	 * @param id
	 *            the id of the dice group
	 * @param faces
	 *            number of faces
	 * @return this cup
	 */
	public static Builder add(final String id, final int faces) {
		return add(id, new RandomDice(faces));
	}

	/**
	 * Adds a fair dice with the given number of faces that transform the roll
	 * outcome and can be identified using an <code>id</code>
	 *
	 * @param id
	 *            the id of the dice group
	 * @param faces
	 *            number of faces
	 * @param features
	 *            the features to transform the dice roll
	 * @return this cup
	 */
	public static Builder add(final String id, final int faces,
			final Feature... features) {
		return add(id, new RandomDice(faces), features);
	}

	/**
	 * Add a {@link Dice} to the cup
	 *
	 * @param dice
	 *            the dice to roll
	 * @return this cup
	 */
	public static Builder add(final Dice dice) {
		return add(null, dice);
	}

	/**
	 * Add {@link Dice} to the cup that can be identified using an
	 * <code>id</code>
	 *
	 * @param id
	 *            the id of the dice group
	 * @param dice
	 *            the dice to roll
	 * @return this cup
	 */
	public static Builder add(final String id, final Dice dice) {
		return add(id, dice, new Feature[] {});
	}

	/**
	 * Add a fair dice with given number of faces to the cup using
	 * {@link Features} that transform the roll outcome
	 *
	 * @param faces
	 *            the faces
	 * @param features
	 *            the features to transform the dice roll
	 * @return this cup
	 */
	public static Builder add(final int faces, final Feature... features) {
		return add(null, faces, features);
	}

	/**
	 * Add {@link Dice} to the cup using {@link Features} that transform the
	 * roll outcome
	 *
	 * @param dice
	 *            the dice to roll
	 * @param features
	 *            the features
	 * @return this cup
	 */
	public static Builder add(final Dice dice, final Feature... features) {
		return add(null, dice, features);
	}

	/**
	 * Adds a {@link Dice} to the cup using {@link Features} that transform the
	 * roll outcome and can be identified using an <code>id</code>
	 *
	 * @param id
	 *            the id of the dice group
	 * @param dice
	 *            the dice to roll
	 * @param features
	 *            the features to transform the dice roll
	 * @return this cup
	 */
	public static Builder add(final String id, final Dice dice,
			final Feature... features) {
		for (final Feature feature : features) {
			feature.setDice(dice);
		}

		return new Builder(new Group(id, dice, features));
	}

	/**
	 * Roll the dice.
	 *
	 * @return the roll
	 */
	public Roll roll(final int c) {
		return roll(c, new int[0]);
	}

	/**
	 * Roll the dice.
	 *
	 * @return the roll
	 */
	public Roll roll(final int c, final int... counts) {

		final int size = groups.size();

		if (counts.length < size - 1) {
			throw new IllegalArgumentException(
					"Count is lesser than the number of dice groups");
		}

		if (counts.length > size - 1) {
			throw new IllegalArgumentException(
					"Count is greater than the number of dice groups");
		}

		final Map<Group, List<Integer>> results = new HashMap<>(groups.size());

		// run the first
		addResult(results, groups.get(0), c);

		// run the rest of the counts
		for (int i = 0; i < counts.length; i++) {
			final int count = counts[i];
			final Group group = groups.get(i + 1);
			addResult(results, group, count);
		}

		return new Roll(results);
	}

	private void addResult(final Map<Group, List<Integer>> results,
			final Group group, final int count) {
		final List<Integer> roll = group.getDice().roll(count);
		final Feature[] features = group.getFeatures();
		if (features.length > 0) {
			for (final Feature feature : features) {
				results.put(group, feature.apply(roll));
			}
		} else {
			results.put(group, roll);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
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
		final Cup other = (Cup) obj;
		if (groups == null) {
			if (other.groups != null) {
				return false;
			}
		} else if (!groups.equals(other.groups)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Cup [" + (groups != null ? "groups=" + groups : "") + "]";
	}

	public static final class Builder {

		/** The groups of dice. */
		private final List<Group> groups = new ArrayList<Group>();

		public Builder(final Group group) {
			groups.add(group);
		}

		/**
		 * Adds a fair dice with the given number of faces to the cup
		 *
		 * @param number
		 *            of faces
		 * @return this cup
		 */
		public Builder add(final int faces) {
			return add(new RandomDice(faces));
		}

		/**
		 * Adds a fair dice with the given number of faces to the cup that can
		 * be identified using an <code>id</code>
		 *
		 * @param id
		 *            the id of the dice group
		 * @param faces
		 *            number of faces
		 * @return this cup
		 */
		public Builder add(final String id, final int faces) {
			return add(id, new RandomDice(faces));
		}

		/**
		 * Adds a fair dice with the given number of faces that transform the
		 * roll outcome and can be identified using an <code>id</code>
		 *
		 * @param id
		 *            the id of the dice group
		 * @param faces
		 *            number of faces
		 * @param features
		 *            the features to transform the dice roll
		 * @return this cup
		 */
		public Builder add(final String id, final int faces,
				final Feature... features) {
			return add(id, new RandomDice(faces), features);
		}

		/**
		 * Add a {@link Dice} to the cup
		 *
		 * @param dice
		 *            the dice to roll
		 * @return this cup
		 */
		public Builder add(final Dice dice) {
			return add(null, dice);
		}

		/**
		 * Add {@link Dice} to the cup that can be identified using an
		 * <code>id</code>
		 *
		 * @param id
		 *            the id of the dice group
		 * @param dice
		 *            the dice to roll
		 * @return this cup
		 */
		public Builder add(final String id, final Dice dice) {
			return add(id, dice, new Feature[] {});
		}

		/**
		 * Add a fair dice with given number of faces to the cup using
		 * {@link Features} that transform the roll outcome
		 *
		 * @param faces
		 *            the faces
		 * @param features
		 *            the features to transform the dice roll
		 * @return this cup
		 */
		public Builder add(final int faces, final Feature... features) {
			return add(null, faces, features);
		}

		/**
		 * Add {@link Dice} to the cup using {@link Features} that transform the
		 * roll outcome
		 *
		 * @param dice
		 *            the dice to roll
		 * @param features
		 *            the features
		 * @return this cup
		 */
		public Builder add(final Dice dice, final Feature... features) {
			return add(null, dice, features);
		}

		/**
		 * Adds a {@link Dice} to the cup using {@link Features} that transform
		 * the roll outcome and can be identified using an <code>id</code>
		 *
		 * @param id
		 *            the id of the dice group
		 * @param dice
		 *            the dice to roll
		 * @param features
		 *            the features to transform the dice roll
		 * @return this cup
		 */
		public Builder add(final String id, final Dice dice,
				final Feature... features) {
			for (final Feature feature : features) {
				feature.setDice(dice);
			}

			groups.add(new Group(id, dice, features));
			return this;
		}

		public Cup build() {
			return new Cup(groups);
		}

	}

}
