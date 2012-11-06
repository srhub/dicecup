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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.srhub.dicecup.api.Dice;

/**
 * A loaded dice based on the Alias Method by Keith Schwarz
 * <p>
 * Construct a new loaded dice using {@link #set(int, double)} and the builder
 * pattern.
 * <p>
 * This probability distribution is an implementation of the alias method
 * implemented using Vose's algorithm. The alias method allows for efficient
 * sampling of random values from a discrete probability distribution (i.e.
 * rolling a loaded die) in O(1) time each after O(n) preprocessing time.
 * <p>
 * For a complete writeup on the alias method, including the intuition and
 * important proofs, please see the article "Darts, Dice, and Coins: Smpling
 * from a Discrete Distribution" at
 *
 * @see <a
 *      href="Darts, Dice and Coins">http://www.keithschwarz.com/darts-dice-coins/</a>
 * @see <a
 *      href="AliasMethod.java">http://www.keithschwarz.com/interesting/code/?dir=alias-method</a>
 *
 * @author Keith Schwarz <htiek@cs.stanford.edu>
 *
 */
public class LoadedDice extends AbstractDice {

	/** The random number generator used to sample from the distribution. */
	private final Random random;

	/** The probability and alias tables. */
	private final int[] alias;

	/** The probability. */
	private final double[] probability;

	/**
	 * Constructs a loaded dice using the AliasMethod to sample from a discrete
	 * distribution and hand back outcomes based on the probability
	 * distribution.
	 * <p>
	 * Given as input a list of probabilities corresponding to outcomes 0, 1,
	 * ..., n - 1, this constructor creates the probability and alias tables
	 * needed to efficiently sample from this distribution.
	 *
	 * @param probabilities
	 *            The list of probabilities.
	 */
	private LoadedDice(final List<Double> probabilities) {
		this(probabilities, new Random());
	}

	/**
	 * Constructs a loaded dice using the AliasMethod to sample from a discrete
	 * distribution and hand back outcomes based on the probability
	 * distribution.
	 * <p>
	 * Given as input a list of probabilities corresponding to outcomes 0, 1,
	 * ..., n - 1, along with the random number generator that should be used as
	 * the underlying generator, this constructor creates the probability and
	 * alias tables needed to efficiently sample from this distribution.
	 *
	 * @param probabilities
	 *            The list of probabilities.
	 * @param random
	 *            The random number generator
	 */
	private LoadedDice(List<Double> probabilities, final Random random) {
		/* Begin by doing basic structural checks on the inputs. */
		if (probabilities == null || random == null) {
			throw new NullPointerException();
		}
		if (probabilities.size() == 0) {
			throw new IllegalArgumentException(
					"Probability vector must be nonempty.");
		}

		/* Allocate space for the probability and alias tables. */
		probability = new double[probabilities.size()];
		alias = new int[probabilities.size()];

		/* Store the underlying generator. */
		this.random = random;

		/* Compute the average probability and cache it for later use. */
		final double average = 1.0 / probabilities.size();

		/*
		 * Make a copy of the probabilities list, since we will be making
		 * changes to it.
		 */
		probabilities = new ArrayList<Double>(probabilities);

		/* Create two stacks to act as worklists as we populate the tables. */
		final Deque<Integer> small = new ArrayDeque<Integer>();
		final Deque<Integer> large = new ArrayDeque<Integer>();

		/* Populate the stacks with the input probabilities. */
		for (int i = 0; i < probabilities.size(); ++i) {
			/*
			 * If the probability is below the average probability, then we add
			 * it to the small list; otherwise we add it to the large list.
			 */
			if (probabilities.get(i) >= average) {
				large.add(i);
			} else {
				small.add(i);
			}
		}

		/*
		 * As a note: in the mathematical specification of the algorithm, we
		 * will always exhaust the small list before the big list. However, due
		 * to floating point inaccuracies, this is not necessarily true.
		 * Consequently, this inner loop (which tries to pair small and large
		 * elements) will have to check that both lists aren't empty.
		 */
		while (!small.isEmpty() && !large.isEmpty()) {
			/* Get the index of the small and the large probabilities. */
			final int less = small.removeLast();
			final int more = large.removeLast();

			/*
			 * These probabilities have not yet been scaled up to be such that
			 * 1/n is given weight 1.0. We do this here instead.
			 */
			probability[less] = probabilities.get(less) * probabilities.size();
			alias[less] = more;

			/*
			 * Decrease the probability of the larger one by the appropriate
			 * amount.
			 */
			probabilities.set(more,
					(probabilities.get(more) + probabilities.get(less))
							- average);

			/*
			 * If the new probability is less than the average, add it into the
			 * small list; otherwise add it to the large list.
			 */
			if (probabilities.get(more) >= 1.0 / probabilities.size()) {
				large.add(more);
			} else {
				small.add(more);
			}
		}

		/*
		 * At this point, everything is in one list, which means that the
		 * remaining probabilities should all be 1/n. Based on this, set them
		 * appropriately. Due to numerical issues, we can't be sure which stack
		 * will hold the entries, so we empty both.
		 */
		while (!small.isEmpty()) {
			probability[small.removeLast()] = 1.0;
		}
		while (!large.isEmpty()) {
			probability[large.removeLast()] = 1.0;
		}
	}

