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

import com.srhub.task.util.Party;

/**
 * Thrown when all dice show only a <code>1</code>. As two parties can be
 * involved in a competing dice roll, such as an {@link OpposedTest} or a
 * {@link SuccessContest} the party that triggered the rule of one can be
 * specified and retrieved.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 *
 */
public class RuleOfOneException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The trigger. */
	private final Party trigger;

	/**
	 * Default constructor, using {@link Party#ATTACKER} as the triggering
	 * party.
	 */
	public RuleOfOneException() {
		this(Party.ATTACKER);
	}

	/**
	 * Creates a new {@link RuleOfOneException} specifying which party has
	 * caused the rule to be triggered.
	 *
	 * @param trigger
	 *            Party that triggered the execution of the rule
	 */
	public RuleOfOneException(final Party trigger) {
		this.trigger = trigger;
	}

	/**
	 * Returns the party responsible for triggering the rule.
	 *
	 * @return the party responsible for triggering the rule
	 */
	public Party triggeringParty() {
		return trigger;
	}

}
