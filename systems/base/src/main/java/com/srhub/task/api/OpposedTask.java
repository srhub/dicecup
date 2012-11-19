package com.srhub.task.api;


public interface OpposedTask<T> {

	T evaluate(final int attackTarget, final int[] attackDiceCount,
			final int defendTarget, final int[] defendDiceCount)
			throws CriticalHitException, CriticalMissException,
			MultiGlitchException;

}