	/**
	 * Samples a value from the underlying distribution.
	 *
	 * @return A random value sampled from the underlying distribution.
	 */
	private int next() {
		/* Generate a fair die roll to determine which column to inspect. */
		final int column = random.nextInt(probability.length);

		/* Generate a biased coin toss to determine which option to pick. */
		final boolean coinToss = random.nextDouble() < probability[column];

		/* Based on the outcome, return either the column or its alias. */
		return coinToss ? column : alias[column];
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.dicecup.api.Dice#roll()
	 */
	@Override
	public int roll() {
		return next() + 1;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.dicecup.api.Dice#getFaces()
	 */
	@Override
	public int getFaces() {
		return probability.length;
	}

	/**
	 * Sets the.
	 *
	 * @param face
	 *            the face
	 * @param probability
	 *            the probability
	 * @return the probability distribution builder
	 */
	public static Builder set(final int face, final double probability) {
		return new Builder().set(face, probability);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(alias);
		result = prime * result + Arrays.hashCode(probability);
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
		final LoadedDice other = (LoadedDice) obj;
		if (!Arrays.equals(alias, other.alias)) {
			return false;
		}
		if (!Arrays.equals(probability, other.probability)) {
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
		return "LoadedDice ["
				+ (random != null ? "random=" + random + ", " : "")
				+ (alias != null ? "alias=" + Arrays.toString(alias) + ", "
						: "")
				+ (probability != null ? "probability="
						+ Arrays.toString(probability) : "") + "]";
	}

	/**
	 * Build a new loaded dice.
	 *
	 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
	 */
	public static class Builder {

		/** The Constant ONE. */
		private static final double ONE = 1.0;

		/** The probability map. */
		// use TreeMap to guarantee iteration using natural order of keys
		private final Map<Integer, Double> probabilityMap = new TreeMap<Integer, Double>();

		/**
		 * Instantiates a new probability distribution builder.
		 */
		protected Builder() {
		}

		/**
		 * Set the probability of of rolling the number of pips
		 *
		 * @param pips
		 *            the number of pips
		 * @param probability
		 *            the probability
		 * @return the probability distribution builder
		 */
		public Builder set(final int pips, final double probability) {
			probabilityMap.put(pips, probability);
			return this;
		}

		/**
		 * Build the loaded dice with the number of faces. If a given face has
		 * no set probability it will default to an average probability.
		 *
		 * @param faces
		 *            the number of faces.
		 * @return the loaded dice
		 */
		public Dice build(final int faces) {
			final TreeSet<Integer> keys = new TreeSet<>(probabilityMap.keySet());

			double rest = ONE;
			for (final int key : keys) {
				rest -= probabilityMap.get(key);
			}

			if (rest < 0) {
				throw new IllegalArgumentException(
						"Sum of set probabilities is greater 1");
			}

			final double average = rest / faces;
			for (int i = 1; i <= faces; i++) {
				if (probabilityMap.get(i) == null) {
					probabilityMap.put(i, average);
				}
			}

			return new LoadedDice(
					new ArrayList<Double>(probabilityMap.values()));
		}

		/**
		 * Build the loaded dice.
		 *
		 * @return the loaded dice
		 */
		public Dice build() {
			final Set<Integer> keys = probabilityMap.keySet();
			final Integer max = Collections.max(keys);
			return build(max);
		}

	}

}
