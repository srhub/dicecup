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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.srhub.dicecup.core.Roll;
import com.srhub.dicecup.util.Lists;
import com.srhub.test.api.CriticalHitException;
import com.srhub.test.api.CriticalMissException;
import com.srhub.test.api.GlitchException;
import com.srhub.test.api.OpenTest;
import com.srhub.test.api.OpposedTest;
import com.srhub.test.api.SuccessTest;

/**
 * Build {@link OpenTest}s, {@link OpposedTest}, and {@link SuccessTest}s
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class Tests {

	private Tests() {
		// prevent instantiation
	}

	/**
	 * Build a new basic open test.
	 *
	 * @return the open test
	 */
	public static OpenTest<Integer> newBasicOpenTest() {
		return new BasicOpenTest();
	}

	/**
	 * New detailed open test.
	 *
	 * @return the open test
	 */
	public static OpenTest<OpenTestResult> newDetailedOpenTest() {
		return new DetailedOpenTest();
	}

	/**
	 * Build a new basic success test.
	 *
	 * @return the success test builder
	 */
	public static BasicSuccessTestBuilder newBasicSuccessTest() {
		return new BasicSuccessTestBuilder();
	}

	/**
	 * New detailed success test.
	 *
	 * @return the detailed success test builder
	 */
	public static DetailedSuccessTestBuilder newDetailedSuccessTest() {
		return new DetailedSuccessTestBuilder();
	}

	/**
	 * Build a new basic opposed test.
	 *
	 * @return the opposed test builder
	 */
	public static BasicOpposedTestBuilder newBasicOpposedTest() {
		return new BasicOpposedTestBuilder();
	}

	/**
	 * New detailed opposed test.
	 *
	 * @return the detailed opposed test builder
	 */
	public static DetailedOpposedTestBuilder newDetailedOpposedTest() {
		return new DetailedOpposedTestBuilder();
	}

	/**
	 * The Interface Builder.
	 *
	 * @param <T>
	 *            the generic type
	 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
	 */
	public static interface Builder<T> {

		/**
		 * Builds the.
		 *
		 * @return the t
		 */
		T build();
	}

	/**
	 * Base builder for success and opposed test.
	 *
	 * @param <T>
	 *            the generic type
	 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
	 */
	public abstract static class AbstractTestBuilder<T> implements Builder<T> {

		/** The Constant ZERO_COUNTER. */
		private final static Function ZERO_COUNTER = new Function() {
			@Override
			public List<Integer> apply(final Roll roll) {
				return Collections.emptyList();
			}
		};

		/** The netto. */
		protected boolean netto = false;

		/** The criticials. */
		protected final List<GlitchThrower> criticials = new LinkedList<>();

		/** The fail counter. */
		protected Function failFunction = ZERO_COUNTER;

		/**
		 * Enables netto successes.
		 * <p>
		 * Instead of returning the number of successes only, the number of
		 * fails are subtracted from the number if successes.
		 * <p>
		 * Calling the method twice does <b>NOT</b> deactive this feature.
		 *
		 * @return the test builder
		 */
		public AbstractTestBuilder<T> netto() {
			netto = true;
			return this;
		}

		/**
		 * Define the number of pips a die has to show to be counted as a fail
		 * for this test
		 *
		 * @param pips
		 *            the pips
		 * @return the test builder
		 */
		public AbstractTestBuilder<T> failOn(final int pips) {
			failFunction = new PipsCounter(pips);
			return this;
		}

		/**
		 * Define the number of pips at least one die has to show in order to be
		 * a critical hit for this test. When this occurs a
		 * {@link CriticalHitException} is thrown for this test.
		 *
		 * @param pips
		 *            the pips
		 * @return the test builder
		 */
		public AbstractTestBuilder<T> criticalHitOn(final int pips) {
			return criticalHitOn(null, pips, Double.MIN_NORMAL);
		}

		/**
		 * Define the number of pips at least one die has to show in order to be
		 * a critical hit for this test. When this occurs a
		 * {@link CriticalHitException} with the given id is thrown for this
		 * test.
		 *
		 * @param id
		 *            the id for the {@link CriticalHitException}
		 * @param pips
		 *            the pips
		 * @return the test builder
		 */
		public AbstractTestBuilder<T> criticalHitOn(final String id,
				final int pips) {
			return criticalHitOn(id, pips, Double.MIN_NORMAL);
		}

		/**
		 * Define the number of pips a die has to show, and the percentage of
		 * dice of a roll that has to show that number of pips to be a critical
		 * hit. When this occurs a {@link CriticalHitException} with the given
		 * id is thrown for this test.
		 *
		 * @param id
		 *            the id
		 * @param pips
		 *            the pips
		 * @param threshold
		 *            the threshold
		 * @return the test builder
		 */
		public AbstractTestBuilder<T> criticalHitOn(final String id,
				final int pips, final double threshold) {
			criticials.add(new CriticalHitThrower(id, pips, threshold,
					Party.ATTACKER));
			return this;
		}

		/**
		 * Critical miss on.
		 *
		 * @param pips
		 *            the pips
		 * @return the abstract test builder
		 */
		public AbstractTestBuilder<T> criticalMissOn(final int pips) {
			return criticalMissOn(null, pips, Double.MIN_NORMAL);
		}

		/**
		 * Critical miss on.
		 *
		 * @param id
		 *            the id
		 * @param pips
		 *            the pips
		 * @return the abstract test builder
		 */
		public AbstractTestBuilder<T> criticalMissOn(final String id,
				final int pips) {
			return criticalMissOn(id, pips, Double.MIN_NORMAL);
		}

		/**
		 * Critical miss on.
		 *
		 * @param id
		 *            the id
		 * @param pips
		 *            the pips
		 * @param threshold
		 *            the threshold
		 * @return the abstract test builder
		 */
		public AbstractTestBuilder<T> criticalMissOn(final String id,
				final int pips, final double threshold) {
			criticials.add(new CriticalMissThrower(id, pips, threshold,
					Party.ATTACKER));
			return this;
		}

	}

	public static final class BasicSuccessTestBuilder extends
			AbstractTestBuilder<SuccessTest<Integer>> {

		/*
		 * (non-Javadoc)
		 *
		 * @see com.srhub.test.core.Tests.Builder#build()
		 */
		@Override
		public SuccessTest<Integer> build() {
			return new BasicSuccessTest(netto, criticials, failFunction);
		}
	}

	public static final class DetailedSuccessTestBuilder extends
			AbstractTestBuilder<SuccessTest<SuccessTestResult>> {

		/*
		 * (non-Javadoc)
		 *
		 * @see com.srhub.test.core.Tests.Builder#build()
		 */
		@Override
		public SuccessTest<SuccessTestResult> build() {
			return new DetailedSuccessTest(netto, criticials, failFunction);
		}
	}

	public static abstract class AbstractOpposedTestBuilder<T> extends
			AbstractTestBuilder<OpposedTest<T>> {

		/** The criticials defender. */
		private final List<GlitchThrower> criticialsDefender = new LinkedList<>();

		/*
		 * (non-Javadoc)
		 *
		 * @see
		 * com.srhub.test.core.Tests.AbstractTestBuilder#criticalHitOn(java.
		 * lang.String, int, double)
		 */
		@Override
		public AbstractOpposedTestBuilder<T> criticalHitOn(final String id,
				final int pips, final double threshold) {
			criticials.add(new CriticalHitThrower(id, pips, threshold,
					Party.ATTACKER));
			criticialsDefender.add(new CriticalHitThrower(id, pips, threshold,
					Party.DEFENDER));
			return this;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see
		 * com.srhub.test.core.Tests.AbstractTestBuilder#criticalMissOn(java
		 * .lang.String, int, double)
		 */
		@Override
		public AbstractOpposedTestBuilder<T> criticalMissOn(final String id,
				final int pips, final double threshold) {
			criticials.add(new CriticalMissThrower(id, pips, threshold,
					Party.ATTACKER));
			criticialsDefender.add(new CriticalMissThrower(id, pips, threshold,
					Party.DEFENDER));
			return this;
		}
	}

	public static final class BasicOpposedTestBuilder extends
			AbstractOpposedTestBuilder<Integer> {

		/** The criticials defender. */
		private final List<GlitchThrower> criticialsDefender = new LinkedList<>();

		/*
		 * (non-Javadoc)
		 *
		 * @see com.srhub.test.core.Tests.Builder#build()
		 */
		@Override
		public OpposedTest<Integer> build() {
			return new BasicOpposedTest(netto, criticials, criticialsDefender,
					failFunction);
		}
	}

	public static final class DetailedOpposedTestBuilder extends
			AbstractOpposedTestBuilder<OpposedTestResult> {

		/** The criticials defender. */
		private final List<GlitchThrower> criticialsDefender = new LinkedList<>();

		/*
		 * (non-Javadoc)
		 *
		 * @see com.srhub.test.core.Tests.Builder#build()
		 */
		@Override
		public OpposedTest<OpposedTestResult> build() {
			return new DetailedOpposedTest(netto, criticials,
					criticialsDefender, failFunction);
		}
	}

	/**
	 * The Interface Function.
	 *
	 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
	 */
	public static interface Function {

		/**
		 * Count.
		 *
		 * @param roll
		 *            the roll
		 * @return the int
		 */
		List<Integer> apply(Roll roll);

	}

	/**
	 * The Class PipsCounter.
	 *
	 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
	 */
	private static class PipsCounter implements Function {

		/** The pips. */
		private final int pips;

		/**
		 * Instantiates a new pips counter.
		 *
		 * @param pips
		 *            the pips
		 */
		public PipsCounter(final int pips) {
			super();
			this.pips = pips;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see
		 * com.srhub.test.core.Tests.Function#count(com.srhub.dicecup.core.Roll)
		 */
		@Override
		public List<Integer> apply(final Roll roll) {
			return Lists.equals(roll.all(), pips);
		}
	}

	/**
	 * The Interface GlitchThrower.
	 *
	 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
	 */
	public static interface GlitchThrower {

		/**
		 * Evaluate.
		 *
		 * @param roll
		 *            the roll
		 * @throws GlitchException
		 *             the glitch exception
		 */
		void evaluate(Roll roll) throws GlitchException;
	}

	/**
	 * The Class CriticalHitThrower.
	 *
	 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
	 */
	private static class CriticalHitThrower implements GlitchThrower {

		/** The id. */
		private final String id;

		/** The pips. */
		private final int pips;

		/** The threshold. */
		private final double threshold;

		/** The party. */
		private final Party party;

		/**
		 * Instantiates a new critical hit thrower.
		 *
		 * @param id
		 *            the id
		 * @param pips
		 *            the pips
		 * @param threshold
		 *            the threshold
		 * @param party
		 *            the party
		 */
		public CriticalHitThrower(final String id, final int pips,
				final double threshold, final Party party) {
			super();
			this.id = id;
			this.pips = pips;
			this.threshold = threshold;
			this.party = party;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see
		 * com.srhub.test.core.Tests.GlitchThrower#evaluate(com.srhub.dicecup
		 * .core.Roll)
		 */
		@Override
		public void evaluate(final Roll roll) throws CriticalHitException {

			final int absolute = roll.count(pips);
			final int size = roll.size();
			final double percentage = (double) absolute / (double) size;

			if (percentage >= threshold) {
				throw new CriticalHitException(id, absolute, percentage, party);
			}

		}
	}

	/**
	 * The Class CriticalMissThrower.
	 *
	 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
	 */
	private static class CriticalMissThrower implements GlitchThrower {

		/** The id. */
		private final String id;

		/** The pips. */
		private final int pips;

		/** The threshold. */
		private final double threshold;

		/** The party. */
		private final Party party;

		/**
		 * Instantiates a new critical miss thrower.
		 *
		 * @param id
		 *            the id
		 * @param pips
		 *            the pips
		 * @param threshold
		 *            the threshold
		 * @param party
		 *            the party
		 */
		public CriticalMissThrower(final String id, final int pips,
				final double threshold, final Party party) {
			super();
			this.id = id;
			this.pips = pips;
			this.threshold = threshold;
			this.party = party;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see
		 * com.srhub.test.core.Tests.GlitchThrower#evaluate(com.srhub.dicecup
		 * .core.Roll)
		 */
		@Override
		public void evaluate(final Roll roll) throws CriticalMissException {

			final int absolute = roll.count(pips);
			final int size = roll.size();
			final double percentage = absolute / size;

			if (percentage >= threshold) {
				throw new CriticalHitException(id, absolute, percentage, party);
			}

		}
	}

}
