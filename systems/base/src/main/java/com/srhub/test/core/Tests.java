package com.srhub.test.core;

import java.util.LinkedList;
import java.util.List;

import com.srhub.dicecup.core.Roll;
import com.srhub.task.util.Party;
import com.srhub.test.api.CriticalHitException;
import com.srhub.test.api.CriticalMissException;
import com.srhub.test.api.GlitchException;
import com.srhub.test.api.OpenTest;
import com.srhub.test.api.OpposedTest;
import com.srhub.test.api.SuccessTest;

public class Tests {

	public static OpenTest newOpenTest() {
		return new DefaultOpenTest();
	}

	public static SuccessTestBuilder newSuccessTest() {
		return new SuccessTestBuilder();
	}

	public static OpposedTestBuilder newOpposedTest() {
		return new OpposedTestBuilder();
	}

	public static interface Builder<T> {
		T build();
	}

	public abstract static class AbstractTestBuilder<T> implements Builder<T> {

		private final static Counter ZERO_COUNTER = new Counter() {
			@Override
			public int count(final Roll roll) {
				return 0;
			}
		};

		protected boolean netto = false;
		protected final List<GlitchThrower> criticials = new LinkedList<>();
		protected Counter failCounter = ZERO_COUNTER;

		public AbstractTestBuilder<T> netto() {
			netto = true;
			return this;
		}

		public AbstractTestBuilder<T> failOn(final int pips) {
			failCounter = new PipsCounter(pips);
			return this;
		}

		public AbstractTestBuilder<T> criticalHitOn(final int pips) {
			return criticalHitOn(null, pips, 1);
		}

		public AbstractTestBuilder<T> criticalHitOn(final String id,
				final int pips) {
			return criticalHitOn(id, pips, 1);
		}

		public AbstractTestBuilder<T> criticalHitOn(final String id,
				final int pips, final float threshold) {
			criticials.add(new CriticalHitThrower(id, pips, threshold,
					Party.ATTACKER));
			return this;
		}

		public AbstractTestBuilder<T> criticalMissOn(final int pips) {
			return criticalMissOn(null, pips, 1);
		}

		public AbstractTestBuilder<T> criticalMissOn(final String id,
				final int pips) {
			return criticalMissOn(id, pips, 1);
		}

		public AbstractTestBuilder<T> criticalMissOn(final String id,
				final int pips, final float threshold) {
			criticials.add(new CriticalMissThrower(id, pips, threshold,
					Party.ATTACKER));
			return this;
		}

	}

	public static final class SuccessTestBuilder extends
			AbstractTestBuilder<SuccessTest> {
		@Override
		public SuccessTest build() {
			return new DefaultSuccessTest(netto, criticials, failCounter);
		}
	}

	public static final class OpposedTestBuilder extends
			AbstractTestBuilder<OpposedTest> {

		private final List<GlitchThrower> criticialsDefender = new LinkedList<>();

		@Override
		public OpposedTest build() {
			return new DefaultOpposedTest(netto, criticials,
					criticialsDefender, failCounter);
		}

		@Override
		public OpposedTestBuilder criticalHitOn(final String id,
				final int pips, final float threshold) {
			criticials.add(new CriticalHitThrower(id, pips, threshold,
					Party.ATTACKER));
			criticialsDefender.add(new CriticalHitThrower(id, pips, threshold,
					Party.DEFENDER));
			return this;
		}

		@Override
		public OpposedTestBuilder criticalMissOn(final String id,
				final int pips, final float threshold) {
			criticials.add(new CriticalMissThrower(id, pips, threshold,
					Party.ATTACKER));
			criticialsDefender.add(new CriticalMissThrower(id, pips, threshold,
					Party.DEFENDER));
			return this;
		}
	}

	public static interface Counter {

		int count(Roll roll);

	}

	private static class PipsCounter implements Counter {

		private final int pips;

		public PipsCounter(final int pips) {
			super();
			this.pips = pips;
		}

		@Override
		public int count(final Roll roll) {
			return roll.count(pips);
		}
	}

	public static interface GlitchThrower {
		void evaluate(Roll roll) throws GlitchException;
	}

	private static class CriticalHitThrower implements GlitchThrower {

		private final String id;
		private final int pips;
		private final float threshold;
		private final Party party;

		public CriticalHitThrower(final String id, final int pips,
				final float threshold, final Party party) {
			super();
			this.id = id;
			this.pips = pips;
			this.threshold = threshold;
			this.party = party;
		}

		@Override
		public void evaluate(final Roll roll) throws CriticalHitException {

			final int absolute = roll.count(pips);
			final int size = roll.size();
			final float percentage = absolute / size;

			if (percentage >= threshold) {
				throw new CriticalHitException(id, absolute, percentage, party);
			}

		}
	}

	private static class CriticalMissThrower implements GlitchThrower {

		private final String id;
		private final int pips;
		private final float threshold;
		private final Party party;

		public CriticalMissThrower(final String id, final int pips,
				final float threshold, final Party party) {
			super();
			this.id = id;
			this.pips = pips;
			this.threshold = threshold;
			this.party = party;
		}

		@Override
		public void evaluate(final Roll roll) throws CriticalMissException {

			final int absolute = roll.count(pips);
			final int size = roll.size();
			final float percentage = absolute / size;

			if (percentage >= threshold) {
				throw new CriticalHitException(id, absolute, percentage, party);
			}

		}
	}

}
