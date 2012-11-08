package com.srhub.test.api;

import com.srhub.task.util.Party;

public class GlitchException extends RuntimeException implements Glitch {

	private static final long serialVersionUID = 1L;

	private final String id;
	private final int absolute;
	private final float percentage;
	private final Party party;

	public GlitchException(final String id, final int absolute,
			final float percentage) {
		this(id, absolute, percentage, Party.ATTACKER);
	}

	public GlitchException(final String id, final int absolute,
			final float percentage, final Party party) {
		super();
		this.id = id;
		this.absolute = absolute;
		this.percentage = percentage;
		this.party = party;
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	public int absolute() {
		return absolute;
	}

	@Override
	public float percentage() {
		return percentage;
	}

	@Override
	public Party party() {
		return party;
	}

}
