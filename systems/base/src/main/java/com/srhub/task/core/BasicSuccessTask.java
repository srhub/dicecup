package com.srhub.task.core;

import com.srhub.dicecup.core.Cup;
import com.srhub.dicecup.core.Roll;
import com.srhub.task.api.SuccessTask;
import com.srhub.test.api.SuccessTest;

public class BasicSuccessTask implements SuccessTask<Integer> {

	Cup cup;
	SuccessTest<Integer> successTest;

	public BasicSuccessTask(final Cup cup,
			final SuccessTest<Integer> successTest) {
		super();
		this.cup = cup;
		this.successTest = successTest;
	}

	@Override
	public Integer evaluate(final int target, final int c, final int... count) {
		final Roll roll = cup.roll(c, count);
		return successTest.evaluate(roll, target);
	}

}
