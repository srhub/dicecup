package com.srhub.task.core;

import java.util.Arrays;

import com.srhub.dicecup.core.Cup;
import com.srhub.dicecup.core.Roll;
import com.srhub.task.api.CriticalHitException;
import com.srhub.task.api.CriticalMissException;
import com.srhub.task.api.MultiGlitchException;
import com.srhub.task.api.OpposedTask;
import com.srhub.task.api.OpposedTest;

public class DefaultOpposedTask implements
		OpposedTask<DefaultOpposedTest.Result> {

	private static final int[] EMPTY = {};

	Cup cup;
	OpposedTest<DefaultOpposedTest.Result> opposedTest;

	public DefaultOpposedTask(final Cup cup,
			final OpposedTest<DefaultOpposedTest.Result> opposedTest) {
		super();
		this.cup = cup;
		this.opposedTest = opposedTest;
	}

	@Override
	public DefaultOpposedTest.Result evaluate(final int attackTarget,
			final int[] attackerDiceCount, final int defendTarget,
			final int[] defenderDiceCount) throws CriticalHitException,
			CriticalMissException, MultiGlitchException {

		final Roll attackRoll = cup.roll(attackerDiceCount[0],
				elementsAfterFirst(attackerDiceCount));
		final Roll defendRoll = cup.roll(defenderDiceCount[0],
				elementsAfterFirst(defenderDiceCount));

		return opposedTest.evaluate(attackRoll, attackTarget, defendRoll,
				defendTarget);
	}

	private int[] elementsAfterFirst(final int[] original) {
		if (original.length <= 1) {
			return EMPTY;
		}

		return Arrays.copyOfRange(original, 1, original.length - 1);

	}

}
