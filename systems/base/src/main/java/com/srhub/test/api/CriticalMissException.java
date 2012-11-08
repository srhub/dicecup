package com.srhub.test.api;

import com.srhub.task.util.Party;

public class CriticalMissException extends GlitchException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public CriticalMissException(final String id, final int absolute,
			final float percentage) {
		super(id, absolute, percentage);
	}

	public CriticalMissException(final String id, final int absolute,
			final float percentage, final Party party) {
		super(id, absolute, percentage, party);
	}

}
