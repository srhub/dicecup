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

import java.util.Arrays;
import java.util.List;

/**
 * A {@link MultiGlitchException} is thrown when a test such as an
 * {@link OpposedTest} involves multiple glitches (for multiple parties)
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class MultiGlitchException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The glitches. */
	private final List<GlitchException> glitches;

	/**
	 * Instantiates a new multi glitch exception.
	 *
	 * @param glitches
	 *            the glitches
	 */
	public MultiGlitchException(final GlitchException... glitches) {
		this(Arrays.asList(glitches));
	}

	/**
	 * Instantiates a new multi glitch exception.
	 *
	 * @param glitches
	 *            the glitches
	 */
	public MultiGlitchException(final List<GlitchException> glitches) {
		this.glitches = glitches;
	}

	/**
	 * All.
	 *
	 * @return the list of glitches
	 */
	public List<GlitchException> all() {
		return glitches;
	}

}
