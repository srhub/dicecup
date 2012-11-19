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
package com.srhub.dicecup.systems.sr4;

import com.srhub.task.core.Party;

/**
 * The Class Glitch.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class GlitchException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The party. */
	private final Party party;

	/** The critical. */
	private final boolean critical;

	/**
	 * Instantiates a new glitch exception.
	 */
	public GlitchException() {
		this(false);
	}

	/**
	 * Instantiates a new glitch exception.
	 *
	 * @param critical
	 *            the critical
	 */
	public GlitchException(final boolean critical) {
		this(Party.ATTACKER, critical);
	}

	/**
	 * Instantiates a new glitch exception.
	 *
	 * @param party
	 *            the party
	 * @param critical
	 *            the critical
	 */
	public GlitchException(final Party party, final boolean critical) {
		this.party = Party.ATTACKER;
		this.critical = critical;
	}

	/**
	 * Checks if is critical.
	 *
	 * @return true, if is critical
	 */
	public boolean isCritical() {
		return critical;
	}

	/**
	 * Gets the party.
	 *
	 * @return the party
	 */
	public Party getParty() {
		return party;
	}

}
