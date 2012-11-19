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
package com.srhub.test.api;

import com.srhub.dicecup.core.Roll;

/**
 * A success test determines if a character can accomplish a task and how well
 * he does it. The number of dice represents the attribute or skill rating and
 * the target number the difficulty of the task.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 *
 */
public interface SuccessTest<T> {

	/**
	 * Returns the number of successes.
	 *
	 * @param roll
	 *            the roll
	 * @param target
	 *            the target number
	 * @return the number of successes
	 */
	public T evaluate(Roll roll, int target);

}
