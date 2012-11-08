package com.srhub.dicecup.dice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.collect.Lists;

public class CyclicDiceTest {

	@Test
	public void test() {

		final CyclicDice dice = CyclicDice.add(1, 2, 4, 5, 5).build(6);
		assertEquals(Lists.newArrayList(1, 2, 4, 5, 5), dice.roll(5));

	}

}
