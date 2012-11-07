package com.srhub.dicecup.core;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class CupTest {

	@Test
	public void testWithFaces() {
		final Cup c = Cup.add(6).build();
		final Roll roll = c.roll(1);
		final List<Integer> result = roll.all();

		assertEquals(1, result.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithLesserCount() {
		final Cup c = Cup.add(6).add(4).build();
		c.roll(2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithGreaterCount() {
		final Cup c = Cup.add(6).build();
		c.roll(2, 2);
	}
}
