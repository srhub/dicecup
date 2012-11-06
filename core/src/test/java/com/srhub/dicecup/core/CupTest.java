package com.srhub.dicecup.core;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class CupTest {

	@Test
	public void testDefaultDice() {
		final Cup c = new Cup().add(5);
		final Roll roll = c.roll();
		final List<Integer> result = roll.all();

		assertEquals(5, result.size());
	}

}
