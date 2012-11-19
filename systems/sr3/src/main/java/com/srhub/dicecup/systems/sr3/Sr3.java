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
package com.srhub.dicecup.systems.sr3;

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

/**
 * Describe constants
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class Sr3 {

	private static final String RULE_OF_ONE = "rule of one";

	private static final int SIX = 6;

	private static final int CRITICAL_MISS_ON = 1;

	private static final double _100_PERCENT = 1;

	private static final Cup CUP = Cup.add(Dices.D6, Features.EXPLODE_AT(SIX))
			.build();
	private static final OpenTest<DefaultOpenTest.Result> OPEN_TEST = Tests
			.newOpenTest();
	private static final SuccessTest<DefaultSuccessTest.Result> SUCCESS_TEST = Tests
			.newSuccessTest()
			.criticalMissOn(RULE_OF_ONE, CRITICAL_MISS_ON, _100_PERCENT)
			.build();
	private static final OpposedTest<DefaultOpposedTest.Result> OPPOSED_TEST = Tests
			.newOpposedTest()
			.criticalMissOn(RULE_OF_ONE, CRITICAL_MISS_ON, _100_PERCENT)
			.build();

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
