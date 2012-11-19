package com.srhub.dicecup.systems.sr4;

import com.srhub.dicecup.core.Cup;
import com.srhub.dicecup.dice.Dices;
import com.srhub.dicecup.features.Features;
import com.srhub.task.api.OpenTask;
import com.srhub.task.api.OpenTest;
import com.srhub.task.api.OpposedTask;
import com.srhub.task.api.OpposedTest;
import com.srhub.task.api.SuccessTask;
import com.srhub.task.api.SuccessTest;
import com.srhub.task.core.DefaultOpenTask;
import com.srhub.task.core.DefaultOpenTest;
import com.srhub.task.core.DefaultOpposedTask;
import com.srhub.task.core.DefaultOpposedTest;
import com.srhub.task.core.DefaultSuccessTask;
import com.srhub.task.core.DefaultSuccessTest;
import com.srhub.task.core.Tests;

public class Sr4 {

	private static final int SIX = 6;

	private static final String GLITCH = "Glitch";

	private static final String CRITICAL_GLITCH = "Critical glitch";

	private static final int GLITCH_ON = 1;

	protected static final int DEFAULT_TARGET_NUMBER = 5;

	private static final double _100_PERCENT = 1;

	private static final Cup CUP = Cup.add(Dices.D6, Features.EXPLODE_AT(SIX))
			.build();
	private static final OpenTest<DefaultOpenTest.Result> OPEN_TEST = Tests
			.newOpenTest();
	private static final SuccessTest<DefaultSuccessTest.Result> SUCCESS_TEST = Tests
			.newSuccessTest()
			.criticalMissOn(RULE_OF_ONE, GLITCH_ON, _100_PERCENT).build();
	private static final OpposedTest<DefaultOpposedTest.Result> OPPOSED_TEST = Tests
			.newOpposedTest()
			.criticalMissOn(RULE_OF_ONE, GLITCH_ON, _100_PERCENT).build();

	public static OpenTask<DefaultOpenTest.Result> newOpenTask() {
		return new DefaultOpenTask(CUP, OPEN_TEST);
	}

	public static SuccessTask<DefaultSuccessTest.Result> newSuccessTask() {
		return new DefaultSuccessTask(CUP, SUCCESS_TEST);
	}

	public static OpposedTask<DefaultOpposedTest.Result> newOpposedTask() {
		return new DefaultOpposedTask(CUP, OPPOSED_TEST);
	}
}
