package com.srhub.task.api;

import com.srhub.test.api.CriticalHitException;
import com.srhub.test.api.CriticalMissException;
import com.srhub.test.api.MultiGlitchException;

public interface OpposedTask<T> {

	T evaluate(final int attackTarget, final int[] attackDiceCount,
			final int defendTarget, final int[] defendDiceCount)
			throws CriticalHitException, CriticalMissException,
			MultiGlitchException;

}
