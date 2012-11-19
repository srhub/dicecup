package com.srhub.task.core;

import com.srhub.dicecup.core.Cup;
import com.srhub.dicecup.core.Roll;
import com.srhub.task.api.SuccessTask;
import com.srhub.task.api.SuccessTest;

public class DefaultSuccessTask implements
		SuccessTask<DefaultSuccessTest.Result> {

	Cup cup;
	SuccessTest<DefaultSuccessTest.Result> successTest;

	public DefaultSuccessTask(final Cup cup,
			final SuccessTest<DefaultSuccessTest.Result> successTest) {
		super();
		this.cup = cup;
		this.successTest = successTest;
	}

	@Override
	public DefaultSuccessTest.Result evaluate(final int target, final int c,
			final int... count) {
		final Roll roll = cup.roll(c, count);
		return successTest.evaluate(roll, target);
	}

}
