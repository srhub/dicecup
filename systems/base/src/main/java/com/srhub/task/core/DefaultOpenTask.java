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
package com.srhub.task.core;

import com.srhub.dicecup.core.Cup;
import com.srhub.dicecup.core.Roll;
import com.srhub.task.api.OpenTask;
import com.srhub.task.api.OpenTest;

// TODO: Auto-generated Javadoc
/**
 * The Class DefaultOpenTask.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class DefaultOpenTask implements OpenTask<DefaultOpenTest.Result> {

	/** The empty array. */
	public static int[] EMPTY_ARRAY = new int[] {};

	/** The cup. */
	Cup cup;

	/** The open test. */
	OpenTest<DefaultOpenTest.Result> openTest;

	/**
	 * Instantiates a new open task.
	 *
	 * @param cup
	 *            the cup
	 * @param openTest
	 *            the open test
	 */
	public DefaultOpenTask(final Cup cup,
			final OpenTest<DefaultOpenTest.Result> openTest) {
		super();
		this.cup = cup;
		this.openTest = openTest;
	}

	@Override
	public DefaultOpenTest.Result evaluate(final int c, final int... count) {
		final Roll roll = cup.roll(c, count);
		return openTest.evaluate(roll);
	}

}
