package com.srhub.test.api;

import java.util.Arrays;
import java.util.List;

public class MultiGlitchException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final List<GlitchException> glitches;

	public MultiGlitchException(final GlitchException... glitches) {
		this(Arrays.asList(glitches));
	}

	public MultiGlitchException(final List<GlitchException> glitches) {
		this.glitches = glitches;
	}

	public List<GlitchException> all() {
		return glitches;
	}

}
