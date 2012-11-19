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
package com.srhub.task.api;

import com.srhub.task.core.Party;

/**
 * An exception that is thrown when a {@link Glitch} occurs.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class GlitchException extends RuntimeException implements Glitch {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private final String id;

	/** The absolute. */
	private final int absolute;

	/** The percentage. */
	private final double percentage;

	/** The party. */
	private final Party party;

	/**
	 * Instantiates a new glitch exception.
	 *
	 * @param id
	 *            the id
	 * @param absolute
	 *            the absolute
	 * @param percentage
	 *            the percentage
	 */
	public GlitchException(final String id, final int absolute,
			final double percentage) {
		this(id, absolute, percentage, Party.ATTACKER);
	}

	/**
	 * Instantiates a new glitch exception.
	 *
	 * @param id
	 *            the id
	 * @param absolute
	 *            the absolute
	 * @param percentage
	 *            the percentage
	 * @param party
	 *            the party
	 */
	public GlitchException(final String id, final int absolute,
			final double percentage, final Party party) {
		super();
		this.id = id;
		this.absolute = absolute;
		this.percentage = percentage;
		this.party = party;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.test.api.Glitch#id()
	 */
	@Override
	public String id() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.test.api.Glitch#absolute()
	 */
	@Override
	public int absolute() {
		return absolute;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.test.api.Glitch#percentage()
	 */
	@Override
	public double percentage() {
		return percentage;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.srhub.test.api.Glitch#party()
	 */
	@Override
	public Party party() {
		return party;
	}

}
