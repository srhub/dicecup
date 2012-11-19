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
 * A critical hit
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class CriticalHitException extends GlitchException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new critical hit exception.
	 *
	 * @param id
	 *            the id
	 * @param absolute
	 *            the absolute
	 * @param percentage
	 *            the percentage
	 */
	public CriticalHitException(final String id, final int absolute,
			final double percentage) {
		super(id, absolute, percentage);
	}

	/**
	 * Instantiates a new critical hit exception.
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
	public CriticalHitException(final String id, final int absolute,
			final double percentage, final Party party) {
		super(id, absolute, percentage, party);
	}

}
