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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	/**
	 * The number of sides of six sided die. Pshht: It'input six.
	 */
	private static final int DEFAULT_DICE = 6;

	/** The groups of dice. */
	private final Set<Group> groups = new HashSet<Group>();

	/**
	 * Instantiates a new dice cup.
	 */
	public Cup() {
	}

	/**
	 * Adds <code>d6</code> number of D6 to the cup
	 *
	 * @param d6
	 *            <code>d6</code> number of D6
	 * @return this cup
	 */
	public Cup add(final int d6) {
		return add(d6, DEFAULT_DICE);
	}

	/**
	 * Add <code>d6</code> number of D6 to the cup using {@link Features} that
	 * transform the roll outcome
	 *
	 * @param d6
	 *            <code>d6</code> number of D6
	 * @param features
	 *            the features to transform the dice roll
	 * @return this cup
	 */
	public Cup add(final int d6, final Feature... features) {
		return add(d6, DEFAULT_DICE, features);
	}

	/**
	 * Add <code>d6</code> number of D6 to the cup that can be identified using
	 * an <code>id</code>
	 *
	 * @param id
	 *            the id of the dice group
	 * @param d6
	 *            <code>d6</code> number of D6
	 * @return this cup
	 */
	public Cup add(final String id, final int d6) {
		return add(id, d6, DEFAULT_DICE);
	}

	/**
	 * Add <code>d6</code> number of D6 to the cup using {@link Features} that
	 * transform the roll outcome and can be identified using an <code>id</code>
	 *
	 * @param id
	 *            the id of the dice group
	 * @param d6
	 *            <code>d6</code> number of D6
	 * @param features
	 *            the features to transform the dice roll
	 * @return this cup
	 */
	public Cup add(final String id, final int d6, final Feature... features) {
		return add(id, d6, DEFAULT_DICE, features);
	}

	/**
	 * Add <code>count</code> number of D<code>{faces}</code> to the cup
	 *
	 * @param count
	 *            number of dice to roll
	 * @param faces
	 *            the faces of the dice to roll
	 * @return this cup
	 */
	public Cup add(final int count, final int faces) {
		return add(null, count, faces);
	}

	/**
	 * Add <code>count</code> number of {@link Dice}
	 *
	 * @param count
	 *            number of dice to roll
	 * @param dice
	 *            the dice to roll
	 * @return this cup
	 */
	public Cup add(final int count, final Dice dice) {
		return add(null, count, dice);
	}

	/**
	 * Add <code>count</code> number of D<code>{faces}</code> to the cup that
	 * can be identified using an <code>id</code>
	 *
	 *
	 * @param id
	 *            the id of the dice group
	 * @param count
	 *            number of dice to roll
	 * @param faces
	 *            the faces
	 * @return this cup
	 */
	public Cup add(final String id, final int count, final int faces) {
		return add(id, count, faces, new Feature[] {});
	}

	/**
	 * Add <code>count</code> number of {@link Dice} to the cup that can be
	 * identified using an <code>id</code>
	 *
	 * @param id
	 *            the id of the dice group
	 * @param count
	 *            number of dice to roll
	 * @param dice
	 *            the dice to roll
	 * @return this cup
	 */
	public Cup add(final String id, final int count, final Dice dice) {
		return add(id, count, dice, new Feature[] {});
	}

	/**
	 * Add <code>count</code> number of D<code>{faces}</code> to the cup using
	 * {@link Features} that transform the roll outcome
	 *
	 * @param count
	 *            number of dice to roll
	 * @param faces
	 *            the faces
	 * @param features
	 *            the features to transform the dice roll
	 * @return this cup
	 */
	public Cup add(final int count, final int faces, final Feature... features) {
		return add(null, count, faces, features);
	}

	/**
	 * Add <code>count</code> number of {@link Dice} to the cup using
	 * {@link Features} that transform the roll outcome
	 *
	 * @param count
	 *            number of dice to roll
	 * @param dice
	 *            the dice to roll
	 * @param features
	 *            the features
	 * @return this cup
	 */
	public Cup add(final int count, final Dice dice, final Feature... features) {
		return add(null, count, dice, features);
	}

	/**
	 * Add <code>count</code> number of D<code>{faces}</code> to the cup using
	 * {@link Features} that transform the roll outcome and can be identified
	 * using an <code>id</code>
	 *
	 * @param id
	 *            the id of the dice group
	 * @param count
	 *            number of dice to roll
	 * @param faces
	 *            the faces
	 * @param features
	 *            the features to transform the dice roll
	 * @return this cup
	 */
	public Cup add(final String id, final int count, final int faces,
			final Feature... features) {
		return add(id, count, new RandomDice(faces), features);
	}

	/**
	 * Add <code>count</code> number of {@link Dice} to the cup using
	 * {@link Features} that transform the roll outcome and can be identified
	 * using an <code>id</code>
	 *
	 * @param id
	 *            the id of the dice group
	 * @param count
	 *            number of dice to roll
	 * @param dice
	 *            the dice to roll
	 * @param features
	 *            the features to transform the dice roll
	 * @return this cup
	 */
	public Cup add(final String id, final int count, final Dice dice,
			final Feature... features) {
		for (final Feature feature : features) {
			feature.setDice(dice);
		}

		groups.add(new Group(id, dice, count, features));
		return this;
	}

	/**
	 * Roll the dice.
	 *
	 * @return the roll
	 */
	public Roll roll() {

		final Map<Group, List<Integer>> result = new HashMap<>(groups.size());

		for (final Group group : groups) {
			final List<Integer> roll = group.getDice().roll(group.getCount());

			final Feature[] features = group.getFeatures();

			if (features.length > 0) {
				for (final Feature feature : features) {
					result.put(group, feature.apply(roll));
				}
			} else {
				result.put(group, roll);
			}
		}

		return new Roll(result);
	}

	/**
	 * Clear the cup.
	 */
	public void clear() {
		groups.clear();
	}
}
